package pfa;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import pfa.managers.FileHandler;
import pfa.managers.FinanceManager;
import pfa.user.User;

public class PersonalFinanceAssistant {

    Scanner scanner = new Scanner(System.in);
    FinanceManager manager = new FinanceManager();
    public User user;

    // entry point of the program
    void start() throws IOException {
        clear();
        System.out.println("Welcome to the Personal Finance Assistant");
        System.out.print("Please enter your name: ");
        String userName = scanner.nextLine();
        user = new User(userName);
        FileHandler.load(manager, user);
        clear();
        menuHandler();
    }

    // method to wait for Enter key press
    void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    // method for clearing the console
    void clear() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // for windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // for unix
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // if the above fails
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    void menu() {
        System.out.println("+------------------------+");
        System.out.println("| Hello, welcome to PFA! |");
        System.out.println("+------------------------+");
        System.out.println("| 1. Add Income          |");
        System.out.println("| 2. Add Expense         |");
        System.out.println("| 3. View Summary        |");
        System.out.println("| 4. Set / Update Goal   |");
        System.out.println("| 5. Filter by date      |");
        System.out.println("| 6. Clear All Data      |");
        System.out.println("| 7. Exit                |");
        System.out.println("+------------------------+");
        System.out.print("Choose an option: ");
    }

    public void menuHandler() throws IOException {
        menu();
        boolean isRunning = true;
        while (isRunning) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        clear();
                        manager.addTransaction(choice);
                        System.out.println("Added new income to the database.");
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 2 -> {
                        clear();
                        manager.addTransaction(choice);
                        System.out.println("Added new expense to the database.");
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 3 -> {
                        clear();
                        manager.printSummary();
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 4 -> {
                        clear();
                        System.out.print("How much do you want to save? ");
                        int amount = scanner.nextInt();
                        manager.goal.setTargetAmount(amount);
                        System.out.println("Goal set!");
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 5 -> {
                        clear();
                        manager.getTransactionsDay();
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 6 -> {
                        manager.clearAllTransactions();
                        FileHandler.deleteFile();
                        waitForEnter();
                        clear();
                        menu();
                    }
                    case 7 -> {
                        FileHandler.save(manager);
                        System.out.println("Tata");
                        isRunning = false;
                    }
                    default ->
                        System.out.println("Invalid choice, please choose between 1-7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid choice.");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PersonalFinanceAssistant pfa = new PersonalFinanceAssistant();
        pfa.start();
    }
}
