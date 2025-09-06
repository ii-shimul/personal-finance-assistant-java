package pfa;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonalFinanceAssistant {

  Scanner scanner = new Scanner(System.in);
  FinanceManager manager = new FinanceManager();
  User user;

  void start() throws IOException {
    boolean isLoggedIn = true;
    if (isLoggedIn) {
      FileHandler.load(manager);
      mainMenu();
    } else {
      System.out.println("Welcome to the Personal Finance Assistant");
      System.out.println("Please enter your name to continue: ");
      String userName = scanner.nextLine();
      user = new User(userName);
      clear();
      mainMenu();
    }
  }

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
    System.out.println("| 6. Clear All Transactions");
    System.out.println("| 7. Exit");
    System.out.println("+-----------------------------+");
    System.out.print("Choose an option: ");
  }

  public void mainMenu() throws IOException {
    menu();
    boolean isRunning = true;
    while (isRunning) {
      try {
        int choice = scanner.nextInt();
        switch (choice) {
          case 1:
            clear();
            System.out.println("Amount:");
            int amount = scanner.nextInt();
            System.out.println("Description:");
            String description = scanner.next();
            manager.addExpense(amount, description);
            clear();
            menu();
            break;
          case 2:
            clear();
            System.out.println("Amount:");
            amount = scanner.nextInt();
            if (amount < 0) {
              throw new InvalidAmountException("Amount should be a positive integer.");
            }
            System.out.println("Description:");
            description = scanner.next();
            manager.addIncome(amount, description);
            clear();
            menu();
            break;
          case 3:
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
            System.out.println("Invalid choice");
            break;
        }
      } catch (InvalidAmountException e) {
        System.out.println(e.getMessage());
      } catch (InputMismatchException e) {
        System.out.println("Invalid input.");
        scanner.nextLine();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    PersonalFinanceAssistant pfa = new PersonalFinanceAssistant();
    pfa.start();
  }
}