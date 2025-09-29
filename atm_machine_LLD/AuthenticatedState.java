package atm_machine_LLD;

public class AuthenticatedState implements ATMState {

    @Override
    public void onCardInserted(ATM atm) {
        System.out.println("Card is already inserted and authenticated.");
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        System.out.println("PIN already validated. Please select a transaction.");
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        System.out.println("Transaction selected: " + txType);
        atm.setCurrentTransactionType(txType);

        switch (txType.toLowerCase()) {
            case "withdraw":
                System.out.println("Please enter the amount to withdraw:");
                break;
            case "balance":
                double balance = atm.getBankConnector().getBalance(atm.getCurrentCard().getCardNumber());
                System.out.println("Your current balance is: $" + balance);
                atm.setState(new PrintingState());
                break;
            case "changepin":
                System.out.println("PIN change selected. Please enter new PIN:");
                break;
            case "ministatement":
                String statement = atm.getBankConnector().getMiniStatement(atm.getCurrentCard().getCardNumber());
                System.out.println(statement);
                atm.setState(new PrintingState());
                break;
            default:
                System.out.println("Invalid transaction type.");
        }
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        if ("withdraw".equals(atm.getCurrentTransactionType())) {
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive amount.");
                return;
            }

            String cardNumber = atm.getCurrentCard().getCardNumber();
            double balance = atm.getBankConnector().getBalance(cardNumber);

            if (amount > balance) {
                System.out.println("Insufficient funds. Your balance is: $" + balance);
                return;
            }

            if (atm.getBankConnector().withdraw(cardNumber, amount)) {
                atm.setCurrentAmount(amount);
                DispensingState dispensingState = new DispensingState();
                atm.setState(dispensingState);
                // Immediately start dispensing
                dispensingState.dispenseCash(atm);
            } else {
                System.out.println("Transaction failed. Please try again.");
            }
        } else {
            System.out.println("Please select withdraw transaction first.");
        }
    }

    @Override
    public void onCancel(ATM atm) {
        System.out.println("Transaction cancelled. Ejecting card.");
        atm.ejectCard();
    }
}