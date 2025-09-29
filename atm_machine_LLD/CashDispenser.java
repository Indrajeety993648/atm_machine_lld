package atm_machine_LLD;

import java.util.HashMap;
import java.util.Map;

public class CashDispenser {
    private DispenseStrategy dispenseStrategy;
    private Map<Integer, Integer> denominations; // denomination -> count
    private double totalCash;

    public CashDispenser(DispenseStrategy dispenseStrategy) {
        this.dispenseStrategy = dispenseStrategy;
        this.denominations = new HashMap<>();
        initializeDenominations();
    }

    public void setDispenseStrategy(DispenseStrategy dispenseStrategy) {
        this.dispenseStrategy = dispenseStrategy;
    }

    private void initializeDenominations() {
        denominations.put(100, 50); // 50 notes of $100
        denominations.put(50, 100); // 100 notes of $50
        denominations.put(20, 200); // 200 notes of $20
        denominations.put(10, 500); // 500 notes of $10

        totalCash = calculateTotalCash();
    }

    private double calculateTotalCash() {
        return denominations.entrySet().stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    public boolean dispenseCash(double amount) {
        if (amount > totalCash) {
            System.out.println("Insufficient cash in ATM");
            return false;
        }

        if (!canDispense(amount)) {
            System.out.println("Cannot dispense the requested amount with available denominations");
            return false;
        }

        dispenseStrategy.dispense(amount);
        updateCashInventory(amount);
        return true;
    }

    private boolean canDispense(double amount) {
        // Simple check - in real implementation would use complex algorithm
        return amount % 10 == 0 && amount <= totalCash;
    }

    private void updateCashInventory(double amount) {
        // Simplified - deduct from largest denominations first
        int remaining = (int) amount;

        for (int denomination : new int[] { 100, 50, 20, 10 }) {
            int notesNeeded = remaining / denomination;
            int availableNotes = denominations.get(denomination);
            int notesToDispense = Math.min(notesNeeded, availableNotes);

            denominations.put(denomination, availableNotes - notesToDispense);
            remaining -= notesToDispense * denomination;

            if (remaining == 0)
                break;
        }

        totalCash = calculateTotalCash();
    }

    public double getTotalCash() {
        return totalCash;
    }
}