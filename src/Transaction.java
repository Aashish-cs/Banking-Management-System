package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String dateTime;
    private String type;       // e.g., Deposit, Withdraw, Transfer
    private String accountInfo;
    private double amount;

    // Constructor
    public Transaction(String type, String accountInfo, double amount) {
        this.dateTime = getCurrentTimestamp();
        this.type = type;
        this.accountInfo = accountInfo;
        this.amount = amount;
    }

    // Get current timestamp
    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    // Format for file: 2025-06-03 20:15:22,Deposit,12345,$500.0
    public String toFileFormat() {
        return dateTime + "," + type + "," + accountInfo + ",$" + String.format("%.2f", amount);
    }

    // Save this transaction to file
    public void logToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(toFileFormat());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }
}
