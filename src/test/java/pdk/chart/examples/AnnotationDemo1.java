package pdk.chart.examples;

import pdk.chart.annotations.XYTextAnnotation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.io.*;

/**
 * A line chart with annotations used to label each series. The axis ranges do not automatically adjust to accommodate
 * the annotations, so the upper margin on the domain axis has been increased manually.
 * <p>
 * A demo showing chart annotations, in this case several instances of
 * {@link XYTextAnnotation}.
 */
public class AnnotationDemo1 {

    private static XYSeriesCollection<String> createDataset() {
        XYSeriesCollection<String> result = new XYSeriesCollection<>();

        try {
            InputStream stream = AnnotationDemo1.class.getResourceAsStream("wtageinf.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
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
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }

        return result;
    }

    static void main() {
        XYSeriesCollection<String> dataset = createDataset();

        TextTitle t1 = new TextTitle("Growth Charts: United States",
                new Font("SansSerif", Font.BOLD, 14));
        TextTitle t2 = new TextTitle("Weight-for-age percentiles: boys, birth to 36 months",
                new Font("SansSerif", Font.PLAIN, 11));

        Font font = new Font("SansSerif", Font.PLAIN, 9);
        XYTextAnnotation annotation1 = new XYTextAnnotation("3rd", 36.5, 11.76);
        annotation1.setFont(font);
        annotation1.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation2 = new XYTextAnnotation("5th", 36.5, 12.04);
        annotation2.setFont(font);
        annotation2.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation3 = new XYTextAnnotation("10th", 36.5, 12.493);
        annotation3.setFont(font);
        annotation3.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation4 = new XYTextAnnotation("25th", 36.5, 13.313);
        annotation4.setFont(font);
        annotation4.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation5 = new XYTextAnnotation("50th", 36.5, 14.33);
        annotation5.setFont(font);
        annotation5.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation6 = new XYTextAnnotation("75th", 36.5, 15.478);
        annotation6.setFont(font);
        annotation6.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation7 = new XYTextAnnotation("90th", 36.5, 16.642);
        annotation7.setFont(font);
        annotation7.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation8 = new XYTextAnnotation("95th", 36.5, 17.408);
        annotation8.setFont(font);
        annotation8.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        XYTextAnnotation annotation9 = new XYTextAnnotation("97th", 36.5, 17.936);
        annotation9.setFont(font);
        annotation9.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);

        XYChart.create()
                .dataset(dataset, XYChartType.LINE)
                .axisNames("Age in Months", "Weight (kg)")
                .showLegend(true)
                .addTitle(t1)
                .addTitle(t2)
                .domainPannable(true)
                .rangePannable(true)

                .addAnnotation(annotation1)
                .addAnnotation(annotation2)
                .addAnnotation(annotation3)
                .addAnnotation(annotation4)
                .addAnnotation(annotation5)
                .addAnnotation(annotation6)
                .addAnnotation(annotation7)
                .addAnnotation(annotation8)
                .addAnnotation(annotation9)

                .domainAxis()
                .upperMargin(0.12)
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .done()

                .rangeAxis()
                .autoRangeIncludesZero(false)
                .done()

                .show();
    }
}
