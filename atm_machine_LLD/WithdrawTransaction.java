package atm_machine_LLD;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(String transactionId, String cardNumber, double amount) {
        super(transactionId, cardNumber, amount);
    }

    @Override
    public boolean execute(BankConnector bankConnector) {
        double currentBalance = bankConnector.getBalance(cardNumber);

        if (currentBalance >= amount) {
            boolean success = bankConnector.withdraw(cardNumber, amount);
            if (success) {
                setStatus("COMPLETED");
                System.out.println("Withdrawal successful. Amount: $" + amount);
                return true;
            } else {
                setStatus("FAILED");
                System.out.println("Withdrawal failed due to system error.");
                return false;
            }
        } else {
            setStatus("FAILED");
            System.out.println("Insufficient funds. Available balance: $" + currentBalance);
            return false;
        }
    }

    @Override
    public String getTransactionType() {
        return "WITHDRAW";
    }
}