package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedXYBarChartDemo1 extends ApplicationFrame {

    public StackedXYBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset<String> createDataset() {
        XYSeries<String> s1 = new XYSeries<>("Series 1", true, false);
        s1.add(new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{5.0, 15.5, 9.5, 7.5});
        XYSeries<String> s2 = new XYSeries<>("Series 2", true, false);
        s2.add(new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{5.0, 15.5, 9.5, 3.5});

        return Data.createTableXYDataset(s1, s2);
    }

    private static Chart createChart(TableXYDataset<String> dataset) {
        Chart chart = JChart.barStackedXY("Stacked XY Bar Chart Demo 1", "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer()
                .drawBarOutline(false)
                .margin(0.1);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedXYBarChartDemo1 demo = new StackedXYBarChartDemo1("Stacked XY Bar Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
