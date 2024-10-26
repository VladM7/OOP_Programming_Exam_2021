public class Record {
    private Ball extractedBall;

    private int index;
    private static int totalRecords = 0;

    public Record(Ball extractedBall) {
        this.extractedBall = extractedBall;
        totalRecords++;
        this.index = totalRecords;
    }

    public String toString() {
        switch (extractedBall.getClass().getName()) {
            case "PrizeBall":
                return index + ". " + extractedBall.getClass().getName() + " (" + extractedBall.getColor() + ") containing " + ((PrizeBall) extractedBall).getPrize().getDescription() + " was drawn.";
            case "EmptyBall":
                return index + ". " + extractedBall.getClass().getName() + " (" + extractedBall.getColor() + ") containing... nothing was drawn.";
            case "ExtraBallBall":
                return index + ". " + extractedBall.getClass().getName() + " (" + extractedBall.getColor() + ") was drawn.";
        }
        return null;
    }
}
