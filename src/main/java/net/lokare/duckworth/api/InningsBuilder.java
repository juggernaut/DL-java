package net.lokare.duckworth.api;

import net.lokare.duckworth.Innings;
import net.lokare.duckworth.Interruption;
import net.lokare.duckworth.Overs;
import net.lokare.duckworth.ResourceTables;
import net.lokare.duckworth.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ameya
 */
public class InningsBuilder {

    private final Overs allottedOvers;
    private Overs remaining;
    private List<Interruption> interruptions;
    private Score finalScore;

    private InningsBuilder(final Overs allottedOvers) {
        this.allottedOvers = allottedOvers;
        this.remaining = allottedOvers;
        this.interruptions = new ArrayList<Interruption>();
    }

    public static InningsBuilder forAllottedOvers(final int overs) {
        return new InningsBuilder(Overs.of(overs));
    }

    public InningsBuilder interrupted(final Score score, final Overs completed, final Overs lost) {
        final Interruption interruption = new Interruption(score, this.remaining.subtract(completed), lost,
                ResourceTables.getStandardEdition());
        this.interruptions.add(interruption);
        this.remaining = this.remaining.subtract(completed);
        return this;
    }

    public InningsBuilder ended(final Score score) {
        this.finalScore = score;
        return this;
    }

    public Innings build() {
        final Score score;
        if (this.finalScore != null) {
            score = finalScore;
        } else if (!interruptions.isEmpty()) {
            score = interruptions.get(interruptions.size() - 1).getScoreOnInterruption();
        } else {
            throw new IllegalArgumentException("A score was not specified");
        }
        return new Innings(allottedOvers, interruptions, score);
    }

}
