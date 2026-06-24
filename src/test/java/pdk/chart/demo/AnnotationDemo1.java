package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.annotations.XYTextAnnotation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnnotationDemo1 extends ApplicationFrame {
    public AnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(360, 500));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        XYSeriesCollection dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private static XYSeriesCollection createDataset() {
        XYSeriesCollection result = new XYSeriesCollection();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(AnnotationDemo1.class.getResourceAsStream("wtageinf.txt")));
            String data = in.readLine();
            data = in.readLine();
            data = in.readLine();
            data = in.readLine();
            XYSeries s3 = new XYSeries("P3", true, false);
            XYSeries s5 = new XYSeries("P5", true, false);
            XYSeries s10 = new XYSeries("P10", true, false);
            XYSeries s25 = new XYSeries("P25", true, false);
            XYSeries s50 = new XYSeries("P50", true, false);
            XYSeries s75 = new XYSeries("P75", true, false);
            XYSeries s90 = new XYSeries("P90", true, false);
            XYSeries s95 = new XYSeries("P95", true, false);
            XYSeries s97 = new XYSeries("P97", true, false);

            for (String var28 = in.readLine(); var28 != null; var28 = in.readLine()) {
                int sex = Integer.parseInt(var28.substring(1, 8).trim());
                float age = Float.parseFloat(var28.substring(9, 17).trim());
                float p3 = Float.parseFloat(var28.substring(69, 86).trim());
                float p5 = Float.parseFloat(var28.substring(87, 103).trim());
                float p10 = Float.parseFloat(var28.substring(104, 122).trim());
                float p25 = Float.parseFloat(var28.substring(123, 140).trim());
                float p50 = Float.parseFloat(var28.substring(141, 158).trim());
                float p75 = Float.parseFloat(var28.substring(159, 176).trim());
                float p90 = Float.parseFloat(var28.substring(177, 193).trim());
                float p95 = Float.parseFloat(var28.substring(194, 212).trim());
                float p97 = Float.parseFloat(var28.substring(212, var28.length()).trim());
                if (sex == 1) {
                    s3.add((double) age, (double) p3);
                    s5.add((double) age, (double) p5);
                    s10.add((double) age, (double) p10);
                    s25.add((double) age, (double) p25);
                    s50.add((double) age, (double) p50);
                    s75.add((double) age, (double) p75);
                    s90.add((double) age, (double) p90);
                    s95.add((double) age, (double) p95);
                    s97.add((double) age, (double) p97);
                }
            }

            result.addSeries(s3);
            result.addSeries(s5);
            result.addSeries(s10);
            result.addSeries(s25);
            result.addSeries(s50);
            result.addSeries(s75);
            result.addSeries(s90);
            result.addSeries(s95);
            result.addSeries(s97);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }

        return result;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createXYLineChart((String) null, "Age in Months", "Weight (kg)", dataset);
        TextTitle t1 = new TextTitle("Growth Charts: United States", new Font("SansSerif", 1, 14));
        TextTitle t2 = new TextTitle("Weight-for-age percentiles: boys, birth to 36 months", new Font("SansSerif", 0, 11));
        chart.addSubtitle(t1);
        chart.addSubtitle(t2);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setUpperMargin(0.12);
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        Font font = new Font("SansSerif", 0, 9);
        XYTextAnnotation annotation = new XYTextAnnotation("3rd", (double) 36.5F, 11.76);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("5th", (double) 36.5F, 12.04);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("10th", (double) 36.5F, 12.493);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("25th", (double) 36.5F, 13.313);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("50th", (double) 36.5F, 14.33);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("75th", (double) 36.5F, 15.478);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("90th", (double) 36.5F, 16.642);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("95th", (double) 36.5F, 17.408);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        annotation = new XYTextAnnotation("97th", (double) 36.5F, 17.936);
        annotation.setFont(font);
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        plot.addAnnotation(annotation);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static void main(String[] args) {
        AnnotationDemo1 demo = new AnnotationDemo1("Chart: AnnotationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
