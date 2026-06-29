package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DefaultKeyedValues2DDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class PopulationChartDemo1 extends ApplicationFrame {
    public PopulationChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createStackedBarChart("Population Chart Demo 1", "Age Group", "Population (millions)", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        return chart;
    }

    public static CategoryDataset createDataset() {
        DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
        data.addValue((double) -6.0F, "Male", "70+");
        data.addValue((double) -8.0F, "Male", "60-69");
        data.addValue((double) -11.0F, "Male", "50-59");
        data.addValue((double) -13.0F, "Male", "40-49");
        data.addValue((double) -14.0F, "Male", "30-39");
        data.addValue((double) -15.0F, "Male", "20-29");
        data.addValue((double) -19.0F, "Male", "10-19");
        data.addValue((double) -21.0F, "Male", "0-9");
        data.addValue((double) 10.0F, "Female", "70+");
        data.addValue((double) 12.0F, "Female", "60-69");
        data.addValue((double) 13.0F, "Female", "50-59");
        data.addValue((double) 14.0F, "Female", "40-49");
        data.addValue((double) 15.0F, "Female", "30-39");
        data.addValue((double) 17.0F, "Female", "20-29");
        data.addValue((double) 19.0F, "Female", "10-19");
        data.addValue((double) 20.0F, "Female", "0-9");
        return data;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    public static void main(String[] args) {
        PopulationChartDemo1 demo = new PopulationChartDemo1("Chart: PopulationChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
