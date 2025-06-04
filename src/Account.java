package src;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw money (returns true if successful)
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Show in console
    public String toString() {
        return "Account Number: " + accountNumber +
               " | Holder: " + accountHolder +
               " | Balance: $" + String.format("%.2f", balance);
    }

    // For saving to file: 12345,Ashish Mishra,2500.00
    public String toFileFormat() {
        return accountNumber + "," + accountHolder + "," + balance;
    }

    // For loading from file
    public static Account fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) return null;

        String accNum = parts[0];
        String accHolder = parts[1];
        double accBalance = Double.parseDouble(parts[2]);

        return new Account(accNum, accHolder, accBalance);
    }
}
