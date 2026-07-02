package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;
import pdk.chart.api.VerticalAlignment;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.AreaRendererEndType;
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

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{
                "Type 1", "Type 2", "Type 3", "Type 4",
                "Type 5", "Type 6", "Type 7", "Type 8"
        };

        return Data.<String, String>category()
                .addSeries("Series 1", categories,
                        new double[]{1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0})
                .addSeries("Series 2", categories,
                        new double[]{5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0})
                .addSeries("Series 3", categories,
                        new double[]{4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0})
                .build();
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.area("Area Chart", "Category", "Value", dataset);

        TextTitle subtitle = new TextTitle("An area chart demonstration.  We use this subtitle as an example of what happens when you get a really long title or subtitle.");
        subtitle.setPosition(RectangleEdge.TOP);
        subtitle.setPadding(new RectangleInsets(UnitType.RELATIVE, 0.05, 0.05, 0.05, 0.05));
        subtitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        chart.addSubtitle(subtitle);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5F);
        plot.setDomainGridlinesVisible(true);

        plot.getAreaRenderer(0)
                .endType(AreaRendererEndType.LEVEL);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.categoryLabelPositions(CategoryLabelPositions.UP_45)
                .lowerMargin(0.0)
                .upperMargin(0.0);
        domainAxis.addCategoryLabelToolTip("Type 1", "The first type.");
        domainAxis.addCategoryLabelToolTip("Type 2", "The second type.");
        domainAxis.addCategoryLabelToolTip("Type 3", "The third type.");

        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .labelAngle(0.0);

        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        AreaChartDemo1 demo = new AreaChartDemo1("Chart: AreaChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
