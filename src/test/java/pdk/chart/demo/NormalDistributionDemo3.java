package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.XYChartType;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jun 2026, 1:55 PM
 */
public class NormalDistributionDemo3 extends ApplicationFrame {

    public NormalDistributionDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }


    public static Chart createChart() {
        NormalDistributionFunction2D func = new NormalDistributionFunction2D(20.6, 1.62);
        XYDataset<String> lineDataset = func.sample(20.6 - 10, 20.6 + 10, 500, "Line");
        XYDataset<String> areaDataset = func.sample(10.6, 18, 100, "Area");

        Chart chart = JChart.line(null, "X", "Probability Density", lineDataset);
        chart.getXYPlot()
                .addDataset(areaDataset, XYChartType.AREA);

        return chart;
    }

    static void main() {
        NormalDistributionDemo3 demo = new NormalDistributionDemo3("NormalDistributionDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
