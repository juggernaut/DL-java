package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Runs {

    private final int runs;

    public Runs(final int runs) {
        this.runs = validate(runs);
    }

    private static int validate(final int runs) {
        if (runs >= 0) {
            return runs;
        }
        throw new IllegalArgumentException("runs must be +ve");
    }

    public int getRuns() {
        return runs;
    }

    public static Runs of(final int runs) {
        return new Runs(runs);
    }

    public Runs add(final Runs that) {
        return new Runs(this.runs + that.getRuns());
    }
}
