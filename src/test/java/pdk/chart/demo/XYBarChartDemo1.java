package pdk.chart.demo;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
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

public class XYBarChartDemo1 extends ApplicationFrame {
    public XYBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.createXYBarChart("State Executions - USA", "Year", true, "Number of People", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart.addSubtitle(new TextTitle("Source: http://www.amnestyusa.org/abolish/listbyyear.do", new Font("Dialog", 2, 10)));
        XYPlot plot = (XYPlot)chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(generator);
        renderer.setMargin(0.1);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries t1 = new TimeSeries("Executions");

        try {
            t1.add(new Year(1976), (double)0.0F);
            t1.add(new Year(1977), (double)1.0F);
            t1.add(new Year(1978), (double)0.0F);
            t1.add(new Year(1979), (double)2.0F);
            t1.add(new Year(1980), (double)0.0F);
            t1.add(new Year(1981), (double)1.0F);
            t1.add(new Year(1982), (double)2.0F);
            t1.add(new Year(1983), (double)5.0F);
            t1.add(new Year(1984), (double)21.0F);
            t1.add(new Year(1985), (double)18.0F);
            t1.add(new Year(1986), (double)18.0F);
            t1.add(new Year(1987), (double)25.0F);
            t1.add(new Year(1988), (double)11.0F);
            t1.add(new Year(1989), (double)16.0F);
            t1.add(new Year(1990), (double)23.0F);
            t1.add(new Year(1991), (double)14.0F);
            t1.add(new Year(1992), (double)31.0F);
            t1.add(new Year(1993), (double)38.0F);
            t1.add(new Year(1994), (double)31.0F);
            t1.add(new Year(1995), (double)56.0F);
            t1.add(new Year(1996), (double)45.0F);
            t1.add(new Year(1997), (double)74.0F);
            t1.add(new Year(1998), (double)68.0F);
            t1.add(new Year(1999), (double)98.0F);
            t1.add(new Year(2000), (double)85.0F);
            t1.add(new Year(2001), (double)66.0F);
            t1.add(new Year(2002), (double)71.0F);
            t1.add(new Year(2003), (double)65.0F);
            t1.add(new Year(2004), (double)59.0F);
            t1.add(new Year(2005), (double)60.0F);
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
        XYBarChartDemo1 demo = new XYBarChartDemo1("State Executions - USA");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
