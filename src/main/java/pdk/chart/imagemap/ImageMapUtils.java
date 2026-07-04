package pdk.chart.imagemap;

import pdk.chart.ChartRenderingInfo;
import pdk.chart.JChartUtils;
import pdk.chart.entity.ChartEntity;
import pdk.chart.entity.EntityCollection;
import pdk.chart.util.Args;
import pdk.chart.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Collection of utility methods related to producing image maps.
 * Functionality was originally in {@link JChartUtils}.
 */
public class ImageMapUtils {

    private ImageMapUtils() {
        // no requirement to instantiate
    }

    /**
     * Writes an image map to an output stream.
     *
     * @param writer the writer ({@code null} not permitted).
     * @param name   the map name ({@code null} not permitted).
     * @param info   the chart rendering info ({@code null} not permitted).
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer, String name,
            ChartRenderingInfo info) throws IOException {

        // defer argument checking...
        writeImageMap(writer, name, info,
                new StandardToolTipTagFragmentGenerator(),
                new StandardURLTagFragmentGenerator());

    }

    /**
     * Writes an image map to an output stream.
     *
     * @param writer                the writer ({@code null} not permitted).
     * @param name                  the map name ({@code null} not permitted).
     * @param info                  the chart rendering info ({@code null} not permitted).
     * @param useOverLibForToolTips whether to use OverLIB for tooltips
     *                              (http://www.bosrup.com/web/overlib/).
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer,
            String name, ChartRenderingInfo info,
            boolean useOverLibForToolTips) throws IOException {

        ToolTipTagFragmentGenerator toolTipTagFragmentGenerator;
        if (useOverLibForToolTips) {
            toolTipTagFragmentGenerator
                    = new OverLIBToolTipTagFragmentGenerator();
        } else {
            toolTipTagFragmentGenerator
                    = new StandardToolTipTagFragmentGenerator();
        }
        writeImageMap(writer, name, info,
                toolTipTagFragmentGenerator,
                new StandardURLTagFragmentGenerator());

    }

    /**
     * Writes an image map to an output stream.
     *
     * @param writer                      the writer ({@code null} not permitted).
     * @param name                        the map name ({@code null} not permitted).
     * @param info                        the chart rendering info ({@code null} not permitted).
     * @param toolTipTagFragmentGenerator a generator for the HTML fragment
     *                                    that will contain the tooltip text ({@code null} not permitted
     *                                    if {@code info} contains tooltip information).
     * @param urlTagFragmentGenerator     a generator for the HTML fragment that
     *                                    will contain the URL reference ({@code null} not permitted if
     *                                    {@code info} contains URLs).
     * @throws java.io.IOException if there are any I/O errors.
     */
    public static void writeImageMap(PrintWriter writer, String name,
            ChartRenderingInfo info,
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator)
            throws IOException {

        writer.println(ImageMapUtils.getImageMap(name, info,
                toolTipTagFragmentGenerator, urlTagFragmentGenerator));
    }

    /**
     * Creates an image map element that complies with the XHTML 1.0
     * specification.
     *
     * @param name the map name ({@code null} not permitted).
     * @param info the chart rendering info ({@code null} not permitted).
     * @return The map element.
     */
    public static String getImageMap(String name, ChartRenderingInfo info) {
        return ImageMapUtils.getImageMap(name, info,
                new StandardToolTipTagFragmentGenerator(),
                new StandardURLTagFragmentGenerator());
    }

    /**
     * Creates an image map element that complies with the XHTML 1.0
     * specification.
     *
     * @param name                        the map name ({@code null} not permitted).
     * @param info                        the chart rendering info ({@code null} not permitted).
     * @param toolTipTagFragmentGenerator a generator for the HTML fragment
     *                                    that will contain the tooltip text ({@code null} not permitted
     *                                    if {@code info} contains tooltip information).
     * @param urlTagFragmentGenerator     a generator for the HTML fragment that
     *                                    will contain the URL reference ({@code null} not permitted if
     *                                    {@code info} contains URLs).
     * @return The map tag.
     */
    public static String getImageMap(String name, ChartRenderingInfo info,
            ToolTipTagFragmentGenerator toolTipTagFragmentGenerator,
            URLTagFragmentGenerator urlTagFragmentGenerator) {

        StringBuilder sb = new StringBuilder();
        sb.append("<map id=\"").append(htmlEscape(name));
        sb.append("\" name=\"").append(htmlEscape(name)).append("\">");
        sb.append(StringUtils.getLineSeparator());
        EntityCollection entities = info.getEntityCollection();
        if (entities != null) {
            int count = entities.getEntityCount();
            for (int i = count - 1; i >= 0; i--) {
                ChartEntity entity = entities.getEntity(i);
                if (entity.getToolTipText() != null
                        || entity.getURLText() != null) {
                    String area = entity.getImageMapAreaTag(
                            toolTipTagFragmentGenerator,
                            urlTagFragmentGenerator);
                    if (area.length() > 0) {
                        sb.append(area);
                        sb.append(StringUtils.getLineSeparator());
                    }
                }
            }
        }
        sb.append("</map>");
        return sb.toString();

    }

    /**
     * Returns a string that is equivalent to the input string, but with
     * special characters converted to HTML escape sequences.
     *
     * @param input the string to escape ({@code null} not permitted).
     * @return A string with characters escaped.
     */
    public static String htmlEscape(String input) {
        Args.nullNotPermitted(input, "input");
        StringBuilder result = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (c == '&') {
                result.append("&amp;");
            } else if (c == '\"') {
                result.append("&quot;");
            } else if (c == '<') {
                result.append("&lt;");
            } else if (c == '>') {
                result.append("&gt;");
            } else if (c == '\'') {
                result.append("&#39;");
            } else if (c == '\\') {
                result.append("&#092;");
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Returns a string that is equivalent to the input string, but with
     * special characters converted to JavaScript escape sequences.
     *
     * @param input the string to escape ({@code null} not permitted).
     * @return A string with characters escaped.
     */
    public static String javascriptEscape(String input) {
        Args.nullNotPermitted(input, "input");
        StringBuilder result = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (c == '\"') {
                result.append("\\\"");
            } else if (c == '\'') {
                result.append("\\'");
            } else if (c == '\\') {
                result.append("\\\\");
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
