package pdk.chart.data.xml;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.util.Args;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A utility class for reading datasets from XML.
 */
public class DatasetReader {

    /**
     * A factory for creating new parser instances.
     */
    static SAXParserFactory factory;

    private DatasetReader() {}

    /**
     * Returns the {@link SAXParserFactory} used to create {@link SAXParser} instances.
     *
     * @return The {@link SAXParserFactory} (never {@code null}).
     */
    public static SAXParserFactory getSAXParserFactory() {
        if (factory == null) {
            SAXParserFactory f = SAXParserFactory.newInstance();
            try {
                f.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                f.setFeature("http://xml.org/sax/features/external-general-entities", false);
                factory = f;
            } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return factory;
    }

    /**
     * Sets the SAXParserFactory that will be used to create SAXParser instances.
     * You would only call this method if you wish to configure a new factory because
     * the default does not meet requirements.
     *
     * @param f the new factory ({@code null} not permitted).
     */
    public static void setSAXParserFactory(SAXParserFactory f) {
        Args.nullNotPermitted(f, "f");
        factory = f;
    }

    /**
     * Reads a {@link PieDataset} from an XML file.
     *
     * @param file the file ({@code null} not permitted).
     * @return A dataset.
     * @throws IOException if there is a problem reading the file.
     */
    public static PieDataset<String> readPieDatasetFromXML(File file)
            throws IOException {
        InputStream in = new FileInputStream(file);
        return readPieDatasetFromXML(in);
    }

    /**
     * Reads a {@link PieDataset} from a stream.
     *
     * @param in the input stream.
     * @return A dataset.
     * @throws IOException if there is an I/O error.
     */
    public static PieDataset<String> readPieDatasetFromXML(InputStream in)
            throws IOException {
        try {
            SAXParser parser = getSAXParserFactory().newSAXParser();
            PieDatasetHandler handler = new PieDatasetHandler();
            parser.parse(in, handler);
            return handler.getDataset();
        } catch (SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a {@link CategoryDataset} from a file.
     *
     * @param file the file.
     * @return A dataset.
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset readCategoryDatasetFromXML(File file)
            throws IOException {
        InputStream in = new FileInputStream(file);
        return readCategoryDatasetFromXML(in);
    }

    /**
     * Reads a {@link CategoryDataset} from a stream.
     *
     * @param in the stream.
     * @return A dataset.
     * @throws IOException if there is a problem reading the file.
     */
    public static CategoryDataset<String, String> readCategoryDatasetFromXML(InputStream in)
            throws IOException {
        try {
            SAXParser parser = getSAXParserFactory().newSAXParser();
            CategoryDatasetHandler handler = new CategoryDatasetHandler();
            parser.parse(in, handler);
            return handler.getDataset();
        } catch (SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

}