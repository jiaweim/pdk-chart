package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.CategoryDataset;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * A standard series label generator for plots that use data from
 * a {@link CategoryDataset}.
 */
public class StandardCategorySeriesLabelGenerator implements
        CategorySeriesLabelGenerator, Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 4630760091523940820L;

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
    public StandardCategorySeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
    }

    /**
     * Creates a new series label generator.
     *
     * @param format the format pattern ({@code null} not permitted).
     */
    public StandardCategorySeriesLabelGenerator(String format) {
        Objects.requireNonNull(format, "format");
        this.formatPattern = format;
    }

    /**
     * Generates a label for the specified series.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series.
     * @return A series label.
     */
    @Override
    public String generateLabel(CategoryDataset dataset, int series) {
        Objects.requireNonNull(dataset, "dataset");
        String label = MessageFormat.format(this.formatPattern,
                createItemArray(dataset, series));
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
    protected Object[] createItemArray(CategoryDataset dataset, int series) {
        Object[] result = new Object[1];
        result[0] = dataset.getRowKey(series).toString();
        return result;
    }

    /**
     * Returns an independent copy of the generator.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StandardCategorySeriesLabelGenerator that)) return false;
        return Objects.equals(formatPattern, that.formatPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(formatPattern);
    }
}
