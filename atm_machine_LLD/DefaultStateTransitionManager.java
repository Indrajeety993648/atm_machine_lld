package atm_machine_LLD;

import java.util.HashMap;
import java.util.Map;

public class DefaultStateTransitionManager implements StateTransitionManager {
    private Map<String, ATMState> stateInstances;

    public DefaultStateTransitionManager() {
        initializeStates();
    }

    private void initializeStates() {
        stateInstances = new HashMap<>();
        stateInstances.put("IDLE", new IdleState());
        stateInstances.put("CARD_INSERTED", new CardInsertedState());
        stateInstances.put("AUTHENTICATED", new AuthenticatedState());
        stateInstances.put("DISPENSING", new DispensingState());
        stateInstances.put("PRINTING", new PrintingState());
    }

    @Override
    public ATMState getNextState(String currentState, String event) {
        switch (currentState) {
            case "IDLE":
                if ("CARD_INSERTED".equals(event)) {
                    return stateInstances.get("CARD_INSERTED");
                }
                break;
            case "CARD_INSERTED":
                if ("PIN_VALIDATED".equals(event)) {
                    return stateInstances.get("AUTHENTICATED");
                } else if ("CANCEL".equals(event)) {
                    return stateInstances.get("IDLE");
                }
                break;
            case "AUTHENTICATED":
                if ("WITHDRAW_SELECTED".equals(event)) {
                    return stateInstances.get("DISPENSING");
                } else if ("PRINT_RECEIPT".equals(event)) {
                    return stateInstances.get("PRINTING");
                } else if ("CANCEL".equals(event)) {
                    return stateInstances.get("IDLE");
                }
                break;
            case "DISPENSING":
                if ("CASH_DISPENSED".equals(event)) {
                    return stateInstances.get("PRINTING");
                }
                break;
            case "PRINTING":
                if ("RECEIPT_PRINTED".equals(event)) {
                    return stateInstances.get("IDLE");
                }
                break;
        }
        return null; // No valid transition
    }
}