package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.IntervalBarRenderer;
import pdk.chart.data.category.DefaultIntervalCategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class IntervalBarChartDemo1 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    public IntervalBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalCategoryDataset createDataset() {
        double[] starts_S1 = new double[]{0.1, 0.2, 0.3};
        double[] starts_S2 = new double[]{0.3, 0.4, (double)0.5F};
        double[] ends_S1 = new double[]{(double)0.5F, 0.6, 0.7};
        double[] ends_S2 = new double[]{0.7, 0.8, 0.9};
        double[][] starts = new double[][]{starts_S1, starts_S2};
        double[][] ends = new double[][]{ends_S1, ends_S2};
        DefaultIntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(starts, ends);
        return dataset;
    }

    private static Chart createChart(IntervalCategoryDataset dataset) {
        CategoryAxis domainAxis = new CategoryAxis("Category");
        NumberAxis rangeAxis = new NumberAxis("Percentage");
        rangeAxis.setNumberFormatOverride(new DecimalFormat("0.00%"));
        IntervalBarRenderer renderer = new IntervalBarRenderer();
        CategoryPlot plot = new CategoryPlot(dataset, domainAxis, rangeAxis, renderer);
        Chart chart = new Chart("IntervalBarChartDemo1", plot);
        plot.setDomainGridlinesVisible(true);
        plot.setRangePannable(true);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        JFrame demo = new IntervalBarChartDemo1("Chart: IntervalBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
