package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.XYPlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.xy.XYBlockRenderer;

import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYBlockChartDemo3 extends ApplicationFrame {
    public XYBlockChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setLowerMargin((double)0.0F);
        xAxis.setUpperMargin((double)0.0F);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setInverted(true);
        yAxis.setLowerMargin((double)0.0F);
        yAxis.setUpperMargin((double)0.0F);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBlockRenderer renderer = new XYBlockRenderer();
        LookupPaintScale paintScale = new LookupPaintScale((double)0.5F, (double)3.5F, Color.black);
        paintScale.add((double)0.5F, Color.GREEN);
        paintScale.add((double)1.5F, Color.orange);
        paintScale.add((double)2.5F, Color.RED);
        renderer.setPaintScale(paintScale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setForegroundAlpha(0.66F);
        plot.setAxisOffset(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)5.0F));
        Chart chart = new Chart("XYBlockChartDemo3", plot);
        chart.removeLegend();
        SymbolAxis scaleAxis = new SymbolAxis((String)null, new String[]{"", "OK", "Uncertain", "Bad"});
        scaleAxis.setRange((double)0.5F, (double)3.5F);
        scaleAxis.setPlot(new PiePlot());
        scaleAxis.setGridBandsVisible(false);
        PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
        psl.setAxisOffset((double)5.0F);
        psl.setPosition(RectangleEdge.BOTTOM);
        psl.setMargin(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)5.0F));
        chart.addSubtitle(psl);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static void setValue(double[][] data, int c, int r, double value) {
        data[0][(r - 8) * 60 + c] = (double)c;
        data[1][(r - 8) * 60 + c] = (double)r;
        data[2][(r - 8) * 60 + c] = value;
    }

    private static XYZDataset createDataset() {
        double[] xvalues = new double[840];
        double[] yvalues = new double[840];
        double[] zvalues = new double[840];
        double[][] data = new double[][]{xvalues, yvalues, zvalues};

        for(int c = 0; c < 60; ++c) {
            for(int r = 8; r < 22; ++r) {
                setValue(data, c, r, (double)0.0F);
            }
        }

        for(int r = 8; r < 12; ++r) {
            for(int c = 13; c < 48; ++c) {
                setValue(data, c, r, (double)1.0F);
            }
        }

        for(int r = 12; r < 20; ++r) {
            for(int c = 23; c < 43; ++c) {
                setValue(data, c, r, (double)1.0F);
            }
        }

        setValue(data, 2, 20, (double)2.0F);
        setValue(data, 5, 20, (double)3.0F);
        setValue(data, 6, 20, (double)3.0F);
        setValue(data, 7, 20, (double)3.0F);
        setValue(data, 8, 20, (double)3.0F);
        setValue(data, 9, 20, (double)3.0F);
        setValue(data, 11, 20, (double)3.0F);
        setValue(data, 17, 20, (double)2.0F);
        setValue(data, 18, 20, (double)2.0F);
        setValue(data, 19, 20, (double)2.0F);
        setValue(data, 20, 20, (double)2.0F);
        setValue(data, 22, 20, (double)2.0F);
        setValue(data, 25, 20, (double)2.0F);
        setValue(data, 28, 20, (double)2.0F);
        setValue(data, 35, 20, (double)2.0F);

        for(int c = 40; c < 60; ++c) {
            setValue(data, c, 20, (double)3.0F);
        }

        for(int c = 23; c < 43; ++c) {
            setValue(data, c, 21, (double)1.0F);
        }

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries("Series 1", data);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBlockChartDemo3 demo = new XYBlockChartDemo3("Block Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
