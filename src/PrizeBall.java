import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrizeBall extends Ball {
    private int rarity;
    private Prize prize;

    public PrizeBall(String color, String description, int rarity, List<Prize> possiblePrizes) {
        super(color, description);
        this.rarity = rarity;
        this.prize = drawPrize(possiblePrizes);
    }

    public int getRarity() {
        return rarity;
    }

    public Prize getPrize() {
        return prize;
    }

    /**
     * Reads a PrizeBall from the given Scanner.
     *
     * @param scanner the Scanner to read from
     * @param rarity  the rarity of the PrizeBall
     * @return the PrizeBall read from the Scanner
     */
    public static PrizeBall read(Scanner scanner, int rarity, String color, String description) {
        List<Prize> prizes = new ArrayList<>();
        Scanner prizesScanner = new Scanner(scanner.nextLine());
        prizesScanner.useDelimiter(", |\\{|}");
        while (prizesScanner.hasNext()) {
            String prizeDescription = prizesScanner.next();
            prizes.add(new Prize(prizeDescription));
        }
        return new PrizeBall(color, description, rarity, prizes);
    }

    @Override
    public String toString() {
        return "Prize Ball (" + getColor() + "), it contains " + prize.getDescription() + ".";
    }

    /**
     * Draws a prize from the possible prizes.
     *
     * @return the prize drawn
     */
    public Prize drawPrize(List<Prize> possiblePrizes) {
        return possiblePrizes.get((int) (Math.random() * possiblePrizes.size()));
    }
}
