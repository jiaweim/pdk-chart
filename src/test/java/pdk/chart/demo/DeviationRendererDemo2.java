package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.Week;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.DeviationRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A time series chart where the y-values are displayed with
 * a "range of uncertainty".
 * <p>
 * This plot uses the DeviationRenderer first introduced in JFreeChart version 1.0.5.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 23 Jun 2026, 1:04 PM
 */
public class DeviationRendererDemo2 extends ApplicationFrame {
    public DeviationRendererDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        RegularTimePeriod t = new Week();
        double y1 = 100.0;
        double y2 = 100.0;
        for (int i = 0; i <= 52; ++i) {
            double dev1 = 0.05 * (double) i;
            series1.add((double) t.getFirstMillisecond(), y1, y1 - dev1, y1 + dev1);
            y1 = y1 + Math.random() - 0.45;
            double dev2 = 0.07 * (double) i;
            series2.add((double) t.getFirstMillisecond(), y2, y2 - dev2, y2 + dev2);
            y2 = y2 + Math.random() - 0.55;
            t = t.next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.timeLine("Projected Values - Test",
                "Date", "Index Projection", dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(false);
        plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 20.0));
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        DeviationRendererDemo2 demo = new DeviationRendererDemo2("JFreeChart: DeviationRendererDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
