package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.annotations.XYDrawableAnnotation;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.*;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class XYDrawableAnnotationDemo1 extends ApplicationFrame {
    public XYDrawableAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine("XYDrawableAnnotationDemo1", null, "$ million",
                dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setLowerMargin(0.2);
        xAxis.setUpperMargin(0.2);
        xAxis.setStandardTickUnits(createStandardDateTickUnits());
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setLowerMargin(0.2);
        yAxis.setUpperMargin(0.2);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultLinesVisible(true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        renderer.setSeriesShape(1, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        renderer.setSeriesStroke(0, new BasicStroke(3.0F));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                5.0F, new float[]{10.0F, 5.0F}, 0.0F));
        renderer.setSeriesFillPaint(0, Color.WHITE);
        renderer.setSeriesFillPaint(1, Color.WHITE);
        renderer.setUseFillPaint(true);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        renderer.addAnnotation(new XYDrawableAnnotation((double) (new Month(4, 2005)).getFirstMillisecond(),
                600.0, 180.0, 100.0, 3.0F, createPieChart()));
        renderer.addAnnotation(new XYDrawableAnnotation((double) (new Month(9, 2007)).getFirstMillisecond(),
                1250.0, 120.0, 100.0, 2.0F, createBarChart()));
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Division A");
        series1.add(new Year(2005), 1520.0);
        series1.add(new Year(2006), 1132.0);
        series1.add(new Year(2007), 450.0);
        series1.add(new Year(2008), 620.0);
        TimeSeries series2 = new TimeSeries("Division B");
        series2.add(new Year(2005), 1200.0);
        series2.add(new Year(2006), 1300.0);
        series2.add(new Year(2007), 640.0);
        series2.add(new Year(2008), 520.0);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createPieChart() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("Engineering", 43.2);
        dataset.setValue("Research", 13.2);
        dataset.setValue("Advertising", 20.9);
        PiePlot<String> plot = new PiePlot(dataset);
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        plot.setDefaultSectionOutlinePaint(Color.WHITE);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0F));
        plot.setLabelFont(new Font("Dialog", Font.PLAIN, 18));
        plot.setMaximumLabelWidth(0.25);
        Chart chart = new Chart(plot);
        chart.setBackgroundPaint(null);
        chart.removeLegend();
        chart.setPadding(RectangleInsets.ZERO_INSETS);
        return chart;
    }

    private static Chart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0, "R1", "Q1");
        dataset.addValue(7.0, "R1", "Q2");
        dataset.addValue(8.0, "R1", "Q3");
        dataset.addValue(4.0, "R1", "Q4");
        dataset.addValue(10.6, "R2", "Q1");
        dataset.addValue(6.1, "R2", "Q2");
        dataset.addValue(8.5, "R2", "Q3");
        dataset.addValue(4.3, "R2", "Q4");
        Chart chart = JChart.bar("Sales 2008", null, null,
                dataset, PlotOrientation.VERTICAL, false, false, false);
        chart.setBackgroundPaint(null);
        chart.getPlot().setBackgroundPaint(new Color(200, 200, 255, 60));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    private static TickUnitSource createStandardDateTickUnits() {
        TickUnits units = new TickUnits();
        DateFormat df = new SimpleDateFormat("yyyy");
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 1, DateTickUnitType.YEAR, 1, df));
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 2, DateTickUnitType.YEAR, 1, df));
        units.add(new DateTickUnit(DateTickUnitType.YEAR, 5, DateTickUnitType.YEAR, 5, df));
        return units;
    }

    static void main() {
        XYDrawableAnnotationDemo1 demo = new XYDrawableAnnotationDemo1("Chart: XYDrawableAnnotationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
