package atm_machine_LLD;

public class IdleState implements ATMState {

    public IdleState() {
        // Simple constructor - no circular dependencies
    }

    @Override
    public void onCardInserted(ATM atm) {
        System.out.println("Card insertion processing...");
        atm.setState(new CardInsertedState());
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        System.out.println("Please insert card first.");
    }

    @Override
    public void onCancel(ATM atm) {
        // Already in idle state, do nothing
        System.out.println("ATM is ready for use.");
    }
}