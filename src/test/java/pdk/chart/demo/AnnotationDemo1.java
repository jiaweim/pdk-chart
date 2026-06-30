package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
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
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A line chart with annotations used to label each series. The axis ranges do not automatically adjust to accommodate
 * the annotations, so the upper margin on the domain axis has been increased manually.
 * <p>
 * A demo showing chart annotations, in this case several instances of
 * {@link XYTextAnnotation}.
 */
public class AnnotationDemo1 extends ApplicationFrame {
    public AnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(360, 500));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        XYSeriesCollection<String> dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private static XYSeriesCollection<String> createDataset() {
        XYSeriesCollection<String> result = new XYSeriesCollection<>();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(AnnotationDemo1.class.getResourceAsStream("wtageinf.txt")));
            String data = in.readLine();
            data = in.readLine();
            data = in.readLine();
            data = in.readLine();
            XYSeries<String> s3 = new XYSeries<>("P3", true, false);
            XYSeries<String> s5 = new XYSeries<>("P5", true, false);
            XYSeries<String> s10 = new XYSeries<>("P10", true, false);
            XYSeries<String> s25 = new XYSeries<>("P25", true, false);
            XYSeries<String> s50 = new XYSeries<>("P50", true, false);
            XYSeries<String> s75 = new XYSeries<>("P75", true, false);
            XYSeries<String> s90 = new XYSeries<>("P90", true, false);
            XYSeries<String> s95 = new XYSeries<>("P95", true, false);
            XYSeries<String> s97 = new XYSeries<>("P97", true, false);

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
                    s3.add(age, p3);
                    s5.add(age, p5);
                    s10.add(age, p10);
                    s25.add(age, p25);
                    s50.add(age, p50);
                    s75.add(age, p75);
                    s90.add(age, p90);
                    s95.add(age, p95);
                    s97.add(age, p97);
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
        } catch (IOException e) {
            System.err.println(e);
        }

        return result;
    }

    private static Chart createChart(XYDataset<String> dataset) {
        Chart chart = JChart.line(null, "Age in Months", "Weight (kg)", dataset);
        TextTitle t1 = new TextTitle("Growth Charts: United States",
                new Font("SansSerif", Font.BOLD, 14));
        TextTitle t2 = new TextTitle("Weight-for-age percentiles: boys, birth to 36 months",
                new Font("SansSerif", Font.PLAIN, 11));
        chart.addSubtitle(t1);
        chart.addSubtitle(t2);

        XYPlot<String> plot = chart.getXYPlot();
        plot.domainPannable(true)
                .rangePannable(true);

        plot.getDomainAxisAsNumber()
                .upperMargin(0.12)
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getRangeAxisAsNumber()
                .autoRangeIncludesZero(false);

        Font font = new Font("SansSerif", Font.PLAIN, 9);
        String[] annoTexts = new String[]{
                "3rd", "5th", "10th", "25th", "50th", "75th", "90th", "95th", "97th"
        };
        double[] ys = new double[]{
                11.76, 12.04, 12.493, 13.313, 14.33, 15.478, 16.642, 17.408, 17.936
        };
        for (int i = 0; i < annoTexts.length; i++) {
            XYTextAnnotation annotation = new XYTextAnnotation(annoTexts[i], 36.5, ys[i]);
            annotation.setFont(font);
            annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
            plot.addAnnotation(annotation);
        }

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    static void main() {
        AnnotationDemo1 demo = new AnnotationDemo1("AnnotationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
