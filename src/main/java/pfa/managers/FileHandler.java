package pfa.managers;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import pfa.transaction.Expense;
import pfa.transaction.Income;
import pfa.transaction.Transaction;
import pfa.user.User;

public class FileHandler {

    private static String tran_file;

    // method for saving all transactions before exiting
    public static void save(FinanceManager manager) throws IOException {
        if (manager.getTransactions().isEmpty()) {
            return;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(tran_file));

        // write goal first
        writer.write(manager.goal.toString());
        writer.newLine();

        // write the transactions
        for (Transaction t : manager.getTransactions()) {
            writer.write(t.toString());
            writer.newLine();
        }
        writer.close();
    }

    // loading previous transactions at the start
    public static void load(FinanceManager manager, User user) throws IOException {
        try {
            tran_file = "src/main/java/pfa/data/" + user.getName() + ".csv";
            File prevData = new File(tran_file);
            if (!prevData.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(prevData));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split("\\|");
                switch (splitLine[0]) {
                    case "INCOME" ->
                        manager.transactions.add(new Income(parseInt(splitLine[1]), splitLine[3], LocalDate.parse(splitLine[2])));
                    case "EXPENSE" ->
                        manager.transactions.add(new Expense(parseInt(splitLine[1]), splitLine[3], LocalDate.parse(splitLine[2])));
                    case "GOAL" ->
                        manager.goal.setTargetAmount(parseInt(splitLine[1]));
                    default -> {
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void deleteFile() {
        File file = new File(tran_file);
        file.delete();
    }
}
