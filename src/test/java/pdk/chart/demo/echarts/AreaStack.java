package pdk.chart.demo.echarts;

import pdk.chart.CategoryStackedAreaChart;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.model.Data;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.StackedAreaRenderer;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=area-stack
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Jul 2026, 1:00 PM
 */
public class AreaStack {
    static void main() {
        String[] categories = new String[]{
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
        };
        DefaultCategoryDataset<String, String> dataset = Data.<String, String>category()
                .addSeries("Email", categories, new double[]{120, 132, 101, 134, 90, 230, 210})
                .addSeries("Union Ads", categories, new double[]{220, 182, 191, 234, 290, 330, 310})
                .addSeries("Video Ads", categories, new double[]{150, 232, 201, 154, 190, 330, 410})
                .addSeries("Direct", categories, new double[]{320, 332, 301, 334, 390, 330, 320})
                .addSeries("Search Engine", categories, new double[]{820, 932, 901, 934, 1290, 1330, 1320})
                .build();
        CategoryStackedAreaChart chart = new CategoryStackedAreaChart(dataset, null, null, "Stacked Area Chart");
        StackedAreaRenderer renderer = (StackedAreaRenderer) chart.getCategoryPlot()
                .getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);
        chart.getCategoryPlot().getDomainAxis()
                .lowerMargin(0)
                .upperMargin(0);

        chart.show();
    }
}
