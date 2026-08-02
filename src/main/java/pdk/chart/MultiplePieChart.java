package pdk.chart;

import pdk.chart.api.TableOrder;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.PieToolTipGenerator;
import pdk.chart.labels.StandardPieToolTipGenerator;
import pdk.chart.plot.pie.MultiplePiePlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.urls.PieURLGenerator;
import pdk.chart.urls.StandardPieURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 5:13 PM
 */
public class MultiplePieChart extends Chart {

    private MultiplePiePlot plot_;

    /**
     * Creates a chart that displays multiple pie plots.  The chart object
     * returned by this method uses a {@link MultiplePiePlot} instance as the
     * plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset ({@code null} permitted).
     * @param order    the order that the data is extracted (by row or by column)
     *                 ({@code null} not permitted).
     * @param legend   include a legend?
     * @param tooltips generate tooltips?
     * @param urls     generate URLs?
     */
    public MultiplePieChart(CategoryDataset dataset, String title, TableOrder order,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, DEFAULT_TITLE_FONT, new MultiplePiePlot(), legend);

        plot_ = (MultiplePiePlot) getPlot();
        plot_.setDataExtractOrder(order);
        plot_.setBackgroundPaint(null);
        plot_.setOutlineStroke(null);

        if (tooltips) {
            PieToolTipGenerator tooltipGenerator = new StandardPieToolTipGenerator();
            PiePlot pp = (PiePlot) plot_.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
        }

        if (urls) {
            PieURLGenerator urlGenerator = new StandardPieURLGenerator();
            PiePlot pp = (PiePlot) plot_.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
        }
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }
}
