package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PolarPlot;
import pdk.chart.renderer.DefaultPolarItemRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 5:03 PM
 */
public class PolarChart extends Chart {

    private NumberAxis axis_;
    private PolarPlot plot_;
    private DefaultPolarItemRenderer renderer_;

    /**
     * Creates a polar plot for the specified dataset (x-values interpreted as
     * angles in degrees).  The chart object returned by this method uses a
     * {@link PolarPlot} instance as the plot, with a {@link NumberAxis} for
     * the radial axis.
     *
     * @param title   the chart title ({@code null} permitted).
     * @param dataset the dataset ({@code null} permitted).
     * @param legend  legend required?
     */
    public PolarChart(XYDataset dataset, String title, boolean legend) {
        super(title, DEFAULT_TITLE_FONT, new PolarPlot(), legend);
        this.plot_ = (PolarPlot) getPlot();
        axis_ = new NumberAxis();
        axis_.setAxisLineVisible(false);
        axis_.setTickMarksVisible(false);
        axis_.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));

        renderer_ = new DefaultPolarItemRenderer();

        plot_.setAxis(axis_);
        plot_.setDataset(dataset);
        plot_.setRenderer(renderer_);
        JChart.applyCurrentTheme(this);
    }
}
