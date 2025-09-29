package atm_machine_LLD;

public abstract class BaseATMState implements ATMState {
    protected StateTransitionManager transitionManager;

    protected StateValidator stateValidator;

    public BaseATMState() {
        // Don't initialize transition manager here to avoid circular dependency
        this.transitionManager = null; // Will be lazily initialized
        this.stateValidator = new DefaultStateValidator();
    }

    // Constructor for dependency injection
    public BaseATMState(StateTransitionManager transitionManager,
            StateValidator stateValidator) {
        this.transitionManager = transitionManager;
        this.stateValidator = stateValidator;
    }

    protected void handleInvalidAction(ATM atm, String action) {
        String currentStateName = getStateName();
        String messageKey = stateValidator.getInvalidActionMessage(currentStateName, action);
    }

    protected void transitionToState(ATM atm, String event) {
        // Lazy initialization to avoid circular dependency
        if (transitionManager == null) {
            transitionManager = new DefaultStateTransitionManager();
        }
        String currentStateName = getStateName();
        ATMState nextState = transitionManager.getNextState(currentStateName, event);
        if (nextState != null) {
            atm.setState(nextState);
        }
    }

    protected abstract String getStateName();
}