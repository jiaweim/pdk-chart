package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.CombinedRangeXYPlot;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class CombinedXYPlotDemo2 extends ApplicationFrame {
    public CombinedXYPlotDemo2(String title) {
        super(title);
        Chart chart = createCombinedChart();
        ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
        panel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(panel);
    }

    private static Chart createCombinedChart() {
        IntervalXYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new XYBarRenderer(0.2);
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.0")));
        XYPlot subplot1 = new XYPlot(data1, new DateAxis("Date"), (ValueAxis)null, renderer1);
        XYDataset data2 = createDataset2();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.0")));
        XYPlot subplot2 = new XYPlot(data2, new DateAxis("Date"), (ValueAxis)null, renderer2);
        NumberAxis sharedAxis = new NumberAxis("Value");
        sharedAxis.setTickMarkInsideLength(3.0F);
        CombinedRangeXYPlot plot = new CombinedRangeXYPlot(sharedAxis);
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        Chart chart = new Chart("Combined (Range) XY Plot", Chart.DEFAULT_TITLE_FONT, plot, true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset1() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, 3, 2002), 12353.3);
        series1.add(new Day(2, 3, 2002), 13734.4);
        series1.add(new Day(3, 3, 2002), 14525.3);
        series1.add(new Day(4, 3, 2002), 13984.3);
        series1.add(new Day(5, 3, 2002), 12999.4);
        series1.add(new Day(6, 3, 2002), 14274.3);
        series1.add(new Day(7, 3, 2002), (double)15943.5F);
        series1.add(new Day(8, 3, 2002), 14845.3);
        series1.add(new Day(9, 3, 2002), 14645.4);
        series1.add(new Day(10, 3, 2002), 16234.6);
        series1.add(new Day(11, 3, 2002), 17232.3);
        series1.add(new Day(12, 3, 2002), 14232.2);
        series1.add(new Day(13, 3, 2002), 13102.2);
        series1.add(new Day(14, 3, 2002), 14230.2);
        series1.add(new Day(15, 3, 2002), 11235.2);
        TimeSeriesCollection collection = new TimeSeriesCollection(series1);
        return collection;
    }

    private static XYDataset createDataset2() {
        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Day(3, 3, 2002), 6853.2);
        series2.add(new Day(4, 3, 2002), 9642.3);
        series2.add(new Day(5, 3, 2002), (double)8253.5F);
        series2.add(new Day(6, 3, 2002), 5352.3);
        series2.add(new Day(7, 3, 2002), (double)3532.0F);
        series2.add(new Day(8, 3, 2002), 2635.3);
        series2.add(new Day(9, 3, 2002), 3998.2);
        series2.add(new Day(10, 3, 2002), 1943.2);
        series2.add(new Day(11, 3, 2002), 6943.9);
        series2.add(new Day(12, 3, 2002), 7843.2);
        series2.add(new Day(13, 3, 2002), 6495.3);
        series2.add(new Day(14, 3, 2002), 7943.6);
        series2.add(new Day(15, 3, 2002), 8500.7);
        series2.add(new Day(16, 3, 2002), 9595.9);
        return new TimeSeriesCollection(series2);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createCombinedChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        CombinedXYPlotDemo2 demo = new CombinedXYPlotDemo2("Chart: CombinedXYPlotDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
