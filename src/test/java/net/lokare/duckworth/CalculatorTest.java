package net.lokare.duckworth;

import org.junit.Test;

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

    /*
    @Test
    public void testExample4() {
        final Innings team1 = new Innings(Overs.of(50), Collections.<Interruption>emptyList(),
                new Score(Runs.of(250), Wickets.of(10)));
        final Interruption interruption1 = new Interruption(new Score(Runs.of(40), Wickets.of(1)), Overs.of(38),
                Overs.of(10), ResourceTables.getStandardEdition());
        final Interruption interruption2 = new Interruption(new Score(Runs.of(98), Wickets.of(3)))

    }
    */
}
