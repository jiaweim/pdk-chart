package pdk.chart.renderer;

import pdk.chart.util.SerialUtils;

import java.awt.*;
import java.io.*;
import java.util.Objects;

/**
 * Store the paint for a value.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jul 2026, 9:35 AM
 */
public class PaintItem implements Comparable<PaintItem>, Serializable {

    private double value;
    private Paint paint;

    /**
     * Creates a new instance.
     *
     * @param value the value.
     * @param paint the paint.
     */
    public PaintItem(double value, Paint paint) {
        this.value = value;
        this.paint = paint;
    }

    public double getValue() {
        return value;
    }

    public Paint getPaint() {
        return paint;
    }

    @Override
    public int compareTo(PaintItem o) {
        return Double.compare(value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaintItem paintItem)) return false;
        return Double.compare(value, paintItem.value) == 0
                && Objects.equals(paint, paintItem.paint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, paint);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the output stream.
     * @throws IOException if there is an I/O error.
     */
    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtils.writePaint(this.paint, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the input stream.
     * @throws IOException            if there is an I/O error.
     * @throws ClassNotFoundException if there is a classpath problem.
     */
    @Serial
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtils.readPaint(stream);
    }
}
