package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.EmptyBlock;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.CompositeTitle;


public class DualAxisDemo2 extends ApplicationFrame {
    public DualAxisDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset1() {
        TimeSeries s1 = new TimeSeries("Random Data 1");
        s1.add(new Month(2, 2016), 181.8);
        s1.add(new Month(3, 2016), 167.3);
        s1.add(new Month(4, 2016), 153.8);
        s1.add(new Month(5, 2016), 167.6);
        s1.add(new Month(6, 2016), 158.8);
        s1.add(new Month(7, 2016), 148.3);
        s1.add(new Month(8, 2016), 153.9);
        s1.add(new Month(9, 2016), 142.7);
        s1.add(new Month(10, 2016), 123.2);
        s1.add(new Month(11, 2016), 131.8);
        s1.add(new Month(12, 2016), 139.6);
        s1.add(new Month(1, 2017), 142.9);
        s1.add(new Month(2, 2017), 138.7);
        s1.add(new Month(3, 2017), 137.3);
        s1.add(new Month(4, 2017), 143.9);
        s1.add(new Month(5, 2017), 139.8);
        s1.add(new Month(6, 2017), (double)137.0F);
        s1.add(new Month(7, 2017), 132.8);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private static XYDataset createDataset2() {
        TimeSeries s2 = new TimeSeries("Random Data 2");
        s2.add(new Month(2, 2016), 429.6);
        s2.add(new Month(3, 2016), 323.2);
        s2.add(new Month(4, 2016), 417.2);
        s2.add(new Month(5, 2016), 624.1);
        s2.add(new Month(6, 2016), 422.6);
        s2.add(new Month(7, 2016), 619.2);
        s2.add(new Month(8, 2016), (double)416.5F);
        s2.add(new Month(9, 2016), 512.7);
        s2.add(new Month(10, 2016), (double)501.5F);
        s2.add(new Month(11, 2016), 306.1);
        s2.add(new Month(12, 2016), 410.3);
        s2.add(new Month(1, 2017), 511.7);
        s2.add(new Month(2, 2017), (double)611.0F);
        s2.add(new Month(3, 2017), 709.6);
        s2.add(new Month(4, 2017), 613.2);
        s2.add(new Month(5, 2017), 711.6);
        s2.add(new Month(6, 2017), 708.8);
        s2.add(new Month(7, 2017), 501.6);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s2);
        return dataset;
    }

    private static Chart createChart() {
        XYDataset dataset = createDataset1();
        Chart chart = JChart.timeLine("Dual Axis Demo 2", "Date", "Price Per Unit", dataset, false, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairLockedOnData(true);
        NumberAxis axis2 = new NumberAxis("Secondary");
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer)renderer;
            rr.setDefaultShapesVisible(true);
            rr.setDefaultShapesFilled(true);
        }

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0, Color.black);
        renderer2.setDefaultShapesVisible(true);
        renderer2.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        plot.setRenderer(1, renderer2);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        LegendTitle legend1 = new LegendTitle(renderer);
        LegendTitle legend2 = new LegendTitle(renderer2);
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock((double)2000.0F, (double)0.0F));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        DualAxisDemo2 demo = new DualAxisDemo2("Chart: DualAxisDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
