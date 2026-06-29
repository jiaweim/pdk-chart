package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberTickUnit;
import pdk.chart.axis.TickUnits;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WaterfallChartDemo1 extends ApplicationFrame {
    public WaterfallChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15.76, "Product 1", "Labour");
        dataset.addValue(8.66, "Product 1", "Administration");
        dataset.addValue(4.71, "Product 1", "Marketing");
        dataset.addValue(3.51, "Product 1", "Distribution");
        dataset.addValue(32.64, "Product 1", "Total Expense");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createWaterfallChart("Product Cost Breakdown", "Expense Category", "Cost Per Unit", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        DecimalFormat formatter = new DecimalFormat("##,###");
        formatter.setNegativePrefix("(");
        formatter.setNegativeSuffix(")");
        TickUnits standardUnits = new TickUnits();
        standardUnits.add(new NumberTickUnit((double) 5.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 10.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 20.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 50.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 100.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 200.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 500.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 1000.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 2000.0F, formatter));
        standardUnits.add(new NumberTickUnit((double) 5000.0F, formatter));
        rangeAxis.setStandardTickUnits(standardUnits);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBase((double) 5.0F);
        DecimalFormat labelFormatter = new DecimalFormat("$##,###.00");
        labelFormatter.setNegativePrefix("(");
        labelFormatter.setNegativeSuffix(")");
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", labelFormatter));
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1}) = {2}", new DecimalFormat("$##,###.00")));
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        WaterfallChartDemo1 demo = new WaterfallChartDemo1("Chart: WaterfallChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
