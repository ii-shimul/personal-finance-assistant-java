package pfa;

import java.time.LocalDate;

public class Expense extends Transaction {
  public Expense(int amount, String description) {
    super(amount, description);
  }

  public Expense(int amount, String description, LocalDate date) {
    super(amount, description, date);
  }

  @Override
  public String getType() {
    return "EXPENSE";
  }
}