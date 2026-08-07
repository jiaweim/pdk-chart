package pdk.chart.data.general;

/**
 * A default implementation of the {@link KeyedValuesDataset} interface.
 * This is an alias for {@link DefaultPieDataset}.
 *
 * @param <K> the key type.
 */
public class DefaultKeyedValuesDataset<K extends Comparable<K>>
        extends DefaultPieDataset<K> implements KeyedValuesDataset<K> {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 306264413152815781L;

    /**
     * Creates a new empty dataset.
     */
    public DefaultKeyedValuesDataset() {
        super();
    }

}
