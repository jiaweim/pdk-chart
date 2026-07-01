package pdk.chart.data.xy;

/**
 * Represent a data set where X is a symbolic values. Each symbolic value is
 * linked with an Integer.
 */
public interface XisSymbolic {

    /**
     * Returns the list of symbolic values.
     *
     * @return An array of symbolic values.
     */
    String[] getXSymbolicValues();

    /**
     * Returns the symbolic value of the data set specified by
     * {@code series} and {@code item} parameters.
     *
     * @param series value of the serie.
     * @param item   value of the item.
     * @return The symbolic value.
     */
    String getXSymbolicValue(int series, int item);

    /**
     * Returns the symbolic value linked with the specified {@code Integer}.
     *
     * @param val value of the integer linked with the symbolic value.
     * @return The symbolic value.
     */
    String getXSymbolicValue(Integer val);

}
