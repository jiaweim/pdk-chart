package pdk.chart.data.function;

import pdk.chart.util.HashUtils;

import java.io.Serializable;

/**
 * A function of the form y = a * x ^ b.
 */
public class PowerFunction2D implements Function2D, Serializable {

    /**
     * The 'a' coefficient.
     */
    private final double a;

    /**
     * The 'b' coefficient.
     */
    private final double b;

    /**
     * Creates a new power function.
     *
     * @param a the 'a' coefficient.
     * @param b the 'b' coefficient.
     */
    public PowerFunction2D(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Returns the 'a' coefficient that was specified in the constructor.
     *
     * @return The 'a' coefficient.
     * @since 1.0.14
     */
    public double getA() {
        return this.a;
    }

    /**
     * Returns the 'b' coefficient that was specified in the constructor.
     *
     * @return The 'b' coefficient.
     * @since 1.0.14
     */
    public double getB() {
        return this.b;
    }

    /**
     * Returns the value of the function for a given input ('x').
     *
     * @param x the x-value.
     * @return The value.
     */
    @Override
    public double getValue(double x) {
        return this.a * Math.pow(x, this.b);
    }

    /**
     * Tests this function for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PowerFunction2D)) {
            return false;
        }
        PowerFunction2D that = (PowerFunction2D) obj;
        if (this.a != that.a) {
            return false;
        }
        if (this.b != that.b) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = 29;
        result = HashUtils.hashCode(result, this.a);
        result = HashUtils.hashCode(result, this.b);
        return result;
    }

}
