package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.BarChart;
import pdk.chart.Chart;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYBarChartDemo6 extends ApplicationFrame {
    public XYBarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static BarChart createChart(IntervalXYDataset<String> dataset) {
        BarChart chart = new BarChart(dataset, "X", AxisType.NUMBER,
                "Y", "XYBarChartDemo6", PlotOrientation.HORIZONTAL, false, false);

        chart.withPannable(true, true);
        chart.setUseYInterval(true);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        return DatasetUtils.createIntervalXY("Series 1",
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{0.9, 1.8, 2.7, 3.6},
                new double[]{1.1, 2.2, 3.3, 4.4},
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{0.9, 1.8, 2.7, 3.6},
                new double[]{1.1, 2.2, 3.3, 4.4}
        );
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        XYBarChartDemo6 demo = new XYBarChartDemo6("XYBarChartDemo6");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
