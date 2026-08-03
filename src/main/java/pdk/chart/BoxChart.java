package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.renderer.xy.XYBoxAndWhiskerRenderer;

/**
 * A box-and-whisker chart.
 * <p>
 * This chart uses an {@link XYBoxAndWhiskerRenderer} to display the
 * distribution of a dataset that implements {@link BoxAndWhiskerXYDataset}.
 * <p>
 * The domain axis is created from the supplied {@link AxisType}, allowing
 * numeric or date axes; the range axis is always a {@link NumberAxis} with
 * {@code autoRangeIncludesZero} set to {@code false}.
 * <p>
 * <b>Note:</b> This class overrides {@link #getRenderer()} to return the
 * concrete {@code XYBoxAndWhiskerRenderer} instance.  If a different
 * renderer is set via {@link #setRenderer(XYItemRenderer)}, the overridden
 * {@code getRenderer()} will still return the original box renderer, which
 * may lead to inconsistency.  It is recommended to avoid replacing the
 * renderer directly.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:41 PM
 */
public class BoxChart extends XYChart {

    private XYBoxAndWhiskerRenderer renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new XYBoxAndWhiskerRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the box-and-whisker renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public XYBoxAndWhiskerRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a box-and-whisker chart.
     *
     * @param dataset         the dataset (must implement {@link BoxAndWhiskerXYDataset};
     *                        {@code null} permitted)
     * @param domainAxisType  the axis type for the domain axis ({@code null} not permitted)
     * @param domainAxisLabel the domain axis label ({@code null} permitted)
     * @param rangeAxisLabel  the range axis label ({@code null} permitted)
     * @param title           the chart title ({@code null} permitted)
     * @param legend          {@code true} to include a legend
     */
    public BoxChart(BoxAndWhiskerXYDataset dataset, AxisType domainAxisType,
            String domainAxisLabel, String rangeAxisLabel,
            String title, boolean legend) {
        super(title, legend);
        ValueAxis xAxis = domainAxisType.createInstance(domainAxisLabel);
        NumberAxis yAxis = new NumberAxis(rangeAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

}
