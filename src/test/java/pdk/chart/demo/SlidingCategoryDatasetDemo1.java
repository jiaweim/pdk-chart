package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.category.SlidingCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
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
 * @since 15 Jun 2026, 3:00 PM
 */
public class SlidingCategoryDatasetDemo1 extends ApplicationFrame {

    public SlidingCategoryDatasetDemo1(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        SlidingCategoryDatasetDemo1 demo = new SlidingCategoryDatasetDemo1("SlidingCategoryDatasetDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ChangeListener {
        JScrollBar scroller;
        SlidingCategoryDataset dataset = new SlidingCategoryDataset(createDataset(), 0, 20);

        public MyDemoPanel() {
            super(new BorderLayout());
            Chart chart = createChart(this.dataset);
            this.addChart(chart);
            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.scroller = new JScrollBar(Adjustable.VERTICAL, 0, 20, 0, 50);
            this.add(cp1);
            this.scroller.getModel().addChangeListener(this);
            JPanel scrollPanel = new JPanel(new BorderLayout());
            scrollPanel.add(this.scroller);
            scrollPanel.setBorder(BorderFactory.createEmptyBorder(66, 2, 2, 2));
            scrollPanel.setBackground(Color.WHITE);
            this.add(scrollPanel, "East");
        }

        private static CategoryDataset<String, String> createDataset() {
            DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
            for (int i = 0; i < 50; ++i) {
                dataset.addValue(Math.random() * 100.0, "S1", "Series " + i);
            }

            return dataset;
        }

        private static Chart createChart(CategoryDataset dataset) {
            CategoryXYChart chart = CategoryXYChart.create()
                    .title("SlidingCategoryDatasetDemo1")
                    .axisNames("Series", "Value")
                    .orientation(PlotOrientation.HORIZONTAL)
                    .dataset(dataset, CategoryChartType.BAR)

                    .domainAxis()
                    .maximumCategoryLabelWidthRatio(0.8f)
                    .lowerMargin(0.02)
                    .upperMargin(0.02)
                    .done()

                    .barProps(0)
                    .addTooltips(true)
                    .drawBarOutline(false)
                    .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                    .done();
            chart.rangeAxisNumber()
                    .standardTickUnits(NumberAxis.createIntegerTickUnits())
                    .range(0, 100);

            return chart;
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setFirstCategoryIndex(this.scroller.getValue());
        }
    }
}
