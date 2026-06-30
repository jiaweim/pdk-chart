package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartRenderingInfo;
import pdk.chart.ChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.renderer.xy.DeviationRenderer;
import pdk.chart.renderer.xy.XYBarRenderer;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.Week;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

public class ThumbnailDemo1 extends ApplicationFrame {
    public ThumbnailDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset1() {
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

    private static Chart createChart1(CategoryDataset dataset) {
        Chart chart = JChart.bar("Bar Chart Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((Math.PI / 6D)));
        return chart;
    }

    private static PieDataset createDataset2() {
        DefaultPieDataset<String> result = new DefaultPieDataset();
        result.setValue("Java", 43.2);
        result.setValue("Visual Basic", (double)10.0F);
        result.setValue("C/C++", (double)17.5F);
        result.setValue("PHP", (double)32.5F);
        result.setValue("Perl", (Number)null);
        return result;
    }

    private static CategoryDataset createDataset3() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)81.0F, "Against all torture", "Italy");
        dataset.addValue((double)72.0F, "Against all torture", "Great Britain");
        dataset.addValue((double)58.0F, "Against all torture", "USA");
        dataset.addValue((double)48.0F, "Against all torture", "Israel");
        dataset.addValue((double)43.0F, "Against all torture", "Russia");
        dataset.addValue((double)23.0F, "Against all torture", "India");
        dataset.addValue((double)59.0F, "Against all torture", "Average (*)");
        dataset.addValue((double)5.0F, "-", "Italy");
        dataset.addValue((double)4.0F, "-", "Great Britain");
        dataset.addValue((double)6.0F, "-", "USA");
        dataset.addValue((double)9.0F, "-", "Israel");
        dataset.addValue((double)20.0F, "-", "Russia");
        dataset.addValue((double)45.0F, "-", "India");
        dataset.addValue((double)12.0F, "-", "Average (*)");
        dataset.addValue((double)14.0F, "Some degree permissible", "Italy");
        dataset.addValue((double)24.0F, "Some degree permissible", "Great Britain");
        dataset.addValue((double)36.0F, "Some degree permissible", "USA");
        dataset.addValue((double)43.0F, "Some degree permissible", "Israel");
        dataset.addValue((double)37.0F, "Some degree permissible", "Russia");
        dataset.addValue((double)32.0F, "Some degree permissible", "India");
        dataset.addValue((double)29.0F, "Some degree permissible", "Average (*)");
        return dataset;
    }

    private static Chart createChart3(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Public Opinion : Torture of Prisoners", "Country", "%", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.getTitle().setMargin((double)2.0F, (double)0.0F, (double)0.0F, (double)0.0F);
        TextTitle tt = new TextTitle("Source: http://news.bbc.co.uk/1/hi/world/6063386.stm", new Font("Dialog", 0, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin((double)0.0F, (double)0.0F, (double)4.0F, (double)4.0F);
        chart.addSubtitle(tt);
        TextTitle t = new TextTitle("(*) Across 27,000 respondents in 25 countries", new Font("Dialog", 0, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin((double)4.0F, (double)0.0F, (double)2.0F, (double)4.0F);
        chart.addSubtitle(t);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", (String)null, (String)null, (String)null, new Rectangle2D.Double((double)-6.0F, (double)-3.0F, (double)12.0F, (double)6.0F), Color.GREEN));
        items.add(new LegendItem("Some degree permissible", (String)null, (String)null, (String)null, new Rectangle2D.Double((double)-6.0F, (double)-3.0F, (double)12.0F, (double)6.0F), Color.RED));
        plot.setFixedLegendItems(items);
        plot.setInsets(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)20.0F));
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin((double)0.0F);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        Paint gp1 = new Color(0, 0, 0, 0);
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        return chart;
    }

    private static XYDataset createDataset4() {
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        RegularTimePeriod t = new Week();
        double y1 = (double)100.0F;
        double y2 = (double)100.0F;

        for(int i = 0; i <= 52; ++i) {
            double dev1 = 0.05 * (double)i;
            series1.add((double)t.getFirstMillisecond(), y1, y1 - dev1, y1 + dev1);
            y1 = y1 + Math.random() - 0.45;
            double dev2 = 0.07 * (double)i;
            series2.add((double)t.getFirstMillisecond(), y2, y2 - dev2, y2 + dev2);
            y2 = y2 + Math.random() - 0.55;
            t = t.next();
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createChart4(XYDataset dataset) {
        Chart chart = JChart.timeLine("Projected Values - Test", "Date", "Index Projection", dataset, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setInsets(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)20.0F));
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setAxisOffset(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)5.0F));
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);
        NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    private static IntervalXYDataset createDataset5() {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[1000];
        Random generator = new Random(12345678L);

        for(int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + (double)5.0F;
        }

        dataset.addSeries("H1", values, 100, (double)2.0F, (double)8.0F);
        values = new double[1000];

        for(int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + (double)7.0F;
        }

        dataset.addSeries("H2", values, 100, (double)4.0F, (double)10.0F);
        return dataset;
    }

    private static Chart createChart5(IntervalXYDataset dataset) {
        Chart chart = JChart.histogram("Histogram Demo 1", (String)null, (String)null, dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setForegroundAlpha(0.85F);
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    private static CategoryDataset createDataset6() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)212.0F, "Classes", "JDK 1.0");
        dataset.addValue((double)504.0F, "Classes", "JDK 1.1");
        dataset.addValue((double)1520.0F, "Classes", "SDK 1.2");
        dataset.addValue((double)1842.0F, "Classes", "SDK 1.3");
        dataset.addValue((double)2991.0F, "Classes", "SDK 1.4");
        return dataset;
    }

    private static Chart createChart6(CategoryDataset dataset) {
        Chart chart = JChart.line("Java Standard Class Library", "Release", "Class Count", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle("Source: Java In A Nutshell (4th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.WHITE);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.15);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 3));
        Chart chart1 = createChart1(createDataset1());
        ChartUtils.applyCurrentTheme(chart1);
        BufferedImage thumb1 = chart1.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
        ImageIcon image1 = new ImageIcon(thumb1);
        mainPanel.add(new JButton(image1));
//        Chart chart2 = createChart2(createDataset2());
//        ChartUtils.applyCurrentTheme(chart2);
//        BufferedImage thumb2 = chart2.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
//        ImageIcon image2 = new ImageIcon(thumb2);
//        mainPanel.add(new JButton(image2));
        Chart chart3 = createChart3(createDataset3());
        ChartUtils.applyCurrentTheme(chart3);
        BufferedImage thumb3 = chart3.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
        ImageIcon image3 = new ImageIcon(thumb3);
        mainPanel.add(new JButton(image3));
        Chart chart4 = createChart4(createDataset4());
        ChartUtils.applyCurrentTheme(chart4);
        BufferedImage thumb4 = chart4.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
        ImageIcon image4 = new ImageIcon(thumb4);
        mainPanel.add(new JButton(image4));
        Chart chart5 = createChart5(createDataset5());
        ChartUtils.applyCurrentTheme(chart5);
        BufferedImage thumb5 = chart5.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
        ImageIcon image5 = new ImageIcon(thumb5);
        mainPanel.add(new JButton(image5));
        Chart chart6 = createChart6(createDataset6());
        ChartUtils.applyCurrentTheme(chart6);
        BufferedImage thumb6 = chart6.createBufferedImage(120, 80, (double)360.0F, (double)240.0F, (ChartRenderingInfo)null);
        ImageIcon image6 = new ImageIcon(thumb6);
        mainPanel.add(new JButton(image6));
        return mainPanel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ThumbnailDemo1 demo = new ThumbnailDemo1("Chart: ThumbnailDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
