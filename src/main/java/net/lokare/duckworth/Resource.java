package net.lokare.duckworth;

import java.math.BigDecimal;

/**
 * @author ameya
 */
public class Resource implements Comparable<Resource> {

    private final BigDecimal resource;

    public Resource(final BigDecimal resource) {
        this.resource = resource;
    }

    public BigDecimal getResource() {
        return resource;
    }

    public Resource subtract(final Resource that) {
        if (that.getResource().compareTo(this.resource) > 0) {
            throw new IllegalArgumentException();
        }
        return new Resource(this.resource.subtract(that.getResource()));

    }

    public Resource add(final Resource that) {
        return new Resource(this.resource.add(that.getResource()));
    }

    @Override
    public int compareTo(final Resource o) {
        return this.resource.compareTo(o.getResource());
    }
}
