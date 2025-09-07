package pfa;

public class Goal {
  private int targetAmount = 0;

  public int getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(int targetAmount) {
    this.targetAmount = targetAmount;
  }

  public String toString() {
    return "GOAL|" + targetAmount;
  }
}
