package pfa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.util.List;

public class FileHandler {

    private static final String tran_file = "transactions.csv";

    public static void save(FinanceManager manager) throws IOException {
        saveTransactions(manager.getTransactions());
    }

    public static void load(FinanceManager manager) throws IOException {
        loadTransactions(manager);
    }

    public static void loadTransactions(FinanceManager manager) throws IOException {
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

    public static void saveTransactions(List<Transaction> transactions) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(tran_file));
        for (Transaction t : transactions) {
            writer.write(t.toString());
            writer.newLine();
        }
        writer.close();
    }
}
