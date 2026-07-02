package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.plot.compass.CompassPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CompassDemo1 extends ApplicationFrame {
    public CompassDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(ValueDataset dataset) {
        CompassPlot plot = new CompassPlot(dataset);
        plot.setSeriesNeedle(7);
        plot.setSeriesPaint(0, Color.BLACK);
        plot.setSeriesOutlinePaint(0, Color.BLACK);
        plot.setRosePaint(Color.RED);
        plot.setRoseHighlightPaint(Color.GRAY);
        plot.setRoseCenterPaint(Color.WHITE);
        plot.setDrawBorder(false);
        Chart chart = new Chart(plot);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new DefaultValueDataset((double) 45.0F));
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        CompassDemo1 demo = new CompassDemo1("Chart: CompassDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
