package src;

import java.io.*;
import java.util.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private final String ACCOUNTS_FILE = "data/accounts.txt";
    private final String TRANSACTIONS_FILE = "data/transactions.txt";

    // Constructor: load accounts from file on startup
    public Bank() {
        loadAccountsFromFile();
    }

    // Create a new account
    public void createAccount(String accountNumber, String holderName, double initialDeposit) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
            return;
        }

        Account acc = new Account(accountNumber, holderName, initialDeposit);
        accounts.put(accountNumber, acc);
        saveAccountsToFile();

        new Transaction("Account Created", accountNumber, initialDeposit).logToFile(TRANSACTIONS_FILE);
        System.out.println("Account created successfully.");
    }

    // Deposit money
    public void deposit(String accountNumber, double amount) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
            saveAccountsToFile();
            new Transaction("Deposit", accountNumber, amount).logToFile(TRANSACTIONS_FILE);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw money
    public void withdraw(String accountNumber, double amount) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            if (acc.withdraw(amount)) {
                saveAccountsToFile();
                new Transaction("Withdraw", accountNumber, amount).logToFile(TRANSACTIONS_FILE);
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Check balance
    public void checkBalance(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            System.out.println(acc.toString());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Show all accounts
    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (Account acc : accounts.values()) {
                System.out.println(acc);
            }
        }
    }

    // Load accounts from file
    private void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account acc = Account.fromFileFormat(line);
                if (acc != null) {
                    accounts.put(acc.getAccountNumber(), acc);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous account data found.");
        }
    }

    // Save all accounts to file
    private void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account acc : accounts.values()) {
                writer.write(acc.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save accounts: " + e.getMessage());
        }
    }

    public void showTransactionHistory(String accountNumber) {
    System.out.println("\n📜 Transaction History for Account: " + accountNumber);
    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(accountNumber)) {
                System.out.println(line);
                found = true;
            }
        }
    } catch (IOException e) {
        System.out.println("Unable to read transaction history.");
    }

    if (!found) {
        System.out.println("No transactions found for this account.");
    }
}

}
