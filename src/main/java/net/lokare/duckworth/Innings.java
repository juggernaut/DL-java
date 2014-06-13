package net.lokare.duckworth;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author ameya
 */
public class Innings {

    private final List<Interruption> interruptions;
    private final Resource initialResource;
    private final Score finalScore;

    public Innings(final List<Interruption> interruptions, final Resource initialResource, final Score finalScore) {
        this.interruptions = interruptions;
        this.initialResource = initialResource;
        this.finalScore = finalScore;
    }

    public Innings(final ResourceTable table, final Overs allocated, final List<Interruption> interruptions, final Score finalScore) {
        this(interruptions, table.getValue(allocated, Wickets.of(0)), finalScore);
    }

    public Innings(final Overs allocated, final List<Interruption> interruptions, final Score finalScore)  {
        this(ResourceTables.getStandardEdition(), allocated, interruptions, finalScore);
    }

    public Innings(final Overs allocated) {
        this(allocated, Collections.<Interruption>emptyList(), new Score(Runs.of(0), Wickets.of(0)));
    }

    public Resource getFinalResourceAvailable() {
        Resource totalLost = new Resource(new BigDecimal(0));
        for (final Interruption interruption: interruptions) {
            totalLost = totalLost.add(interruption.calculateResourcesLost());
        }
        if (totalLost.compareTo(initialResource) > 0) {
            throw new RuntimeException("something went wrong; total lost > initial resource");
        }
        return initialResource.subtract(totalLost);
    }

    public Score getFinalScore() {
        return finalScore;
    }

    public List<Interruption> getInterruptions() {
        return interruptions;
    }
}
