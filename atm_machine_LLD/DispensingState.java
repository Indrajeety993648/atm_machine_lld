package atm_machine_LLD;

public class DispensingState implements ATMState {

    @Override
    public void onCardInserted(ATM atm) {
        System.out.println("Transaction in progress. Please wait.");
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        System.out.println("Transaction in progress. Please wait.");
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        System.out.println("Transaction in progress. Please wait.");
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        System.out.println("Already dispensing cash. Please wait.");
    }

    public void dispenseCash(ATM atm) {
        // Start dispensing process
        if (atm.getDispenser().dispenseCash(atm.getCurrentAmount())) {
            System.out.println("Cash dispensed successfully!");
            atm.setState(new PrintingState());
        } else {
            System.out.println("Unable to dispense cash. Transaction cancelled.");
            // Refund the amount back to account
            String cardNumber = atm.getCurrentCard().getCardNumber();
            double balance = atm.getBankConnector().getBalance(cardNumber);
            // In real implementation, we would credit back the amount
            atm.ejectCard();
        }
    }

    @Override
    public void onCancel(ATM atm) {
        System.out.println("Cannot cancel during cash dispensing.");
    }
}