import java.util.Scanner;

public class InputValidator {

    // Get integer safely

    public static int getInt(Scanner scanner) {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Invalid input. Please enter a number: "
                );
            }
        }
    }

    // Get positive integer

    public static int getPositiveInt(Scanner scanner) {

        while (true) {

            int value = getInt(scanner);

            if (value > 0) {
                return value;
            }

            System.out.print(
                    "Please enter a number greater than zero: "
            );
        }
    }

    // Get non-negative integer

    public static int getNonNegativeInt(Scanner scanner) {

        while (true) {

            int value = getInt(scanner);

            if (value >= 0) {
                return value;
            }

            System.out.print(
                    "Please enter zero or a positive number: "
            );
        }
    }

    // Get double safely

    public static double getDouble(Scanner scanner) {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Invalid amount. Please enter a valid number: "
                );
            }
        }
    }

    // Get positive double

    public static double getPositiveDouble(Scanner scanner) {

        while (true) {

            double value = getDouble(scanner);

            if (value > 0) {
                return value;
            }

            System.out.print(
                    "Please enter an amount greater than zero: "
            );
        }
    }

    // Get non-negative double

    public static double getNonNegativeDouble(Scanner scanner) {

        while (true) {

            double value = getDouble(scanner);

            if (value >= 0) {
                return value;
            }

            System.out.print(
                    "Please enter zero or a positive amount: "
            );
        }
    }
}