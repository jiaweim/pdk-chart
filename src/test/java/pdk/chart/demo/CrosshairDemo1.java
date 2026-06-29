package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.event.ChartProgressEvent;
import pdk.chart.event.ChartProgressEventType;
import pdk.chart.event.ChartProgressListener;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.data.Range;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.TimeSeriesDataItem;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.NumberCellRenderer;
import pdk.chart.swing.UIUtils;

public class CrosshairDemo1 extends ApplicationFrame {
    public CrosshairDemo1(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        CrosshairDemo1 demo = new CrosshairDemo1("Chart: CrosshairDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel implements ChangeListener, ChartProgressListener {
        private TimeSeries series;
        private final ChartPanel chartPanel;
        private final DemoTableModel model;
        private final Chart chart = this.createChart();
        private final JSlider slider;

        public MyDemoPanel() {
            super(new BorderLayout());
            this.addChart(this.chart);
            this.chart.addProgressListener(this);
            this.chartPanel = new ChartPanel(this.chart);
            this.chartPanel.setPreferredSize(new Dimension(600, 250));
            this.chartPanel.setDomainZoomable(true);
            this.chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());
            this.chartPanel.setBorder(border);
            this.add(this.chartPanel);
            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setPreferredSize(new Dimension(400, 80));
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
            this.model = new DemoTableModel(3);
            XYPlot plot = (XYPlot) this.chart.getPlot();
            this.model.setValueAt(plot.getDataset().getSeriesKey(0), 0, 0);
            this.model.setValueAt((double) 0.0F, 0, 1);
            this.model.setValueAt((double) 0.0F, 0, 2);
            this.model.setValueAt((double) 0.0F, 0, 3);
            this.model.setValueAt((double) 0.0F, 0, 4);
            this.model.setValueAt((double) 0.0F, 0, 5);
            this.model.setValueAt((double) 0.0F, 0, 6);
            JTable table = new JTable(this.model);
            TableCellRenderer renderer1 = new DateCellRenderer(new SimpleDateFormat("HH:mm"));
            TableCellRenderer renderer2 = new NumberCellRenderer();
            table.getColumnModel().getColumn(1).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(3).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(4).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(5).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(6).setCellRenderer(renderer2);
            JScrollPane scroller = new JScrollPane(table);
            dashboard.add(scroller);
            this.slider = new JSlider(0, 100, 50);
            this.slider.addChangeListener(this);
            dashboard.add(this.slider, "South");
            this.add(dashboard, "South");
        }

        private Chart createChart() {
            XYDataset dataset1 = this.createDataset("Random 1", (double) 100.0F, new Minute(), 200);
            Chart chart1 = JChart.timeLine("Crosshair Demo 1", "Time of Day", "Value", dataset1);
            XYPlot plot = (XYPlot) chart1.getPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);
            return chart1;
        }

        private XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {
            this.series = new TimeSeries(name);
            RegularTimePeriod period = start;
            double value = base;

            for (int i = 0; i < count; ++i) {
                this.series.add(period, value);
                period = period.next();
                value *= (double) 1.0F + (Math.random() - 0.495) / (double) 10.0F;
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(this.series);
            return dataset;
        }

        public void stateChanged(ChangeEvent event) {
            int value = this.slider.getValue();
            XYPlot plot = (XYPlot) this.chart.getPlot();
            ValueAxis domainAxis = plot.getDomainAxis();
            Range range = domainAxis.getRange();
            double c = domainAxis.getLowerBound() + (double) value / (double) 100.0F * range.getLength();
            plot.setDomainCrosshairValue(c);
        }

        public void chartProgress(ChartProgressEvent event) {
            if (event.getType() == ChartProgressEventType.DRAWING_FINISHED) {
                if (this.chartPanel != null) {
                    Chart c = this.chartPanel.getChart();
                    if (c != null) {
                        XYPlot plot = (XYPlot) c.getPlot();
                        XYDataset dataset = plot.getDataset();
                        Comparable seriesKey = dataset.getSeriesKey(0);
                        double xx = plot.getDomainCrosshairValue();
                        this.model.setValueAt(seriesKey, 0, 0);
                        long millis = (long) xx;
                        this.model.setValueAt(millis, 0, 1);
                        int itemIndex = this.series.getIndex(new Minute(new Date(millis)));
                        if (itemIndex >= 0) {
                            TimeSeriesDataItem item = this.series.getDataItem(Math.min(199, Math.max(0, itemIndex)));
                            TimeSeriesDataItem prevItem = this.series.getDataItem(Math.max(0, itemIndex - 1));
                            TimeSeriesDataItem nextItem = this.series.getDataItem(Math.min(199, itemIndex + 1));
                            long x = item.getPeriod().getMiddleMillisecond();
                            double y = item.getValue().doubleValue();
                            long prevX = prevItem.getPeriod().getMiddleMillisecond();
                            double prevY = prevItem.getValue().doubleValue();
                            long nextX = nextItem.getPeriod().getMiddleMillisecond();
                            double nextY = nextItem.getValue().doubleValue();
                            this.model.setValueAt(x, 0, 1);
                            this.model.setValueAt(y, 0, 2);
                            this.model.setValueAt(prevX, 0, 3);
                            this.model.setValueAt(prevY, 0, 4);
                            this.model.setValueAt(nextX, 0, 5);
                            this.model.setValueAt(nextY, 0, 6);
                        }
                    }
                }

            }
        }
    }

    static class DemoTableModel extends AbstractTableModel {
        private Object[][] data;

        public DemoTableModel(int rows) {
            this.data = new Object[rows][7];
        }

        public int getColumnCount() {
            return 7;
        }

        public int getRowCount() {
            return 1;
        }

        public Object getValueAt(int row, int column) {
            return this.data[row][column];
        }

        public void setValueAt(Object value, int row, int column) {
            this.data[row][column] = value;
            this.fireTableDataChanged();
        }

        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Series Name:";
                case 1:
                    return "X:";
                case 2:
                    return "Y:";
                case 3:
                    return "X (prev)";
                case 4:
                    return "Y (prev):";
                case 5:
                    return "X (next):";
                case 6:
                    return "Y (next):";
                default:
                    return null;
            }
        }
    }
}
