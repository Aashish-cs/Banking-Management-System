package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String option = "";

        System.out.println("=====================================");
        System.out.println("      Welcome to Ashish Bank 🏦");
        System.out.println("=====================================");

        try {
            while (true) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Show All Accounts");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                option = reader.readLine();

                switch (option) {
                    case "1":
                        System.out.print("Enter Account Number: ");
                        String accNum = reader.readLine();

                        System.out.print("Enter Account Holder Name: ");
                        String accHolder = reader.readLine();

                        System.out.print("Enter Initial Deposit: ");
                        double initDeposit = Double.parseDouble(reader.readLine());

                        bank.createAccount(accNum, accHolder, initDeposit);
                        break;

                    case "2":
                        System.out.print("Enter Account Number: ");
                        String depAcc = reader.readLine();

                        System.out.print("Enter Amount to Deposit: ");
                        double depAmount = Double.parseDouble(reader.readLine());

                        bank.deposit(depAcc, depAmount);
                        break;

                    case "3":
                        System.out.print("Enter Account Number: ");
                        String withAcc = reader.readLine();

                        System.out.print("Enter Amount to Withdraw: ");
                        double withAmount = Double.parseDouble(reader.readLine());

                        bank.withdraw(withAcc, withAmount);
                        break;

                    case "4":
                        System.out.print("Enter Account Number: ");
                        String checkAcc = reader.readLine();

                        bank.checkBalance(checkAcc);
                        break;

                    case "5":
                        bank.showAllAccounts();
                        break;

                    case "6":
                        System.out.println("Thank you for using Ashish Bank. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
