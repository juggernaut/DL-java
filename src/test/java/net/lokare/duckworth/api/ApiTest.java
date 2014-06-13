package net.lokare.duckworth.api;

import net.lokare.duckworth.Innings;
import org.junit.Test;

import static net.lokare.duckworth.api.AtScore.atScore;
import static net.lokare.duckworth.api.AtScore.runs;
import static net.lokare.duckworth.api.Down.down;
import static net.lokare.duckworth.api.In.in;
import static net.lokare.duckworth.api.Lost.lost;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author ameya
 */
public class ApiTest {

    @Test
    public void testSimpleApi() {
        final InningsBuilder builder = InningsBuilder.forAllottedOvers(50);
        builder.interrupted(atScore(runs(200), down(5)), in(30), lost(5))
               .ended(atScore(runs(300), down(9)));
        final Innings innings = builder.build();
        assertThat(innings.getFinalScore().getRuns().getRuns(), is(300));
        assertTrue(!innings.getInterruptions().isEmpty());
    }
}
