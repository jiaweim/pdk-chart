package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYSeriesDemo2 extends ApplicationFrame {
    public XYSeriesDemo2(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.line("XY Series Demo 2", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        NumberAxis axis = (NumberAxis)plot.getRangeAxis();
        axis.setAutoRangeIncludesZero(false);
        axis.setAutoRangeMinimumSize((double)1.0F);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Flat Data");
        series.add((double)1.0F, (double)100.0F);
        series.add((double)5.0F, (double)100.0F);
        series.add((double)4.0F, (double)100.0F);
        series.add((double)12.5F, (double)100.0F);
        series.add(17.3, (double)100.0F);
        series.add(21.2, (double)100.0F);
        series.add(21.9, (double)100.0F);
        series.add(25.6, (double)100.0F);
        series.add((double)30.0F, (double)100.0F);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        XYSeriesDemo2 demo = new XYSeriesDemo2("Chart: XYSeriesDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
