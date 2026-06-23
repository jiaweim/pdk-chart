package pdk.chart.demo;

import pdk.chart.data.general.DatasetChangeListener;
import pdk.chart.data.xy.AbstractIntervalXYDataset;
import pdk.chart.data.xy.IntervalXYDataset;

public class SimpleIntervalXYDataset extends AbstractIntervalXYDataset implements IntervalXYDataset {
    private Double[] xStart = new Double[3];
    private Double[] xEnd = new Double[3];
    private Double[] yValues = new Double[3];

    public SimpleIntervalXYDataset() {
        this.xStart[0] = 0.0;
        this.xStart[1] = 2.0;
        this.xStart[2] = 3.5;
        this.xEnd[0] = 2.0;
        this.xEnd[1] = 3.5;
        this.xEnd[2] = 4.0;
        this.yValues[0] = 3.0;
        this.yValues[1] = 4.5;
        this.yValues[2] = 2.5;
    }

    public int getSeriesCount() {
        return 1;
    }

    public Comparable getSeriesKey(int series) {
        return "Series 1";
    }

    public int getItemCount(int series) {
        return 3;
    }

    public Number getX(int series, int item) {
        return this.xStart[item];
    }

    public Number getY(int series, int item) {
        return this.yValues[item];
    }

    public Number getStartX(int series, int item) {
        return this.xStart[item];
    }

    public Number getEndX(int series, int item) {
        return this.xEnd[item];
    }

    public Number getStartY(int series, int item) {
        return this.yValues[item];
    }

    public Number getEndY(int series, int item) {
        return this.yValues[item];
    }

    public void addChangeListener(DatasetChangeListener listener) {
    }

    public void removeChangeListener(DatasetChangeListener listener) {
    }
}
