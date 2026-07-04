package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.util.Args;
import pdk.chart.util.HashUtils;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * A standard series label generator for plots that use data from
 * an {@link XYDataset}.
 * <br><br>
 * This class implements {@code PublicCloneable} by mistake but we retain
 * this for the sake of backward compatibility.
 */
public class StandardXYSeriesLabelGenerator implements XYSeriesLabelGenerator,
        Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1916017081848400024L;

    /**
     * The default item label format.
     */
    public static final String DEFAULT_LABEL_FORMAT = "{0}";

    /**
     * The format pattern.
     */
    private final String formatPattern;

    /**
     * Creates a default series label generator (uses
     * {@link #DEFAULT_LABEL_FORMAT}).
     */
    public StandardXYSeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
    }

    /**
     * Creates a new series label generator.
     *
     * @param format the format pattern ({@code null} not permitted).
     */
    public StandardXYSeriesLabelGenerator(String format) {
        Args.nullNotPermitted(format, "format");
        this.formatPattern = format;
    }

    /**
     * Generates a label for the specified series.  This label will be
     * used for the chart legend.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series.
     * @return A series label.
     */
    @Override
    public String generateLabel(XYDataset dataset, int series) {
        Args.nullNotPermitted(dataset, "dataset");
        String label = MessageFormat.format(
                this.formatPattern, createItemArray(dataset, series)
        );
        return label;
    }

    /**
     * Creates the array of items that can be passed to the
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series (zero-based index).
     * @return The items (never {@code null}).
     */
    protected Object[] createItemArray(XYDataset dataset, int series) {
        Object[] result = new Object[1];
        result[0] = dataset.getSeriesKey(series).toString();
        return result;
    }

    /**
     * Returns an independent copy of the generator.  This is unnecessary,
     * because instances are immutable anyway, but we retain this
     * behaviour for backwards compatibility.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj the other object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYSeriesLabelGenerator)) {
            return false;
        }
        StandardXYSeriesLabelGenerator that
                = (StandardXYSeriesLabelGenerator) obj;
        if (!this.formatPattern.equals(that.formatPattern)) {
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
        int result = 127;
        result = HashUtils.hashCode(result, this.formatPattern);
        return result;
    }

}
