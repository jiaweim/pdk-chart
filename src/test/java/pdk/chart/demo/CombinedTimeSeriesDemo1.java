package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.CombinedRangeXYPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Year;
import pdk.chart.swing.*;

public class CombinedTimeSeriesDemo1 extends ApplicationFrame {
    public CombinedTimeSeriesDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        TimeSeries series1 = new TimeSeries("Annual");
        series1.add(new Year(1998), (double)80.0F);
        series1.add(new Year(1999), (double)85.0F);
        series1.add(new Year(2000), 87.6);
        TimeSeriesCollection dataset1 = new TimeSeriesCollection(series1);
        TimeSeries series2A = new TimeSeries("Monthly A");
        series2A.add(new Month(7, 2000), 85.8);
        series2A.add(new Month(8, 2000), 85.8);
        series2A.add(new Month(9, 2000), 85.8);
        series2A.add(new Month(10, 2000), (double)86.5F);
        series2A.add(new Month(11, 2000), (double)86.5F);
        series2A.add(new Month(12, 2000), (double)86.5F);
        series2A.add(new Month(1, 2001), 87.7);
        series2A.add(new Month(2, 2001), 87.7);
        series2A.add(new Month(3, 2001), 87.7);
        series2A.add(new Month(4, 2001), (double)88.5F);
        series2A.add(new Month(5, 2001), (double)88.5F);
        series2A.add(new Month(6, 2001), (double)88.5F);
        series2A.add(new Month(7, 2001), (double)90.0F);
        series2A.add(new Month(8, 2001), (double)90.0F);
        series2A.add(new Month(9, 2001), (double)90.0F);
        series2A.add(new Month(10, 2001), (double)90.0F);
        series2A.add(new Month(11, 2001), (double)90.0F);
        series2A.add(new Month(12, 2001), (double)90.0F);
        series2A.add(new Month(1, 2002), (double)90.0F);
        series2A.add(new Month(2, 2002), (double)90.0F);
        series2A.add(new Month(3, 2002), (double)90.0F);
        series2A.add(new Month(4, 2002), (double)90.0F);
        series2A.add(new Month(5, 2002), (double)90.0F);
        series2A.add(new Month(6, 2002), (double)90.0F);
        TimeSeries series2B = new TimeSeries("Monthly B");
        series2B.add(new Month(7, 2000), 83.8);
        series2B.add(new Month(8, 2000), 83.8);
        series2B.add(new Month(9, 2000), 83.8);
        series2B.add(new Month(10, 2000), (double)84.5F);
        series2B.add(new Month(11, 2000), (double)84.5F);
        series2B.add(new Month(12, 2000), (double)84.5F);
        series2B.add(new Month(1, 2001), 85.7);
        series2B.add(new Month(2, 2001), 85.7);
        series2B.add(new Month(3, 2001), 85.7);
        series2B.add(new Month(4, 2001), (double)86.5F);
        series2B.add(new Month(5, 2001), (double)86.5F);
        series2B.add(new Month(6, 2001), (double)86.5F);
        series2B.add(new Month(7, 2001), (double)88.0F);
        series2B.add(new Month(8, 2001), (double)88.0F);
        series2B.add(new Month(9, 2001), (double)88.0F);
        series2B.add(new Month(10, 2001), (double)88.0F);
        series2B.add(new Month(11, 2001), (double)88.0F);
        series2B.add(new Month(12, 2001), (double)88.0F);
        series2B.add(new Month(1, 2002), (double)88.0F);
        series2B.add(new Month(2, 2002), (double)88.0F);
        series2B.add(new Month(3, 2002), (double)88.0F);
        series2B.add(new Month(4, 2002), (double)88.0F);
        series2B.add(new Month(5, 2002), (double)88.0F);
        series2B.add(new Month(6, 2002), (double)88.0F);
        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(series2A);
        dataset2.addSeries(series2B);
        TimeSeries series3 = new TimeSeries("XXX");
        series3.add(new Month(7, 2000), (double)81.5F);
        series3.add(new Month(8, 2000), (double)86.0F);
        series3.add(new Month(9, 2000), (double)82.0F);
        series3.add(new Month(10, 2000), (double)89.5F);
        series3.add(new Month(11, 2000), (double)88.0F);
        series3.add(new Month(12, 2000), (double)88.0F);
        series3.add(new Month(1, 2001), (double)90.0F);
        series3.add(new Month(2, 2001), (double)89.5F);
        series3.add(new Month(3, 2001), 90.2);
        series3.add(new Month(4, 2001), 90.6);
        series3.add(new Month(5, 2001), (double)87.5F);
        series3.add(new Month(6, 2001), (double)91.0F);
        series3.add(new Month(7, 2001), 89.7);
        series3.add(new Month(8, 2001), (double)87.0F);
        series3.add(new Month(9, 2001), 91.2);
        series3.add(new Month(10, 2001), (double)84.0F);
        series3.add(new Month(11, 2001), (double)90.0F);
        series3.add(new Month(12, 2001), (double)92.0F);
        TimeSeriesCollection dataset3 = new TimeSeriesCollection(series3);
        XYItemRenderer renderer1 = new XYBarRenderer(0.2);
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0} ({1}, {2})", new SimpleDateFormat("yyyy"), new DecimalFormat("0.00")));
        XYPlot plot1 = new XYPlot(dataset1, new DateAxis("Date"), (ValueAxis)null, renderer1);
        XYItemRenderer renderer2 = new XYAreaRenderer();
        XYPlot plot2 = new XYPlot(dataset2, new DateAxis("Date"), (ValueAxis)null, renderer2);
        StandardXYItemRenderer renderer3 = new StandardXYItemRenderer(3);
        renderer3.setBaseShapesFilled(true);
        plot2.setDataset(1, dataset3);
        plot2.setRenderer(1, renderer3);
        plot2.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        NumberAxis rangeAxis = new NumberAxis("Value");
        rangeAxis.setAutoRangeIncludesZero(false);
        CombinedRangeXYPlot combinedPlot = new CombinedRangeXYPlot(rangeAxis);
        combinedPlot.add(plot1, 1);
        combinedPlot.add(plot2, 4);
        Chart chart = new Chart("Sample Combined Plot", Chart.DEFAULT_TITLE_FONT, combinedPlot, true);
        ChartUtils.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            public void chartMouseClicked(ChartMouseEvent event) {
                System.out.println(event.getEntity());
            }

            public void chartMouseMoved(ChartMouseEvent event) {
                System.out.println(event.getEntity());
            }
        });
        return chartPanel;
    }

    public static void main(String[] args) {
        CombinedTimeSeriesDemo1 demo = new CombinedTimeSeriesDemo1("Chart: CombinedTimeSeriesDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
