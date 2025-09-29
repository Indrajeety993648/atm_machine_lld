package atm_machine_LLD;

public class IdleState extends BaseATMState {

    public IdleState() {
        super();
    }

    public IdleState(StateTransitionManager transitionManager,

            StateValidator stateValidator) {
        super(transitionManager, stateValidator);
    }

    @Override
    public void onCardInserted(ATM atm) {
        if (stateValidator.isValidAction(getStateName(), "CARD_INSERTED")) {
            transitionToState(atm, "CARD_INSERTED");
        } else {
            handleInvalidAction(atm, "CARD_INSERTED");
        }
    }

    @Override
    public void onPinEntered(ATM atm, String pin) {
        handleInvalidAction(atm, "PIN_ENTERED");
    }

    @Override
    public void onSelectTransaction(ATM atm, String txType) {
        handleInvalidAction(atm, "SELECT_TRANSACTION");
    }

    @Override
    public void onAmountEntered(ATM atm, double amount) {
        handleInvalidAction(atm, "AMOUNT_ENTERED");
    }

    @Override
    public void onCancel(ATM atm) {
        if (stateValidator.isValidAction(getStateName(), "CANCEL")) {

        } else {
            handleInvalidAction(atm, "CANCEL");
        }
    }

    @Override
    protected String getStateName() {
        return "IDLE";
    }
}