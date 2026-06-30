package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.CategoryTableXYDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYAreaRenderer2;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedXYAreaChartDemo2 extends ApplicationFrame {
    public StackedXYAreaChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset createDataset() {
        CategoryTableXYDataset dataset = new CategoryTableXYDataset();
        dataset.add((double) 0.0F, (double) 0.0F, "Series 1");
        dataset.add((double) 10.0F, (double) 20.0F, "Series 1");
        dataset.add((double) 20.0F, (double) 15.0F, "Series 1");
        dataset.add((double) 30.0F, (double) 25.0F, "Series 1");
        dataset.add((double) 40.0F, (double) 21.0F, "Series 1");
        dataset.add((double) 10.0F, (double) 9.0F, "Series 2");
        dataset.add((double) 20.0F, (double) -7.0F, "Series 2");
        dataset.add((double) 30.0F, (double) 15.0F, "Series 2");
        dataset.add((double) 40.0F, (double) 11.0F, "Series 2");
        dataset.add((double) 45.0F, (double) -10.0F, "Series 2");
        dataset.add((double) 50.0F, (double) 0.0F, "Series 2");
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        Chart chart = JChart.stackedAreaXY("Stacked XY Area Chart Demo 2", "X Value", "Y Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2();
        renderer.setRoundXCoordinates(true);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(0, renderer);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedXYAreaChartDemo2 demo = new StackedXYAreaChartDemo2("Stacked XY Area Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
