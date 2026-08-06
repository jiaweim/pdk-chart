package pdk.chart.demo;

import pdk.chart.CandleStickChart;
import pdk.chart.Chart;
import pdk.chart.data.xy.DefaultHighLowDataset;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class CandlestickChartDemo1 extends ApplicationFrame {
    private static final Calendar calendar = Calendar.getInstance();

    public CandlestickChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(OHLCDataset dataset) {
        CandleStickChart chart = new CandleStickChart(dataset, "Time", "Value",
                "Candlestick Demo 1", true);
        chart.setDomainPannable(true);

        chart.getRangeAxisAsNumber()
                .withAutoRangeIncludesZero(false)
                .withLowerMargin(0.0)
                .withUpperMargin(0.0);

        return chart;
    }

    private static Date createDate(int y, int m, int d, int hour, int min) {
        calendar.clear();
        calendar.set(y, m - 1, d, hour, min);
        return calendar.getTime();
    }

    public static OHLCDataset createDataset() {
        int jan = 1;
        int feb = 2;

        Date[] date = new Date[]{
                createDate(2001, jan, 4, 12, 0),
                createDate(2001, jan, 5, 12, 0),
                createDate(2001, jan, 6, 12, 0),
                createDate(2001, jan, 7, 12, 0),
                createDate(2001, jan, 8, 12, 0),
                createDate(2001, jan, 9, 12, 0),
                createDate(2001, jan, 10, 12, 0),
                createDate(2001, jan, 11, 12, 0),
                createDate(2001, jan, 12, 12, 0),
                createDate(2001, jan, 13, 12, 0),
                createDate(2001, jan, 14, 12, 0),
                createDate(2001, jan, 15, 12, 0),
                createDate(2001, jan, 17, 12, 0),
                createDate(2001, jan, 18, 12, 0),
                createDate(2001, jan, 19, 12, 0),
                createDate(2001, jan, 20, 12, 0),
                createDate(2001, jan, 21, 12, 0),
                createDate(2001, jan, 22, 12, 0),
                createDate(2001, jan, 23, 12, 0),
                createDate(2001, jan, 24, 12, 0),
                createDate(2001, jan, 25, 12, 0),
                createDate(2001, jan, 26, 12, 0),
                createDate(2001, jan, 27, 12, 0),
                createDate(2001, jan, 28, 12, 0),
                createDate(2001, jan, 29, 12, 0),
                createDate(2001, jan, 30, 12, 0),
                createDate(2001, jan, 31, 12, 0),
                createDate(2001, feb, 1, 12, 0),
                createDate(2001, feb, 2, 12, 0),
                createDate(2001, feb, 3, 12, 0),
                createDate(2001, feb, 4, 12, 0),
                createDate(2001, feb, 5, 12, 0),
                createDate(2001, feb, 6, 12, 0),
                createDate(2001, feb, 7, 12, 0),
                createDate(2001, feb, 8, 12, 0),
                createDate(2001, feb, 9, 12, 0),
                createDate(2001, feb, 10, 12, 0),
                createDate(2001, feb, 11, 12, 0),
                createDate(2001, feb, 12, 12, 0),
                createDate(2001, feb, 13, 12, 0),
                createDate(2001, feb, 14, 12, 0),
                createDate(2001, feb, 15, 12, 0),
                createDate(2001, feb, 16, 12, 0),
                createDate(2001, feb, 17, 12, 0),
                createDate(2001, feb, 18, 12, 0),
                createDate(2001, feb, 19, 12, 0),
                createDate(2001, feb, 20, 12, 0),

        };
        double[] high = {
                47, 47, 49, 51, 60,
                62, 65, 55, 54, 47,
                54, 48, 60, 58, 54,
                53, 47, 55, 54, 48,
                58, 47, 58, 46, 56,
                56, 53, 51, 47, 57,
                49, 46, 55, 50, 59,
                48, 55, 60, 56, 49,
                55, 49, 47, 53, 47,
                46, 48
        };
        double[] low = {
                33, 32, 43, 39, 40,
                55, 56, 43, 33, 33,
                38, 41, 30, 44, 32,
                39, 33, 37, 42, 37,
                33, 31, 44, 41, 39,
                39, 39, 30, 30, 37,
                40, 38, 38, 33, 34,
                39, 30, 32, 42, 42,
                42, 35, 38, 42, 44,
                40, 41
        };
        double[] open = {
                35, 41, 46, 40, 46,
                57, 62, 45, 40, 35,
                43, 44, 34, 54, 42,
                50, 41, 43, 50, 37,
                39, 36, 49, 43, 39,
                47, 52, 45, 34, 44,
                47, 43, 39, 37, 57,
                46, 37, 56, 53, 45,
                47, 38, 43, 47, 46,
                43, 46
        };
        double[] close = {
                33, 37, 48, 47, 53,
                61, 59, 47, 51, 33,
                52, 41, 44, 56, 53,
                49, 40, 45, 42, 47,
                41, 41, 44, 44, 51,
                49, 47, 47, 46, 56,
                44, 40, 53, 37, 43,
                47, 30, 36, 54, 42,
                54, 35, 42, 48, 30,
                44, 41
        };
        double[] volume = {
                100, 150, 70, 200, 120,
                110, 70, 20, 30, 100,
                50, 80, 90, 20, 70,
                60, 30, 90, 150, 120,
                80, 40, 20, 60, 40,
                70, 60, 90, 100, 20,
                50, 70, 120, 140, 70,
                70, 30, 70, 40, 90,
                70, 20, 10, 20, 30,
                50, 100
        };
        return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        CandlestickChartDemo1 demo = new CandlestickChartDemo1("CandlestickChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
