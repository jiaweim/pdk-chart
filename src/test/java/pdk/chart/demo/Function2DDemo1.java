package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.function.Function2D;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class Function2DDemo1 extends ApplicationFrame {
    public Function2DDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("Function2DDemo1 ", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setLowerMargin((double) 0.0F);
        xAxis.setUpperMargin((double) 0.0F);
        xAxis.setRange((double) -2.0F, (double) 2.0F);
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange((double) 0.0F, (double) 5.0F);
        return chart;
    }

    public static XYDataset createDataset() {
        XYDataset result = new X2().sample(-40.0, 40.0, 400, "f(x)");
        return result;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        Function2DDemo1 demo = new Function2DDemo1("Chart: Function2DDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class X2 implements Function2D {
        public double getValue(double x) {
            return x * x + (double) 2.0F;
        }
    }
}
