package net.lokare.duckworth;

/**
 * @author ameya
 */
public class ResourceTables {

    private static final ResourceTable standardEdition = new StandardEdition();

    public static ResourceTable getStandardEdition() {
        return standardEdition;
    }
}
