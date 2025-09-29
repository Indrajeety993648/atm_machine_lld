package atm_machine_LLD;

public class Receipt {
    private String transactionId;
    private String transactionType;
    private double amount;
    private double balance;
    private String dateTime;
    private String cardNumber;

    public Receipt(String transactionId, String transactionType, double amount,
            double balance, String dateTime, String cardNumber) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.dateTime = dateTime;
        this.cardNumber = cardNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "=== ATM RECEIPT ===\n" +
                "Transaction ID: " + transactionId + "\n" +
                "Type: " + transactionType + "\n" +
                "Amount: $" + amount + "\n" +
                "Balance: $" + balance + "\n" +
                "Date/Time: " + dateTime + "\n" +
                "Card: ****" + cardNumber.substring(cardNumber.length() - 4) + "\n" +
                "==================";
    }
}