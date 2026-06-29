package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryLabelPositionsDemo1 extends ApplicationFrame {
    static Chart chart;
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

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.bar("CategoryLabelPositionsDemo1", "Category", "Value", dataset, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setMaximumCategoryLabelLines(Integer.MAX_VALUE);
        axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((Math.PI / 4D)));
        return chart;
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, "R1", "Category 1 (Long)");
        dataset.addValue((double) 5.0F, "R1", "Category 2 (Long)");
        dataset.addValue((double) 3.0F, "R1", "Category 3 (Long)");
        dataset.addValue((double) 2.0F, "R1", "Category 4 (Long)");
        dataset.addValue((double) 9.0F, "R1", "Category 5 (Long)");
        dataset.addValue((double) 7.0F, "R1", "Category 6 (Long)");
        dataset.addValue((double) 6.0F, "R1", "Category 7 (Long)");
        dataset.addValue((double) 8.0F, "R1", "Category 8 (Long)");
        return dataset;
    }

    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset();
        chart = createChart(dataset);
        DemoPanel panel = new DemoPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel orientPanel = new JPanel();
        invertCheckBox = new JCheckBox("Invert Range Axis?");
        invertCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryPlot plot = (CategoryPlot) CategoryLabelPositionsDemo1.chart.getPlot();
                plot.getRangeAxis().setInverted(CategoryLabelPositionsDemo1.invertCheckBox.isSelected());
            }
        });
        orientPanel.add(invertCheckBox);
        ButtonGroup group = new ButtonGroup();
        horizontalRadioButton = new JRadioButton("Horizontal", false);
        horizontalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CategoryLabelPositionsDemo1.horizontalRadioButton.isSelected()) {
                    CategoryPlot plot = (CategoryPlot) CategoryLabelPositionsDemo1.chart.getPlot();
                    plot.setOrientation(PlotOrientation.HORIZONTAL);
                }

            }
        });
        group.add(horizontalRadioButton);
        verticalRadioButton = new JRadioButton("Vertical", true);
        verticalRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CategoryLabelPositionsDemo1.verticalRadioButton.isSelected()) {
                    CategoryPlot plot = (CategoryPlot) CategoryLabelPositionsDemo1.chart.getPlot();
                    plot.setOrientation(PlotOrientation.VERTICAL);
                }

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
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                CategoryPlot plot = (CategoryPlot) CategoryLabelPositionsDemo1.chart.getPlot();
                CategoryAxis axis = plot.getDomainAxis();
                axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((double) CategoryLabelPositionsDemo1.slider.getValue() * Math.PI / (double) 180.0F));
            }
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

    public static void main(String[] args) {
        CategoryLabelPositionsDemo1 demo = new CategoryLabelPositionsDemo1("Chart: CategoryLabelPositionsDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
