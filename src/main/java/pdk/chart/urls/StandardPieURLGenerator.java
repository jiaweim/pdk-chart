package pdk.chart.urls;

import pdk.chart.data.general.PieDataset;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * A URL generator for pie charts.  Instances of this class are immutable.
 *
 * @param <K> the dataset key type.
 */
public class StandardPieURLGenerator<K extends Comparable<K>> implements PieURLGenerator<K>, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1626966402065883419L;

    /**
     * The prefix.
     */
    private String prefix = "index.html";

    /**
     * The category parameter name.
     */
    private String categoryParamName = "category";

    /**
     * The pie index parameter name.
     */
    private String indexParamName = "pieIndex";

    /**
     * Default constructor.
     */
    public StandardPieURLGenerator() {
        this("index.html");
    }

    /**
     * Creates a new generator.
     *
     * @param prefix the prefix ({@code null} not permitted).
     */
    public StandardPieURLGenerator(String prefix) {
        this(prefix, "category");
    }

    /**
     * Creates a new generator.
     *
     * @param prefix            the prefix ({@code null} not permitted).
     * @param categoryParamName the category parameter name ({@code null} not
     *                          permitted).
     */
    public StandardPieURLGenerator(String prefix, String categoryParamName) {
        this(prefix, categoryParamName, "pieIndex");
    }

    /**
     * Creates a new generator.
     *
     * @param prefix            the prefix ({@code null} not permitted).
     * @param categoryParamName the category parameter name ({@code null} not
     *                          permitted).
     * @param indexParamName    the index parameter name ({@code null} permitted).
     */
    public StandardPieURLGenerator(String prefix, String categoryParamName,
            String indexParamName) {
        Args.nullNotPermitted(prefix, "prefix");
        Args.nullNotPermitted(categoryParamName, "categoryParamName");
        this.prefix = prefix;
        this.categoryParamName = categoryParamName;
        this.indexParamName = indexParamName;
    }

    /**
     * Generates a URL.
     *
     * @param dataset  the dataset (ignored).
     * @param key      the item key ({@code null} not permitted).
     * @param pieIndex the pie index.
     * @return A string containing the generated URL.
     */
    @Override
    public String generateURL(PieDataset<K> dataset, K key, int pieIndex) {
        String url = this.prefix;
        try {
            if (url.contains("?")) {
                url += "&amp;" + this.categoryParamName + "="
                        + URLEncoder.encode(key.toString(), "UTF-8");
            } else {
                url += "?" + this.categoryParamName + "="
                        + URLEncoder.encode(key.toString(), "UTF-8");
            }
            if (this.indexParamName != null) {
                url += "&amp;" + this.indexParamName + "=" + pieIndex;
            }
        } catch (UnsupportedEncodingException e) {  // this won't happen :)
            throw new RuntimeException(e);
        }
        return url;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardPieURLGenerator)) {
            return false;
        }
        StandardPieURLGenerator<K> that = (StandardPieURLGenerator<K>) obj;
        if (!this.prefix.equals(that.prefix)) {
            return false;
        }
        if (!this.categoryParamName.equals(that.categoryParamName)) {
            return false;
        }
        if (!Objects.equals(this.indexParamName, that.indexParamName)) {
            return false;
        }
        return true;
    }
}
