package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;
import pdk.chart.api.VerticalAlignment;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class AreaChartDemo1 extends ApplicationFrame {
    public AreaChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, "Series 1", "Type 1");
        dataset.addValue((double) 4.0F, "Series 1", "Type 2");
        dataset.addValue((double) 3.0F, "Series 1", "Type 3");
        dataset.addValue((double) 5.0F, "Series 1", "Type 4");
        dataset.addValue((double) 5.0F, "Series 1", "Type 5");
        dataset.addValue((double) 7.0F, "Series 1", "Type 6");
        dataset.addValue((double) 7.0F, "Series 1", "Type 7");
        dataset.addValue((double) 8.0F, "Series 1", "Type 8");
        dataset.addValue((double) 5.0F, "Series 2", "Type 1");
        dataset.addValue((double) 7.0F, "Series 2", "Type 2");
        dataset.addValue((double) 6.0F, "Series 2", "Type 3");
        dataset.addValue((double) 8.0F, "Series 2", "Type 4");
        dataset.addValue((double) 4.0F, "Series 2", "Type 5");
        dataset.addValue((double) 4.0F, "Series 2", "Type 6");
        dataset.addValue((double) 2.0F, "Series 2", "Type 7");
        dataset.addValue((double) 1.0F, "Series 2", "Type 8");
        dataset.addValue((double) 4.0F, "Series 3", "Type 1");
        dataset.addValue((double) 3.0F, "Series 3", "Type 2");
        dataset.addValue((double) 2.0F, "Series 3", "Type 3");
        dataset.addValue((double) 3.0F, "Series 3", "Type 4");
        dataset.addValue((double) 6.0F, "Series 3", "Type 5");
        dataset.addValue((double) 3.0F, "Series 3", "Type 6");
        dataset.addValue((double) 4.0F, "Series 3", "Type 7");
        dataset.addValue((double) 3.0F, "Series 3", "Type 8");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createAreaChart("Area Chart", "Category", "Value", dataset);
        TextTitle subtitle = new TextTitle("An area chart demonstration.  We use this subtitle as an example of what happens when you get a really long title or subtitle.");
        subtitle.setPosition(RectangleEdge.TOP);
        subtitle.setPadding(new RectangleInsets(UnitType.RELATIVE, 0.05, 0.05, 0.05, 0.05));
        subtitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        chart.addSubtitle(subtitle);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.5F);
        plot.setDomainGridlinesVisible(true);
        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        domainAxis.addCategoryLabelToolTip("Type 1", "The first type.");
        domainAxis.addCategoryLabelToolTip("Type 2", "The second type.");
        domainAxis.addCategoryLabelToolTip("Type 3", "The third type.");
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle((double) 0.0F);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        AreaChartDemo1 demo = new AreaChartDemo1("Chart: AreaChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
