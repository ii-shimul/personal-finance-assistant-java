package pfa;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;

public class FileHandler {

  private static String tran_file;

  public static void save(FinanceManager manager) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(tran_file));
    for (Transaction t : manager.getTransactions()) {
      writer.write(t.toString());
      writer.newLine();
    }
    writer.close();
  }

  public static void load(FinanceManager manager, User user) throws IOException {
    tran_file = "data/" + user.getName() + ".csv";
    File data = new File(tran_file);
    if (!data.exists()) {
      return;
    }

    BufferedReader reader = new BufferedReader(new FileReader(data));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] splitLine = line.split("\\|");
      if (splitLine[0].equals("INCOME")) {
        System.out.println("inside income");
        manager.transactions.add(new Income(parseInt(splitLine[1]), splitLine[3], LocalDate.parse(splitLine[2])));
      } else if (splitLine[0].equals("EXPENSE")) {
        manager.transactions.add(new Expense(parseInt(splitLine[1]), splitLine[3], LocalDate.parse(splitLine[2])));
      }
    }
    reader.close();
  }
}