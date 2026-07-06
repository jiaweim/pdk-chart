package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A bar chart created using data from a TimeSeriesCollection. The chart uses a subtitle to cite the data source.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:10 PM
 */
public class XYBarChartDemo1 extends ApplicationFrame {
    public XYBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.bar(dataset, "Year", AxisType.DATE,
                "Number of People",
                "State Executions - USA",
                PlotOrientation.VERTICAL, true, false);
        chart.addSubtitle(new TextTitle("Source: http://www.amnestyusa.org/abolish/listbyyear.do", new Font("Dialog", Font.ITALIC, 10)));
        XYPlot plot = chart.getXYPlot();
        XYBarRenderer renderer = plot.getBarRenderer();
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(generator);
        renderer.setMargin(0.1);
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        TimeSeries<String> t1 = new TimeSeries<>("Executions");
        try {
            t1.add(new Year(1976), 0.0);
            t1.add(new Year(1977), 1.0);
            t1.add(new Year(1978), 0.0);
            t1.add(new Year(1979), 2.0);
            t1.add(new Year(1980), 0.0);
            t1.add(new Year(1981), 1.0);
            t1.add(new Year(1982), 2.0);
            t1.add(new Year(1983), 5.0);
            t1.add(new Year(1984), 21.0);
            t1.add(new Year(1985), 18.0);
            t1.add(new Year(1986), 18.0);
            t1.add(new Year(1987), 25.0);
            t1.add(new Year(1988), 11.0);
            t1.add(new Year(1989), 16.0);
            t1.add(new Year(1990), 23.0);
            t1.add(new Year(1991), 14.0);
            t1.add(new Year(1992), 31.0);
            t1.add(new Year(1993), 38.0);
            t1.add(new Year(1994), 31.0);
            t1.add(new Year(1995), 56.0);
            t1.add(new Year(1996), 45.0);
            t1.add(new Year(1997), 74.0);
            t1.add(new Year(1998), 68.0);
            t1.add(new Year(1999), 98.0);
            t1.add(new Year(2000), 85.0);
            t1.add(new Year(2001), 66.0);
            t1.add(new Year(2002), 71.0);
            t1.add(new Year(2003), 65.0);
            t1.add(new Year(2004), 59.0);
            t1.add(new Year(2005), 60.0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        TimeSeriesCollection<String> tsc = new TimeSeriesCollection<>(t1);
        return tsc;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYBarChartDemo1 demo = new XYBarChartDemo1("State Executions - USA");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
