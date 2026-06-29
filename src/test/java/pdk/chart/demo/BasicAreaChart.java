package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=area-basic
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:43 AM
 */
public class BasicAreaChart {
    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{820, 932, 901, 934, 1290, 1330, 1320});

        Chart area = JChart.area(null, null, null, dataset, PlotOrientation.VERTICAL,
                true, true, false);
        CategoryPlot plot = area.getCategoryPlot();
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.getDomainAxis()
                .lowerMargin(0.0)
                .upperMargin(0.0)
                .categoryMargin(0.0);
        plot.getAreaRenderer(0)
                .endType(AreaRendererEndType.LEVEL);
        area.show();
    }
}
