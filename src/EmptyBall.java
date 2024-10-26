public class EmptyBall extends Ball {
    public EmptyBall(String color, String description) {
        super(color, description);
    }

    public String toString() {
        return "EmptyBall (" + getColor() + "), it contains... nothing :(";
    }
}
