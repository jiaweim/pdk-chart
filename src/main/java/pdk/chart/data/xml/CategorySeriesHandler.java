package pdk.chart.data.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pdk.chart.data.DefaultKeyedValues;

/**
 * A handler for reading a series for a category dataset.
 */
public class CategorySeriesHandler extends DefaultHandler
        implements DatasetTags {

    /**
     * The root handler.
     */
    private final RootHandler root;

    /**
     * The series key.
     */
    private String seriesKey;

    /**
     * The values.
     */
    private final DefaultKeyedValues<String> values;

    /**
     * Creates a new item handler.
     *
     * @param root the root handler.
     */
    public CategorySeriesHandler(RootHandler root) {
        super();
        this.root = root;
        this.values = new DefaultKeyedValues<>();
    }

    /**
     * Sets the series key.
     *
     * @param key the key.
     */
    public void setSeriesKey(String key) {
        this.seriesKey = key;
    }

    /**
     * Adds an item to the temporary storage for the series.
     *
     * @param key   the key.
     * @param value the value.
     */
    public void addItem(String key, Number value) {
        this.values.addValue(key, value);
    }

    /**
     * The start of an element.
     *
     * @param namespaceURI the namespace.
     * @param localName    the element name.
     * @param qName        the element name.
     * @param atts         the attributes.
     * @throws SAXException for errors.
     */
    @Override
    public void startElement(String namespaceURI,
            String localName,
            String qName,
            Attributes atts) throws SAXException {

        if (qName.equals(SERIES_TAG)) {
            setSeriesKey(atts.getValue("name"));
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
        } else if (qName.equals(ITEM_TAG)) {
            ItemHandler subhandler = new ItemHandler(this.root, this);
            this.root.pushSubHandler(subhandler);
            subhandler.startElement(namespaceURI, localName, qName, atts);
        } else {
            throw new SAXException(
                    "Expecting <Series> or <Item> tag...found " + qName
            );
        }
    }

    /**
     * The end of an element.
     *
     * @param namespaceURI the namespace.
     * @param localName    the element name.
     * @param qName        the element name.
     */
    @Override
    public void endElement(String namespaceURI,
            String localName,
            String qName) {

        if (this.root instanceof CategoryDatasetHandler) {
            CategoryDatasetHandler handler = (CategoryDatasetHandler) this.root;

            for (String key : this.values.getKeys()) {
                Number value = this.values.getValue(key);
                handler.addItem(this.seriesKey, key, value);
            }

            this.root.popSubHandler();
        }

    }
}
