package pdk.chart.examples;

import pdk.chart.axis.NumberTickUnit;
import pdk.chart.axis.TickUnits;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;

import java.text.DecimalFormat;

public class WaterfallChartDemo1 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(15.76, "Product 1", "Labour");
        dataset.addValue(8.66, "Product 1", "Administration");
        dataset.addValue(4.71, "Product 1", "Marketing");
        dataset.addValue(3.51, "Product 1", "Distribution");
        dataset.addValue(32.64, "Product 1", "Total Expense");
        return dataset;
    }

    static void main() {

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

        DecimalFormat labelFormatter = new DecimalFormat("$##,###.00");
        labelFormatter.setNegativePrefix("(");
        labelFormatter.setNegativeSuffix(")");

        CategoryXYChart.create()
                .orientation(PlotOrientation.VERTICAL)
                .title("Product Cost Breakdown")
                .axisNames("Expense Category", "Cost Per Unit")
                .dataset(createDataset(), CategoryXYChartType.BAR_WATERFALL)

                .barRenderer(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .base(5)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>("{2}", labelFormatter))
                .defaultToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1}) = {2}", new DecimalFormat("$##,###.00")))
                .defaultItemLabelsVisible(true)
                .done()

                .rangeAxis()
                .standardTickUnits(standardUnits)
                .doneCategory()

                .show(500, 270);
    }
}
