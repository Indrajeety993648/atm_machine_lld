package atm_machine_LLD;

public interface Keypad {
    /**
     * Gets input from the keypad (PIN, amount, etc.)
     * 
     * @return The input string entered by user
     */
    String getInput();

    /**
     * Clears the current input buffer
     */
    void clearInput();

    /**
     * Checks if keypad is in secure mode (for PIN entry)
     * 
     * @return true if in secure mode, false otherwise
     */
    boolean isSecureMode();

    /**
     * Sets the keypad to secure mode for PIN entry
     * 
     * @param secure true to enable secure mode, false to disable
     */
    void setSecureMode(boolean secure);

    /**
     * Checks if keypad is available for input
     * 
     * @return true if available, false if busy or malfunctioning
     */
    boolean isAvailable();
}