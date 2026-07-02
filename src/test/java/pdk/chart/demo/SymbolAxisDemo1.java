package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class SymbolAxisDemo1 extends ApplicationFrame {
    public SymbolAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        SymbolAxis domainAxis = new SymbolAxis("Domain", new String[]{"A", "B", "C", "DDDDDDDDDDDDDDDDDDD"});
        SymbolAxis rangeAxis = new SymbolAxis("Range", new String[]{"V", "X", "Y", "Z"});
        rangeAxis.setUpperMargin((double) 0.0F);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        Chart chart = new Chart("SymbolAxis Demo 1", plot);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries s1 = new XYSeries("Series 1");
        s1.add((double) 0.0F, (double) 3.0F);
        s1.add((double) 1.0F, (double) 3.0F);
        s1.add((double) 2.0F, (double) 0.0F);
        s1.add((double) 3.0F, (double) 1.0F);
        XYSeries s2 = new XYSeries("Series 2");
        s2.add((double) 0.0F, (double) 1.0F);
        s2.add((double) 1.0F, (double) 2.0F);
        s2.add((double) 2.0F, (double) 1.0F);
        s2.add((double) 3.0F, (double) 3.0F);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        SymbolAxisDemo1 demo = new SymbolAxisDemo1("Chart: SymbolAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
