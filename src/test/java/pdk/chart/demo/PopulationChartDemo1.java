package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A population pyramid chart. This is constructed using a stacked bar chart...
 * but it would be better to create a dedicated plot type. That's on the TODO list (since forever).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 4:36 PM
 */
public class PopulationChartDemo1 extends ApplicationFrame {

    public PopulationChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static Chart createChart(CategoryDataset<String, String> dataset) {
        return JChart.barStacked(dataset,
                "Age Group", "Population (millions)", "Population Chart Demo 1",
                PlotOrientation.HORIZONTAL, true, true);
    }

    public static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{
                "70+", "60-69", "50-59", "40-49",
                "30-39", "20-29", "10-19", "0-9"
        };

        return Data.<String, String>category()
                .addSeries("Male", categories,
                        new double[]{
                                -6.0, -8.0, -11.0, -13.0,
                                -14.0, -15.0, -19.0, -21.0
                        })
                .addSeries("Female", categories,
                        new double[]{
                                10.0, 12.0, 13.0, 14.0,
                                15.0, 17.0, 19.0, 20.0
                        })
                .build();
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        PopulationChartDemo1 demo = new PopulationChartDemo1("PopulationChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
