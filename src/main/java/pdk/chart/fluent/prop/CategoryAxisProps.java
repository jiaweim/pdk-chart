package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.fluent.CategoryXYChart;

/**
 * A class for configuring properties of {@link pdk.chart.axis.CategoryAxis}, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:18 AM
 */
public class CategoryAxisProps {

    private final CategoryXYChart chart_;
    private final CategoryAxis axis_;

    public CategoryAxisProps(CategoryXYChart chart, CategoryAxis axis) {
        this.chart_ = chart;
        this.axis_ = axis;
    }

    public CategoryXYChart chart() {
        return chart_;
    }

    public CategoryXYChart done() {
        return chart_;
    }

    /**
     * Set Axis title.
     *
     * @param axisTitle axis title.
     * @return this.
     */
    public CategoryAxisProps name(String axisTitle) {
        axis_.setLabel(axisTitle);
        return this;
    }

    /**
     * Sets the category margin.
     * <p>
     * The overall category margin is distributed over
     * N-1 gaps, where N is the number of categories on the axis.
     *
     * @param margin the margin as a percentage of the axis length (for
     *               example, 0.05 is five percent).
     */
    public CategoryAxisProps categoryMargin(double margin) {
        axis_.setCategoryMargin(margin);
        return this;
    }

    /**
     * Sets the lower margin for the axis.
     *
     * @param margin the margin as a percentage of the axis length (for
     *               example, 0.05 is five percent).
     */
    public CategoryAxisProps lowerMargin(double margin) {
        axis_.setLowerMargin(margin);
        return this;
    }

    /**
     * Sets the upper margin for the axis.
     *
     * @param margin the margin as a percentage of the axis length (for
     *               example, 0.05 is five percent).
     */
    public CategoryAxisProps upperMargin(double margin) {
        axis_.setUpperMargin(margin);
        return this;
    }

    /**
     * Sets the category label position specification for the axis.
     *
     * @param positions the positions.
     */
    public CategoryAxisProps categoryLabelPositions(@NonNull CategoryLabelPositions positions) {
        axis_.setCategoryLabelPositions(positions);
        return this;
    }

    /**
     * Sets  whether the axis is visible.
     *
     * @param flag the flag.
     */
    public CategoryAxisProps visible(boolean flag) {
        axis_.setVisible(flag);
        return this;
    }

    /**
     * Sets the maximum category label width ratio.
     *
     * @param ratio the ratio.
     */
    public CategoryAxisProps maximumCategoryLabelWidthRatio(float ratio) {
        axis_.setMaximumCategoryLabelWidthRatio(ratio);
        return this;
    }
}
