package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.StatisticalLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class HideSeriesDemo2 extends ApplicationFrame {
    public HideSeriesDemo2(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        HideSeriesDemo2 demo = new HideSeriesDemo2("Chart: HideSeriesDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private CategoryItemRenderer renderer;

        public MyDemoPanel() {
            super(new BorderLayout());
            CategoryDataset dataset = this.createSampleDataset();
            Chart chart = this.createChart(dataset);
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel boxPanel = new JPanel();
            JCheckBox box1 = new JCheckBox("Series 1");
            box1.setActionCommand("S1");
            box1.addActionListener(this);
            box1.setSelected(true);
            JCheckBox box2 = new JCheckBox("Series 2");
            box2.setActionCommand("S2");
            box2.addActionListener(this);
            box2.setSelected(true);
            JCheckBox box3 = new JCheckBox("Series 3");
            box3.setActionCommand("S3");
            box3.addActionListener(this);
            box3.setSelected(true);
            boxPanel.add(box1);
            boxPanel.add(box2);
            boxPanel.add(box3);
            this.add(chartPanel);
            this.add(boxPanel, "South");
            chartPanel.setPreferredSize(new Dimension(500, 270));
        }

        private CategoryDataset createSampleDataset() {
            DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
            dataset.add((double)10.0F, 2.4, "Row 1", "Column 1");
            dataset.add((double)15.0F, 4.4, "Row 1", "Column 2");
            dataset.add((double)13.0F, 2.1, "Row 1", "Column 3");
            dataset.add((double)7.0F, 1.3, "Row 1", "Column 4");
            dataset.add((double)22.0F, 2.4, "Row 2", "Column 1");
            dataset.add((double)18.0F, 4.4, "Row 2", "Column 2");
            dataset.add((double)28.0F, 2.1, "Row 2", "Column 3");
            dataset.add((double)7.0F, 1.3, "Row 2", "Column 4");
            dataset.add((double)2.0F, 2.4, "Row 3", "Column 1");
            dataset.add((double)8.0F, 4.4, "Row 3", "Column 2");
            dataset.add((double)8.0F, 2.1, "Row 3", "Column 3");
            dataset.add((double)7.0F, 1.3, "Row 3", "Column 4");
            return dataset;
        }

        private Chart createChart(CategoryDataset dataset) {
            Chart result = ChartFactory.createAreaChart("Hide Series Demo 2", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
            CategoryPlot plot = (CategoryPlot)result.getPlot();
            plot.setRenderer(new StatisticalLineAndShapeRenderer());
            this.renderer = plot.getRenderer(0);
            return result;
        }

        public void actionPerformed(ActionEvent e) {
            int series = -1;
            if (e.getActionCommand().equals("S1")) {
                series = 0;
            } else if (e.getActionCommand().equals("S2")) {
                series = 1;
            } else if (e.getActionCommand().equals("S3")) {
                series = 2;
            }

            if (series >= 0) {
                boolean visible = this.renderer.getItemVisible(series, 0);
                this.renderer.setSeriesVisible(series, !visible);
            }

        }
    }
}
