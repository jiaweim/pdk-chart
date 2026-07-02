package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.*;

import javax.swing.*;
import java.awt.*;

public class OverlaidBarChartDemo1 extends ApplicationFrame {
    public OverlaidBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static Chart createChart() {
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue((double) 1.0F, "S1", "Category 1");
        dataset1.addValue((double) 4.0F, "S1", "Category 2");
        dataset1.addValue((double) 3.0F, "S1", "Category 3");
        dataset1.addValue((double) 5.0F, "S1", "Category 4");
        dataset1.addValue((double) 5.0F, "S1", "Category 5");
        dataset1.addValue((double) 7.0F, "S1", "Category 6");
        dataset1.addValue((double) 7.0F, "S1", "Category 7");
        dataset1.addValue((double) 8.0F, "S1", "Category 8");
        dataset1.addValue((double) 5.0F, "S2", "Category 1");
        dataset1.addValue((double) 7.0F, "S2", "Category 2");
        dataset1.addValue((double) 6.0F, "S2", "Category 3");
        dataset1.addValue((double) 8.0F, "S2", "Category 4");
        dataset1.addValue((double) 4.0F, "S2", "Category 5");
        dataset1.addValue((double) 4.0F, "S2", "Category 6");
        dataset1.addValue((double) 2.0F, "S2", "Category 7");
        dataset1.addValue((double) 1.0F, "S2", "Category 8");
        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        CategoryItemRenderer renderer = new BarRenderer();
        renderer.setDefaultItemLabelGenerator(generator);
        renderer.setDefaultItemLabelsVisible(true);
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        plot.setDomainAxis(new CategoryAxis("Category"));
        plot.setRangeAxis(new NumberAxis("Value"));
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue((double) 9.0F, "T1", "Category 1");
        dataset2.addValue((double) 7.0F, "T1", "Category 2");
        dataset2.addValue((double) 2.0F, "T1", "Category 3");
        dataset2.addValue((double) 6.0F, "T1", "Category 4");
        dataset2.addValue((double) 6.0F, "T1", "Category 5");
        dataset2.addValue((double) 9.0F, "T1", "Category 6");
        dataset2.addValue((double) 5.0F, "T1", "Category 7");
        dataset2.addValue((double) 4.0F, "T1", "Category 8");
        CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        ValueAxis rangeAxis2 = new NumberAxis("Axis 2");
        plot.setRangeAxis(1, rangeAxis2);
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        dataset3.addValue((double) 94.0F, "R1", "Category 1");
        dataset3.addValue((double) 75.0F, "R1", "Category 2");
        dataset3.addValue((double) 22.0F, "R1", "Category 3");
        dataset3.addValue((double) 74.0F, "R1", "Category 4");
        dataset3.addValue((double) 83.0F, "R1", "Category 5");
        dataset3.addValue((double) 9.0F, "R1", "Category 6");
        dataset3.addValue((double) 23.0F, "R1", "Category 7");
        dataset3.addValue((double) 98.0F, "R1", "Category 8");
        plot.setDataset(2, dataset3);
        CategoryItemRenderer renderer3 = new LineAndShapeRenderer();
        plot.setRenderer(2, renderer3);
        plot.mapDatasetToRangeAxis(2, 1);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        Chart chart = new Chart(plot);
        chart.setTitle("Overlaid Bar Chart");
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.addChartMouseListener(new ChartMouseListener() {
            public void chartMouseClicked(ChartMouseEvent event) {
                System.out.println(event.getEntity());
            }

            public void chartMouseMoved(ChartMouseEvent event) {
            }
        });
        return panel;
    }

    static void main(String[] args) {
        OverlaidBarChartDemo1 demo = new OverlaidBarChartDemo1("JFreeChart: OverlaidBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
