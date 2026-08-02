package pdk.chart;

import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.DeviationRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 8:40 PM
 */
public class DeviationChart extends LineChart {

    private DeviationRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new DeviationRenderer(true, false);
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    public DeviationChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title, orientation, legend, tooltips, urls);
    }

    public DeviationChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        super(dataset, xAxisLabel, yAxisLabel, title);
    }
}
