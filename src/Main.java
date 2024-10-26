import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void readFromFile(String fileName, BallMachine ballMachine) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine())
                Ball.read(scanner, ballMachine);
            // System.out.println(ballMachine);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file." + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "resources/gacha.txt";
        BallMachine ballMachine = new BallMachine();
        readFromFile(fileName, ballMachine);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("""
                        Please make your choice:
                        1 – Show all balls currently in the machine.
                        2 – Show current chance to win the legendary prize.
                        3 – Draw a ball.
                        4 – Write debug output to file.
                        5 – Reset machine state.
                        6 – Quit the application.
                        """);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println(ballMachine);
                        break;
                    case 2:
                        System.out.println("The chance to win a legendary prize is " + (int) (ballMachine.findChance(3) * 100) + "%.");
                        break;
                    case 3:
                        ballMachine.drawBall();
                        break;
                    case 4:
                        ballMachine.writeLogs();
                        System.out.println("Logs have been written to the file.");
                        break;
                    case 5:
                        ballMachine.reset();
                        System.out.println("Machine state has been reset successfully.");
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the input." + e.getMessage());
        }
    }
}