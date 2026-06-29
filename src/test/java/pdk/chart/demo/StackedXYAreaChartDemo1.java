package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.DefaultTableXYDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYAreaRenderer;
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
        s1.add((double) 5.0F, (double) 5.0F);
        s1.add((double) 10.0F, (double) 15.5F);
        s1.add((double) 15.0F, (double) 9.5F);
        s1.add((double) 20.0F, (double) 7.5F);
        dataset.addSeries(s1);
        XYSeries s2 = new XYSeries("Series 2", true, false);
        s2.add((double) 5.0F, (double) 5.0F);
        s2.add((double) 10.0F, (double) 15.5F);
        s2.add((double) 15.0F, (double) 9.5F);
        s2.add((double) 20.0F, (double) 3.5F);
        dataset.addSeries(s2);
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        Chart chart = JChart.createStackedXYAreaChart("Stacked XY Area Chart Demo 1", "X Value", "Y Value", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        StackedXYAreaRenderer renderer = new StackedXYAreaRenderer();
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(0, renderer);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedXYAreaChartDemo1 demo = new StackedXYAreaChartDemo1("Stacked XY Area Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
