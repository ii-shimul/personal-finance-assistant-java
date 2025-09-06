package pfa;

import java.util.Scanner;

public class PersonalFinanceAssistant {
  Scanner scanner = new Scanner(System.in);
  User user; 
  void start(){
    boolean isLoggedIn = false;
    if (isLoggedIn) {
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

  void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void mainMenu() {
    System.out.println("+-----------------------------+");
    System.out.println("| Hello, " + user.getName());
    System.out.println("+-----------------------------+");
    System.out.println("| 1. Add Expense");
    System.out.println("| 2. Add Income");
    System.out.println("| 3. Set / Update Goal");
    System.out.println("| 4. View Summary");
    System.out.println("| 5. Save Data");
    System.out.println("| 6. Exit");
    System.out.println("+-----------------------------+");
    System.out.print("Choose an option: ");
    
    boolean isRunning = true;
    while (isRunning) {
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          clear();
          System.out.println("WOW!!!");
          break;
        case 2:
          // addExpense();
          // mainMenu();
          break;
        case 3:
          // addTransaction();
          break;
        case 4:
          
          // mainMenu();
          break;
        case 5:
          // getExpenses();
          break;
        case 6:
          // getTransactions();
          // mainMenu();
          break;
        case 7:
          isRunning = false;
          break;
        default:
          System.out.println("Invalid choice");
          break;
      }
    }
  }

  public static void main(String[] args) {
    PersonalFinanceAssistant pfa = new PersonalFinanceAssistant();
    pfa.start();
  }
}
