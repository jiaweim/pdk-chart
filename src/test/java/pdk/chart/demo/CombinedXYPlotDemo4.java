package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.annotations.XYTextAnnotation;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.CombinedDomainXYPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CombinedXYPlotDemo4 extends ApplicationFrame {
    public CombinedXYPlotDemo4(String title) {
        super(title);
        ChartPanel panel = (ChartPanel) createDemoPanel();
        panel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(panel);
    }

    private static Chart createCombinedChart() {
        XYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new StandardXYItemRenderer();
        NumberAxis rangeAxis1 = new NumberAxis("Range 1");
        XYPlot subplot1 = new XYPlot(data1, (ValueAxis) null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        subplot1.setDataset(1, createDataset2());
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
        axis2.setAutoRangeIncludesZero(false);
        subplot1.setRangeAxis(1, axis2);
        subplot1.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        subplot1.setRenderer(1, new StandardXYItemRenderer());
        subplot1.mapDatasetToRangeAxis(1, 1);
        subplot1.setRangePannable(true);
        XYTextAnnotation annotation = new XYTextAnnotation("Hello!", (double) 50.0F, (double) 10000.0F);
        annotation.setFont(new Font("SansSerif", 0, 9));
        annotation.setRotationAngle((Math.PI / 4D));
        subplot1.addAnnotation(annotation);
        XYDataset data2 = createDataset2();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        NumberAxis rangeAxis2 = new NumberAxis("Range 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        XYPlot subplot2 = new XYPlot(data2, (ValueAxis) null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
        plot.setGap((double) 10.0F);
        plot.setDomainPannable(true);
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);
        Chart chart = new Chart("CombinedDomainXYPlot Demo", Chart.DEFAULT_TITLE_FONT, plot, true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset1() {
        XYSeries series1 = new XYSeries("Series 1a");
        series1.add((double) 10.0F, 12353.3);
        series1.add((double) 20.0F, 13734.4);
        series1.add((double) 30.0F, 14525.3);
        series1.add((double) 40.0F, 13984.3);
        series1.add((double) 50.0F, 12999.4);
        series1.add((double) 60.0F, 14274.3);
        series1.add((double) 70.0F, (double) 15943.5F);
        series1.add((double) 80.0F, 14845.3);
        series1.add((double) 90.0F, 14645.4);
        series1.add((double) 100.0F, 16234.6);
        series1.add((double) 110.0F, 17232.3);
        series1.add((double) 120.0F, 14232.2);
        series1.add((double) 130.0F, 13102.2);
        series1.add((double) 140.0F, 14230.2);
        series1.add((double) 150.0F, 11235.2);
        XYSeries series1b = new XYSeries("Series 1b");
        series1b.add((double) 10.0F, 15000.3);
        series1b.add((double) 20.0F, 11000.4);
        series1b.add((double) 30.0F, 17000.3);
        series1b.add((double) 40.0F, 15000.3);
        series1b.add((double) 50.0F, 14000.4);
        series1b.add((double) 60.0F, 12000.3);
        series1b.add((double) 70.0F, (double) 11000.5F);
        series1b.add((double) 80.0F, 12000.3);
        series1b.add((double) 90.0F, 13000.4);
        series1b.add((double) 100.0F, 12000.6);
        series1b.add((double) 110.0F, 13000.3);
        series1b.add((double) 120.0F, 17000.2);
        series1b.add((double) 130.0F, 18000.2);
        series1b.add((double) 140.0F, 16000.2);
        series1b.add((double) 150.0F, 17000.2);
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series1b);
        return collection;
    }

    private static XYDataset createDataset2() {
        XYSeries series2 = new XYSeries("Series 2");
        series2.add((double) 10.0F, 6853.2);
        series2.add((double) 20.0F, 9642.3);
        series2.add((double) 30.0F, (double) 8253.5F);
        series2.add((double) 40.0F, 5352.3);
        series2.add((double) 50.0F, (double) 3532.0F);
        series2.add((double) 60.0F, 2635.3);
        series2.add((double) 70.0F, 3998.2);
        series2.add((double) 80.0F, 1943.2);
        series2.add((double) 90.0F, 6943.9);
        series2.add((double) 100.0F, 7843.2);
        series2.add((double) 105.0F, 6495.3);
        series2.add((double) 110.0F, 7943.6);
        series2.add((double) 115.0F, 8500.7);
        series2.add((double) 120.0F, 9595.9);
        return new XYSeriesCollection(series2);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createCombinedChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        CombinedXYPlotDemo4 demo = new CombinedXYPlotDemo4("Chart: CombinedDomainXYPlotDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
