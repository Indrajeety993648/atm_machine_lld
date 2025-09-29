package atm_machine_LLD;

public class ChangePinTransaction extends Transaction {
    private String oldPin;
    private String newPin;

    public ChangePinTransaction(String transactionId, String cardNumber, String oldPin, String newPin) {
        super(transactionId, cardNumber, 0.0);
        this.oldPin = oldPin;
        this.newPin = newPin;
    }

    @Override
    public boolean execute(BankConnector bankConnector) {
        try {
            boolean success = bankConnector.changePin(cardNumber, oldPin, newPin);
            if (success) {
                setStatus("COMPLETED");
                System.out.println("PIN changed successfully.");
                return true;
            } else {
                setStatus("FAILED");
                System.out.println("PIN change failed. Invalid old PIN.");
                return false;
            }
        } catch (Exception e) {
            setStatus("FAILED");
            System.out.println("PIN change failed due to system error.");
            return false;
        }
    }

    @Override
    public String getTransactionType() {
        return "CHANGE_PIN";
    }

    public String getOldPin() {
        return oldPin;
    }

    public String getNewPin() {
        return newPin;
    }
}