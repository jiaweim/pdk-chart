package pdk.chart.labels;

import pdk.chart.data.category.CategoryDataset;

/**
 * A generator that creates labels for the series in a {@link CategoryDataset}.
 * <p>
 * Classes that implement this interface should be either (a) immutable, or
 * (b) cloneable via the {@code PublicCloneable} interface (defined in
 * the JCommon class library).  This provides a mechanism for the referring
 * renderer to clone the generator if necessary.
 */
public interface CategorySeriesLabelGenerator {

    /**
     * Generates a label for the specified series.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series index.
     * @return A series label.
     */
    String generateLabel(CategoryDataset dataset, int series);

}
