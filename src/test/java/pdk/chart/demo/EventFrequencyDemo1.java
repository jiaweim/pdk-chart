package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.time.Day;
import pdk.chart.labels.CategoryToolTipGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;

public class EventFrequencyDemo1 extends ApplicationFrame {
    public EventFrequencyDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Event Frequency Demo", "Category", "Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0F);
        plot.setRangeAxis(new DateAxis("Date"));
        CategoryToolTipGenerator toolTipGenerator = new StandardCategoryToolTipGenerator("{0}, {1} : {2}", DateFormat.getDateInstance());
        CategoryItemRenderer renderer = new LineAndShapeRenderer(false, true);
        renderer.setDefaultToolTipGenerator(toolTipGenerator);
        plot.setRenderer(renderer);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Day d1 = new Day(12, 6, 2002);
        Day d2 = new Day(14, 6, 2002);
        Day d3 = new Day(15, 6, 2002);
        Day d4 = new Day(10, 7, 2002);
        Day d5 = new Day(20, 7, 2002);
        Day d6 = new Day(22, 8, 2002);
        dataset.setValue((double) d1.getMiddleMillisecond(), "Series 1", "Requirement 1");
        dataset.setValue((double) d1.getMiddleMillisecond(), "Series 1", "Requirement 2");
        dataset.setValue((double) d2.getMiddleMillisecond(), "Series 1", "Requirement 3");
        dataset.setValue((double) d3.getMiddleMillisecond(), "Series 2", "Requirement 1");
        dataset.setValue((double) d4.getMiddleMillisecond(), "Series 2", "Requirement 3");
        dataset.setValue((double) d5.getMiddleMillisecond(), "Series 3", "Requirement 2");
        dataset.setValue((double) d6.getMiddleMillisecond(), "Series 1", "Requirement 4");
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        EventFrequencyDemo1 demo = new EventFrequencyDemo1("Chart: EventFrequencyDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
