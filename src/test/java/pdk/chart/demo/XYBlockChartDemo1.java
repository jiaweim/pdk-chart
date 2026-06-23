package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockBorder;
import pdk.chart.data.DomainOrder;
import pdk.chart.data.general.DatasetChangeListener;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.GrayPaintScale;
import pdk.chart.renderer.PaintScale;
import pdk.chart.renderer.xy.XYBlockRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYBlockChartDemo1 extends ApplicationFrame {
    public XYBlockChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin((double) 0.0F);
        xAxis.setUpperMargin((double) 0.0F);
        xAxis.setAxisLinePaint(Color.WHITE);
        xAxis.setTickMarkPaint(Color.WHITE);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin((double) 0.0F);
        yAxis.setUpperMargin((double) 0.0F);
        yAxis.setAxisLinePaint(Color.WHITE);
        yAxis.setTickMarkPaint(Color.WHITE);
        XYBlockRenderer renderer = new XYBlockRenderer();
        PaintScale scale = new GrayPaintScale((double) -2.0F, (double) 1.0F);
        renderer.setPaintScale(scale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets((double) 5.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        plot.setOutlinePaint(Color.BLUE);
        Chart chart = new Chart("XYBlockChartDemo1", plot);
        chart.removeLegend();
        NumberAxis scaleAxis = new NumberAxis("Scale");
        scaleAxis.setAxisLinePaint(Color.WHITE);
        scaleAxis.setTickMarkPaint(Color.WHITE);
        scaleAxis.setTickLabelFont(new Font("Dialog", 0, 7));
        PaintScaleLegend legend = new PaintScaleLegend(new GrayPaintScale(), scaleAxis);
        legend.setStripOutlineVisible(false);
        legend.setSubdivisionCount(20);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setAxisOffset((double) 5.0F);
        legend.setMargin(new RectangleInsets((double) 5.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        legend.setFrame(new BlockBorder(Color.RED));
        legend.setPadding(new RectangleInsets((double) 10.0F, (double) 10.0F, (double) 10.0F, (double) 10.0F));
        legend.setStripWidth((double) 10.0F);
        legend.setPosition(RectangleEdge.LEFT);
        chart.addSubtitle(legend);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYZDataset createDataset() {
        return new XYZDataset() {
            public int getSeriesCount() {
                return 1;
            }

            public int getItemCount(int series) {
                return 10000;
            }

            public Number getX(int series, int item) {
                return this.getXValue(series, item);
            }

            public double getXValue(int series, int item) {
                return (double) (item / 100 - 50);
            }

            public Number getY(int series, int item) {
                return this.getYValue(series, item);
            }

            public double getYValue(int series, int item) {
                return (double) (item - item / 100 * 100 - 50);
            }

            public Number getZ(int series, int item) {
                return this.getZValue(series, item);
            }

            public double getZValue(int series, int item) {
                double x = this.getXValue(series, item);
                double y = this.getYValue(series, item);
                return Math.sin(Math.sqrt(x * x + y * y) / (double) 5.0F);
            }

            public void addChangeListener(DatasetChangeListener listener) {
            }

            public void removeChangeListener(DatasetChangeListener listener) {
            }


            public Comparable getSeriesKey(int series) {
                return "sin(sqrt(x + y))";
            }

            public int indexOf(Comparable seriesKey) {
                return 0;
            }

            public DomainOrder getDomainOrder() {
                return DomainOrder.ASCENDING;
            }
        };
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBlockChartDemo1 demo = new XYBlockChartDemo1("Chart: XYBlockChartDemo1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
