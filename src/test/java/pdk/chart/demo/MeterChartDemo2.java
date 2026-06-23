package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.data.Range;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.plot.MeterInterval;
import pdk.chart.plot.MeterPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MeterChartDemo2 extends ApplicationFrame {
    private static DefaultValueDataset dataset;

    public MeterChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(ValueDataset dataset) {
        MeterPlot plot = new MeterPlot(dataset);
        plot.addInterval(new MeterInterval("High", new Range((double) 80.0F, (double) 100.0F)));
        plot.setDialOutlinePaint(Color.WHITE);
        Chart chart = new Chart("Meter Chart 2", Chart.DEFAULT_TITLE_FONT, plot, false);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        dataset = new DefaultValueDataset((double) 50.0F);
        Chart chart = createChart(dataset);
        JPanel panel = new JPanel(new BorderLayout());
        JSlider slider = new JSlider(-10, 110, 50);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                MeterChartDemo2.dataset.setValue(s.getValue());
            }
        });
        panel.add(new ChartPanel(chart));
        panel.add("South", slider);
        return panel;
    }

    public static void main(String[] args) {
        MeterChartDemo2 demo = new MeterChartDemo2("Chart: MeterChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
