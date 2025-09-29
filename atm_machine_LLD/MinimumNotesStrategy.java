package atm_machine_LLD;

import java.util.ArrayList;
import java.util.List;

public class MinimumNotesStrategy implements DispenseStrategy {

    @Override
    public void dispense(double amount) {
        System.out.println("Dispensing $" + amount + " using minimum notes strategy...");

        List<String> notesDispensed = calculateMinimumNotes((int) amount);

        for (String note : notesDispensed) {
            System.out.println("Dispensing: " + note);
        }

        System.out.println("Please collect your cash from the dispenser");
    }

    private List<String> calculateMinimumNotes(int amount) {
        List<String> result = new ArrayList<>();
        int[] denominations = { 100, 50, 20, 10, 5, 1 }; // Available denominations

        for (int denomination : denominations) {
            int count = amount / denomination;
            if (count > 0) {
                result.add(count + " x $" + denomination + " notes");
                amount = amount % denomination;
            }
        }

        return result;
    }

    public int getTotalNotesCount(int amount) {
        int totalNotes = 0;
        int[] denominations = { 100, 50, 20, 10, 5, 1 };

        for (int denomination : denominations) {
            totalNotes += amount / denomination;
            amount = amount % denomination;
        }

        return totalNotes;
    }
}