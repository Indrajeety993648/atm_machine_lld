package atm_machine_LLD;

import java.util.Scanner;

public class DigitalKeypad implements Keypad {
    private boolean secureMode;
    private boolean available;
    private Scanner scanner;

    public DigitalKeypad() {
        this.secureMode = false;
        this.available = true;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        if (!available) {
            throw new RuntimeException("Digital keypad is not available");
        }

        System.out.println("Digital Keypad: Touch screen input ready...");

        if (secureMode) {
            System.out.print("Enter PIN (input will be masked): ");
            // In real implementation, this would mask the input
            return "1234"; // Mock for demonstration
        } else {
            System.out.print("Enter amount: $");
            // Mock input for demonstration
            return "100";
        }
    }

    @Override
    public void clearInput() {
        System.out.println("Digital Keypad: Screen cleared");
    }

    @Override
    public boolean isSecureMode() {
        return secureMode;
    }

    @Override
    public void setSecureMode(boolean secure) {
        this.secureMode = secure;
        if (secure) {
            System.out.println("Digital Keypad: Secure mode - PIN dots will be displayed");
            displaySecureScreen();
        } else {
            System.out.println("Digital Keypad: Standard mode - Normal input display");
            displayStandardScreen();
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Additional methods specific to digital keypad
    private void displaySecureScreen() {
        System.out.println("┌─────────────────┐");
        System.out.println("│   ENTER PIN     │");
        System.out.println("│                 │");
        System.out.println("│   [•] [•] [•] [•] │");
        System.out.println("│                 │");
        System.out.println("│ [1] [2] [3] [⌫] │");
        System.out.println("│ [4] [5] [6] [✓] │");
        System.out.println("│ [7] [8] [9] [✗] │");
        System.out.println("│   [0] [#] [*]   │");
        System.out.println("└─────────────────┘");
    }

    private void displayStandardScreen() {
        System.out.println("┌─────────────────┐");
        System.out.println("│  ENTER AMOUNT   │");
        System.out.println("│                 │");
        System.out.println("│    $______      │");
        System.out.println("│                 │");
        System.out.println("│ [1] [2] [3] [⌫] │");
        System.out.println("│ [4] [5] [6] [✓] │");
        System.out.println("│ [7] [8] [9] [✗] │");
        System.out.println("│   [0] [.] [00]  │");
        System.out.println("└─────────────────┘");
    }

    public void setBrightness(int level) {
        System.out.println("Digital Keypad: Screen brightness set to " + level + "%");
    }

    public boolean performTouchCalibration() {
        System.out.println("Digital Keypad: Performing touch calibration...");
        // Simulate calibration
        boolean calibrationResult = true;
        System.out.println("Digital Keypad: Touch calibration " + (calibrationResult ? "COMPLETED" : "FAILED"));
        return calibrationResult;
    }
}