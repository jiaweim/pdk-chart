package pdk.chart.data.function;

import pdk.chart.util.Args;
import pdk.chart.util.HashUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A function in the form {@code y = a0 + a1 * x + a2 * x^2 + ... + an *
 * x^n}.  Instances of this class are immutable.
 *
 * @since 1.0.14
 */
public class PolynomialFunction2D implements Function2D, Serializable {

    /**
     * The coefficients.
     */
    private double[] coefficients;

    /**
     * Constructs a new polynomial function {@code y = a0 + a1 * x + a2 * x^2 +
     * ... + an * x^n}
     *
     * @param coefficients an array with the coefficients [a0, a1, ..., an]
     *                     ({@code null} not permitted).
     */
    public PolynomialFunction2D(double[] coefficients) {
        Args.nullNotPermitted(coefficients, "coefficients");
        this.coefficients = (double[]) coefficients.clone();
    }

    /**
     * Returns a copy of the coefficients array that was specified in the
     * constructor.
     *
     * @return The coefficients array.
     */
    public double[] getCoefficients() {
        return (double[]) this.coefficients.clone();
    }

    /**
     * Returns the order of the polynomial.
     *
     * @return The order.
     */
    public int getOrder() {
        return this.coefficients.length - 1;
    }

    /**
     * Returns the function value.
     *
     * @param x the x-value.
     * @return The value.
     */
    @Override
    public double getValue(double x) {
        double y = 0;
        for (int i = 0; i < coefficients.length; i++) {
            y += coefficients[i] * Math.pow(x, i);
        }
        return y;
    }

    /**
     * Tests this function for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PolynomialFunction2D)) {
            return false;
        }
        PolynomialFunction2D that = (PolynomialFunction2D) obj;
        return Arrays.equals(this.coefficients, that.coefficients);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return HashUtils.hashCodeForDoubleArray(this.coefficients);
    }

}
