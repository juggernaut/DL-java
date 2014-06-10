package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Overs {

    private final int overs;

    public Overs(final int overs) {
        this.overs = validate(overs);
    }

    private static int validate(final int overs) {
        if (overs >= 0 && overs <= 50) {
            return overs;
        }
        throw new IllegalArgumentException("overs must be >= 0 and <= 50");
    }

    public int getOvers() {
        return overs;
    }

    public static Overs of(final int overs) {
        return new Overs(overs);
    }

    public Overs subtract(final Overs that) {
        if (that.getOvers() > this.getOvers()) {
            throw new IllegalArgumentException();
        }
        return Overs.of(this.getOvers() - that.getOvers());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Overs overs1 = (Overs) o;

        if (overs != overs1.overs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return overs;
    }
}
