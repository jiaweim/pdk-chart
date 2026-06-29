package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.data.time.Millisecond;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class DynamicDataDemo1 extends ApplicationFrame {
    public DynamicDataDemo1(String title) {
        super(title);
        MyDemoPanel demoPanel = new MyDemoPanel();
        this.setContentPane(demoPanel);
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        DynamicDataDemo1 demo = new DynamicDataDemo1("Chart: DynamicDataDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private TimeSeries series = new TimeSeries("Random Data");
        private double lastValue = (double)100.0F;

        public MyDemoPanel() {
            super(new BorderLayout());
            TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
            ChartPanel chartPanel = new ChartPanel(this.createChart(dataset), false);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            this.addChart(chartPanel.getChart());
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JButton button = new JButton("Add New Data Item");
            button.setActionCommand("ADD_DATA");
            button.addActionListener(this);
            buttonPanel.add(button);
            this.add(chartPanel);
            this.add(buttonPanel, "South");
        }

        private Chart createChart(XYDataset dataset) {
            Chart result = ChartFactory.timeLine("Dynamic Data Demo", "Time", "Value", dataset);
            XYPlot plot = (XYPlot)result.getPlot();
            ValueAxis axis = plot.getDomainAxis();
            axis.setAutoRange(true);
            axis.setFixedAutoRange((double)60000.0F);
            axis = plot.getRangeAxis();
            axis.setRange((double)0.0F, (double)200.0F);
            return result;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("ADD_DATA")) {
                double factor = 0.9 + 0.2 * Math.random();
                this.lastValue *= factor;
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                this.series.add(new Millisecond(), this.lastValue);
            }

        }
    }
}
