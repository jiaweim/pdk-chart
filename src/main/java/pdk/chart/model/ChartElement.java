package pdk.chart.model;

import org.jspecify.annotations.NonNull;

/**
 * A chart element (used to support a visitor pattern for traversing all the
 * elements in a chart).
 */
public interface ChartElement {

    /**
     * Receives a visitor to the element.
     *
     * @param visitor the visitor.
     */
    void receive(@NonNull ChartElementVisitor visitor);

}
