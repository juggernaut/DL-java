package net.lokare.duckworth;

/**
 * @author ameya
 */
public class Interruption {
    /**
     * Number of overs remaining before interruption.
     */
    private final Overs numOversRemaining;
    private final Overs numOversLost;
    private final Score scoreOnInterruption;
    private final ResourceTable table;

    public Interruption(final Score scoreOnInterruption, final Overs numOversRemaining, final Overs numOversLost, final ResourceTable table) {
        this.scoreOnInterruption = scoreOnInterruption;
        this.numOversLost = numOversLost;
        this.numOversRemaining = numOversRemaining;
        this.table = table;
    }

    public Overs getNumOversLost() {
        return numOversLost;
    }

    public Score getScoreOnInterruption() {
        return scoreOnInterruption;
    }

    public Resource calculateResourcesLost() {
        final Resource beforeResourceRemaining = table.getValue(numOversRemaining, scoreOnInterruption.getWickets());
        final Resource afterResourceRemaining = table.getValue(numOversRemaining.subtract(numOversLost),
                scoreOnInterruption.getWickets());
        return afterResourceRemaining.subtract(beforeResourceRemaining);
    }
}
