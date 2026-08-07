package pdk.chart.api;

/**
 * Represents a direction of rotation ({@code CLOCKWISE} or
 * {@code ANTICLOCKWISE}).
 */
public enum Rotation {

    /**
     * Clockwise.
     */
    CLOCKWISE(-1.0),

    /**
     * The reverse order renders the primary dataset first.
     */
    ANTICLOCKWISE(1.0);

    /**
     * The factor (-1.0 for {@code CLOCKWISE} and 1.0 for
     * {@code ANTICLOCKWISE}).
     */
    private final double factor;

    /**
     * Private constructor.
     *
     * @param factor the rotation factor.
     */
    Rotation(double factor) {
        this.factor = factor;
    }

    /**
     * Returns the rotation factor, which is -1.0 for {@code CLOCKWISE}
     * and 1.0 for {@code ANTICLOCKWISE}.
     *
     * @return the rotation factor.
     */
    public double getFactor() {
        return this.factor;
    }
}

