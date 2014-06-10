package net.lokare.duckworth;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author ameya
 */
public class StandardEditionTest {

    @Test
    public void testLoad() {
        StandardEdition table = new StandardEdition();
        assertThat(table.getValue(Overs.of(50), Wickets.of(0)).getResource().intValue(), is(100));
        assertThat(table.getValue(Overs.of(30), Wickets.of(2)).getResource(), is(new BigDecimal("67.3")));
    }
}
