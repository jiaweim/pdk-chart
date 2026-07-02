package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.EmptyBlock;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.CategoryChartType;
import pdk.chart.Data;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.CompositeTitle;

import javax.swing.*;


public class DualAxisDemo1 extends ApplicationFrame {

    public DualAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset1() {
        String[] categories = new String[]{
                "Category 1",
                "Category 2",
                "Category 3",
                "Category 4",
                "Category 5",
                "Category 6",
                "Category 7",
                "Category 8"
        };

        return Data.<String, String>category()
                .addSeries("S1",
                        categories,
                        new double[]{1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0})
                .addSeries("S2",
                        categories,
                        new double[]{5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0})
                .addSeries("S3",
                        categories,
                        new double[]{4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0,}).build();
    }

    private static CategoryDataset<String, String> createDataset2() {
        return Data.createCategory("S4",
                new String[]{"Category 1",
                        "Category 2",
                        "Category 3",
                        "Category 4",
                        "Category 5",
                        "Category 6",
                        "Category 7",
                        "Category 8"},
                new double[]{15.0, 24.0, 31.0, 25.0, 56.0, 37.0, 77.0, 18.0}
        );
    }

    private static Chart createChart() {
        Chart chart = JChart.bar("DualAxisDemo1", "Category", "Value",
                createDataset1());
        chart.removeLegend();

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setDataset(1, createDataset2(), CategoryChartType.LINE);
        plot.setRangeAxis(1, new NumberAxis("Secondary"));

        plot.mapDatasetToRangeAxis(1, 1);
        plot.getDomainAxis().categoryLabelPositions(CategoryLabelPositions.DOWN_45);

        plot.getLineAndShapeRenderer(1)
                .setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        LegendTitle legend1 = new LegendTitle(plot.getRenderer(0));
        LegendTitle legend2 = new LegendTitle(plot.getRenderer(1));
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock(2000.0, 0.0));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    static void main() {
        DualAxisDemo1 demo = new DualAxisDemo1("DualAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
