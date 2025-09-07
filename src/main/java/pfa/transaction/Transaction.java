package pfa.transaction;

import java.time.LocalDate;

public abstract class Transaction {
  private final int amount;
  private final String description;
  private final LocalDate date;

  public Transaction(int amount, String description) {
    this.amount = amount;
    this.description = description;
    this.date = LocalDate.now();
  }

  public Transaction(int amount, String description, LocalDate date) {
    this.amount = amount;
    this.description = description;
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public String getDesc() {
    return description;
  }

  public LocalDate getDate() {
    return date;
  }

  public abstract String getType();

  public String toString() {
    return getType() + "|" + amount + "|" + date + "|" + description;
  }

}