package atm_machine_LLD;

public class BalanceEnquiryTransaction extends Transaction {

    public BalanceEnquiryTransaction(String transactionId, String cardNumber) {
        super(transactionId, cardNumber, 0.0);
    }

    @Override
    public boolean execute(BankConnector bankConnector) {
        try {
            double balance = bankConnector.getBalance(cardNumber);
            setStatus("COMPLETED");
            System.out.println("Balance Enquiry successful. Current balance: $" + balance);
            return true;
        } catch (Exception e) {
            setStatus("FAILED");
            System.out.println("Balance enquiry failed due to system error.");
            return false;
        }
    }

    @Override
    public String getTransactionType() {
        return "BALANCE_ENQUIRY";
    }
}