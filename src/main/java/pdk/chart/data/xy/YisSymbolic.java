package pdk.chart.data.xy;

/**
 * Represent a data set where Y is a symbolic values. Each symbolic value is
 * linked with an Integer.
 */
public interface YisSymbolic {

    /**
     * Returns the list of symbolic values.
     *
     * @return The symbolic values.
     */
    String[] getYSymbolicValues();

    /**
     * Returns the symbolic value of the data set specified by
     * {@code series} and {@code item} parameters.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The symbolic value.
     */
    String getYSymbolicValue(int series, int item);

    /**
     * Returns the symbolic value linked with the specified {@code Integer}.
     *
     * @param val value of the integer linked with the symbolic value.
     * @return The symbolic value.
     */
    String getYSymbolicValue(Integer val);

}
