package net.lokare.duckworth;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ameya
 */
public class StandardEdition implements ResourceTable {


    private static class Tuple {
        private final Overs oversLeft;
        private final Wickets wicketsLost;

        private Tuple(final Overs oversLeft, final Wickets wicketsLost) {
            this.oversLeft = oversLeft;
            this.wicketsLost = wicketsLost;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Tuple tuple = (Tuple) o;

            return oversLeft.equals(tuple.oversLeft) && wicketsLost.equals(tuple.wicketsLost);

        }

        @Override
        public int hashCode() {
            int result = oversLeft.hashCode();
            result = 31 * result + wicketsLost.hashCode();
            return result;
        }
    }

    private static final String tableFile = "/ball_by_ball_resource_table.txt";
    private final Map<Tuple, Resource> resourceTable = new HashMap<Tuple, Resource>();


    public StandardEdition(final InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream);
        for (int i = 0; i < 50; i++) {
            for (int b = 0; b < 6; b++) {
                for (int j = 0; j < 10; j++) {
                    final BigDecimal resourceValue = scanner.nextBigDecimal();
                    resourceTable.put(new Tuple(Overs.of(i, b), Wickets.of(j)), new Resource(resourceValue));
                }
            }
        }

        for (int j = 0; j < 10; j++) {
            final BigDecimal resourceValue = scanner.nextBigDecimal();
            resourceTable.put(new Tuple(Overs.of(50, 0), Wickets.of(j)), new Resource(resourceValue));
        }
    }

    public StandardEdition() {
        this(StandardEdition.class.getResourceAsStream(tableFile));
    }

    @Override
    public Resource getValue(final Overs oversLeft, final Wickets wicketsLost) {
        return resourceTable.get(new Tuple(oversLeft, wicketsLost));
    }

}
