package net.lokare.duckworth;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author ameya
 */
public class CalculatorTest {

    @Test
    public void testExample1() {
        final Score scoreOnInterruption = new Score(Runs.of(79), Wickets.of(3));
        final Interruption interruption = new Interruption(scoreOnInterruption, Overs.of(30), Overs.of(10),
                ResourceTables.getStandardEdition());
        final Innings team1 = new Innings(Overs.of(50), Collections.singletonList(interruption),
                new Score(Runs.of(180), Wickets.of(5)));
        final Innings team2 = new Innings(Overs.of(40), Collections.<Interruption>emptyList(), new Score(Runs.of(0),
                Wickets.of(0)));
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(185));
    }

    @Test
    public void testExample2() {
        final Innings team1 = new Innings(Overs.of(45), Collections.<Interruption>emptyList(),
                new Score(Runs.of(212), Wickets.of(5)));
        final Innings team2 = new Innings(Overs.of(35), Collections.<Interruption>emptyList(), new Score(Runs.of(0),
                Wickets.of(0)));
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(185));
    }

    @Test
    public void testExample3() {
        final Innings team1 = new Innings(Overs.of(50), Collections.<Interruption>emptyList(),
                new Score(Runs.of(250), Wickets.of(10)));
        final Interruption interruption = new Interruption(new Score(Runs.of(40), Wickets.of(1)), Overs.of(38),
                Overs.of(10), ResourceTables.getStandardEdition());
        final Innings team2 = new Innings(Overs.of(50), Collections.singletonList(interruption), null);
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(218));
    }

    @Test
    public void testExample4() {
        final Innings team1 = new Innings(Overs.of(50), Collections.<Interruption>emptyList(),
                new Score(Runs.of(250), Wickets.of(10)));
        final Interruption interruption1 = new Interruption(new Score(Runs.of(40), Wickets.of(1)), Overs.of(38),
                Overs.of(10), ResourceTables.getStandardEdition());
        final Interruption interruption2 = new Interruption(new Score(Runs.of(98), Wickets.of(3)), Overs.of(18),
                Overs.of(2), ResourceTables.getStandardEdition());
        final Interruption interruption3 = new Interruption(new Score(Runs.of(154), Wickets.of(6)), Overs.of(7, 4),
                Overs.of(7, 4), ResourceTables.getStandardEdition());
        final Innings team2 = new Innings(Overs.of(50), Arrays.asList(interruption1, interruption2, interruption3),
                null);
        DLCalculator calculator = new DLCalculator(team1, team2);
        // NOTE: par score is 159, target is 160
        assertThat(calculator.getRevisedTarget().getRuns(), is(160));
    }

    /**
     * India vs Pakistan, Singapore, April 1996
     */
    @Test
    public void testExample5() {
        final Interruption interruption1 = new Interruption(new Score(Runs.of(226), Wickets.of(8)), Overs.of(2, 5),
                Overs.of(2, 5), ResourceTables.getStandardEdition());
        final Innings team1 = new Innings(Overs.of(50), Collections.singletonList(interruption1),
                new Score(Runs.of(226), Wickets.of(8)));
        final Innings team2 = new Innings(Overs.of(33), Collections.<Interruption>emptyList(), null);
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(194));
    }

    @Test
    public void testExample6() {
        final Interruption interruption1 = new Interruption(new Score(Runs.of(226), Wickets.of(8)), Overs.of(2, 5),
                Overs.of(2, 5), ResourceTables.getStandardEdition());
        final Innings team1 = new Innings(Overs.of(50), Collections.singletonList(interruption1),
                new Score(Runs.of(226), Wickets.of(8)));
        final Interruption interruption2 = new Interruption(new Score(Runs.of(140), Wickets.of(2)), Overs.of(8),
                Overs.of(5), ResourceTables.getStandardEdition());
        final Innings team2 = new Innings(Overs.of(33), Collections.singletonList(interruption2), null);
        DLCalculator calculator = new DLCalculator(team1, team2);
        assertThat(calculator.getRevisedTarget().getRuns(), is(158));
    }

    /**
     * Heart breaking game in the 2003 world cup when RSA reached the par score to tie the game,
     * but Mark Boucher defended the last ball thinking the par score was a "victory" score.
     */
    @Test
    public void testSriLankavsSouthAfrica() {
        final Innings sriLanka = new Innings(Overs.of(50), Collections.<Interruption>emptyList(),
                new Score(Runs.of(268), Wickets.of(9)));
        final Interruption interruption = new Interruption(new Score(Runs.of(229), Wickets.of(6)), Overs.of(5),
                Overs.of(5), ResourceTables.getStandardEdition());
        final Innings southAfrica = new Innings(Overs.of(50), Collections.singletonList(interruption),
                new Score(Runs.of(229), Wickets.of(6)));
        DLCalculator calculator = new DLCalculator(sriLanka, southAfrica);
        assertThat(calculator.getRevisedTarget().getRuns(), is(230));

    }
}
