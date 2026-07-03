package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TimeSeriesDemo14 extends ApplicationFrame {
    public TimeSeriesDemo14(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(720, 340));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Market Share", "Browser Market Share");
        chart.addSubtitle(new TextTitle("Source: http://gs.statcounter.com"));
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setLowerMargin(0.0);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setNumberFormatOverride(new DecimalFormat("0.0%"));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultStroke(new BasicStroke(3.0F));
            renderer.setAutoPopulateSeriesStroke(false);
            renderer.setSeriesPaint(0, new Color(1742401));
            renderer.setSeriesPaint(1, new Color(10934634));
            renderer.setSeriesPaint(2, new Color(16625249));
            renderer.setSeriesPaint(3, new Color(16777151));
        }

        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(createChromeData());
        dataset.addSeries(createFirefoxData());
        dataset.addSeries(createInternetExplorerData());
        dataset.addSeries(createSafariData());
        return dataset;
    }

    private static TimeSeries createChromeData() {
        TimeSeries series = new TimeSeries("Chrome");
        series.add(new Month(6, 2013), 0.4268);
        series.add(new Month(5, 2013), 0.4138);
        series.add(new Month(4, 2013), 0.3915);
        series.add(new Month(3, 2013), 0.3807);
        series.add(new Month(2, 2013), 0.3709);
        series.add(new Month(1, 2013), 0.3652);
        series.add(new Month(12, 2012), 0.3642);
        series.add(new Month(11, 2012), 0.3572);
        series.add(new Month(10, 2012), 0.3477);
        series.add(new Month(9, 2012), 0.3421);
        series.add(new Month(8, 2012), 0.3359);
        series.add(new Month(7, 2012), 0.3381);
        series.add(new Month(6, 2012), 0.3276);
        series.add(new Month(5, 2012), 0.3243);
        series.add(new Month(4, 2012), 0.3123);
        series.add(new Month(3, 2012), 0.3087);
        series.add(new Month(2, 2012), 0.2984);
        series.add(new Month(1, 2012), 0.284);
        series.add(new Month(12, 2011), 0.2727);
        series.add(new Month(11, 2011), 0.2569);
        series.add(new Month(10, 2011), (double) 0.25F);
        series.add(new Month(9, 2011), 0.2361);
        series.add(new Month(8, 2011), 0.2316);
        series.add(new Month(7, 2011), 0.2214);
        series.add(new Month(6, 2011), 0.2065);
        series.add(new Month(5, 2011), 0.1936);
        series.add(new Month(4, 2011), 0.1829);
        series.add(new Month(3, 2011), 0.1737);
        series.add(new Month(2, 2011), 0.1654);
        series.add(new Month(1, 2011), 0.1568);
        series.add(new Month(12, 2010), 0.1485);
        series.add(new Month(11, 2010), 0.1335);
        series.add(new Month(10, 2010), 0.1239);
        series.add(new Month(9, 2010), 0.1154);
        series.add(new Month(8, 2010), 0.1076);
        series.add(new Month(7, 2010), 0.0988);
        series.add(new Month(6, 2010), 0.0924);
        series.add(new Month(5, 2010), 0.0861);
        series.add(new Month(4, 2010), 0.0806);
        series.add(new Month(3, 2010), 0.0729);
        series.add(new Month(2, 2010), 0.0672);
        series.add(new Month(1, 2010), 0.0604);
        series.add(new Month(12, 2009), 0.0545);
        series.add(new Month(11, 2009), 0.0466);
        series.add(new Month(10, 2009), 0.0417);
        series.add(new Month(9, 2009), 0.0369);
        series.add(new Month(8, 2009), 0.0338);
        series.add(new Month(7, 2009), 0.0301);
        series.add(new Month(6, 2009), 0.0282);
        series.add(new Month(5, 2009), 0.0242);
        series.add(new Month(4, 2009), 0.0207);
        series.add(new Month(3, 2009), 0.0173);
        series.add(new Month(2, 2009), 0.0152);
        series.add(new Month(1, 2009), 0.0138);
        return series;
    }

    private static TimeSeries createFirefoxData() {
        TimeSeries series = new TimeSeries("Firefox");
        series.add(new Month(6, 2013), 0.2001);
        series.add(new Month(5, 2013), 0.1976);
        series.add(new Month(4, 2013), 0.2006);
        series.add(new Month(3, 2013), 0.2087);
        series.add(new Month(2, 2013), 0.2134);
        series.add(new Month(1, 2013), 0.2142);
        series.add(new Month(12, 2012), 0.2189);
        series.add(new Month(11, 2012), 0.2237);
        series.add(new Month(10, 2012), 0.2232);
        series.add(new Month(9, 2012), 0.224);
        series.add(new Month(8, 2012), 0.2285);
        series.add(new Month(7, 2012), 0.2373);
        series.add(new Month(6, 2012), 0.2456);
        series.add(new Month(5, 2012), 0.2555);
        series.add(new Month(4, 2012), 0.2487);
        series.add(new Month(3, 2012), 0.2498);
        series.add(new Month(2, 2012), 0.2488);
        series.add(new Month(1, 2012), 0.2478);
        series.add(new Month(12, 2011), 0.2527);
        series.add(new Month(11, 2011), 0.2523);
        series.add(new Month(10, 2011), 0.2639);
        series.add(new Month(9, 2011), 0.2679);
        series.add(new Month(8, 2011), 0.2749);
        series.add(new Month(7, 2011), 0.2795);
        series.add(new Month(6, 2011), 0.2834);
        series.add(new Month(5, 2011), 0.2929);
        series.add(new Month(4, 2011), 0.2967);
        series.add(new Month(3, 2011), 0.2998);
        series.add(new Month(2, 2011), 0.3037);
        series.add(new Month(1, 2011), 0.3068);
        series.add(new Month(12, 2010), 0.3076);
        series.add(new Month(11, 2010), 0.3117);
        series.add(new Month(10, 2010), 0.3124);
        series.add(new Month(9, 2010), 0.315);
        series.add(new Month(8, 2010), 0.3109);
        series.add(new Month(7, 2010), 0.3069);
        series.add(new Month(6, 2010), 0.3115);
        series.add(new Month(5, 2010), 0.3164);
        series.add(new Month(4, 2010), 0.3174);
        series.add(new Month(3, 2010), 0.3127);
        series.add(new Month(2, 2010), 0.3182);
        series.add(new Month(1, 2010), 0.3164);
        series.add(new Month(12, 2009), 0.3197);
        series.add(new Month(11, 2009), 0.3221);
        series.add(new Month(10, 2009), 0.3182);
        series.add(new Month(9, 2009), 0.3134);
        series.add(new Month(8, 2009), 0.3128);
        series.add(new Month(7, 2009), 0.305);
        series.add(new Month(6, 2009), 0.3033);
        series.add(new Month(5, 2009), 0.2875);
        series.add(new Month(4, 2009), 0.2967);
        series.add(new Month(3, 2009), 0.294);
        series.add(new Month(2, 2009), 0.2785);
        series.add(new Month(1, 2009), 0.2703);
        return series;
    }

    private static TimeSeries createInternetExplorerData() {
        TimeSeries series = new TimeSeries("Internet Explorer");
        series.add(new Month(6, 2013), 0.2544);
        series.add(new Month(5, 2013), 0.2772);
        series.add(new Month(4, 2013), 0.2971);
        series.add(new Month(3, 2013), 0.293);
        series.add(new Month(2, 2013), 0.2982);
        series.add(new Month(1, 2013), 0.3069);
        series.add(new Month(12, 2012), 0.3078);
        series.add(new Month(11, 2012), 0.3123);
        series.add(new Month(10, 2012), 0.3208);
        series.add(new Month(9, 2012), 0.327);
        series.add(new Month(8, 2012), 0.3285);
        series.add(new Month(7, 2012), 0.3204);
        series.add(new Month(6, 2012), 0.3231);
        series.add(new Month(5, 2012), 0.3212);
        series.add(new Month(4, 2012), 0.3407);
        series.add(new Month(3, 2012), 0.3481);
        series.add(new Month(2, 2012), 0.3575);
        series.add(new Month(1, 2012), 0.3745);
        series.add(new Month(12, 2011), 0.3865);
        series.add(new Month(11, 2011), 0.4063);
        series.add(new Month(10, 2011), 0.4018);
        series.add(new Month(9, 2011), 0.4166);
        series.add(new Month(8, 2011), 0.4189);
        series.add(new Month(7, 2011), 0.4245);
        series.add(new Month(6, 2011), 0.4358);
        series.add(new Month(5, 2011), 0.4387);
        series.add(new Month(4, 2011), 0.4452);
        series.add(new Month(3, 2011), 0.4511);
        series.add(new Month(2, 2011), 0.4544);
        series.add(new Month(1, 2011), 0.46);
        series.add(new Month(12, 2010), 0.4694);
        series.add(new Month(11, 2010), 0.4816);
        series.add(new Month(10, 2010), 0.4921);
        series.add(new Month(9, 2010), 0.4987);
        series.add(new Month(8, 2010), 0.5134);
        series.add(new Month(7, 2010), 0.5268);
        series.add(new Month(6, 2010), 0.5286);
        series.add(new Month(5, 2010), 0.5277);
        series.add(new Month(4, 2010), 0.5326);
        series.add(new Month(3, 2010), 0.5444);
        series.add(new Month(2, 2010), 0.545);
        series.add(new Month(1, 2010), 0.5525);
        series.add(new Month(12, 2009), 0.5572);
        series.add(new Month(11, 2009), 0.5657);
        series.add(new Month(10, 2009), 0.5796);
        series.add(new Month(9, 2009), 0.5837);
        series.add(new Month(8, 2009), 0.5869);
        series.add(new Month(7, 2009), 0.6011);
        series.add(new Month(6, 2009), 0.5949);
        series.add(new Month(5, 2009), 0.6209);
        series.add(new Month(4, 2009), 0.6188);
        series.add(new Month(3, 2009), 0.6252);
        series.add(new Month(2, 2009), 0.6443);
        series.add(new Month(1, 2009), 0.6541);
        return series;
    }

    private static TimeSeries createSafariData() {
        TimeSeries series = new TimeSeries("Safari");
        series.add(new Month(6, 2013), 0.0839);
        series.add(new Month(5, 2013), 0.0796);
        series.add(new Month(4, 2013), 0.08);
        series.add(new Month(3, 2013), 0.085);
        series.add(new Month(2, 2013), 0.086);
        series.add(new Month(1, 2013), 0.083);
        series.add(new Month(12, 2012), 0.0792);
        series.add(new Month(11, 2012), 0.0783);
        series.add(new Month(10, 2012), 0.0781);
        series.add(new Month(9, 2012), 0.077);
        series.add(new Month(8, 2012), 0.0739);
        series.add(new Month(7, 2012), 0.0712);
        series.add(new Month(6, 2012), 0.07);
        series.add(new Month(5, 2012), 0.0709);
        series.add(new Month(4, 2012), 0.0713);
        series.add(new Month(3, 2012), 0.0672);
        series.add(new Month(2, 2012), 0.0677);
        series.add(new Month(1, 2012), 0.0662);
        series.add(new Month(12, 2011), 0.0608);
        series.add(new Month(11, 2011), 0.0592);
        series.add(new Month(10, 2011), 0.0593);
        series.add(new Month(9, 2011), 0.056);
        series.add(new Month(8, 2011), 0.0519);
        series.add(new Month(7, 2011), 0.0517);
        series.add(new Month(6, 2011), 0.0507);
        series.add(new Month(5, 2011), 0.0501);
        series.add(new Month(4, 2011), 0.0504);
        series.add(new Month(3, 2011), 0.0502);
        series.add(new Month(2, 2011), 0.0508);
        series.add(new Month(1, 2011), 0.0509);
        series.add(new Month(12, 2010), 0.0479);
        series.add(new Month(11, 2010), 0.047);
        series.add(new Month(10, 2010), 0.0456);
        series.add(new Month(9, 2010), 0.0442);
        series.add(new Month(8, 2010), 0.0423);
        series.add(new Month(7, 2010), 0.0409);
        series.add(new Month(6, 2010), 0.0407);
        series.add(new Month(5, 2010), 0.0414);
        series.add(new Month(4, 2010), 0.0423);
        series.add(new Month(3, 2010), 0.0416);
        series.add(new Month(2, 2010), 0.0408);
        series.add(new Month(1, 2010), 0.0376);
        series.add(new Month(12, 2009), 0.0348);
        series.add(new Month(11, 2009), 0.0367);
        series.add(new Month(10, 2009), 0.0347);
        series.add(new Month(9, 2009), 0.0328);
        series.add(new Month(8, 2009), 0.0325);
        series.add(new Month(7, 2009), 0.0302);
        series.add(new Month(6, 2009), 0.0293);
        series.add(new Month(5, 2009), 0.0265);
        series.add(new Month(4, 2009), 0.0275);
        series.add(new Month(3, 2009), 0.0273);
        series.add(new Month(2, 2009), 0.0259);
        series.add(new Month(1, 2009), 0.0257);
        return series;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        TimeSeriesDemo14 demo = new TimeSeriesDemo14("Time Series Demo 14");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
