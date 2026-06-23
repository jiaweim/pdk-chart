package pdk.chart.demo;

import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.EmptyBlock;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.CompositeTitle;


public class DualAxisDemo1 extends ApplicationFrame {
    public DualAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset1() {
        String series1 = "S1";
        String series2 = "S2";
        String series3 = "S3";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        String category6 = "Category 6";
        String category7 = "Category 7";
        String category8 = "Category 8";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)1.0F, series1, category1);
        dataset.addValue((double)4.0F, series1, category2);
        dataset.addValue((double)3.0F, series1, category3);
        dataset.addValue((double)5.0F, series1, category4);
        dataset.addValue((double)5.0F, series1, category5);
        dataset.addValue((double)7.0F, series1, category6);
        dataset.addValue((double)7.0F, series1, category7);
        dataset.addValue((double)8.0F, series1, category8);
        dataset.addValue((double)5.0F, series2, category1);
        dataset.addValue((double)7.0F, series2, category2);
        dataset.addValue((double)6.0F, series2, category3);
        dataset.addValue((double)8.0F, series2, category4);
        dataset.addValue((double)4.0F, series2, category5);
        dataset.addValue((double)4.0F, series2, category6);
        dataset.addValue((double)2.0F, series2, category7);
        dataset.addValue((double)1.0F, series2, category8);
        dataset.addValue((double)4.0F, series3, category1);
        dataset.addValue((double)3.0F, series3, category2);
        dataset.addValue((double)2.0F, series3, category3);
        dataset.addValue((double)3.0F, series3, category4);
        dataset.addValue((double)6.0F, series3, category5);
        dataset.addValue((double)3.0F, series3, category6);
        dataset.addValue((double)4.0F, series3, category7);
        dataset.addValue((double)3.0F, series3, category8);
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        String series1 = "S4";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        String category6 = "Category 6";
        String category7 = "Category 7";
        String category8 = "Category 8";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)15.0F, series1, category1);
        dataset.addValue((double)24.0F, series1, category2);
        dataset.addValue((double)31.0F, series1, category3);
        dataset.addValue((double)25.0F, series1, category4);
        dataset.addValue((double)56.0F, series1, category5);
        dataset.addValue((double)37.0F, series1, category6);
        dataset.addValue((double)77.0F, series1, category7);
        dataset.addValue((double)18.0F, series1, category8);
        return dataset;
    }

    private static Chart createChart() {
        Chart chart = ChartFactory.createBarChart("DualAxisDemo1", "Category", "Value", createDataset1());
        chart.removeLegend();
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        CategoryDataset dataset2 = createDataset2();
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        LegendTitle legend1 = new LegendTitle(plot.getRenderer(0));
        LegendTitle legend2 = new LegendTitle(plot.getRenderer(1));
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock((double)2000.0F, (double)0.0F));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        DualAxisDemo1 demo = new DualAxisDemo1("Chart: DualAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
