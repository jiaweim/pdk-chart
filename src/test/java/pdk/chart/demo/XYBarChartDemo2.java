package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.ClusteredXYBarRenderer;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYBarChartDemo2 extends ApplicationFrame {
    public XYBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, 1, 2003), 54.3);
        series1.add(new Day(2, 1, 2003), 20.3);
        series1.add(new Day(3, 1, 2003), 43.4);
        series1.add(new Day(4, 1, 2003), (double)-12.0F);
        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Day(1, 1, 2003), (double)8.0F);
        series2.add(new Day(2, 1, 2003), (double)16.0F);
        series2.add(new Day(3, 1, 2003), (double)21.0F);
        series2.add(new Day(4, 1, 2003), (double)5.0F);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar("XY Bar Chart Demo 2", "Date", true, "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        ClusteredXYBarRenderer r = new ClusteredXYBarRenderer((double)0.0F, false);
        plot.setRenderer(r);
        r.setDrawBarOutline(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBarChartDemo2 demo = new XYBarChartDemo2("Chart: XYBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
