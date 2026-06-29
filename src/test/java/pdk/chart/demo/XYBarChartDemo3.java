package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYBarChartDemo3 extends ApplicationFrame {
    public XYBarChartDemo3(String title) {
        super(title);
        IntervalXYDataset dataset = new SimpleIntervalXYDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.bar("Sample", "X", false, "Y", dataset);
        return chart;
    }

    public static JPanel createDemoPanel() {
        IntervalXYDataset dataset = new SimpleIntervalXYDataset();
        return new ChartPanel(createChart(dataset), false);
    }

    public static void main(String[] args) {
        XYBarChartDemo3 demo = new XYBarChartDemo3("XY Bar Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
