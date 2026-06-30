package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-smooth
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 25 Jun 2026, 3:22 PM
 */
public class LineSmooth extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public LineSmooth(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(720, 480));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("line", new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{820, 932, 901, 934, 1290, 1330, 1320});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        return JChart.line(null, null, null, dataset);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        LineSimple demo = new LineSimple("Basic Line Chart");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
