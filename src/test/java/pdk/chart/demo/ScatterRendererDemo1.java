package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.DefaultMultiValueCategoryDataset;
import pdk.chart.data.statistics.MultiValueCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.ScatterRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class ScatterRendererDemo1 extends ApplicationFrame {
    public ScatterRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static List<Double> listOfValues(double[] values) {
        List<Double> result = new ArrayList();

        for(int i = 0; i < values.length; ++i) {
            result.add(values[i]);
        }

        return result;
    }

    private static MultiValueCategoryDataset createDataset() {
        DefaultMultiValueCategoryDataset dataset = new DefaultMultiValueCategoryDataset();
        dataset.add(listOfValues(new double[]{(double)1.0F, (double)2.0F, (double)3.0F}), "Series 1", "C1");
        dataset.add(listOfValues(new double[]{1.2, 2.2, 3.2}), "Series 1", "C2");
        dataset.add(listOfValues(new double[]{1.4, 2.4, 3.4}), "Series 1", "C3");
        dataset.add(listOfValues(new double[]{(double)1.0F, (double)3.0F}), "Series 2", "C1");
        dataset.add(listOfValues(new double[]{1.2, 3.2}), "Series 2", "C2");
        dataset.add(listOfValues(new double[]{1.4, 3.6}), "Series 2", "C3");
        return dataset;
    }

    private static Chart createChart(MultiValueCategoryDataset dataset) {
        CategoryPlot plot = new CategoryPlot(dataset, new CategoryAxis("Category"), new NumberAxis("Value"), new ScatterRenderer());
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets((double)4.0F, (double)4.0F, (double)4.0F, (double)4.0F));
        Chart chart = new Chart("ScatterRendererDemo1", plot);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        ScatterRendererDemo1 demo = new ScatterRendererDemo1("Chart: ScatterRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
