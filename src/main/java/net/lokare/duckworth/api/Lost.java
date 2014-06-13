package net.lokare.duckworth.api;

import net.lokare.duckworth.Overs;

/**
 * @author ameya
 */
public class Lost {

    public static Overs lost(final int overs, final int balls) {
        return Overs.of(overs, balls);
    }

    public static Overs lost(final int overs) {
        return lost(overs, 0);
    }
}
