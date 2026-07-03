package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.CategoryTableXYDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
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
        dataset.add(0.0, 0.0, "Series 1");
        dataset.add(10.0, 20.0, "Series 1");
        dataset.add(20.0, 15.0, "Series 1");
        dataset.add(30.0, 25.0, "Series 1");
        dataset.add(40.0, 21.0, "Series 1");
        dataset.add(10.0, 9.0, "Series 2");
        dataset.add(20.0, -7.0, "Series 2");
        dataset.add(30.0, 15.0, "Series 2");
        dataset.add(40.0, 11.0, "Series 2");
        dataset.add(45.0, -10.0, "Series 2");
        dataset.add(50.0, 0.0, "Series 2");
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        Chart chart = JChart.stackedAreaXY(dataset, "X Value", "Y Value",
                "Stacked XY Area Chart Demo 2");
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
