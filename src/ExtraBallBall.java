import java.util.Scanner;

public class ExtraBallBall extends Ball {
    private int numberOfExtraBalls;

    public ExtraBallBall(String color, String description, int numberOfExtraBalls) {
        super(color, description);
        this.numberOfExtraBalls = numberOfExtraBalls;
    }

    public int getNumberOfExtraBalls() {
        return numberOfExtraBalls;
    }

    public static ExtraBallBall read(Scanner scanner, String color, String description) {
        int extraBalls = Integer.parseInt(scanner.nextLine());
        return new ExtraBallBall(color, description, extraBalls);
    }

    @Override
    public String toString() {
        return "Extra Ball Ball (" + getColor() + "), it gives " + numberOfExtraBalls + " extra draws.";
    }
}
