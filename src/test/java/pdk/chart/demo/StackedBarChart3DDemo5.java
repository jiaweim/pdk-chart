package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedBarChart3DDemo5 extends ApplicationFrame {
    private static int CHART_COUNT = 4;

    public StackedBarChart3DDemo5(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static CategoryDataset createDataset(int index) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, "Series 1", "Category 1");
        dataset.addValue((double) 2.0F, "Series 1", "Category 2");
        dataset.addValue((double) 1.5F, "Series 1", "Category 3");
        dataset.addValue((double) 1.5F, "Series 1", "Category 4");
        dataset.addValue((double) -1.0F, "Series 2", "Category 1");
        dataset.addValue(-1.9, "Series 2", "Category 2");
        dataset.addValue((double) -1.5F, "Series 2", "Category 3");
        dataset.addValue((double) -1.5F, "Series 2", "Category 4");
        dataset.addValue((double) 1.0F, "Series 3", "Category 1");
        dataset.addValue(1.9, "Series 3", "Category 2");
        dataset.addValue((double) 1.5F, "Series 3", "Category 3");
        dataset.addValue((double) 1.5F, "Series 3", "Category 4");
        return dataset;
    }

    private static Chart createChart(int index, CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Chart " + (index + 1), "Category", "Value", dataset, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        StackedBarChart3DDemo5 demo = new StackedBarChart3DDemo5("Chart - Stacked Bar Chart 3D Demo 5");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private CategoryDataset[] datasets;
        private Chart[] charts;
        private ChartPanel[] panels;

        public MyDemoPanel() {
            super(new GridLayout(2, 2));
            this.datasets = new CategoryDataset[StackedBarChart3DDemo5.CHART_COUNT];
            this.charts = new Chart[StackedBarChart3DDemo5.CHART_COUNT];
            this.panels = new ChartPanel[StackedBarChart3DDemo5.CHART_COUNT];

            for (int i = 0; i < StackedBarChart3DDemo5.CHART_COUNT; ++i) {
                this.datasets[i] = StackedBarChart3DDemo5.createDataset(i);
                this.charts[i] = StackedBarChart3DDemo5.createChart(i, this.datasets[i]);
                this.addChart(this.charts[i]);
                this.panels[i] = new ChartPanel(this.charts[i], false);
            }

            CategoryPlot plot1 = (CategoryPlot) this.charts[1].getPlot();
            CategoryPlot plot2 = (CategoryPlot) this.charts[2].getPlot();
            CategoryPlot plot3 = (CategoryPlot) this.charts[3].getPlot();
            plot1.getRangeAxis().setInverted(true);
            plot3.getRangeAxis().setInverted(true);
            plot2.setOrientation(PlotOrientation.HORIZONTAL);
            plot3.setOrientation(PlotOrientation.HORIZONTAL);
            this.add(this.panels[0]);
            this.add(this.panels[1]);
            this.add(this.panels[2]);
            this.add(this.panels[3]);
            this.setPreferredSize(new Dimension(800, 600));
        }
    }
}
