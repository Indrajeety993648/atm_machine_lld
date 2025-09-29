package atm_machine_LLD;

public interface StateTransitionManager {
    ATMState getNextState(String currentState, String event);
}