package pdk.chart.labels;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XisSymbolic;
import pdk.chart.data.xy.YisSymbolic;
import pdk.chart.util.Args;

import java.io.Serializable;

/**
 * A standard item label generator for plots that use data from an
 * {@link XYDataset}.
 */
public class SymbolicXYItemLabelGenerator implements XYItemLabelGenerator,
        XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 3963400354475494395L;

    /**
     * Creates a new default instance.
     */
    public SymbolicXYItemLabelGenerator() {
        super();
    }

    /**
     * Generates a tool tip text item for a particular item within a series.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series number (zero-based index).
     * @param item    the item number (zero-based index).
     * @return The tool tip text (possibly {@code null}).
     */
    @Override
    public String generateToolTip(XYDataset dataset, int series, int item) {
        Args.nullNotPermitted(dataset, "dataset");
        String xStr, yStr;
        if (dataset instanceof YisSymbolic) {
            yStr = ((YisSymbolic) dataset).getYSymbolicValue(series, item);
        } else {
            double y = dataset.getYValue(series, item);
            yStr = Double.toString(round(y, 2));
        }
        if (dataset instanceof XisSymbolic) {
            xStr = ((XisSymbolic) dataset).getXSymbolicValue(series, item);
        } else if (dataset instanceof TimeSeriesCollection) {
            RegularTimePeriod p
                    = ((TimeSeriesCollection) dataset).getSeries(series)
                    .getTimePeriod(item);
            xStr = p.toString();
        } else {
            double x = dataset.getXValue(series, item);
            xStr = Double.toString(round(x, 2));
        }
        return "X: " + xStr + ", Y: " + yStr;
    }

    /**
     * Generates a label for the specified item. The label is typically a
     * formatted version of the data value, but any text can be used.
     *
     * @param dataset  the dataset ({@code null} not permitted).
     * @param series   the series index (zero-based).
     * @param category the category index (zero-based).
     * @return The label (possibly {@code null}).
     */
    @Override
    public String generateLabel(XYDataset dataset, int series, int category) {
        return null;  //TODO: implement this method properly
    }

    /**
     * Round a double value.
     *
     * @param value the value.
     * @param nb    the exponent.
     * @return The rounded value.
     */
    private static double round(double value, int nb) {
        if (nb <= 0) {
            return Math.floor(value + 0.5d);
        }
        double p = Math.pow(10, nb);
        double tempval = Math.floor(value * p + 0.5d);
        return tempval / p;
    }

    /**
     * Returns an independent copy of the generator.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj the other object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SymbolicXYItemLabelGenerator) {
            return true;
        }
        return false;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = 127;
        return result;
    }

}
