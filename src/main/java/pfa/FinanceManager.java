package pfa;

import java.util.*;

public class FinanceManager {

  Scanner scanner = new Scanner(System.in);

  List<Transaction> transactions = new ArrayList<>();

  // adding an income or expense
  public void addTransaction(int choice) {
    try {
      System.out.print("Amount: ");
      int amount = scanner.nextInt();
      if (amount < 0) {
        throw new InvalidAmountException("Amount should be a positive integer.");
      }
      System.out.print("Description: ");
      scanner.nextLine();
      String description = scanner.nextLine();
      if (choice == 1) {
        transactions.add(new Expense(amount, description));
      } else if (choice == 2) {
        transactions.add(new Income(amount, description));
      }
    } catch (InvalidAmountException e) {
      System.out.println(e.getMessage());
    }
  }

  // calculating total income
  public int totalIncome() {
    int sum = 0;
    for (Transaction t : transactions) {
      if (t.getType() == "INCOME") {
        sum += t.getAmount();
      }
    }
    return sum;
  }

  // calculating total expense
  public int totalExpense() {
    int sum = 0;
    for (Transaction t : transactions) {
      if (t.getType() == "EXPENSE") {
        sum += t.getAmount();
      }
    }
    return sum;
  }

  // print the overall summary
  public void printSummary() {
    System.out.println("Total Income: " + totalIncome());
    System.out.println("Total Expense: " + totalExpense());
  }

  // clearing all income and expenses
  public void clearAllTransactions() {
    transactions.clear();
    System.out.println("Transactions cleared successfully");
  }

  // returning all transactions
  public List<Transaction> getTransactions() {
    return transactions;
  }
}