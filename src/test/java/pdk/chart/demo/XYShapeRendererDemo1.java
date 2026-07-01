package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.Data;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.xy.XYShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYShapeRendererDemo1 extends ApplicationFrame {

    public XYShapeRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);
        XYShapeRenderer renderer = new XYShapeRenderer();
        LookupPaintScale ps = new LookupPaintScale(1.0, 4.0, new Color(0, 0, 255));
        ps.add(2.0F, new Color(100, 100, 255));
        ps.add(3.0F, new Color(200, 200, 255));
        renderer.setPaintScale(ps);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);
        Chart chart = new Chart("XYShapeRendererDemo1", plot);
        chart.removeLegend();

        NumberAxis zAxis = new NumberAxis("Score");
        zAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        PaintScaleLegend psl = new PaintScaleLegend(ps, zAxis);
        psl.setPosition(RectangleEdge.RIGHT);
        psl.setMargin(4.0, 4.0, 40.0, 4.0);
        psl.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        chart.addSubtitle(psl);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static XYZDataset<String> createDataset() {
        double[] x = new double[]{2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 2.8};
        double[] y = new double[]{14.1, 17.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25.0};
        double[] z = new double[]{2.4, 2.7, 1.7, 2.2, 1.3, 2.2, 2.1, 3.2, 1.6, 3.4};

        return Data.createXYZ("Series 1", x, y, z);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    static void main() {
        XYShapeRendererDemo1 demo = new XYShapeRendererDemo1("XYShapeRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
