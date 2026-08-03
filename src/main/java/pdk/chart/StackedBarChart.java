package pdk.chart;

import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.StackedXYBarRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 12:18 PM
 */
public class StackedBarChart extends BarChart {

    private StackedXYBarRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new StackedXYBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    @Override
    public StackedXYBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Create a stacked bar chart.
     *
     * @param dataset     {@link IntervalXYDataset}
     * @param xAxisLabel  x axis label
     * @param xAxisType   {@link AxisType} of x axis
     * @param yAxisLabel  y axis label
     * @param yAxisType   {@link AxisType} of y axis
     * @param title       chart title
     * @param orientation {@link PlotOrientation}
     * @param legend      true if create legend
     * @param tooltips    true if generate tooltips.
     */
    public StackedBarChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        ValueAxis xAxis_ = xAxisType.createInstance(xAxisLabel);
        ValueAxis yAxis_ = yAxisType.createInstance(yAxisLabel);

        if (tooltips) {
            if (xAxisType == AxisType.DATE) {
                renderer2_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer2_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            }
        }
        if (urls) {
            renderer2_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Create a stacked bar chart.
     *
     * @param dataset    {@link XYDataset}
     * @param xAxisLabel x axis label
     * @param xAxisType  {@link AxisType} of x axis
     * @param yAxisLabel y axis label
     * @param title      chart title
     */
    public StackedBarChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, true, false);
    }


    /**
     * Create a stacked bar chart.
     *
     * @param dataset    {@link XYDataset}
     * @param xAxisLabel x axis label
     * @param yAxisLabel y axis label
     * @param title      chart title
     */
    public StackedBarChart(XYDataset dataset,
            String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, true, false);
    }


    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param asPercentages the flag.
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        renderer2_.setRenderAsPercentages(asPercentages);
    }


}
