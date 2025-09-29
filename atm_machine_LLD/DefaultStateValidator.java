package atm_machine_LLD;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultStateValidator implements StateValidator {
    private Map<String, Set<String>> validActions;

    public DefaultStateValidator() {
        initializeValidActions();
    }

    private void initializeValidActions() {
        validActions = new HashMap<>();

        // Idle state - only card insertion is valid
        Set<String> idleActions = new HashSet<>();
        idleActions.add("CARD_INSERTED");
        idleActions.add("CANCEL"); // Cancel is always valid but may do nothing
        validActions.put("IDLE", idleActions);

        // Card inserted state - PIN entry and cancel are valid
        Set<String> cardInsertedActions = new HashSet<>();
        cardInsertedActions.add("PIN_ENTERED");
        cardInsertedActions.add("CANCEL");
        validActions.put("CARD_INSERTED", cardInsertedActions);

        // Authenticated state - transaction selection and cancel are valid
        Set<String> authenticatedActions = new HashSet<>();
        authenticatedActions.add("SELECT_TRANSACTION");
        authenticatedActions.add("AMOUNT_ENTERED");
        authenticatedActions.add("CANCEL");
        validActions.put("AUTHENTICATED", authenticatedActions);

        // Dispensing state - only internal transitions are valid
        Set<String> dispensingActions = new HashSet<>();
        dispensingActions.add("AMOUNT_ENTERED"); // For internal processing
        validActions.put("DISPENSING", dispensingActions);

        // Printing state - only internal transitions are valid
        Set<String> printingActions = new HashSet<>();
        printingActions.add("SELECT_TRANSACTION"); // For receipt printing
        printingActions.add("AMOUNT_ENTERED"); // For receipt printing
        printingActions.add("CANCEL"); // For receipt printing
        validActions.put("PRINTING", printingActions);
    }

    @Override
    public boolean isValidAction(String currentState, String action) {
        Set<String> actions = validActions.get(currentState);
        return actions != null && actions.contains(action);
    }

    @Override
    public String getInvalidActionMessage(String currentState, String action) {
        switch (currentState) {
            case "IDLE":
                return "INSERT_CARD_FIRST";
            case "DISPENSING":
            case "PRINTING":
                return "TRANSACTION_IN_PROGRESS";
            default:
                return "INVALID_ACTION";
        }
    }
}