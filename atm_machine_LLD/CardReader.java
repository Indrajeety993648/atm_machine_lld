package atm_machine_LLD;

public class CardReader {
    private Card currentCard;

    public Card readCard() {
        System.out.println("Reading card...");
        // Simulate card reading - in real implementation this would interface with
        // hardware
        return currentCard;
    }

    public void ejectCard() {
        System.out.println("Ejecting card...");
        currentCard = null;
    }

    public void insertCard(Card card) {
        this.currentCard = card;
        System.out.println("Card inserted: " + card.getCardHolderName());
    }

    public boolean hasCard() {
        return currentCard != null;
    }
}