package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.renderer.xy.XYBoxAndWhiskerRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:41 PM
 */
public class BoxChart extends XYChart {

    private XYBoxAndWhiskerRenderer renderer1_;
    private ValueAxis domainAxis_;
    private NumberAxis rangeAxis_;

    @Override
    protected void initRenderer() {
        renderer1_ = new XYBoxAndWhiskerRenderer();
        renderer0_ = renderer1_;
    }

    public BoxChart(AxisType domainAxisType, String title, boolean createLegend) {
        super(title, createLegend);
        if (domainAxisType == AxisType.NUMBER) {
            domainAxis_ = new NumberAxis();
        } else if (domainAxisType == AxisType.DATE) {
            domainAxis_ = new DateAxis();
        } else {
            throw new IllegalArgumentException("Unknown domain axis type: " + domainAxisType);
        }
        rangeAxis_ = new NumberAxis();
        rangeAxis_.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer1_);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates and returns a default instance of a box and whisker chart.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel a label for the time axis ({@code null}
     *                        permitted).
     * @param rangeAxisLabel  a label for the value axis ({@code null}
     *                        permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @param legend          a flag specifying whether a legend is required.
     */
    public BoxChart(BoxAndWhiskerXYDataset dataset, AxisType domainAxisType,
            String domainAxisLabel, String rangeAxisLabel,
            String title, boolean legend) {
        this(domainAxisType, title, legend);
        domainAxis_.setLabel(domainAxisLabel);
        rangeAxis_.setLabel(rangeAxisLabel);
        plot_.setDataset(dataset);
    }

}
