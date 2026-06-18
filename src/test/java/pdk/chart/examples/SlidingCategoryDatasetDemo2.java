package pdk.chart.examples;

import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.category.SlidingCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * A simple demo for the SlidingCategoryDataset class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 3:21 PM
 */
public class SlidingCategoryDatasetDemo2 extends ApplicationFrame {
    public SlidingCategoryDatasetDemo2(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        SlidingCategoryDatasetDemo2 demo = new SlidingCategoryDatasetDemo2("SlidingCategoryDatasetDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ChangeListener {
        JScrollBar scroller;
        SlidingCategoryDataset dataset = new SlidingCategoryDataset(createDataset(), 0, 10);

        public MyDemoPanel() {
            super(new BorderLayout());
            Chart chart = createChart(this.dataset);
            this.addChart(chart);

            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.scroller = new JScrollBar(Adjustable.HORIZONTAL, 0, 10, 0, 50);
            this.add(cp1);
            this.scroller.getModel().addChangeListener(this);
            JPanel scrollPanel = new JPanel(new BorderLayout());
            scrollPanel.add(this.scroller);
            scrollPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            scrollPanel.setBackground(Color.WHITE);
            this.add(scrollPanel, "South");
        }

        private static CategoryDataset<String, String> createDataset() {
            DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
            for (int i = 0; i < 50; ++i) {
                dataset.addValue(Math.random() * 100.0, "S1", "S" + i);
            }
            return dataset;
        }

        private static Chart createChart(CategoryDataset<String, String> dataset) {
            CategoryXYChart chart = CategoryXYChart.create()
                    .title("SlidingCategoryDatasetDemo2")
                    .axisNames("Series", "Value")
                    .orientation(PlotOrientation.VERTICAL)
                    .dataset(dataset, CategoryXYChartType.BAR)

                    .domainAxis()
                    .maximumCategoryLabelWidthRatio(0.8f)
                    .lowerMargin(0.02)
                    .upperMargin(0.02)
                    .done()

                    .rangeAxis()
                    .standardTickUnits(NumberAxis.createIntegerTickUnits())
                    .range(0, 100)
                    .doneCategory()

                    .barProps(0)
                    .addTooltips(true)
                    .drawBarOutline(false)
                    .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                    .done();

            return chart;
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setFirstCategoryIndex(this.scroller.getValue());
        }
    }
}
