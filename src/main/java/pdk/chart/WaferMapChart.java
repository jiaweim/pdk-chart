package pdk.chart;

import pdk.chart.data.general.WaferMapDataset;
import pdk.chart.plot.WaferMapPlot;
import pdk.chart.renderer.WaferMapRenderer;

/**
 * WaferMapChart was designed for yield analysis of semiconductor wafers,
 * it essentially functions as a circular grid heatmap and can also
 * be used to visualize general two-dimensional matrix data.
 * The final rendering effect resembles:
 * <pre>
 *           ○ ○ ○ ○ ○
 *         ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *         ○ ○ ○ ○ ○ ○ ○
 *            ○ ○ ○ ○ ○
 * </pre>
 * <p>
 * Each die cell is filled with a distinct color mapped from its corresponding numeric value.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 12:28 PM
 */
public class WaferMapChart extends Chart {

    private WaferMapRenderer renderer_;
    private WaferMapPlot plot_;

    /**
     * Creates a wafer map chart.
     *
     * @param title   the chart title ({@code null} permitted).
     * @param dataset the dataset ({@code null} permitted).
     * @param legend  display a legend?
     */
    public WaferMapChart(WaferMapDataset dataset, String title, boolean legend) {
        super(title, DEFAULT_TITLE_FONT, new WaferMapPlot(), legend);
        plot_ = (WaferMapPlot) getPlot();

        renderer_ = new WaferMapRenderer();
        plot_.setDataset(dataset);
        plot_.setRenderer(renderer_);
        JChart.applyCurrentTheme(this);
    }


}
