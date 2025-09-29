package atm_machine_LLD;

public class CardInsertedState implements ATMState {

    @Override
    public void onCardInserted(ATM atm) {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        if (atm.getBankConnector().validatePin(atm.getCurrentCard().getCardNumber(), pin)) {
            System.out.println("PIN validated successfully. Please select a transaction.");
            atm.setState(new AuthenticatedState());
        } else {
            System.out.println("Invalid PIN. Please try again or press cancel.");
        }
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        System.out.println("Please enter your PIN first.");
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        System.out.println("Please enter your PIN first.");
    }

    @Override
    public void onCancel(ATM atm) {
        System.out.println("Transaction cancelled. Ejecting card.");
        atm.ejectCard();
    }
}