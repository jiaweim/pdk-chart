package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.function.Function2D;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYDataset;
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
public class NormalDistributionDemo1 extends ApplicationFrame {
    public NormalDistributionDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static XYDataset<String> createDataset() {
        Function2D normal = new NormalDistributionFunction2D(0.0, 1.0);
        return normal.sample(-5.0, 5.0, 100, "Normal");
    }

    public static Chart createChart(XYDataset<String> dataset) {
        return JChart.line(dataset, "Normal Distribution", "X", "Y");
    }

    static void main() {
        NormalDistributionDemo1 demo = new NormalDistributionDemo1("Chart: NormalDistributionDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
