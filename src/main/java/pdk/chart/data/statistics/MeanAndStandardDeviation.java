package pdk.chart.data.statistics;

import java.io.Serializable;
import java.util.Objects;

/**
 * A simple data structure that holds a mean value and a standard deviation
 * value.  This is used in the
 * {@link DefaultStatisticalCategoryDataset} class.
 */
public class MeanAndStandardDeviation implements Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 7413468697315721515L;

    /**
     * The mean.
     */
    private Number mean;

    /**
     * The standard deviation.
     */
    private Number standardDeviation;

    /**
     * Creates a new mean and standard deviation record.
     *
     * @param mean              the mean.
     * @param standardDeviation the standard deviation.
     */
    public MeanAndStandardDeviation(double mean, double standardDeviation) {
        this(Double.valueOf(mean), Double.valueOf(standardDeviation));
    }

    /**
     * Creates a new mean and standard deviation record.
     *
     * @param mean              the mean ({@code null} permitted).
     * @param standardDeviation the standard deviation ({@code null}
     *                          permitted.
     */
    public MeanAndStandardDeviation(Number mean, Number standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }

    /**
     * Returns the mean.
     *
     * @return The mean.
     */
    public Number getMean() {
        return this.mean;
    }

    /**
     * Returns the mean as a double primitive.  If the underlying mean is
     * {@code null}, this method will return {@code Double.NaN}.
     *
     * @return The mean.
     * @see #getMean()
     * @since 1.0.7
     */
    public double getMeanValue() {
        double result = Double.NaN;
        if (this.mean != null) {
            result = this.mean.doubleValue();
        }
        return result;
    }

    /**
     * Returns the standard deviation.
     *
     * @return The standard deviation.
     */
    public Number getStandardDeviation() {
        return this.standardDeviation;
    }

    /**
     * Returns the standard deviation as a double primitive.  If the underlying
     * standard deviation is {@code null}, this method will return
     * {@code Double.NaN}.
     *
     * @return The standard deviation.
     * @since 1.0.7
     */
    public double getStandardDeviationValue() {
        double result = Double.NaN;
        if (this.standardDeviation != null) {
            result = this.standardDeviation.doubleValue();
        }
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MeanAndStandardDeviation)) {
            return false;
        }
        MeanAndStandardDeviation that = (MeanAndStandardDeviation) obj;
        if (!Objects.equals(this.mean, that.mean)) {
            return false;
        }
        if (!Objects.equals(this.standardDeviation, that.standardDeviation)
        ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.mean);
        hash = 79 * hash + Objects.hashCode(this.standardDeviation);
        return hash;
    }

    /**
     * Returns a string representing this instance.
     *
     * @return A string.
     * @since 1.0.7
     */
    @Override
    public String toString() {
        return "[" + this.mean + ", " + this.standardDeviation + "]";
    }

}