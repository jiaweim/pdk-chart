package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;


public class XYAreaChartDemo1 extends ApplicationFrame {
    public XYAreaChartDemo1(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Random 1");
        series1.add((double)1.0F, 500.2);
        series1.add((double)2.0F, 694.1);
        series1.add((double)3.0F, -734.4);
        series1.add((double)4.0F, 453.2);
        series1.add((double)5.0F, 500.2);
        series1.add((double)6.0F, 300.7);
        series1.add((double)7.0F, 734.4);
        series1.add((double)8.0F, 453.2);
        XYSeries series2 = new XYSeries("Random 2");
        series2.add((double)1.0F, 700.2);
        series2.add((double)2.0F, 534.1);
        series2.add((double)3.0F, 323.4);
        series2.add((double)4.0F, 125.2);
        series2.add((double)5.0F, 653.2);
        series2.add((double)6.0F, 432.7);
        series2.add((double)7.0F, 564.4);
        series2.add((double)8.0F, 322.2);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth((double)0.0F);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createXYAreaChart("XY Area Chart Demo", "Domain (X)", "Range (Y)", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setForegroundAlpha(0.65F);
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setLowerMargin((double)0.0F);
        domainAxis.setUpperMargin((double)0.0F);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickMarkPaint(Color.black);
        XYPointerAnnotation pointer = new XYPointerAnnotation("Test", (double)5.0F, (double)-500.0F, 2.356194490192345);
        pointer.setTipRadius((double)0.0F);
        pointer.setBaseRadius((double)35.0F);
        pointer.setFont(new Font("SansSerif", 0, 9));
        pointer.setPaint(Color.BLUE);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYAreaChartDemo1 demo = new XYAreaChartDemo1("XY Area Chart Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
