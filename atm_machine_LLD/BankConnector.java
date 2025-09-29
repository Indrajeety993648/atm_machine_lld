package atm_machine_LLD;

import java.util.HashMap;
import java.util.Map;

public class BankConnector {
    private Map<String, Account> accounts;

    public BankConnector() {
        this.accounts = new HashMap<>();
        initializeAccounts();
    }

    private void initializeAccounts() {
        // Mock accounts for demonstration
        accounts.put("1234567890123456", new Account("1234567890123456", "1234", 5000.0));
        accounts.put("9876543210987654", new Account("9876543210987654", "5678", 2500.0));
    }

    public boolean validatePin(String cardNumber, String pin) {
        Account account = accounts.get(cardNumber);
        return account != null && account.getPin().equals(pin);
    }

    public double getBalance(String cardNumber) {
        Account account = accounts.get(cardNumber);
        return account != null ? account.getBalance() : 0.0;
    }

    public boolean withdraw(String cardNumber, double amount) {
        Account account = accounts.get(cardNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean changePin(String cardNumber, String oldPin, String newPin) {
        Account account = accounts.get(cardNumber);
        if (account != null && account.getPin().equals(oldPin)) {
            account.setPin(newPin);
            return true;
        }
        return false;
    }

    public String getMiniStatement(String cardNumber) {
        // Mock mini statement
        return "Last 5 transactions:\n" +
                "1. Withdrawal: $100 - Balance: $4900\n" +
                "2. Deposit: $200 - Balance: $5000\n" +
                "3. Withdrawal: $50 - Balance: $4800\n" +
                "4. Balance Inquiry - Balance: $4850\n" +
                "5. Withdrawal: $75 - Balance: $4850";
    }

    public MiniStatement getMiniStatementObject(String cardNumber) {
        java.util.List<String> transactions = java.util.Arrays.asList(
                "Withdrawal: $100 - Balance: $4900",
                "Deposit: $200 - Balance: $5000",
                "Withdrawal: $50 - Balance: $4800",
                "Balance Inquiry - Balance: $4850",
                "Withdrawal: $75 - Balance: $4850");

        double currentBalance = getBalance(cardNumber);
        return new MiniStatement(cardNumber, transactions, currentBalance);
    }

    private static class Account {
        private String cardNumber;
        private String pin;
        private double balance;

        public Account(String cardNumber, String pin, double balance) {
            this.cardNumber = cardNumber;
            this.pin = pin;
            this.balance = balance;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}