package atm_machine_LLD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MiniStatement {
    private String cardNumber;
    private List<String> transactions;
    private String generatedDate;
    private double currentBalance;

    public MiniStatement(String cardNumber, List<String> transactions, double currentBalance) {
        this.cardNumber = cardNumber;
        this.transactions = transactions;
        this.currentBalance = currentBalance;
        this.generatedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public String toString() {
        StringBuilder statement = new StringBuilder();
        statement.append("=== MINI STATEMENT ===\n");
        statement.append("Card: ****").append(cardNumber.substring(cardNumber.length() - 4)).append("\n");
        statement.append("Generated: ").append(generatedDate).append("\n");
        statement.append("Current Balance: $").append(currentBalance).append("\n");
        statement.append("======================\n");
        statement.append("Recent Transactions:\n");

        if (transactions.isEmpty()) {
            statement.append("No recent transactions\n");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                statement.append((i + 1)).append(". ").append(transactions.get(i)).append("\n");
            }
        }

        statement.append("======================\n");
        statement.append("Thank you for banking with us!\n");

        return statement.toString();
    }
}