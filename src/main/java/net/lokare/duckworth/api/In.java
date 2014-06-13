package net.lokare.duckworth.api;

import net.lokare.duckworth.Overs;

/**
 * @author ameya
 */
public class In {

    public static Overs in(final int overs, final int balls) {
        return Overs.of(overs, balls);
    }

    public static Overs in(final int overs) {
        return in(overs, 0);
    }
}
