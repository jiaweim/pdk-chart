package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.SpiderWebPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;

public class SpiderWebChartDemo1 extends ApplicationFrame {
    public SpiderWebChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)1.0F, series1, category1);
        dataset.addValue((double)4.0F, series1, category2);
        dataset.addValue((double)3.0F, series1, category3);
        dataset.addValue((double)5.0F, series1, category4);
        dataset.addValue((double)5.0F, series1, category5);
        dataset.addValue((double)5.0F, series2, category1);
        dataset.addValue((double)7.0F, series2, category2);
        dataset.addValue((double)6.0F, series2, category3);
        dataset.addValue((double)8.0F, series2, category4);
        dataset.addValue((double)4.0F, series2, category5);
        dataset.addValue((double)4.0F, series3, category1);
        dataset.addValue((double)3.0F, series3, category2);
        dataset.addValue((double)2.0F, series3, category3);
        dataset.addValue((double)3.0F, series3, category4);
        dataset.addValue((double)6.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        plot.setStartAngle((double)54.0F);
        plot.setInteriorGap(0.4);
        plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        Chart chart = new Chart("Spider Web Chart Demo 1", TextTitle.DEFAULT_FONT, plot, false);
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        SpiderWebChartDemo1 demo = new SpiderWebChartDemo1("Chart: SpiderWebChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
