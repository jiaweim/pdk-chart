package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.annotations.XYDrawableAnnotation;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickUnit;
import pdk.chart.axis.DateTickUnitType;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.TickUnitSource;
import pdk.chart.axis.TickUnits;
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


public class XYDrawableAnnotationDemo1 extends ApplicationFrame {
    public XYDrawableAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createTimeSeriesChart("XYDrawableAnnotationDemo1", (String)null, "$ million", dataset, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        DateAxis xAxis = (DateAxis)plot.getDomainAxis();
        xAxis.setLowerMargin(0.2);
        xAxis.setUpperMargin(0.2);
        xAxis.setStandardTickUnits(createStandardDateTickUnits());
        NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
        yAxis.setLowerMargin(0.2);
        yAxis.setUpperMargin(0.2);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultLinesVisible(true);
        renderer.setSeriesShape(0, new Ellipse2D.Double((double)-5.0F, (double)-5.0F, (double)10.0F, (double)10.0F));
        renderer.setSeriesShape(1, new Ellipse2D.Double((double)-5.0F, (double)-5.0F, (double)10.0F, (double)10.0F));
        renderer.setSeriesStroke(0, new BasicStroke(3.0F));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, 1, 1, 5.0F, new float[]{10.0F, 5.0F}, 0.0F));
        renderer.setSeriesFillPaint(0, Color.WHITE);
        renderer.setSeriesFillPaint(1, Color.WHITE);
        renderer.setUseFillPaint(true);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        renderer.addAnnotation(new XYDrawableAnnotation((double)(new Month(4, 2005)).getFirstMillisecond(), (double)600.0F, (double)180.0F, (double)100.0F, (double)3.0F, createPieChart()));
        renderer.addAnnotation(new XYDrawableAnnotation((double)(new Month(9, 2007)).getFirstMillisecond(), (double)1250.0F, (double)120.0F, (double)100.0F, (double)2.0F, createBarChart()));
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Division A");
        series1.add(new Year(2005), (double)1520.0F);
        series1.add(new Year(2006), (double)1132.0F);
        series1.add(new Year(2007), (double)450.0F);
        series1.add(new Year(2008), (double)620.0F);
        TimeSeries series2 = new TimeSeries("Division B");
        series2.add(new Year(2005), (double)1200.0F);
        series2.add(new Year(2006), (double)1300.0F);
        series2.add(new Year(2007), (double)640.0F);
        series2.add(new Year(2008), (double)520.0F);
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
        plot.setBackgroundPaint((Paint)null);
        plot.setOutlinePaint((Paint)null);
        plot.setDefaultSectionOutlinePaint(Color.WHITE);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0F));
        plot.setLabelFont(new Font("Dialog", 0, 18));
        plot.setMaximumLabelWidth((double)0.25F);
        Chart chart = new Chart(plot);
        chart.setBackgroundPaint((Paint)null);
        chart.removeLegend();
        chart.setPadding(RectangleInsets.ZERO_INSETS);
        return chart;
    }

    private static Chart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)10.0F, "R1", "Q1");
        dataset.addValue((double)7.0F, "R1", "Q2");
        dataset.addValue((double)8.0F, "R1", "Q3");
        dataset.addValue((double)4.0F, "R1", "Q4");
        dataset.addValue(10.6, "R2", "Q1");
        dataset.addValue(6.1, "R2", "Q2");
        dataset.addValue((double)8.5F, "R2", "Q3");
        dataset.addValue(4.3, "R2", "Q4");
        Chart chart = ChartFactory.createBarChart("Sales 2008", (String)null, (String)null, dataset, PlotOrientation.VERTICAL, false, false, false);
        chart.setBackgroundPaint((Paint)null);
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

    public static void main(String[] args) {
        XYDrawableAnnotationDemo1 demo = new XYDrawableAnnotationDemo1("Chart: XYDrawableAnnotationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
