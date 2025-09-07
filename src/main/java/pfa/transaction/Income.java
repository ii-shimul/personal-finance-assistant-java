package pfa.transaction;

import java.time.LocalDate;

public class Income extends Transaction {
  public Income(int amount, String description) {
    super(amount, description);
  }

  public Income(int amount, String description, LocalDate date) {
    super(amount, description, date);
  }

  @Override
  public String getType() {
    return "INCOME";
  }
}