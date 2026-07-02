//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CombinedDomainXYPlot;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;

import pdk.chart.data.time.Millisecond;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class DynamicDataDemo3 extends ApplicationFrame {
    public DynamicDataDemo3(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        DynamicDataDemo3 demo = new DynamicDataDemo3("Chart: DynamicDataDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        public static final int SUBPLOT_COUNT = 3;
        private TimeSeriesCollection[] datasets;
        private double[] lastValue = new double[3];

        public MyDemoPanel() {
            super(new BorderLayout());
            CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
            this.datasets = new TimeSeriesCollection[3];

            for(int i = 0; i < 3; ++i) {
                this.lastValue[i] = (double)100.0F;
                TimeSeries series = new TimeSeries("Random " + i);
                this.datasets[i] = new TimeSeriesCollection(series);
                NumberAxis rangeAxis = new NumberAxis("Y" + i);
                rangeAxis.setAutoRangeIncludesZero(false);
                XYPlot subplot = new XYPlot(this.datasets[i], (ValueAxis)null, rangeAxis, new StandardXYItemRenderer());
                subplot.setBackgroundPaint(Color.LIGHT_GRAY);
                subplot.setDomainGridlinePaint(Color.WHITE);
                subplot.setRangeGridlinePaint(Color.WHITE);
                plot.add(subplot);
            }

            Chart chart = new Chart("Dynamic Data Demo 3", plot);
            this.addChart(chart);
            LegendTitle legend = (LegendTitle)chart.getSubtitle(0);
            legend.setPosition(RectangleEdge.RIGHT);
            legend.setMargin(new RectangleInsets(UnitType.ABSOLUTE, (double)0.0F, (double)4.0F, (double)0.0F, (double)4.0F));
            chart.setBorderPaint(Color.black);
            chart.setBorderVisible(true);
            ValueAxis axis = plot.getDomainAxis();
            axis.setAutoRange(true);
            axis.setFixedAutoRange((double)20000.0F);
            JChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            this.add(chartPanel);
            JPanel buttonPanel = new JPanel(new FlowLayout());

            for(int i = 0; i < 3; ++i) {
                JButton button = new JButton("Series " + i);
                button.setActionCommand("ADD_DATA_" + i);
                button.addActionListener(this);
                buttonPanel.add(button);
            }

            JButton buttonAll = new JButton("ALL");
            buttonAll.setActionCommand("ADD_ALL");
            buttonAll.addActionListener(this);
            buttonPanel.add(buttonAll);
            this.add(buttonPanel, "South");
            chartPanel.setPreferredSize(new Dimension(500, 470));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < 3; ++i) {
                if (e.getActionCommand().endsWith(String.valueOf(i))) {
                    Millisecond now = new Millisecond();
                    System.out.println("Now = " + now.toString());
                    this.lastValue[i] *= 0.9 + 0.2 * Math.random();
                    this.datasets[i].getSeries(0).add(new Millisecond(), this.lastValue[i]);
                }
            }

            if (e.getActionCommand().equals("ADD_ALL")) {
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());

                for(int i = 0; i < 3; ++i) {
                    this.lastValue[i] *= 0.9 + 0.2 * Math.random();
                    this.datasets[i].getSeries(0).add(new Millisecond(), this.lastValue[i]);
                }
            }

        }
    }
}
