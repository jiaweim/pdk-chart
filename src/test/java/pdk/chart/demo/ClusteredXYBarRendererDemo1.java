package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.ClusteredXYBarRenderer;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

public class ClusteredXYBarRendererDemo1 extends ApplicationFrame {
    public ClusteredXYBarRendererDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static Chart createChart(String title, IntervalXYDataset dataset) {
        Chart chart = ChartFactory.createXYBarChart(title, (String)null, true, "Y", dataset);
        XYPlot plot = (XYPlot)chart.getPlot();
        ClusteredXYBarRenderer r = new ClusteredXYBarRenderer(0.2, false);
        plot.setRenderer(r);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, 1, 2003), 54.3);
        series1.add(new Day(2, 1, 2003), 20.3);
        series1.add(new Day(3, 1, 2003), 43.4);
        series1.add(new Day(4, 1, 2003), (double)-12.0F);
        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Day(1, 1, 2003), (double)8.0F);
        series2.add(new Day(2, 1, 2003), (double)16.0F);
        series2.add(new Day(3, 1, 2003), (double)21.0F);
        series2.add(new Day(4, 1, 2003), (double)5.0F);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800, 600));
        IntervalXYDataset dataset = createDataset();
        Chart chart1 = createChart("Vertical", dataset);
        XYPlot plot1 = (XYPlot)chart1.getPlot();
        XYBarRenderer renderer1 = (XYBarRenderer)plot1.getRenderer();
        renderer1.setDrawBarOutline(false);
        renderer1.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer1.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);
        Chart chart2 = createChart("Vertical / Inverted Axis", dataset);
        XYPlot plot2 = (XYPlot)chart2.getPlot();
        XYBarRenderer renderer2 = (XYBarRenderer)plot2.getRenderer();
        renderer2.setDrawBarOutline(false);
        renderer2.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer2.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer2.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        plot2.getDomainAxis().setInverted(true);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);
        Chart chart3 = createChart("Horizontal", dataset);
        XYPlot plot3 = (XYPlot)chart3.getPlot();
        plot3.setOrientation(PlotOrientation.HORIZONTAL);
        XYBarRenderer renderer3 = (XYBarRenderer)plot3.getRenderer();
        renderer3.setDrawBarOutline(false);
        renderer3.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer3.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer3.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);
        Chart chart4 = createChart("Horizontal / Inverted Axis", dataset);
        XYPlot plot4 = (XYPlot)chart4.getPlot();
        plot4.setOrientation(PlotOrientation.HORIZONTAL);
        XYBarRenderer renderer4 = (XYBarRenderer)plot4.getRenderer();
        renderer4.setDrawBarOutline(false);
        renderer4.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer4.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer4.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        plot4.getDomainAxis().setInverted(true);
        ChartPanel chartPanel4 = new ChartPanel(chart4);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    public static void main(String[] args) {
        ClusteredXYBarRendererDemo1 demo = new ClusteredXYBarRendererDemo1("Chart: ClusteredXYBarRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
