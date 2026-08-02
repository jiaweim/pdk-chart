package pdk.chart.imagemap;

import org.junit.jupiter.api.Test;
import pdk.chart.model.ChartRenderingInfo;
import pdk.chart.entity.ChartEntity;
import pdk.chart.entity.EntityCollection;
import pdk.chart.entity.StandardEntityCollection;
import pdk.chart.util.StringUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link ImageMapUtils} class.
 */
public class ImageMapUtilsTest {

    /**
     * Some checks for the htmlEscape() method.
     */
    @Test
    public void testHTMLEscape() {
        assertEquals("", ImageMapUtils.htmlEscape(""));
        assertEquals("abc", ImageMapUtils.htmlEscape("abc"));
        assertEquals("&amp;", ImageMapUtils.htmlEscape("&"));
        assertEquals("&quot;", ImageMapUtils.htmlEscape("\""));
        assertEquals("&lt;", ImageMapUtils.htmlEscape("<"));
        assertEquals("&gt;", ImageMapUtils.htmlEscape(">"));
        assertEquals("&#39;", ImageMapUtils.htmlEscape("\'"));
        assertEquals("&#092;abc", ImageMapUtils.htmlEscape("\\abc"));
        assertEquals("abc\n", ImageMapUtils.htmlEscape("abc\n"));
    }

    /**
     * Some checks for the javascriptEscape() method.
     */
    @Test
    public void testJavascriptEscape() {
        assertEquals("", ImageMapUtils.javascriptEscape(""));
        assertEquals("abc", ImageMapUtils.javascriptEscape("abc"));
        assertEquals("\\\'", ImageMapUtils.javascriptEscape("\'"));
        assertEquals("\\\"", ImageMapUtils.javascriptEscape("\""));
        assertEquals("\\\\", ImageMapUtils.javascriptEscape("\\"));
    }

    @Test
    public void testGetImageMap() {
        final Shape shape = new Rectangle(1, 2, 3, 4);
        final Shape shape2 = new Rectangle(5, 6, 7, 8);

        EntityCollection entities = new StandardEntityCollection();

        entities.add(new ChartEntity(shape, "toolTip1", "URL1"));
        entities.add(new ChartEntity(shape2, "toolTip2", "URL2"));

        final String retval = ImageMapUtils.getImageMap("name", new ChartRenderingInfo(entities),
                new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());

        assertEquals("<map id=\"name\" name=\"name\">" + StringUtils.getLineSeparator() +
                "<area shape=\"rect\" coords=\"5,6,12,14\" title=\"toolTip2\" alt=\"\" href=\"URL2\"/>" + StringUtils.getLineSeparator() +
                "<area shape=\"rect\" coords=\"1,2,4,6\" title=\"toolTip1\" alt=\"\" href=\"URL1\"/>" + StringUtils.getLineSeparator() +
                "</map>", retval);
    }

    @Test
    public void testGetImageMapIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ImageMapUtils.getImageMap(null, null, null, null);
        });
    }

    @Test
    public void testGetImageMapIllegalArgumentException_2() {
        assertThrows(IllegalArgumentException.class, () -> {
            ImageMapUtils.getImageMap(null, null);
        });
    }
}
