package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.ohlc.OHLCSeries;
import pdk.chart.data.time.ohlc.OHLCSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYBarPainter;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class PriceVolumeDemo2 extends ApplicationFrame {
    public PriceVolumeDemo2(String title) {
        super(title);
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(panel);
    }

    private static Chart createChart() {
        OHLCDataset priceData = createPriceDataset();
        String title = "Sun Microsystems (SUNW)";
        Chart chart = JChart.highLow(title, "Date", "Price", priceData, true);
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setLowerMargin(0.01);
        xAxis.setUpperMargin(0.01);
        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setLowerMargin(0.6);
        rangeAxis1.setAutoRangeIncludesZero(false);
        XYItemRenderer renderer1 = plot.getRenderer();
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        NumberAxis rangeAxis2 = new NumberAxis("Volume");
        rangeAxis2.setUpperMargin((double) 1.0F);
        plot.setRangeAxis(1, rangeAxis2);
        plot.setDataset(1, createVolumeDataset());
        plot.setRangeAxis(1, rangeAxis2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer renderer2 = new XYBarRenderer();
        renderer2.setDrawBarOutline(false);
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
        plot.setRenderer(1, renderer2);
        ChartUtils.applyCurrentTheme(chart);
        renderer2.setShadowVisible(false);
        renderer2.setBarPainter(new StandardXYBarPainter());
        return chart;
    }

    private static OHLCDataset createPriceDataset() {
        OHLCSeries s1 = new OHLCSeries("SUNW");
        s1.add(new Day(12, 4, 2007), 5.9, 5.96, 5.87, 5.9);
        s1.add(new Day(13, 4, 2007), 5.89, 5.9, 5.78, 5.8);
        s1.add(new Day(16, 4, 2007), 5.81, 5.87, 5.79, 5.86);
        s1.add(new Day(17, 4, 2007), 5.87, 5.95, 5.82, 5.95);
        s1.add(new Day(18, 4, 2007), 5.89, 5.95, 5.87, 5.94);
        s1.add(new Day(19, 4, 2007), 5.87, 5.96, 5.86, 5.89);
        s1.add(new Day(20, 4, 2007), 5.94, 5.95, 5.87, 5.93);
        s1.add(new Day(23, 4, 2007), 5.93, 5.95, 5.89, 5.92);
        s1.add(new Day(24, 4, 2007), 5.93, 5.97, 5.88, 5.94);
        s1.add(new Day(25, 4, 2007), 5.58, 5.58, 5.17, 5.27);
        s1.add(new Day(26, 4, 2007), (double) 5.25F, 5.3, 5.2, (double) 5.25F);
        s1.add(new Day(27, 4, 2007), 5.23, 5.28, 5.19, 5.26);
        s1.add(new Day(30, 4, 2007), (double) 5.25F, 5.26, 5.2, 5.22);
        s1.add(new Day(1, 5, 2007), 5.23, 5.24, 4.99, 5.09);
        s1.add(new Day(2, 5, 2007), 5.09, 5.17, 5.08, 5.15);
        s1.add(new Day(3, 5, 2007), 5.14, 5.2, 5.11, 5.18);
        s1.add(new Day(4, 5, 2007), 5.21, 5.3, 5.2, 5.24);
        s1.add(new Day(7, 5, 2007), 5.22, 5.28, 5.21, 5.22);
        s1.add(new Day(8, 5, 2007), 5.24, 5.27, 5.21, 5.22);
        s1.add(new Day(9, 5, 2007), 5.21, 5.22, 5.15, 5.2);
        s1.add(new Day(10, 5, 2007), 5.16, 5.19, 5.13, 5.13);
        s1.add(new Day(11, 5, 2007), 5.14, 5.18, 5.12, 5.15);
        s1.add(new Day(14, 5, 2007), 5.16, 5.29, 5.16, 5.22);
        s1.add(new Day(15, 5, 2007), 5.22, 5.23, 5.13, 5.14);
        s1.add(new Day(16, 5, 2007), 5.14, 5.16, 5.07, 5.12);
        s1.add(new Day(17, 5, 2007), 5.35, 5.43, 5.3, 5.3);
        s1.add(new Day(18, 5, 2007), 5.35, 5.35, 5.26, 5.29);
        s1.add(new Day(21, 5, 2007), 5.29, 5.39, 5.24, 5.39);
        s1.add(new Day(22, 5, 2007), 5.39, 5.42, 5.34, 5.38);
        s1.add(new Day(23, 5, 2007), 5.37, 5.43, 5.36, 5.38);
        s1.add(new Day(24, 5, 2007), 5.36, 5.37, 5.15, 5.15);
        s1.add(new Day(25, 5, 2007), 5.18, 5.21, 5.15, 5.16);
        s1.add(new Day(29, 5, 2007), 5.16, 5.17, (double) 5.0F, 5.06);
        s1.add(new Day(30, 5, 2007), 5.01, 5.15, 4.96, 5.12);
        s1.add(new Day(31, 5, 2007), 5.16, 5.19, 5.07, 5.1);
        s1.add(new Day(1, 6, 2007), 5.12, 5.2, 5.12, 5.18);
        s1.add(new Day(4, 6, 2007), 5.15, 5.24, 5.07, 5.11);
        s1.add(new Day(5, 6, 2007), 5.08, 5.08, 4.97, 5.07);
        s1.add(new Day(6, 6, 2007), 5.03, 5.05, 4.99, 5.02);
        s1.add(new Day(7, 6, 2007), (double) 5.0F, 5.05, 4.97, 4.97);
        s1.add(new Day(8, 6, 2007), 4.98, 5.04, 4.95, 5.04);
        s1.add(new Day(11, 6, 2007), 5.05, 5.06, 4.95, 4.96);
        s1.add(new Day(12, 6, 2007), 4.95, 5.01, 4.92, 4.92);
        s1.add(new Day(13, 6, 2007), 4.95, 4.99, 4.83, 4.99);
        s1.add(new Day(14, 6, 2007), 5.05, 5.1, 5.02, 5.08);
        s1.add(new Day(15, 6, 2007), 5.13, 5.13, 5.03, 5.05);
        s1.add(new Day(18, 6, 2007), 5.06, 5.07, 5.01, 5.05);
        s1.add(new Day(19, 6, 2007), 5.03, 5.16, 5.03, 5.1);
        s1.add(new Day(20, 6, 2007), 5.14, 5.15, 5.05, 5.05);
        s1.add(new Day(21, 6, 2007), 5.07, 5.18, 5.06, 5.13);
        s1.add(new Day(22, 6, 2007), 5.11, 5.14, 5.08, 5.08);
        s1.add(new Day(25, 6, 2007), 5.08, 5.1, 4.99, 5.02);
        s1.add(new Day(26, 6, 2007), 5.04, 5.1, 4.99, 5.01);
        s1.add(new Day(27, 6, 2007), (double) 5.0F, 5.09, 4.99, 5.07);
        s1.add(new Day(28, 6, 2007), 5.08, 5.19, 5.07, 5.16);
        s1.add(new Day(29, 6, 2007), 5.19, 5.33, 5.16, 5.26);
        s1.add(new Day(2, 7, 2007), 5.13, 5.33, 5.12, 5.19);
        s1.add(new Day(3, 7, 2007), 5.2, 5.24, 5.17, 5.22);
        s1.add(new Day(5, 7, 2007), 5.28, 5.35, 5.24, 5.33);
        s1.add(new Day(6, 7, 2007), 5.36, 5.49, 5.34, 5.38);
        s1.add(new Day(9, 7, 2007), 5.4, 5.44, 5.31, 5.33);
        s1.add(new Day(10, 7, 2007), 5.29, 5.41, 5.28, 5.32);
        s1.add(new Day(11, 7, 2007), 5.29, 5.38, 5.29, 5.38);
        s1.add(new Day(12, 7, 2007), 5.38, 5.43, 5.33, 5.43);
        s1.add(new Day(13, 7, 2007), 5.42, 5.43, 5.34, 5.37);
        s1.add(new Day(16, 7, 2007), 5.33, 5.38, 5.3, 5.34);
        OHLCSeriesCollection dataset = new OHLCSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private static IntervalXYDataset createVolumeDataset() {
        TimeSeries s1 = new TimeSeries("Volume");
        s1.add(new Day(12, 4, 2007), (double) 4.96468E7F);
        s1.add(new Day(13, 4, 2007), 6.73193E7);
        s1.add(new Day(16, 4, 2007), (double) 5.68402E7F);
        s1.add(new Day(17, 4, 2007), (double) 4.07523E7F);
        s1.add(new Day(18, 4, 2007), (double) 4.25338E7F);
        s1.add(new Day(19, 4, 2007), (double) 3.42321E7F);
        s1.add(new Day(20, 4, 2007), (double) 3.24376E7F);
        s1.add(new Day(23, 4, 2007), (double) 2.87559E7F);
        s1.add(new Day(24, 4, 2007), 7.45033E7);
        s1.add(new Day(25, 4, 2007), 3.22214E8);
        s1.add(new Day(26, 4, 2007), 9.61417E7);
        s1.add(new Day(27, 4, 2007), (double) 6.23262E7F);
        s1.add(new Day(30, 4, 2007), (double) 5.23344E7F);
        s1.add(new Day(1, 5, 2007), 1.330961E8);
        s1.add(new Day(2, 5, 2007), 9.38745E7);
        s1.add(new Day(3, 5, 2007), (double) 1.010114E8F);
        s1.add(new Day(4, 5, 2007), (double) 5.66297E7F);
        s1.add(new Day(7, 5, 2007), (double) 4.33022E7F);
        s1.add(new Day(8, 5, 2007), (double) 5.14565E7F);
        s1.add(new Day(9, 5, 2007), (double) 4.82309E7F);
        s1.add(new Day(10, 5, 2007), (double) 6.5536E7F);
        s1.add(new Day(11, 5, 2007), (double) 4.64697E7F);
        s1.add(new Day(14, 5, 2007), (double) 1.1858E8F);
        s1.add(new Day(15, 5, 2007), (double) 5.07741E7F);
        s1.add(new Day(16, 5, 2007), (double) 4.45902E7F);
        s1.add(new Day(17, 5, 2007), 1.254825E8);
        s1.add(new Day(18, 5, 2007), (double) 4.99875E7F);
        s1.add(new Day(21, 5, 2007), (double) 4.83874E7F);
        s1.add(new Day(22, 5, 2007), (double) 6.79926E7F);
        s1.add(new Day(23, 5, 2007), (double) 4.56292E7F);
        s1.add(new Day(24, 5, 2007), (double) 1.232886E8F);
        s1.add(new Day(25, 5, 2007), (double) 4.73453E7F);
        s1.add(new Day(29, 5, 2007), (double) 9.0454E7F);
        s1.add(new Day(30, 5, 2007), (double) 7.30492E7F);
        s1.add(new Day(31, 5, 2007), (double) 6.03954E7F);
        s1.add(new Day(1, 6, 2007), (double) 4.87923E7F);
        s1.add(new Day(4, 6, 2007), (double) 1.099728E8F);
        s1.add(new Day(5, 6, 2007), (double) 1.982012E8F);
        s1.add(new Day(6, 6, 2007), 1.214157E8);
        s1.add(new Day(7, 6, 2007), (double) 5.66374E7F);
        s1.add(new Day(8, 6, 2007), (double) 6.41166E7F);
        s1.add(new Day(11, 6, 2007), (double) 6.02748E7F);
        s1.add(new Day(12, 6, 2007), 9.34513E7);
        s1.add(new Day(13, 6, 2007), (double) 1.03309E8F);
        s1.add(new Day(14, 6, 2007), (double) 1.031354E8F);
        s1.add(new Day(15, 6, 2007), 1.044159E8);
        s1.add(new Day(18, 6, 2007), (double) 5.15069E7F);
        s1.add(new Day(19, 6, 2007), 7.45921E7);
        s1.add(new Day(20, 6, 2007), (double) 6.90276E7F);
        s1.add(new Day(21, 6, 2007), 9.72125E7);
        s1.add(new Day(22, 6, 2007), (double) 5.1612E7F);
        s1.add(new Day(25, 6, 2007), (double) 6.38454E7F);
        s1.add(new Day(26, 6, 2007), (double) 8.48184E7F);
        s1.add(new Day(27, 6, 2007), 7.35129E7);
        s1.add(new Day(28, 6, 2007), 7.52751E7);
        s1.add(new Day(29, 6, 2007), 1.042039E8);
        s1.add(new Day(2, 7, 2007), (double) 6.65404E7F);
        s1.add(new Day(3, 7, 2007), (double) 2.85208E7F);
        s1.add(new Day(5, 7, 2007), (double) 4.71763E7F);
        s1.add(new Day(6, 7, 2007), (double) 7.00848E7F);
        s1.add(new Day(9, 7, 2007), (double) 9.16308E7F);
        s1.add(new Day(10, 7, 2007), 1.140717E8);
        s1.add(new Day(11, 7, 2007), (double) 3.42179E7F);
        s1.add(new Day(12, 7, 2007), (double) 3.0298E7F);
        s1.add(new Day(13, 7, 2007), (double) 4.04235E7F);
        s1.add(new Day(16, 7, 2007), (double) 3.9238E7F);
        return new TimeSeriesCollection(s1);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        PriceVolumeDemo2 demo = new PriceVolumeDemo2("Chart: PriceVolumeDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
