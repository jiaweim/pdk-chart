package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.Second;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.format.RelativeDateFormat;

public class RelativeDateFormatDemo1 extends ApplicationFrame {
    public RelativeDateFormatDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createTimeSeriesChart("Exercise Chart", "Elapsed Time", "Beats Per Minute", dataset, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
        }

        DateAxis axis = (DateAxis)plot.getDomainAxis();
        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));
        axis.setDateFormatOverride(rdf);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Heart Rate");
        s1.add(new Second(45, 6, 9, 1, 10, 2006), (double)143.0F);
        s1.add(new Second(33, 8, 9, 1, 10, 2006), (double)167.0F);
        s1.add(new Second(10, 10, 9, 1, 10, 2006), (double)189.0F);
        s1.add(new Second(19, 12, 9, 1, 10, 2006), (double)156.0F);
        s1.add(new Second(5, 15, 9, 1, 10, 2006), (double)176.0F);
        s1.add(new Second(12, 16, 9, 1, 10, 2006), (double)183.0F);
        s1.add(new Second(6, 18, 9, 1, 10, 2006), (double)138.0F);
        s1.add(new Second(11, 20, 9, 1, 10, 2006), (double)102.0F);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        RelativeDateFormatDemo1 demo = new RelativeDateFormatDemo1("Chart: RelativeDateFormatDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
