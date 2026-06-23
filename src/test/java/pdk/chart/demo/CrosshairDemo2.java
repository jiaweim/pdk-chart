package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.*;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.*;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.NumberCellRenderer;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A demo showing a chart with crosshairs. Here the crosshair
 * position is updated only when the user clicks on the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 23 Jun 2026, 12:54 PM
 */
public class CrosshairDemo2 extends ApplicationFrame {

    public CrosshairDemo2(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        CrosshairDemo2 demo = new CrosshairDemo2("JFreeChart: CrosshairDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel
            implements ChartChangeListener, ChartProgressListener {

        private static final int SERIES_COUNT = 4;

        private TimeSeriesCollection<String>[] datasets = new TimeSeriesCollection[4];
        private TimeSeries<String>[] series = new TimeSeries[4];
        private final ChartPanel chartPanel;
        private final DemoTableModel model;

        public MyDemoPanel() {
            super(new BorderLayout());
            JPanel content = new JPanel(new BorderLayout());
            Chart chart = this.createChart();
            this.addChart(chart);
            this.chartPanel = new ChartPanel(chart);
            this.chartPanel.setPreferredSize(new Dimension(600, 270));
            this.chartPanel.setDomainZoomable(true);
            this.chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());
            this.chartPanel.setBorder(border);
            content.add(this.chartPanel);
            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setPreferredSize(new Dimension(400, 120));
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
            this.model = new DemoTableModel(4);

            for (int row = 0; row < SERIES_COUNT; ++row) {
                XYPlot plot = (XYPlot) chart.getPlot();
                this.model.setValueAt(plot.getDataset(row).getSeriesKey(0), row, 0);
                this.model.setValueAt(0.0, row, 1);
                this.model.setValueAt(0.0, row, 2);
                this.model.setValueAt(0.0, row, 3);
                this.model.setValueAt(0.0, row, 4);
                this.model.setValueAt(0.0, row, 5);
                this.model.setValueAt(0.0, row, 6);
            }

            JTable table = new JTable(this.model);
            TableCellRenderer renderer1 = new DateCellRenderer(new SimpleDateFormat("HH:mm:ss"));
            TableCellRenderer renderer2 = new NumberCellRenderer();
            table.getColumnModel().getColumn(1).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(3).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(4).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(5).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(6).setCellRenderer(renderer2);
            dashboard.add(new JScrollPane(table));
            content.add(dashboard, "South");
            this.add(content);
        }

        private XYDataset createDataset(int index, String name, double base, RegularTimePeriod start, int count) {
            this.series[index] = new TimeSeries(name);
            RegularTimePeriod period = start;
            double value = base;

            for (int i = 0; i < count; ++i) {
                this.series[index].add(period, value);
                period = period.next();
                value *= (double) 1.0F + (Math.random() - 0.495) / (double) 10.0F;
            }

            this.datasets[index] = new TimeSeriesCollection();
            this.datasets[index].addSeries(this.series[index]);
            return this.datasets[index];
        }

        public void chartChanged(ChartChangeEvent event) {
            if (this.chartPanel != null) {
                Chart chart = this.chartPanel.getChart();
                if (chart != null) {
                    XYPlot plot = (XYPlot) chart.getPlot();
                    XYDataset dataset = plot.getDataset();
                    Comparable seriesKey = dataset.getSeriesKey(0);
                    double xx = plot.getDomainCrosshairValue();
                    this.model.setValueAt(seriesKey, 0, 0);
                    long millis = (long) xx;

                    for (int row = 0; row < 4; ++row) {
                        this.model.setValueAt(millis, row, 1);
                        int[] bounds = this.datasets[row].getSurroundingItems(0, millis);
                        long prevX = 0L;
                        long nextX = 0L;
                        double prevY = (double) 0.0F;
                        double nextY = (double) 0.0F;
                        if (bounds[0] >= 0) {
                            TimeSeriesDataItem prevItem = this.series[row].getDataItem(bounds[0]);
                            prevX = prevItem.getPeriod().getMiddleMillisecond();
                            Number y = prevItem.getValue();
                            if (y != null) {
                                prevY = y.doubleValue();
                                this.model.setValueAt(prevY, row, 4);
                            } else {
                                this.model.setValueAt((Object) null, row, 4);
                            }

                            this.model.setValueAt(prevX, row, 3);
                        } else {
                            this.model.setValueAt((double) 0.0F, row, 4);
                            this.model.setValueAt(plot.getDomainAxis().getRange().getLowerBound(), row, 3);
                        }

                        if (bounds[1] >= 0) {
                            TimeSeriesDataItem nextItem = this.series[row].getDataItem(bounds[1]);
                            nextX = nextItem.getPeriod().getMiddleMillisecond();
                            Number y = nextItem.getValue();
                            if (y != null) {
                                nextY = y.doubleValue();
                                this.model.setValueAt(nextY, row, 6);
                            } else {
                                this.model.setValueAt((Object) null, row, 6);
                            }

                            this.model.setValueAt(nextX, row, 5);
                        } else {
                            this.model.setValueAt((double) 0.0F, row, 6);
                            this.model.setValueAt(plot.getDomainAxis().getRange().getUpperBound(), row, 5);
                        }

                        double interpolatedY;
                        if (nextX - prevX > 0L) {
                            interpolatedY = prevY + ((double) millis - (double) prevX) / ((double) nextX - (double) prevX) * (nextY - prevY);
                        } else {
                            interpolatedY = prevY;
                        }

                        this.model.setValueAt(interpolatedY, row, 2);
                    }
                }

            }
        }

        private Chart createChart() {
            Chart chart = ChartFactory.createTimeSeriesChart("Crosshair Demo 2", "Time of Day", "Value", (XYDataset) null);
            XYPlot plot = (XYPlot) chart.getPlot();
            XYDataset[] datasets = new XYDataset[4];

            for (int i = 0; i < 4; ++i) {
                datasets[i] = this.createDataset(i, "Series " + i, 100.0 + i * 200.0, new Minute(), 200);
                if (i == 0) {
                    plot.setDataset(datasets[i]);
                } else {
                    plot.setDataset(i, datasets[i]);
                    plot.setRangeAxis(i, new NumberAxis("Axis " + (i + 1)));
                    plot.mapDatasetToRangeAxis(i, i);
                    plot.setRenderer(i, new XYLineAndShapeRenderer(true, false));
                }
            }

            chart.addChangeListener(this);
            chart.addProgressListener(this);
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);
            ChartUtils.applyCurrentTheme(chart);
            return chart;
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

                        for (int i = 0; i < 4; ++i) {
                            int itemIndex = this.series[i].getIndex(new Minute(new Date(millis)));
                            if (itemIndex >= 0) {
                                TimeSeriesDataItem item = this.series[i].getDataItem(Math.min(199, Math.max(0, itemIndex)));
                                TimeSeriesDataItem prevItem = this.series[i].getDataItem(Math.max(0, itemIndex - 1));
                                TimeSeriesDataItem nextItem = this.series[i].getDataItem(Math.min(199, itemIndex + 1));
                                long x = item.getPeriod().getMiddleMillisecond();
                                double y = item.getValue().doubleValue();
                                long prevX = prevItem.getPeriod().getMiddleMillisecond();
                                double prevY = prevItem.getValue().doubleValue();
                                long nextX = nextItem.getPeriod().getMiddleMillisecond();
                                double nextY = nextItem.getValue().doubleValue();
                                this.model.setValueAt(x, i, 1);
                                this.model.setValueAt(y, i, 2);
                                this.model.setValueAt(prevX, i, 3);
                                this.model.setValueAt(prevY, i, 4);
                                this.model.setValueAt(nextX, i, 5);
                                this.model.setValueAt(nextY, i, 6);
                            }
                        }
                    }

                }
            }
        }
    }

    static class DemoTableModel extends AbstractTableModel implements TableModel {

        private Object[][] data;

        public DemoTableModel(int rows) {
            this.data = new Object[rows][7];
        }

        public int getColumnCount() {
            return 7;
        }

        public int getRowCount() {
            return this.data.length;
        }

        public Object getValueAt(int row, int column) {
            return this.data[row][column];
        }

        public void setValueAt(Object value, int row, int column) {
            this.data[row][column] = value;
            this.fireTableDataChanged();
        }

        public String getColumnName(int column) {
            return switch (column) {
                case 0 -> "Series Name:";
                case 1 -> "X:";
                case 2 -> "Y:";
                case 3 -> "X (prev)";
                case 4 -> "Y (prev):";
                case 5 -> "X (next):";
                case 6 -> "Y (next):";
                default -> null;
            };
        }
    }
}
