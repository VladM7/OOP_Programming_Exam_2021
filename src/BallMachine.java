import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BallMachine {
    private List<Ball> balls;
    private List<Ball> drawnBalls;
    private List<Record> records;

    public BallMachine() {
        this.balls = new ArrayList<>();
        this.drawnBalls = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    /**
     * Adds a ball to the machine.
     *
     * @param ball the ball to add
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * Adds multiple balls to the machine.
     *
     * @param ball     the ball to add
     * @param quantity the number of balls to add
     */
    public void addMultipleBalls(Ball ball, int quantity) {
        for (int i = 0; i < quantity; i++)
            addBall(ball);
    }

    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    public void addDrawnBall(Ball ball) {
        drawnBalls.add(ball);
    }

    /**
     * Returns a string representation of the balls in the machine.
     *
     * @return a string representation of the balls in the machine
     */
    @Override
    public String toString() {
        String result = "";
        for (Ball ball : balls)
            result += ball.toString() + "\n";
        return result;
    }

    /**
     * Returns the chance to win a prize of a certain rarity.
     *
     * @param rarity the rarity of the prize
     * @return the chance to win a prize of the given rarity
     */
    public double findChance(int rarity) {
        int rarityPrizeBalls = 0;
        for (Ball ball : balls) {
            if (ball instanceof PrizeBall prizeBall)
                if (prizeBall.getRarity() == rarity)
                    rarityPrizeBalls++;
        }
        return (double) rarityPrizeBalls / balls.size();
    }

    /**
     * Draws a ball from the machine.
     */
    public void drawBall() {
        int drawnBall = (int) (Math.random() * balls.size());
        Ball ball = balls.get(drawnBall);
        removeBall(ball);
        addDrawnBall(ball);
        records.add(new Record(ball));

        if (ball.getClass() == PrizeBall.class) {
            PrizeBall prizeBall = (PrizeBall) ball;
            System.out.print("You get a ");
            switch (prizeBall.getRarity()) {
                case 0:
                    System.out.println("normal prize: " + prizeBall.getPrize().getDescription());
                    break;
                case 1:
                    System.out.println("rare prize: " + prizeBall.getPrize().getDescription());
                    break;
                case 2:
                    System.out.println("epic prize: " + prizeBall.getPrize().getDescription());
                    break;
                case 3:
                    System.out.println("legendary prize: " + prizeBall.getPrize().getDescription());
                    break;
            }
        } else if (ball.getClass() == ExtraBallBall.class) {
            ExtraBallBall extraBall = (ExtraBallBall) ball;
            System.out.println("You get an extra ball! It gives you " + extraBall.getNumberOfExtraBalls() + " extra draws.");
            for (int i = 0; i < extraBall.getNumberOfExtraBalls(); i++)
                drawBall();
        } else {
            System.out.println("You get an empty ball :(");
        }
    }

    /**
     * Resets the machine state.
     */
    public void reset() {
        balls.addAll(drawnBalls);
        drawnBalls.clear();
    }

    /**
     * Writes the logs to a file.
     */
    public void writeLogs() {
        try (PrintWriter writer = new PrintWriter("logs.txt")) {
            for (Record record : records)
                writer.println(record);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: logs.txt");
        }
    }
}
