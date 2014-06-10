package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Wickets {

    private final int wickets;

    public Wickets(final int wickets) {
        this.wickets = wickets;
    }

    private static int validate(final int wickets) {
        if (wickets >= 0 && wickets <= 10) {
            return wickets;
        }
        throw new IllegalArgumentException("wickets must be between 0 and 10");
    }

    public int getWickets() {
        return wickets;
    }

    public static Wickets of(int wickets) {
        return new Wickets(wickets);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Wickets wickets1 = (Wickets) o;

        if (wickets != wickets1.wickets) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return wickets;
    }
}
