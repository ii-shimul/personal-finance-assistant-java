package pfa;

import java.util.ArrayList;
import java.util.List;

public class FinanceManager {

  List<Transaction> transactions = new ArrayList<>();

  public void addIncome(int amount, String description) {
    transactions.add(new Income(amount, description));
  }

  public void addExpense(int amount, String description) {
    transactions.add(new Expense(amount, description));
  }

  public int totalIncome() {
    int sum = 0;
    for (Transaction t : transactions) {
      if (t.getType() == "INCOME") {
        sum += t.getAmount();
      }
    }
    return sum;
  }

  public int totalExpense() {
    int sum = 0;
    for (Transaction t : transactions) {
      if (t.getType() == "EXPENSE") {
        sum += t.getAmount();
      }
    }
    return sum;
  }

  public void printSummary() {
    System.out.println("Total Income: " + totalIncome());
    System.out.println("Total Expense: " + totalExpense());
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }
}