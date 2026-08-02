package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYSplineRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 3:36 PM
 */
public class SmoothLineChart extends LineChart {

    private XYSplineRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new XYSplineRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public SmoothLineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, @Nullable String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        Objects.requireNonNull(xAxisType);
        Objects.requireNonNull(yAxisType);

        xAxis_ = xAxisType.createInstance(xAxisLabel);
        if (xAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        yAxis_ = yAxisType.createInstance(yAxisLabel);
        if (yAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        if (tooltips) {
            if (xAxisType == AxisType.NUMBER) {
                renderer2_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            } else if (xAxisType == AxisType.DATE) {
                renderer2_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
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
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public SmoothLineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public SmoothLineChart(XYDataset dataset) {
        this(dataset, null, null, null, PlotOrientation.VERTICAL,
                true, true);
    }


}
