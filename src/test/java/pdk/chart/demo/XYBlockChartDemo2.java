package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.xy.XYBlockRenderer;

import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYBlockChartDemo2 extends ApplicationFrame {
    public XYBlockChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        DateAxis xAxis = new DateAxis("Date");
        xAxis.setLowerMargin((double)0.0F);
        xAxis.setUpperMargin((double)0.0F);
        xAxis.setInverted(true);
        NumberAxis yAxis = new NumberAxis("Hour");
        yAxis.setUpperMargin((double)0.0F);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBlockRenderer renderer = new XYBlockRenderer();
        renderer.setBlockWidth((double)8.64E7F);
        renderer.setBlockAnchor(RectangleAnchor.BOTTOM_LEFT);
        LookupPaintScale paintScale = new LookupPaintScale((double)0.5F, (double)4.5F, Color.WHITE);
        paintScale.add((double)0.5F, Color.RED);
        paintScale.add((double)1.5F, Color.GREEN);
        paintScale.add((double)2.5F, Color.BLUE);
        paintScale.add((double)3.5F, Color.YELLOW);
        renderer.setPaintScale(paintScale);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)5.0F));
        Chart chart = new Chart("XYBlockChartDemo2", plot);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.WHITE);
        SymbolAxis scaleAxis = new SymbolAxis((String)null, new String[]{"", "Unavailable", "Free", "Group 1", "Group 2"});
        scaleAxis.setRange((double)0.5F, (double)4.5F);
        scaleAxis.setPlot(new PiePlot());
        scaleAxis.setGridBandsVisible(false);
        PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
        psl.setMargin(new RectangleInsets((double)3.0F, (double)10.0F, (double)3.0F, (double)10.0F));
        psl.setPosition(RectangleEdge.BOTTOM);
        psl.setAxisOffset((double)5.0F);
        chart.addSubtitle(psl);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYZDataset createDataset() {
        double[] xvalues = new double[2400];
        double[] yvalues = new double[2400];
        double[] zvalues = new double[2400];
        RegularTimePeriod t = new Day();

        for(int days = 0; days < 100; ++days) {
            double value = (double)1.0F;

            for(int hour = 0; hour < 24; ++hour) {
                if (Math.random() < 0.1) {
                    value = Math.random() * (double)4.0F;
                }

                xvalues[days * 24 + hour] = (double)t.getFirstMillisecond();
                yvalues[days * 24 + hour] = (double)hour;
                zvalues[days * 24 + hour] = value;
            }

            t = t.next();
        }

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries("Series 1", new double[][]{xvalues, yvalues, zvalues});
        return dataset;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBlockChartDemo2 demo = new XYBlockChartDemo2("Block Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
