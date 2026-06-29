package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;

public class XYBarChartDemo5 extends ApplicationFrame {
    public XYBarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.bar("US Budget Deficit", "Year", true, "$ Billion", dataset, PlotOrientation.VERTICAL, false, false, false);
        TextTitle source = new TextTitle("Source: http://www.cbo.gov/showdoc.cfm?index=1821&sequence=0#table12");
        source.setFont(new Font("Dialog", 0, 8));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        XYPlot plot = (XYPlot)chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.RED);
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(generator);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries t1 = new TimeSeries("Budget");

        try {
            t1.add(new Year(1980), (double)-74.0F);
            t1.add(new Year(1981), (double)-79.0F);
            t1.add(new Year(1982), (double)-128.0F);
            t1.add(new Year(1983), (double)-208.0F);
            t1.add(new Year(1984), (double)-185.0F);
            t1.add(new Year(1985), (double)-212.0F);
            t1.add(new Year(1986), (double)-221.0F);
            t1.add(new Year(1987), (double)-150.0F);
            t1.add(new Year(1988), (double)-155.0F);
            t1.add(new Year(1989), (double)-153.0F);
            t1.add(new Year(1990), (double)-221.0F);
            t1.add(new Year(1991), (double)-269.0F);
            t1.add(new Year(1992), (double)-290.0F);
            t1.add(new Year(1993), (double)-255.0F);
            t1.add(new Year(1994), (double)-203.0F);
            t1.add(new Year(1995), (double)-164.0F);
            t1.add(new Year(1996), (double)-107.0F);
            t1.add(new Year(1997), (double)-22.0F);
            t1.add(new Year(1998), (double)69.0F);
            t1.add(new Year(1999), (double)126.0F);
            t1.add(new Year(2000), (double)236.0F);
            t1.add(new Year(2001), (double)128.0F);
            t1.add(new Year(2002), (double)-158.0F);
            t1.add(new Year(2003), (double)-378.0F);
            t1.add(new Year(2004), (double)-412.0F);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        TimeSeriesCollection tsc = new TimeSeriesCollection(t1);
        return tsc;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBarChartDemo5 demo = new XYBarChartDemo5("US Budget Deficit");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
