package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.Millisecond;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 23 Jun 2026, 1:09 PM
 */
public class MemoryUsageDemo extends JPanel {

    private final TimeSeries total = new TimeSeries("Total Memory");
    private final TimeSeries free;

    public MemoryUsageDemo(int maxAge) {
        super(new BorderLayout());
        this.total.setMaximumItemAge(maxAge);
        this.free = new TimeSeries("Free Memory");
        this.free.setMaximumItemAge(maxAge);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(this.total);
        dataset.addSeries(this.free);
        DateAxis domain = new DateAxis("Time");
        NumberAxis range = new NumberAxis("Memory");
        domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
        range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
        range.setNumberFormatOverride(new DecimalFormat("#,##0"));
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        Chart chart = new Chart("JVM Memory Usage", new Font("SansSerif", Font.BOLD, 24), plot, true);
        ChartUtils.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));
        this.add(chartPanel);
    }

    private void addTotalObservation(double y) {
        this.total.add(new Millisecond(), y);
    }

    private void addFreeObservation(double y) {
        this.free.add(new Millisecond(), y);
    }

    static void main() {
        JFrame frame = new JFrame("Memory Usage Demo");
        MemoryUsageDemo panel = new MemoryUsageDemo(30000);
        frame.getContentPane().add(panel, "Center");
        frame.setBounds(200, 120, 600, 280);
        frame.setVisible(true);
        Objects.requireNonNull(panel);
        (new DataGenerator(panel, 100)).start();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static class DataGenerator extends Timer implements ActionListener {

        private MemoryUsageDemo demo;

        DataGenerator(MemoryUsageDemo demo, int interval) {
            super(interval, null);
            this.addActionListener(this);
            this.demo = demo;
        }

        public void actionPerformed(ActionEvent event) {
            long f = Runtime.getRuntime().freeMemory();
            long t = Runtime.getRuntime().totalMemory();
            demo.addTotalObservation((double) t);
            demo.addFreeObservation((double) f);
        }
    }
}
