package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.LogAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class LogAxisDemo1 extends ApplicationFrame {
    public LogAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.scatter("Log Axis Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainGridlineStroke(new BasicStroke(1.0F));
        plot.setRangeGridlineStroke(new BasicStroke(1.0F));
        plot.setDomainMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinesVisible(true);
        plot.setDomainMinorGridlineStroke(new BasicStroke(0.1F));
        plot.setRangeMinorGridlineStroke(new BasicStroke(0.1F));
        LogAxis xAxis = new LogAxis("X");
        LogAxis yAxis = new LogAxis("Y");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Random Data");
        series.add((double)1.0F, 500.2);
        series.add((double)5.0F, 694.1);
        series.add((double)4.0F, (double)100.0F);
        series.add((double)12.5F, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, (double)9005.5F);
        series.add(25.6, 734.4);
        series.add((double)6663000.0F, 6453.2);
        return new XYSeriesCollection(series);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        LogAxisDemo1 demo = new LogAxisDemo1("Chart: LogAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
