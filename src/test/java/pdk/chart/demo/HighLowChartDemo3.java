package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.CandlestickRenderer;
import pdk.chart.renderer.xy.HighLowRenderer;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.ohlc.OHLCSeries;
import pdk.chart.data.time.ohlc.OHLCSeriesCollection;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class HighLowChartDemo3 extends ApplicationFrame {
    public HighLowChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static OHLCDataset createDataset1() {
        OHLCSeries s1 = new OHLCSeries("Series 1");
        s1.add(new Day(24, 9, 2007), (double)50.5F, 53.2, 49.8, 50.1);
        s1.add(new Day(25, 9, 2007), 50.2, 51.2, 47.8, 48.1);
        s1.add(new Day(26, 9, 2007), (double)48.0F, 49.2, 45.3, 47.4);
        s1.add(new Day(27, 9, 2007), (double)47.5F, 48.3, 46.8, 46.8);
        s1.add(new Day(28, 9, 2007), 46.6, (double)47.0F, 45.1, (double)46.0F);
        s1.add(new Day(1, 10, 2007), 46.6, (double)47.0F, 45.1, (double)46.0F);
        s1.add(new Day(2, 10, 2007), (double)47.5F, 48.3, 46.8, 46.8);
        s1.add(new Day(3, 10, 2007), (double)48.0F, 49.2, 45.3, 47.4);
        s1.add(new Day(4, 10, 2007), 50.2, 51.2, 47.8, 48.1);
        s1.add(new Day(5, 10, 2007), (double)50.5F, 53.2, 49.8, 50.1);
        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static OHLCDataset createDataset2() {
        OHLCSeries s2 = new OHLCSeries("Series 2");
        s2.add(new Day(24, 9, 2007), (double)5.5F, 6.2, 4.8, 5.9);
        s2.add(new Day(25, 9, 2007), (double)6.0F, 6.9, (double)6.0F, 6.7);
        s2.add(new Day(26, 9, 2007), 6.8, (double)7.5F, 6.4, 7.1);
        s2.add(new Day(27, 9, 2007), 7.2, 8.2, (double)7.0F, 7.9);
        s2.add(new Day(28, 9, 2007), 7.8, (double)8.5F, 7.7, 8.2);
        s2.add(new Day(1, 10, 2007), 8.2, (double)8.5F, 7.7, 7.8);
        s2.add(new Day(2, 10, 2007), 7.9, 8.2, (double)7.0F, 7.2);
        s2.add(new Day(3, 10, 2007), 7.1, (double)7.5F, 6.4, 6.8);
        s2.add(new Day(4, 10, 2007), (double)6.0F, 6.9, (double)6.0F, 6.7);
        s2.add(new Day(5, 10, 2007), (double)5.5F, 6.2, 4.8, 5.9);
        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(s2);
        return dataset;
    }

    private static Chart createChart(OHLCDataset dataset) {
        Chart chart = ChartFactory.createHighLowChart("OHLC Demo 3", "Time", "Price", dataset, true);
        XYPlot plot = (XYPlot)chart.getPlot();
        HighLowRenderer renderer = (HighLowRenderer)plot.getRenderer();
        renderer.setDefaultStroke(new BasicStroke(2.0F));
        renderer.setSeriesPaint(0, Color.BLUE);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        NumberAxis yAxis1 = (NumberAxis)plot.getRangeAxis();
        yAxis1.setAutoRangeIncludesZero(false);
        NumberAxis yAxis2 = new NumberAxis("Price 2");
        yAxis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, yAxis2);
        plot.setDataset(1, createDataset2());
        plot.setRenderer(1, new CandlestickRenderer((double)10.0F));
        plot.mapDatasetToRangeAxis(1, 1);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset1());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        HighLowChartDemo3 demo = new HighLowChartDemo3("Chart: HighLowChartDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
