package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.Second;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.fluent.Data;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.format.RelativeDateFormat;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * A time series chart where the time axis displays a relative date (that is, the elapsed time in hours, minutes and seconds).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 10:18 AM
 */
public class RelativeDateFormatDemo1 extends ApplicationFrame {

    public RelativeDateFormatDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset<String> dataset) {
        Chart chart = JChart.timeLine("Exercise Chart",
                "Elapsed Time", "Beats Per Minute", dataset, true, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.domainCrosshairVisible(true)
                .rangeCrosshairVisible(true);

        plot.getLineAndShapeRenderer()
                .defaultVisible(true, true);

        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));

        plot.domainAxisDate()
                .dateFormatOverride(rdf);

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset<String> createDataset() {
        return Data.createTime("Heart Rate",
                new Second[]{
                        new Second(45, 6, 9, 1, 10, 2006),
                        new Second(33, 8, 9, 1, 10, 2006),
                        new Second(10, 10, 9, 1, 10, 2006),
                        new Second(19, 12, 9, 1, 10, 2006),
                        new Second(5, 15, 9, 1, 10, 2006),
                        new Second(12, 16, 9, 1, 10, 2006),
                        new Second(6, 18, 9, 1, 10, 2006),
                        new Second(11, 20, 9, 1, 10, 2006)
                },
                new double[]{143.0, 167.0, 189.0, 156.0, 176.0, 183.0, 138.0, 102.0}
        );
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        RelativeDateFormatDemo1 demo = new RelativeDateFormatDemo1("RelativeDateFormatDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
