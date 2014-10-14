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
 *         <p/>
 *         A builder object for {@link Innings}. Obtain an instance of this builder by calling {@link
 *         InningsBuilder#allottedOvers}
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

    /**
     * Obtain an instance of {@link InningsBuilder} with the specified number of initally allotted overs for the
     * innings.
     *
     * @param overs the number of initially allotted overs for the innings
     * @return an instance of {@link InningsBuilder}
     */
    public static InningsBuilder forAllottedOvers(final int overs) {
        return new InningsBuilder(Overs.of(overs));
    }

    /**
     * Detail an interruption (rain, light malfunction etc) that halted the game. Provide the score when the
     * interruption happened and the number of overs lost due to the interruption.
     *
     * @param score     the score when the match was interrupted
     * @param completed the number of overs completed when the match was interrupted
     * @param lost      the number of overs lost due to the interruption
     * @return
     */
    public InningsBuilder interrupted(final Score
            score, final Overs completed, final Overs lost) {
        final Interruption interruption = new Interruption(score, this.remaining.subtract(completed), lost,
                ResourceTables.getStandardEdition());
        this.interruptions.add(interruption);
        this.remaining = this.remaining.subtract(completed);
        return this;
    }

    /**
     * Specify the score that the innings ended on.
     *
     * @param score the score that the innings ended on
     * @return
     */
    public InningsBuilder ended(final Score score) {
        this.finalScore = score;
        return this;
    }

    /**
     * Build the {@link Innings} object.
     *
     * @return the {@link Innings} object that was built
     */
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
