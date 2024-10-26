import java.util.Scanner;

public abstract class Ball {
    private String color;
    private String description;

    public Ball(String color, String description) {
        this.color = color;
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public static void read(Scanner scanner, BallMachine ballMachine) {
        String firstLine = scanner.nextLine();
        String quantityString = firstLine.split(" \\[")[1];
        String rarity = firstLine.split(" ")[0];
        int quantity = Integer.parseInt(quantityString.substring(0, quantityString.length() - 1));
        String color = scanner.nextLine();
        String description = scanner.nextLine();
        switch (rarity) {
            case "NORMAL":
                ballMachine.addMultipleBalls(PrizeBall.read(scanner, 0, color, description), quantity);
                break;
            case "RARE":
                ballMachine.addMultipleBalls(PrizeBall.read(scanner, 1, color, description), quantity);
                break;
            case "EPIC":
                ballMachine.addMultipleBalls(PrizeBall.read(scanner, 2, color, description), quantity);
                break;
            case "LEGENDARY":
                ballMachine.addMultipleBalls(PrizeBall.read(scanner, 3, color, description), quantity);
                break;
            case "EMPTY":
                ballMachine.addMultipleBalls(new EmptyBall(color, description), quantity);
                break;
            case "EXTRA":
                ballMachine.addMultipleBalls(ExtraBallBall.read(scanner, color, description), quantity);
                break;
        }
    }
}
