package pdk.chart.demo;

import pdk.chart.AreaChart;
import pdk.chart.Chart;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;


public class XYAreaChartDemo1 extends ApplicationFrame {
    public XYAreaChartDemo1(String title) {
        super(title);
        XYDataset<String> dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset<String> createDataset() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        XYSeries<String> series1 = new XYSeries<>("Random 1",
                new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0},
                new double[]{500.2, 694.1, -734.4, 453.2, 500.2, 300.7, 734.4, 453.2}
        );
        XYSeries<String> series2 = new XYSeries<>("Random 2",
                new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0},
                new double[]{700.2, 534.1, 323.4, 125.2, 653.2, 432.7, 564.4, 322.2}
        );
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth(0.0);
        return dataset;
    }

    private static Chart createChart(XYDataset<String> dataset) {
        AreaChart chart = new AreaChart(dataset, "Domain (X)", "Range (Y)",
                "XY Area Chart Demo");
        chart.setForegroundAlpha(0.65f);

        ValueAxis xAxis = chart.getDomainAxis();
        xAxis.setLowerMargin(0);
        xAxis.setUpperMargin(0);
        xAxis.setTickMarkPaint(Color.BLACK);

        ValueAxis yAxis = chart.getRangeAxis();
        yAxis.setTickMarkPaint(Color.BLACK);

        XYPointerAnnotation pointer = new XYPointerAnnotation("Test", 5.0, -500.0, Math.PI * 2 / 3);
        pointer.setTipRadius(0.0);
        pointer.setBaseRadius(35.0);
        pointer.setFont(new Font("SansSerif", Font.PLAIN, 9));
        pointer.setPaint(Color.BLUE);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        chart.addAnnotation(pointer);

        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYAreaChartDemo1 demo = new XYAreaChartDemo1("XY Area Chart Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
