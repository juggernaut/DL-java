package net.lokare.duckworth;

/**
 * @author ameya
 */
public class DLCalculator {

    // This is for full member nations/first class. Use 200 for lower level matches.
    private static final Runs DEFAULT_G50 = Runs.of(245);

    private final Runs G50;
    private final Innings team1;
    private final Innings team2;

    public DLCalculator(final Runs g50, final Innings team1, final Innings team2) {
        this.G50 = g50;
        this.team1 = team1;
        this.team2 = team2;
    }

    public DLCalculator(final Innings team1, final Innings team2) {
        this(DEFAULT_G50, team1, team2);
    }

    public Runs getRevisedTarget() {
        final Resource r1 = team1.getFinalResourceAvailable();
        final Resource r2 = team2.getFinalResourceAvailable();

        switch(r2.compareTo(r1)) {
            case 0: return team1.getFinalScore().getRuns().add(Runs.of(1));
            case -1: return this.scaleDown();
            case 1: return this.scaleUp();
            default: throw new RuntimeException();
        }
    }

    private Runs scaleDown() {
        final Resource r1 = team1.getFinalResourceAvailable();
        final Resource r2 = team2.getFinalResourceAvailable();
        assert r2.compareTo(r1) < 0;
        final float runs = r2.getResource().divide(r1.getResource()).floatValue() * team1.getFinalScore().getRuns()
                .getRuns() + 1;
        return Runs.of((int) Math.floor(runs));
    }

    private Runs scaleUp() {
        final Resource r1 = team1.getFinalResourceAvailable();
        final Resource r2 = team2.getFinalResourceAvailable();
        assert r2.compareTo(r1) > 0;
        final Resource excess = r2.subtract(r1);
        final float runs = team1.getFinalScore().getRuns().getRuns() + excess.getResource().floatValue() * this.G50.getRuns() / 100 + 1;
        return Runs.of((int) Math.floor(runs));
    }
}
