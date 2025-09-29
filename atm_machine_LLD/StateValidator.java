package atm_machine_LLD;

public interface StateValidator {
    boolean isValidAction(String currentState, String action);

    String getInvalidActionMessage(String currentState, String action);
}