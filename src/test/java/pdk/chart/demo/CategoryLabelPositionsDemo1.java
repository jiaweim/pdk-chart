package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CategoryLabelPositionsDemo1 extends ApplicationFrame {

    static CategoryBarChart chart;
    static JCheckBox invertCheckBox;
    static JRadioButton horizontalRadioButton;
    static JRadioButton verticalRadioButton;
    static JSlider slider;

    public CategoryLabelPositionsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 350));
        this.setContentPane(chartPanel);
    }

    private static CategoryBarChart createChart(CategoryDataset dataset) {
        CategoryBarChart chart = new CategoryBarChart(dataset, "Category", "Value",
                "CategoryLabelPositionsDemo1", PlotOrientation.VERTICAL, false, false);
        chart.getDomainAxis()
                .withMaximumCategoryLabelLines(Integer.MAX_VALUE)
                .withCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((Math.PI / 4)));
        return chart;
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0F, "R1", "Category 1 (Long)");
        dataset.addValue(5.0F, "R1", "Category 2 (Long)");
        dataset.addValue(3.0F, "R1", "Category 3 (Long)");
        dataset.addValue(2.0F, "R1", "Category 4 (Long)");
        dataset.addValue(9.0F, "R1", "Category 5 (Long)");
        dataset.addValue(7.0F, "R1", "Category 6 (Long)");
        dataset.addValue(6.0F, "R1", "Category 7 (Long)");
        dataset.addValue(8.0F, "R1", "Category 8 (Long)");
        return dataset;
    }

    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset();
        chart = createChart(dataset);
        DemoPanel panel = new DemoPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel orientPanel = new JPanel();
        invertCheckBox = new JCheckBox("Invert Range Axis?");
        invertCheckBox.addActionListener(e -> {
            chart.getRangeAxis().setInverted(CategoryLabelPositionsDemo1.invertCheckBox.isSelected());
        });
        orientPanel.add(invertCheckBox);
        ButtonGroup group = new ButtonGroup();
        horizontalRadioButton = new JRadioButton("Horizontal", false);
        horizontalRadioButton.addActionListener(e -> {
            if (CategoryLabelPositionsDemo1.horizontalRadioButton.isSelected()) {
                chart.setOrientation(PlotOrientation.HORIZONTAL);
            }

        });
        group.add(horizontalRadioButton);
        verticalRadioButton = new JRadioButton("Vertical", true);
        verticalRadioButton.addActionListener(e -> {
            if (CategoryLabelPositionsDemo1.verticalRadioButton.isSelected()) {
                chart.setOrientation(PlotOrientation.VERTICAL);
            }
        });
        group.add(verticalRadioButton);
        orientPanel.add(horizontalRadioButton);
        orientPanel.add(verticalRadioButton);
        orientPanel.setBorder(new TitledBorder("Plot Settings: "));
        JPanel axisPanel = new JPanel(new BorderLayout());
        slider = new JSlider(0, 90, 45);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(e -> {
            chart.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(CategoryLabelPositionsDemo1.slider.getValue() * Math.PI / 180.0));
        });
        axisPanel.add(slider);
        axisPanel.setBorder(new TitledBorder("Axis Label Rotation Angle:"));
        controlPanel.add("North", orientPanel);
        controlPanel.add(axisPanel);
        panel.add(new ChartPanel(chart));
        panel.addChart(chart);
        panel.add("South", controlPanel);
        return panel;
    }

    static void main() {
        CategoryLabelPositionsDemo1 demo = new CategoryLabelPositionsDemo1("CategoryLabelPositionsDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
