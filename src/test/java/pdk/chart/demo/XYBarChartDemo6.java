package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
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

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar("XYBarChartDemo6", "X", false, "Y", dataset, PlotOrientation.HORIZONTAL, false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);
        plot.setRenderer(renderer);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        return Data.createIntervalXY("Series 1",
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
        XYBarChartDemo6 demo = new XYBarChartDemo6("Chart : XYBarChartDemo6");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
