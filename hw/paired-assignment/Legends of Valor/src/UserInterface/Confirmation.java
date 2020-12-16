package UserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * includes a general function to check yes or no with users
 */
public interface Confirmation {

    default boolean makeConfirmation(String action){
        System.out.println("\033[0;31m" + "Do you want to " + action +"?(Y/N)");
        Scanner scanner = new Scanner(System.in);
        String s;

        while (true){
            try {
                s = scanner.nextLine();
                if (s.equalsIgnoreCase("Y")
                        || s.equalsIgnoreCase("N")) {
                    return s.equalsIgnoreCase("Y");
                } else {
                    System.out.println("\033[0;31m" + "Invalid input. Please enter Y/N:");
                }
            } catch (InputMismatchException e){
                System.out.println("\033[0;31m" + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
    }
}
