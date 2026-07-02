package pdk.chart.renderer;

/**
 * An enumeration of the 'end types' for an area renderer.
 */
public enum AreaRendererEndType {

    /**
     * The area tapers from the first or last value down to zero.
     */
    TAPER,

    /**
     * The area is truncated at the first or last value.
     */
    TRUNCATE,

    /**
     * The area is leveled at the first or last value.
     */
    LEVEL
}
