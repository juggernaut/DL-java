package net.lokare.duckworth.api;

import net.lokare.duckworth.DLCalculator;
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

    @Test
    public void testExample6Fluent() {
        final InningsBuilder builder = InningsBuilder.forAllottedOvers(50);
        builder.interrupted(atScore(runs(226), down(8)), in(47,1), lost(2,5));
        final Innings team1 = builder.build();
        final InningsBuilder builder2 = InningsBuilder.forAllottedOvers(33);
        builder2.interrupted(atScore(runs(140), down(2)), in(25), lost(5));
        final Innings team2 = builder2.build();
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(158));
    }
}
