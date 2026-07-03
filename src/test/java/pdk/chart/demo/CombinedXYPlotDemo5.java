package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.annotations.XYTextAnnotation;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.CombinedDomainXYPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CombinedXYPlotDemo5 extends ApplicationFrame implements ChartMouseListener {
    private final ChartPanel chartPanel;

    public CombinedXYPlotDemo5(String title) {
        super(title);
        Chart chart = createCombinedChart();
        this.chartPanel = new ChartPanel(chart);
        this.chartPanel.setPreferredSize(new Dimension(500, 270));
        this.chartPanel.addChartMouseListener(this);
        this.setContentPane(this.chartPanel);
    }

    private static Chart createCombinedChart() {
        XYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new StandardXYItemRenderer();
        NumberAxis rangeAxis1 = new NumberAxis("Range 1");
        XYPlot subplot1 = new XYPlot(data1,  null, rangeAxis1, renderer1);

        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        XYTextAnnotation annotation = new XYTextAnnotation("Hello!",  50.0F,  10000.0F);
        annotation.setFont(new Font("SansSerif", 0, 9));
        annotation.setRotationAngle((Math.PI / 4D));
        subplot1.addAnnotation(annotation);

        XYDataset data2 = createDataset2();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        NumberAxis rangeAxis2 = new NumberAxis("Range 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        NumberAxis sharedAxis = new NumberAxis("Domain");
        sharedAxis.setTickMarkInsideLength(5.0F);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(sharedAxis);
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setGap(10.0F);
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);
        Chart chart = new Chart("CombinedDomainXYPlotDemo5", Chart.DEFAULT_TITLE_FONT, plot, true);
        return chart;
    }

    private static XYDataset createDataset1() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add(10.0, 12353.3);
        series1.add(20.0, 13734.4);
        series1.add(30.0, 14525.3);
        series1.add(40.0, 13984.3);
        series1.add(50.0, 12999.4);
        series1.add(60.0, 14274.3);
        series1.add(70.0, 15943.5F);
        series1.add(80.0, 14845.3);
        series1.add(90.0, 14645.4);
        series1.add(100.0, 16234.6);
        series1.add(110.0, 17232.3);
        series1.add(120.0, 14232.2);
        series1.add(130.0, 13102.2);
        series1.add(140.0, 14230.2);
        series1.add(150.0, 11235.2);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add(10.0, 15000.3);
        series2.add(20.0, 11000.4);
        series2.add(30.0, 17000.3);
        series2.add(40.0, 15000.3);
        series2.add(50.0, 14000.4);
        series2.add(60.0, 12000.3);
        series2.add(70.0, 11000.5);
        series2.add(80.0, 12000.3);
        series2.add(90.0, 13000.4);
        series2.add(100.0, 12000.6);
        series2.add(110.0, 13000.3);
        series2.add(120.0, 17000.2);
        series2.add(130.0, 18000.2);
        series2.add(140.0, 16000.2);
        series2.add(150.0, 17000.2);
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        return collection;
    }

    private static XYDataset createDataset2() {
        XYSeries series2 = new XYSeries("Series 3");
        series2.add(10.0F, 16853.2);
        series2.add(20.0F, 19642.3);
        series2.add(30.0F, 18253.5F);
        series2.add(40.0F, 15352.3);
        series2.add(50.0F, 13532.0F);
        series2.add(100.0F, 12635.3);
        series2.add(110.0F, 13998.2);
        series2.add(120.0F, 11943.2);
        series2.add(130.0F, 16943.9);
        series2.add(140.0F, 17843.2);
        series2.add(150.0F, 16495.3);
        series2.add(160.0F, 17943.6);
        series2.add(170.0F, 18500.7);
        series2.add(180.0F, 19595.9);
        return new XYSeriesCollection(series2);
    }

    static void main() {
        CombinedXYPlotDemo5 demo = new CombinedXYPlotDemo5("CombinedDomainXYPlotDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    public void chartMouseClicked(ChartMouseEvent event) {
        int mx = event.getTrigger().getX();
        int my = event.getTrigger().getY();
        Rectangle2D dataArea = this.chartPanel.getScreenDataArea(mx, my);
        Chart chart = event.getChart();
        CombinedDomainXYPlot plot = (CombinedDomainXYPlot) chart.getPlot();
        XYPlot subplot = plot.findSubplot(this.chartPanel.getChartRenderingInfo().getPlotInfo(), new Point(mx, my));
        if (subplot != null) {
            ValueAxis xAxis = subplot.getDomainAxis();
            ValueAxis yAxis = subplot.getRangeAxis();
            double x = xAxis.java2DToValue(mx, dataArea, RectangleEdge.BOTTOM);
            double y = yAxis.java2DToValue(my, dataArea, RectangleEdge.LEFT);
            System.out.println("You clicked the point (" + x + ", " + y + ")");
        }

    }

    public void chartMouseMoved(ChartMouseEvent cme) {
    }
}
