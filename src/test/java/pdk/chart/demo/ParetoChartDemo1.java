package pdk.chart.demo;

import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.DataUtils;
import pdk.chart.data.DefaultKeyedValues;
import pdk.chart.data.KeyedValues;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;

public class ParetoChartDemo1 extends ApplicationFrame {
    public ParetoChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(550, 270));
        this.setContentPane(chartPanel);
    }

    public static Chart createChart(CategoryDataset[] datasets) {
        Chart chart = ChartFactory.bar("TIOBE Index of Programming Languages", (String)null, "Index Value", datasets[0]);
        chart.addSubtitle(new TextTitle("As at August 2013"));
        chart.removeLegend();
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.02);
        domainAxis.setUpperMargin(0.02);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        NumberAxis axis2 = new NumberAxis("Percent");
        axis2.setNumberFormatOverride(NumberFormat.getPercentInstance());
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, datasets[1]);
        plot.setRenderer(1, renderer2);
        plot.mapDatasetToRangeAxis(1, 1);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        ChartUtils.applyCurrentTheme(chart);
        TextTitle source = new TextTitle("http://www.tiobe.com/index.php/content/paperinfo/tpci/index.html", new Font("Monospaced", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        return chart;
    }

    public static CategoryDataset[] createDatasets() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("C", 15.974);
        data.addValue("C++", 9.371);
        data.addValue("C#", 6.117);
        data.addValue("Java", 15.978);
        data.addValue("Javascript", 2.093);
        data.addValue("Obj-C", 8.082);
        data.addValue("PHP", 6.694);
        data.addValue("Python", 3.603);
        data.addValue("Ruby", 2.067);
        data.addValue("VB", 3.873);
        data.sortByValues(SortOrder.DESCENDING);
        KeyedValues cumulative = DataUtils.getCumulativePercentages(data);
        CategoryDataset dataset = DatasetUtils.createCategoryDataset("Languages", data);
        CategoryDataset dataset2 = DatasetUtils.createCategoryDataset("Cumulative", cumulative);
        return new CategoryDataset[]{dataset, dataset2};
    }

    public static JPanel createDemoPanel() {
        CategoryDataset[] datasets = createDatasets();
        Chart chart = createChart(datasets);
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        ParetoChartDemo1 demo = new ParetoChartDemo1("Chart: ParetoChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
