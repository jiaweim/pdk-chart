package pdk.chart.data.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;

/**
 * A SAX handler for reading a {@link PieDataset} from an XML file.
 */
public class PieDatasetHandler extends RootHandler implements DatasetTags {

    /**
     * The pie dataset under construction.
     */
    private DefaultPieDataset<String> dataset;

    /**
     * Default constructor.
     */
    public PieDatasetHandler() {
        super();
        this.dataset = null;
    }

    /**
     * Returns the dataset.
     *
     * @return The dataset.
     */
    public PieDataset<String> getDataset() {
        return this.dataset;
    }

    /**
     * Adds an item to the dataset under construction.
     *
     * @param key   the key.
     * @param value the value.
     */
    public void addItem(String key, Number value) {
        this.dataset.setValue(key, value);
    }

    /**
     * Starts an element.
     *
     * @param namespaceURI the namespace.
     * @param localName    the element name.
     * @param qName        the element name.
     * @param atts         the element attributes.
     * @throws SAXException for errors.
     */
    @Override
    public void startElement(String namespaceURI,
            String localName,
            String qName,
            Attributes atts) throws SAXException {

        DefaultHandler current = getCurrentHandler();
        if (current != this) {
            current.startElement(namespaceURI, localName, qName, atts);
        } else if (qName.equals(PIEDATASET_TAG)) {
            this.dataset = new DefaultPieDataset<>();
        } else if (qName.equals(ITEM_TAG)) {
            ItemHandler subhandler = new ItemHandler(this, this);
            getSubHandlers().push(subhandler);
            subhandler.startElement(namespaceURI, localName, qName, atts);
        }

    }

    /**
     * The end of an element.
     *
     * @param namespaceURI the namespace.
     * @param localName    the element name.
     * @param qName        the element name.
     * @throws SAXException for errors.
     */
    @Override
    public void endElement(String namespaceURI,
            String localName,
            String qName) throws SAXException {

        DefaultHandler current = getCurrentHandler();
        if (current != this) {
            current.endElement(namespaceURI, localName, qName);
        }

    }

}
