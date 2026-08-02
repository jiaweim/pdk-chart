package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.StackedAreaChart;
import pdk.chart.data.xy.DefaultTableXYDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedXYAreaChartDemo1 extends ApplicationFrame {
    public StackedXYAreaChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset createDataset() {
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
        XYSeries s1 = new XYSeries("Series 1", true, false);
        s1.add(5.0, 5.0);
        s1.add(10.0, 15.5);
        s1.add(15.0, 9.5);
        s1.add(20.0, 7.5);
        dataset.addSeries(s1);
        XYSeries s2 = new XYSeries("Series 2", true, false);
        s2.add(5.0, 5.0);
        s2.add(10.0, 15.5);
        s2.add(15.0, 9.5);
        s2.add(20.0, 3.5);
        dataset.addSeries(s2);
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        StackedAreaChart chart = new StackedAreaChart(dataset, "X Value", "Y Value", "Stacked XY Area Chart Demo 1");
        chart.setSeriesPaint(0, Color.LIGHT_GRAY);
        chart.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        chart.setDomainCrosshairVisible(true);
        chart.setRangeCrosshairVisible(true);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedXYAreaChartDemo1 demo = new StackedXYAreaChartDemo1("Stacked XY Area Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
