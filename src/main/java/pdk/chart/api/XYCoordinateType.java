package pdk.chart.api;

/**
 * Represents several possible interpretations for an (x, y) coordinate.
 */
public enum XYCoordinateType {

    /**
     * The (x, y) coordinates represent a point in the data space.
     */
    DATA,

    /**
     * The (x, y) coordinates represent a relative position in the data space.
     * In this case, the values should be in the range (0.0 to 1.0).
     */
    RELATIVE,

    /**
     * The (x, y) coordinates represent indices in a dataset.
     * In this case, the values should be in the range (0.0 to 1.0).
     */
    INDEX

}
