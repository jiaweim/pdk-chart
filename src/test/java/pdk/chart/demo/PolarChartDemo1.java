package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PolarPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class PolarChartDemo1 extends ApplicationFrame {
    public PolarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("Series 1");
        s1.add((double) 0.0F, (double) 2.0F);
        s1.add((double) 90.0F, (double) 13.0F);
        s1.add((double) 180.0F, (double) 9.0F);
        s1.add((double) 270.0F, (double) 8.0F);
        result.addSeries(s1);
        XYSeries s2 = new XYSeries("Series 2");
        s2.add((double) 90.0F, -11.2);
        s2.add((double) 180.0F, 21.4);
        s2.add((double) 250.0F, 17.3);
        s2.add((double) 355.0F, 10.9);
        result.addSeries(s2);
        return result;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createPolarChart("Polar Chart Demo 1", dataset, true, false, false);
        PolarPlot plot = (PolarPlot) chart.getPlot();
        plot.addCornerTextItem("Corner Item 1");
        plot.addCornerTextItem("Corner Item 2");
        plot.setAngleGridlinePaint(Color.WHITE);
        plot.setRadiusGridlinePaint(Color.WHITE);
        NumberAxis rangeAxis = (NumberAxis) plot.getAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseZoomable(false);
        return panel;
    }

    public static void main(String[] args) {
        PolarChartDemo1 demo = new PolarChartDemo1("Chart: PolarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
