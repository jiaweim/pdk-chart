package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.entity.CategoryItemEntity;
import pdk.chart.entity.ChartEntity;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.*;

import javax.swing.*;
import java.awt.*;

public class MouseOverDemo1 extends ApplicationFrame {
    public MouseOverDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, series1, category1);
        dataset.addValue((double) 4.0F, series1, category2);
        dataset.addValue((double) 3.0F, series1, category3);
        dataset.addValue((double) 5.0F, series1, category4);
        dataset.addValue((double) 5.0F, series1, category5);
        dataset.addValue((double) 5.0F, series2, category1);
        dataset.addValue((double) 7.0F, series2, category2);
        dataset.addValue((double) 6.0F, series2, category3);
        dataset.addValue((double) 8.0F, series2, category4);
        dataset.addValue((double) 4.0F, series2, category5);
        dataset.addValue((double) 4.0F, series3, category1);
        dataset.addValue((double) 3.0F, series3, category2);
        dataset.addValue((double) 2.0F, series3, category3);
        dataset.addValue((double) 3.0F, series3, category4);
        dataset.addValue((double) 6.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Mouseover Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        MyBarRenderer renderer = new MyBarRenderer();
        renderer.setDrawBarOutline(true);
        plot.setRenderer(renderer);
        JChartUtils.applyCurrentTheme(chart);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        MyBarRenderer renderer = (MyBarRenderer) plot.getRenderer();
        MyDemoPanel demoPanel = new MyDemoPanel(renderer);
        ChartPanel chartPanel = new ChartPanel(chart);
        demoPanel.addChart(chart);
        chartPanel.addChartMouseListener(demoPanel);
        demoPanel.add(chartPanel);
        return demoPanel;
    }

    public static void main(String[] args) {
        MouseOverDemo1 demo = new MouseOverDemo1("Chart: MouseoverDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyBarRenderer extends BarRenderer {
        private int highlightRow = -1;
        private int highlightColumn = -1;

        public void setHighlightedItem(int r, int c) {
            if (this.highlightRow != r || this.highlightColumn != c) {
                this.highlightRow = r;
                this.highlightColumn = c;
                this.notifyListeners(new RendererChangeEvent(this));
            }
        }

        public Paint getItemOutlinePaint(int row, int column) {
            return (Paint) (row == this.highlightRow && column == this.highlightColumn ? Color.YELLOW : super.getItemOutlinePaint(row, column));
        }
    }

    static class MyDemoPanel extends DemoPanel implements ChartMouseListener {
        private MyBarRenderer renderer;

        public MyDemoPanel(MyBarRenderer renderer) {
            super(new BorderLayout());
            this.renderer = renderer;
        }

        public void chartMouseMoved(ChartMouseEvent event) {
            ChartEntity entity = event.getEntity();
            if (!(entity instanceof CategoryItemEntity)) {
                this.renderer.setHighlightedItem(-1, -1);
            } else {
                CategoryItemEntity cie = (CategoryItemEntity) entity;
                CategoryDataset dataset = cie.getDataset();
                this.renderer.setHighlightedItem(dataset.getRowIndex(cie.getRowKey()), dataset.getColumnIndex(cie.getColumnKey()));
            }
        }

        public void chartMouseClicked(ChartMouseEvent event) {
        }
    }
}
