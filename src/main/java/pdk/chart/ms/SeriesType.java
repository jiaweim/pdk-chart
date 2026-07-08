package pdk.chart.ms;

import java.awt.*;

/**
 * type of series in {@link PSMPlot}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 6:12 PM
 */
public enum SeriesType implements Comparable<SeriesType> {

    mz(new Color(90, 90, 90), 1),
    b(new Color(0, 99, 115), 2),
    y(new Color(190, 9, 0), 2),
    c(new Color(99, 110, 250), 2),
    z(new Color(239, 85, 59), 2),
    zdot(new Color(239, 85, 59), 2),
    p(new Color(118, 0, 161), 2);

    private final Color color;
    private final int stokeWidth;

    SeriesType(Color color, int stokeWidth) {
        this.color = color;
        this.stokeWidth = stokeWidth;
    }

    public Color getColor() {
        return color;
    }

    /**
     * @return the stroke width used to render peaks.
     */
    public int getStokeWidth() {
        return stokeWidth;
    }

    /**
     * Return true if it is an N-terminal fragment type.
     *
     * @return true if it is an n-TERMINAL FRAGMENT type.
     */
    public boolean isNTerminal() {
        return this == SeriesType.y || this == SeriesType.z;
    }
}
