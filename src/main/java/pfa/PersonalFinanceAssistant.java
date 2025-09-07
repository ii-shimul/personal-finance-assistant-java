package pfa;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonalFinanceAssistant {

  Scanner scanner = new Scanner(System.in);
  FinanceManager manager = new FinanceManager();
  User user;

  // entry point of the program
  void start() throws IOException {
    System.out.println("Welcome to the Personal Finance Assistant");
    System.out.println("Please enter your name to continue: ");
    String userName = scanner.nextLine();
    user = new User(userName);
    FileHandler.load(manager, user);
    clear();
    menuHandler();
  }

  // method for clearing the console
  void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  void menu() {
    System.out.println("+-----------------------------+");
    System.out.println("| Hello, welcome to PFA!");
    System.out.println("+-----------------------------+");
    System.out.println("| 1. Add Expense");
    System.out.println("| 2. Add Income");
    System.out.println("| 3. Set / Update Goal");
    System.out.println("| 4. View Summary");
    System.out.println("| 6. Clear All Data");
    System.out.println("| 7. Exit");
    System.out.println("+-----------------------------+");
    System.out.print("Choose an option: ");
  }

  public void menuHandler() throws IOException {
    menu();
    boolean isRunning = true;
    while (isRunning) {
      try {
        int choice = scanner.nextInt();
        switch (choice) {
          case 1:
            clear();
            manager.addTransaction(choice);
            System.out.println("Added new expense to the database.");
            menu();
            break;
          case 2:
            clear();
            manager.addTransaction(choice);
            System.out.println("Added new income to the database.");
            menu();
            break;
          case 3:
            System.out.print("How much do you want to save?");
            int amount = scanner.nextInt();
            System.out.println(amount);
            break;
          case 4:
            manager.printSummary();
            break;
          case 6:
            manager.clearAllTransactions();
            break;
          case 7:
            FileHandler.save(manager);
            isRunning = false;
            break;
          default:
            System.out.println("Invalid choice, please choose between 1-7.");
            break;
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