package bank_management.UI;

import java.util.Random;

    public class Util {
        public static final int VERTICAL_MARGIN = 10;
        public static int generate8DigitRandomNumber() {
            Random random = new Random();
            int min = 10000000;  // Minimum 8-digit number
            int max = 99999999;  // Maximum 8-digit number
            return random.nextInt(max - min + 1) + min;
        }
}
