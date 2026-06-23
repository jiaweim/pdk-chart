package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.format.RelativeDateFormat;

public class RelativeDateFormatDemo2 extends ApplicationFrame {
    public RelativeDateFormatDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.createXYBarChart("RelativeDateFormat Demo 2", "Date ", true, "Time To Complete", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYBarRenderer r = (XYBarRenderer)plot.getRenderer();
        r.setDrawBarOutline(false);
        DateAxis rangeAxis = new DateAxis();
        RelativeDateFormat rdf = new RelativeDateFormat();
        rdf.setShowZeroDays(false);
        rdf.setSecondFormatter(new DecimalFormat("00"));
        rangeAxis.setDateFormatOverride(rdf);
        plot.setRangeAxis(rangeAxis);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Completion");
        s1.add(new Day(19, 1, 2007), (double)3343000.0F);
        s1.add(new Day(20, 1, 2007), (double)3420000.0F);
        s1.add(new Day(21, 1, 2007), (double)3515000.0F);
        s1.add(new Day(22, 1, 2007), (double)3315000.0F);
        s1.add(new Day(23, 1, 2007), (double)3490000.0F);
        s1.add(new Day(24, 1, 2007), (double)3556000.0F);
        s1.add(new Day(25, 1, 2007), (double)3383000.0F);
        s1.add(new Day(26, 1, 2007), (double)3575000.0F);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        RelativeDateFormatDemo2 demo = new RelativeDateFormatDemo2("Chart: RelativeDateFormatDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
