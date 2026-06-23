package pdk.chart.demo;

import pdk.chart.data.xy.AbstractXYZDataset;
import pdk.chart.data.xy.XYZDataset;

public class SampleXYZDataset2 extends AbstractXYZDataset implements XYZDataset {
    private double[][] xVal = new double[][]{{(double) 1.0F, (double) 2.0F, (double) 3.0F}, {(double) 4.0F, (double) 5.0F, (double) 6.0F}};
    private double[][] yVal = new double[][]{{(double) 1.0F, (double) 2.0F, (double) 3.0F}, {(double) 4.0F, (double) 5.0F, (double) 6.0F}};
    private double[][] zVal = new double[][]{{1.1, 2.2, 3.3}, {4.4, (double) 5.5F, 6.6}};

    public int getSeriesCount() {
        return this.xVal.length;
    }

    public Comparable getSeriesKey(int series) {
        return "Series " + series;
    }

    public int getItemCount(int series) {
        return this.xVal[0].length;
    }

    public Number getX(int series, int item) {
        return this.xVal[series][item];
    }

    public Number getY(int series, int item) {
        return this.yVal[series][item];
    }

    public Number getZ(int series, int item) {
        return this.zVal[series][item];
    }
}
