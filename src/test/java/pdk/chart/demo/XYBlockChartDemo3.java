package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.XYPlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.xy.XYBlockRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYBlockChartDemo3 extends ApplicationFrame {
    public XYBlockChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setInverted(true);
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBlockRenderer renderer = new XYBlockRenderer();
        LookupPaintScale paintScale = new LookupPaintScale(0.5, 3.5, Color.black);
        paintScale.add(0.5, Color.GREEN);
        paintScale.add(1.5, Color.orange);
        paintScale.add(2.5, Color.RED);
        renderer.setPaintScale(paintScale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setForegroundAlpha(0.66F);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        Chart chart = new Chart("XYBlockChartDemo3", plot);
        chart.removeLegend();
        SymbolAxis scaleAxis = new SymbolAxis(null, new String[]{"", "OK", "Uncertain", "Bad"});
        scaleAxis.setRange(0.5F, 3.5F);
        scaleAxis.setPlot(new PiePlot());
        scaleAxis.setGridBandsVisible(false);
        PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
        psl.setAxisOffset(5.0F);
        psl.setPosition(RectangleEdge.BOTTOM);
        psl.setMargin(new RectangleInsets(5.0F, 5.0F, 5.0F, 5.0F));
        chart.addSubtitle(psl);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static void setValue(double[][] data, int c, int r, double value) {
        data[0][(r - 8) * 60 + c] = c;
        data[1][(r - 8) * 60 + c] = r;
        data[2][(r - 8) * 60 + c] = value;
    }

    private static XYZDataset createDataset() {
        double[] xvalues = new double[840];
        double[] yvalues = new double[840];
        double[] zvalues = new double[840];
        double[][] data = new double[][]{xvalues, yvalues, zvalues};

        for (int c = 0; c < 60; ++c) {
            for (int r = 8; r < 22; ++r) {
                setValue(data, c, r, 0.0);
            }
        }

        for (int r = 8; r < 12; ++r) {
            for (int c = 13; c < 48; ++c) {
                setValue(data, c, r, 1.0);
            }
        }

        for (int r = 12; r < 20; ++r) {
            for (int c = 23; c < 43; ++c) {
                setValue(data, c, r, 1.0);
            }
        }

        setValue(data, 2, 20, 2.0);
        setValue(data, 5, 20, 3.0);
        setValue(data, 6, 20, 3.0);
        setValue(data, 7, 20, 3.0);
        setValue(data, 8, 20, 3.0);
        setValue(data, 9, 20, 3.0);
        setValue(data, 11, 20, 3.0);
        setValue(data, 17, 20, 2.0);
        setValue(data, 18, 20, 2.0);
        setValue(data, 19, 20, 2.0);
        setValue(data, 20, 20, 2.0);
        setValue(data, 22, 20, 2.0);
        setValue(data, 25, 20, 2.0);
        setValue(data, 28, 20, 2.0);
        setValue(data, 35, 20, 2.0);

        for (int c = 40; c < 60; ++c) {
            setValue(data, c, 20, 3.0);
        }

        for (int c = 23; c < 43; ++c) {
            setValue(data, c, 21, 1.0);
        }

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries("Series 1", data);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYBlockChartDemo3 demo = new XYBlockChartDemo3("Block Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
