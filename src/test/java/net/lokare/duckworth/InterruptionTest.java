package net.lokare.duckworth;


import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author ameya
 */
public class InterruptionTest {

    /**
     * Example 1 from the ICC rules and regulations pdf
     */
    @Test
    public void testExample1() {
        final Score scoreOnInterruption = new Score(Runs.of(79), Wickets.of(3));
        final Interruption interruption = new Interruption(scoreOnInterruption, Overs.of(30), Overs.of(10),
                ResourceTables.getStandardEdition());
        assertThat(interruption.calculateResourcesLost().getResource(), is(new BigDecimal("12.5")));
    }
}
