package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.function.Function2D;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

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

    public static XYDataset createDataset() {
        Function2D normal = new NormalDistributionFunction2D((double) 0.0F, (double) 1.0F);
        XYDataset dataset = normal.sample(-5.0F, (double) 5.0F, 100, "Normal");
        return dataset;
    }

    public static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("Normal Distribution", "X", "Y", dataset);
        return chart;
    }

    public static void main(String[] args) {
        NormalDistributionDemo1 demo = new NormalDistributionDemo1("Chart: NormalDistributionDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
