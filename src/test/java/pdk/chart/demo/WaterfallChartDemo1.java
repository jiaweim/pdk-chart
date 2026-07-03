package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.axis.NumberTickUnit;
import pdk.chart.axis.TickUnits;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
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

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Product 1",
                new String[]{"Labour", "Administration", "Marketing", "Distribution", "Total Expense"},
                new double[]{15.76, 8.66, 4.71, 3.51, 32.64});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.waterfall(dataset, "Expense Category", "Cost Per Unit",
                "Product Cost Breakdown", PlotOrientation.VERTICAL, false, true);
        CategoryPlot plot = chart.getCategoryPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        DecimalFormat formatter = new DecimalFormat("##,###");
        formatter.setNegativePrefix("(");
        formatter.setNegativeSuffix(")");
        TickUnits standardUnits = new TickUnits();
        standardUnits.add(new NumberTickUnit(5.0, formatter));
        standardUnits.add(new NumberTickUnit(10.0, formatter));
        standardUnits.add(new NumberTickUnit(20.0, formatter));
        standardUnits.add(new NumberTickUnit(50.0, formatter));
        standardUnits.add(new NumberTickUnit(100.0, formatter));
        standardUnits.add(new NumberTickUnit(200.0, formatter));
        standardUnits.add(new NumberTickUnit(500.0, formatter));
        standardUnits.add(new NumberTickUnit(1000.0, formatter));
        standardUnits.add(new NumberTickUnit(2000.0, formatter));
        standardUnits.add(new NumberTickUnit(5000.0, formatter));
        rangeAxis.setStandardTickUnits(standardUnits);

        BarRenderer renderer = plot.getBarRenderer(0);
        renderer.setDrawBarOutline(false);
        renderer.setBase(5.0);
        DecimalFormat labelFormatter = new DecimalFormat("$##,###.00");
        labelFormatter.setNegativePrefix("(");
        labelFormatter.setNegativeSuffix(")");
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>("{2}", labelFormatter));
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>("{0}, {1}) = {2}", new DecimalFormat("$##,###.00")));
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        WaterfallChartDemo1 demo = new WaterfallChartDemo1("Chart: WaterfallChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
