package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Overs {

    private final int overs;
    private final int balls;

    public Overs(final int overs, final int balls) {
        this.overs = validateOvers(overs);
        this.balls = validateBalls(balls);
    }

    public Overs(final int overs) {
        this(overs, 0);
    }

    private static int validateOvers(final int overs) {
        if (overs >= 0 && overs <= 50) {
            return overs;
        }
        throw new IllegalArgumentException("overs must be >= 0 and <= 50");
    }

    private static int validateBalls(final int balls) {
        if (balls >= 0 && balls <= 5) {
            return balls;
        }
        throw new IllegalArgumentException("balls must be >= 0 and <= 5");
    }

    public int getOvers() {
        return overs;
    }

    public static Overs of(final int overs) {
        return new Overs(overs);
    }

    public static Overs of(final int overs, final int balls) {
        return new Overs(overs, balls);
    }

    public Overs subtract(final Overs that) {
        if (that.getOvers() > this.getOvers()) {
            throw new IllegalArgumentException();
        }
        if (this.balls >= that.balls) {
            return Overs.of(this.overs - that.overs, this.balls - that.balls);
        } else {
            return Overs.of(this.overs - (that.overs + 1), this.balls - (6 - that.balls));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Overs overs1 = (Overs) o;

        return overs == overs1.overs && balls == overs1.balls;
    }

    @Override
    public int hashCode() {
        return overs;
    }
}
