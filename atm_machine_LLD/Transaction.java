package atm_machine_LLD;

public abstract class Transaction {
    protected String transactionId;
    protected String cardNumber;
    protected double amount;
    protected String status;

    public Transaction(String transactionId, String cardNumber, double amount) {
        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.status = "PENDING";
    }

    public abstract boolean execute(BankConnector bankConnector);

    public abstract String getTransactionType();

    // Getters and setters
    public String getTransactionId() {
        return transactionId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}