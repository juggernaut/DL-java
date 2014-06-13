package net.lokare.duckworth.api;

import net.lokare.duckworth.Runs;
import net.lokare.duckworth.Score;
import net.lokare.duckworth.Wickets;

/**
 * @author ameya
 */
public class AtScore {

    public static Score atScore(final Runs runs, final Wickets wickets) {
        return new Score(runs, wickets);
    }

    public static Runs runs(final int runs) {
        return Runs.of(runs);
    }
}
