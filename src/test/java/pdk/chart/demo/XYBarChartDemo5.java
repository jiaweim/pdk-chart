package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.Data;
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
 * Another demo for the XYBarRenderer class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 10:17 AM
 */
public class XYBarChartDemo5 extends ApplicationFrame {
    public XYBarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.bar("US Budget Deficit", "Year", true, "$ Billion",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        TextTitle source = new TextTitle("Source: http://www.cbo.gov/showdoc.cfm?index=1821&sequence=0#table12");
        source.setFont(new Font("Dialog", 0, 8));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        XYPlot plot = chart.getXYPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.RED);
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(generator);
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        TimeSeries<String> timeSeries = new TimeSeries<>("Budget");
        timeSeries.add(
                new Year[]{
                        new Year(1980), new Year(1981), new Year(1982), new Year(1983), new Year(1984),
                        new Year(1985), new Year(1986), new Year(1987), new Year(1988), new Year(1989),
                        new Year(1990), new Year(1991), new Year(1992), new Year(1993), new Year(1994),
                        new Year(1995), new Year(1996), new Year(1997), new Year(1998), new Year(1999),
                        new Year(2000), new Year(2001), new Year(2002), new Year(2003), new Year(2004)
                }, new double[]{
                        -74.0, -79.0, -128.0, -208.0, -185.0,
                        -212.0, -221.0, -150.0, -155.0, -153.0,
                        -221.0, -269.0, -290.0, -255.0, -203.0,
                        -164.0, -107.0, -22.0, 69.0, 126.0,
                        236.0, 128.0, -158.0, -378.0, -412.0
                }
        );
        return Data.createTime(timeSeries);
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYBarChartDemo5 demo = new XYBarChartDemo5("US Budget Deficit");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
