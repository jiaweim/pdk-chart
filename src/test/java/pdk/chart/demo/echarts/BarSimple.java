package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=bar-simple
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 29 Jun 2026, 12:55 PM
 */
public class BarSimple {
    static void main() {
        String[] categories = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] values = new double[]{120, 200, 150, 80, 70, 110, 130};
        Chart chart = ChartFactory.bar(categories, values);
        chart.show();
    }
}
