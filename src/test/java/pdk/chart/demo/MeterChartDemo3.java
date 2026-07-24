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

public class MeterChartDemo3 extends ApplicationFrame {

    public MeterChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(String chartTitle, ValueDataset dataset, DialShape shape) {
        MeterPlot plot = new MeterPlot(dataset);
        plot.setDialShape(shape);
        plot.setRange(new Range(0.0, 60.0));
        plot.addInterval(new MeterInterval("Normal", new Range(0.0, 35.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(0, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Warning", new Range(35.0, 50.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
        plot.addInterval(new MeterInterval("Critical", new Range(50.0, 60.0), Color.LIGHT_GRAY,
                new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
        plot.setNeedlePaint(Color.DARK_GRAY);
        plot.setDialBackgroundPaint(Color.WHITE);
        plot.setDialOutlinePaint(Color.GRAY);
        plot.setMeterAngle(260);
        plot.setTickLabelsVisible(true);
        plot.setTickLabelFont(new Font("Dialog", Font.BOLD, 10));
        plot.setTickLabelPaint(Color.darkGray);
        plot.setTickSize(5.0);
        plot.setTickPaint(Color.LIGHT_GRAY);
        plot.setValuePaint(Color.black);
        plot.setValueFont(new Font("Dialog", Font.BOLD, 14));
        Chart chart = new Chart(chartTitle, Chart.DEFAULT_TITLE_FONT, plot, true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        DefaultValueDataset dataset = new DefaultValueDataset(23.0);
        JPanel panel1 = new ChartPanel(createChart("DialShape.PIE", dataset, DialShape.PIE), false);
        JPanel panel2 = new ChartPanel(createChart("DialShape.CHORD", dataset, DialShape.CHORD), false);
        JPanel panel3 = new ChartPanel(createChart("DialShape.CIRCLE", dataset, DialShape.CIRCLE), false);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        return panel;
    }

    static void main() {
        MeterChartDemo3 demo = new MeterChartDemo3("MeterChartDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
