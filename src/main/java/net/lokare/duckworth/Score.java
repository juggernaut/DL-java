package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Score {

    private final Runs runs;
    private final Wickets wickets;

    public Score(final Runs runs, final Wickets wickets) {
        this.runs = runs;
        this.wickets = wickets;
    }

    public Runs getRuns() {
        return runs;
    }

    public Wickets getWickets() {
        return wickets;
    }

    @Override
    public String toString() {
        return "Score{" +
                "runs=" + runs +
                ", wickets=" + wickets +
                '}';
    }
}
