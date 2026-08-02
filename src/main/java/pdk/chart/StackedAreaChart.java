package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYAreaRenderer2;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 3:54 PM
 */
public class StackedAreaChart extends XYChart {

    private NumberAxis xAxis_;
    private NumberAxis yAxis_;
    private StackedXYAreaRenderer2 renderer1_;

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        xAxis_ = new NumberAxis(xAxisLabel);
        xAxis_.setAutoRangeIncludesZero(false);
        xAxis_.setLowerMargin(0.0);
        xAxis_.setUpperMargin(0.0);
        yAxis_ = new NumberAxis(yAxisLabel);

        renderer1_ = new StackedXYAreaRenderer2();
        renderer1_.setDrawOutline(true);
        renderer0_ = renderer1_;

        if (tooltips) {
            XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
            renderer1_.setDefaultToolTipGenerator(toolTipGenerator);
        }

        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setOrientation(orientation);
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title,
                PlotOrientation.VERTICAL, true, true, false);
    }



    /**
     * Sets the flag that controls whether the x-coordinates (in
     * Java2D space) are rounded to integer values, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param round the new flag value.
     */
    public void setRoundXCoordinates(boolean round) {
        renderer1_.setRoundXCoordinates(round);
    }

}
