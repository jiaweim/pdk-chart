package pdk.chart.demo;

import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.xy.AbstractXYDataset;
import pdk.chart.data.xy.XYDataset;

public class SampleXYDataset extends AbstractXYDataset implements XYDataset {
    private double translate = (double) 0.0F;

    public double getTranslate() {
        return this.translate;
    }

    public void setTranslate(double translate) {
        this.translate = translate;
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }

    public Number getX(int series, int item) {
        return (double) -10.0F + this.translate + (double) item / (double) 10.0F;
    }

    public Number getY(int series, int item) {
        return series == 0 ? Math.cos((double) -10.0F + this.translate + (double) item / (double) 10.0F) : (double) 2.0F * Math.sin((double) -10.0F + this.translate + (double) item / (double) 10.0F);
    }

    public int getSeriesCount() {
        return 2;
    }

    public Comparable getSeriesKey(int series) {
        if (series == 0) {
            return "y = cosine(x)";
        } else {
            return series == 1 ? "y = 2*sine(x)" : "Error";
        }
    }

    public int getItemCount(int series) {
        return 200;
    }
}
