package bank_management.UI.admin;

import Models.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank_management.UI.Util.VERTICAL_MARGIN;

public class AccountsPanel extends JPanel {
    private Account user;
    public AccountsPanel(Account user){
        this.user = user;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        JLabel title = new JLabel("Accounts");
        title.setFont(titleFont);
        title.setForeground(Color.darkGray);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        add(title);
        add(Box.createVerticalStrut(VERTICAL_MARGIN));
        addTable();
        setPreferredSize(new Dimension(400,300));

    }
    private void addTable() {
        String[] columnNames = {"UserNane", "Account_No", "Balance"};
        Object[][] data = {
                {"Alice", "$100", "Verify"},
                {"Bob", "$200", "Verify"},
                {"Carol", "$300", "Verify"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(),table));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private final JTable table;
        public ButtonEditor(JCheckBox checkBox,JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                int row = table.getSelectedRow();
                String name = table.getModel().getValueAt(row, 0).toString();
                String amount = table.getModel().getValueAt(row, 1).toString();
                JOptionPane.showMessageDialog(button, "Name: " + name + ", Amount: " + amount);
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}

