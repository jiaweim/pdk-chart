package pdk.chart.urls;

import pdk.chart.data.xy.XYDataset;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * A URL generator.
 */
public class StandardXYURLGenerator implements XYURLGenerator, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -1771624523496595382L;

    /**
     * The default prefix.
     */
    public static final String DEFAULT_PREFIX = "index.html";

    /**
     * The default series parameter.
     */
    public static final String DEFAULT_SERIES_PARAMETER = "series";

    /**
     * The default item parameter.
     */
    public static final String DEFAULT_ITEM_PARAMETER = "item";

    /**
     * Prefix to the URL
     */
    private String prefix;

    /**
     * Series parameter name to go in each URL
     */
    private String seriesParameterName;

    /**
     * Item parameter name to go in each URL
     */
    private String itemParameterName;

    /**
     * Creates a new default generator.  This constructor is equivalent to
     * calling {@code StandardXYURLGenerator("index.html", "series", "item");}.
     */
    public StandardXYURLGenerator() {
        this(DEFAULT_PREFIX, DEFAULT_SERIES_PARAMETER, DEFAULT_ITEM_PARAMETER);
    }

    /**
     * Creates a new generator with the specified prefix.  This constructor
     * is equivalent to calling
     * {@code StandardXYURLGenerator(prefix, "series", "item");}.
     *
     * @param prefix the prefix to the URL ({@code null} not permitted).
     */
    public StandardXYURLGenerator(String prefix) {
        this(prefix, DEFAULT_SERIES_PARAMETER, DEFAULT_ITEM_PARAMETER);
    }

    /**
     * Constructor that overrides all the defaults
     *
     * @param prefix              the prefix to the URL ({@code null} not permitted).
     * @param seriesParameterName the name of the series parameter to go in
     *                            each URL ({@code null} not permitted).
     * @param itemParameterName   the name of the item parameter to go in each
     *                            URL ({@code null} not permitted).
     */
    public StandardXYURLGenerator(String prefix, String seriesParameterName,
            String itemParameterName) {
        Args.nullNotPermitted(prefix, "prefix");
        Args.nullNotPermitted(seriesParameterName, "seriesParameterName");
        Args.nullNotPermitted(itemParameterName, "itemParameterName");
        this.prefix = prefix;
        this.seriesParameterName = seriesParameterName;
        this.itemParameterName = itemParameterName;
    }

    /**
     * Generates a URL for a particular item within a series.
     *
     * @param dataset the dataset.
     * @param series  the series number (zero-based index).
     * @param item    the item number (zero-based index).
     * @return The generated URL.
     */
    @Override
    public String generateURL(XYDataset dataset, int series, int item) {
        // TODO: URLEncode?
        String url = this.prefix;
        boolean firstParameter = !url.contains("?");
        url += firstParameter ? "?" : "&amp;";
        url += this.seriesParameterName + "=" + series
                + "&amp;" + this.itemParameterName + "=" + item;
        return url;
    }

    /**
     * Tests this generator for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYURLGenerator)) {
            return false;
        }
        StandardXYURLGenerator that = (StandardXYURLGenerator) obj;
        if (!Objects.equals(that.prefix, this.prefix)) {
            return false;
        }
        if (!Objects.equals(that.seriesParameterName, this.seriesParameterName)) {
            return false;
        }
        if (!Objects.equals(that.itemParameterName, this.itemParameterName)) {
            return false;
        }
        return true;
    }

}
