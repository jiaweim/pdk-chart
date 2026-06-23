package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYBarRenderer;
import pdk.chart.data.xy.DefaultTableXYDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class StackedXYBarChartDemo1 extends ApplicationFrame {
    public StackedXYBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset createDataset() {
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();
        XYSeries s1 = new XYSeries("Series 1", true, false);
        s1.add((double)1.0F, (double)5.0F);
        s1.add((double)2.0F, (double)15.5F);
        s1.add((double)3.0F, (double)9.5F);
        s1.add((double)4.0F, (double)7.5F);
        dataset.addSeries(s1);
        XYSeries s2 = new XYSeries("Series 2", true, false);
        s2.add((double)1.0F, (double)5.0F);
        s2.add((double)2.0F, (double)15.5F);
        s2.add((double)3.0F, (double)9.5F);
        s2.add((double)4.0F, (double)3.5F);
        dataset.addSeries(s2);
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        NumberAxis domainAxis = new NumberAxis("X");
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis rangeAxis = new NumberAxis("Y");
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.1);
        renderer.setDrawBarOutline(false);
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        Chart chart = new Chart("Stacked XY Bar Chart Demo 1", plot);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedXYBarChartDemo1 demo = new StackedXYBarChartDemo1("Stacked XY Bar Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
