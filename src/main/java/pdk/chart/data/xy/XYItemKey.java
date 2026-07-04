package pdk.chart.data.xy;

import pdk.chart.data.ItemKey;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * An object that references one data item in an {@link XYZDataset}.  This is
 * used internally to track the data item that a 3D object is related to, if
 * any (and later that link is used for chart interaction).  Instances of
 * this class are immutable.
 *
 * @param <S> the series key type.
 */
public class XYItemKey<S extends Comparable<S>> implements ItemKey,
        Comparable<XYItemKey<S>>, Serializable {

    /**
     * A key identifying a series in the dataset.
     */
    private final S seriesKey;

    /**
     * The index of an item within a series.
     */
    private final int itemIndex;

    /**
     * Creates a new instance.
     *
     * @param seriesKey the series key.
     * @param itemIndex the item index.
     */
    public XYItemKey(S seriesKey, int itemIndex) {
        Args.nullNotPermitted(seriesKey, "seriesKey");
        this.seriesKey = seriesKey;
        this.itemIndex = itemIndex;
    }

    /**
     * Returns the series key.
     *
     * @return The series key (never {@code null}).
     */
    public S getSeriesKey() {
        return this.seriesKey;
    }

    /**
     * Returns the item index.
     *
     * @return The item index.
     */
    public int getItemIndex() {
        return this.itemIndex;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object to test ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYItemKey)) {
            return false;
        }
        XYItemKey that = (XYItemKey) obj;
        if (!this.seriesKey.equals(that.seriesKey)) {
            return false;
        }
        if (this.itemIndex != that.itemIndex) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.seriesKey);
        hash = 41 * hash + this.itemIndex;
        return hash;
    }

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"seriesKey\": \"").append(this.seriesKey.toString());
        sb.append("\", ");
        sb.append("\"itemIndex\": ").append(this.itemIndex).append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("XYItemKey[seriesKey=");
        sb.append(this.seriesKey.toString()).append(",item=");
        sb.append(itemIndex);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int compareTo(XYItemKey<S> key) {
        int result = this.seriesKey.compareTo(key.seriesKey);
        if (result == 0) {
            result = this.itemIndex - key.itemIndex;
        }
        return result;
    }

}

