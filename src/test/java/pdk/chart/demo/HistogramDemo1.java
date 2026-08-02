package pdk.chart.demo;

import pdk.chart.BarChart;
import pdk.chart.Chart;
import pdk.chart.model.Data;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * A simple histogram illustrating the use of the HistogramDataset class.
 * <p>
 * Mouse-wheel zooming has been enabled for this chart, as well as panning (via CTRL-mouse-drag).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:38 PM
 */
public class HistogramDemo1 extends ApplicationFrame {

    public HistogramDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset<String> createDataset() {
        Data.HistogramDatasetBuilder his = Data.his();
        double[] values = new double[1000];
        Random generator = new Random(12345678L);
        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + 5.0;
        }
        his.addSeries("H1", values, 100, 2.0, 8.0);

        values = new double[1000];
        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + 7.0;
        }

        his.addSeries("H2", values, 100, 4.0, 10.0);
        return his.build();
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        BarChart chart = new BarChart(dataset, null, null, "Histogram Demo 1",
                PlotOrientation.VERTICAL, true, true, false);
        chart.setPannable(true, true);
        chart.setForegroundAlpha(0.85f);

        NumberAxis yAxis = chart.getRangeAxisAsNumber();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        chart.setDrawBarOutline(false);
        chart.setShadowVisible(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() throws IOException {
        HistogramDemo1 demo = new HistogramDemo1("HistogramDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
