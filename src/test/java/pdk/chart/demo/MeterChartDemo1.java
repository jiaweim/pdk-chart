package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.data.Range;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.plot.DialShape;
import pdk.chart.plot.MeterInterval;
import pdk.chart.plot.MeterPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class MeterChartDemo1 extends ApplicationFrame {

    private static DefaultValueDataset dataset;

    public MeterChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(ValueDataset dataset) {
        MeterPlot plot = new MeterPlot(dataset);
        plot.setRange(new Range(0.0, 60.0));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 5.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.DARK_GRAY);
        plot.setDialBackgroundPaint(Color.WHITE);
        plot.setDialOutlinePaint(Color.GRAY);
        plot.setDialShape(DialShape.CHORD);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.LIGHT_GRAY);
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        Chart chart = new Chart("Meter Chart 1", Chart.DEFAULT_TITLE_FONT, plot, true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        dataset = new DefaultValueDataset(23.0);
        Chart chart = createChart(dataset);
        JPanel panel = new ChartPanel(chart);
        return panel;
    }

    static void main() {
        MeterChartDemo1 demo = new MeterChartDemo1("MeterChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
