package pdk.chart.axis;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link SymbolAxis} class.
 */
public class SymbolAxisTest {

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        String[] tickLabels = new String[]{"One", "Two", "Three"};
        SymbolAxis a1 = new SymbolAxis("Test Axis", tickLabels);
        SymbolAxis a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        SymbolAxis a1 = new SymbolAxis("Axis", new String[]{"A", "B"});
        SymbolAxis a2 = CloneUtils.clone(a1);
        assertNotSame(a1, a2);
        assertSame(a1.getClass(), a2.getClass());
        assertEquals(a1, a2);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        SymbolAxis a1 = new SymbolAxis("Axis", new String[]{"A", "B"});
        SymbolAxis a2 = new SymbolAxis("Axis", new String[]{"A", "B"});
        assertEquals(a1, a2);
        assertEquals(a2, a1);

        a1 = new SymbolAxis("Axis 2", new String[]{"A", "B"});
        assertNotEquals(a1, a2);
        a2 = new SymbolAxis("Axis 2", new String[]{"A", "B"});
        assertEquals(a1, a2);

        a1 = new SymbolAxis("Axis 2", new String[]{"C", "B"});
        assertNotEquals(a1, a2);
        a2 = new SymbolAxis("Axis 2", new String[]{"C", "B"});
        assertEquals(a1, a2);

        a1.setGridBandsVisible(false);
        assertNotEquals(a1, a2);
        a2.setGridBandsVisible(false);
        assertEquals(a1, a2);

        a1.setGridBandPaint(Color.BLACK);
        assertNotEquals(a1, a2);
        a2.setGridBandPaint(Color.BLACK);
        assertEquals(a1, a2);

        a1.setGridBandAlternatePaint(Color.RED);
        assertNotEquals(a1, a2);
        a2.setGridBandAlternatePaint(Color.RED);
        assertEquals(a1, a2);
    }

}
