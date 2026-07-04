package pdk.chart.entity;

import pdk.chart.Chart;
import pdk.chart.util.Args;
import pdk.chart.util.HashUtils;
import pdk.chart.util.SerialUtils;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * A class that captures information about an entire chart.
 */
public class JFreeChartEntity extends ChartEntity {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -4445994133561919083L;
    //same as for ChartEntity!

    /**
     * The chart.
     */
    private final Chart chart;

    /**
     * Creates a new chart entity.
     *
     * @param area  the area ({@code null} not permitted).
     * @param chart the chart ({@code null} not permitted).
     */
    public JFreeChartEntity(Shape area, Chart chart) {
        // defer argument checks...
        this(area, chart, null);
    }

    /**
     * Creates a new chart entity.
     *
     * @param area        the area ({@code null} not permitted).
     * @param chart       the chart ({@code null} not permitted).
     * @param toolTipText the tool tip text ({@code null} permitted).
     */
    public JFreeChartEntity(Shape area, Chart chart, String toolTipText) {
        // defer argument checks...
        this(area, chart, toolTipText, null);
    }

    /**
     * Creates a new chart entity.
     *
     * @param area        the area ({@code null} not permitted).
     * @param chart       the chart ({@code null} not permitted).
     * @param toolTipText the tool tip text ({@code null} permitted).
     * @param urlText     the URL text for HTML image maps ({@code null}
     *                    permitted).
     */
    public JFreeChartEntity(Shape area, Chart chart, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        Args.nullNotPermitted(chart, "chart");
        this.chart = chart;
    }

    /**
     * Returns the chart that occupies the entity area.
     *
     * @return The chart (never {@code null}).
     */
    public Chart getChart() {
        return this.chart;
    }

    /**
     * Returns a string representation of the chart entity, useful for
     * debugging.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JFreeChartEntity: ");
        sb.append("tooltip = ");
        sb.append(getToolTipText());
        return sb.toString();
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     *
     * @param obj the object to test against ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JFreeChartEntity)) {
            return false;
        }
        JFreeChartEntity that = (JFreeChartEntity) obj;
        if (!getArea().equals(that.getArea())) {
            return false;
        }
        if (!Objects.equals(getToolTipText(), that.getToolTipText())) {
            return false;
        }
        if (!Objects.equals(getURLText(), that.getURLText())) {
            return false;
        }
        if (!(this.chart.equals(that.chart))) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = 39;
        result = HashUtils.hashCode(result, getToolTipText());
        result = HashUtils.hashCode(result, getURLText());
        return result;
    }

    /**
     * Returns a clone of the entity.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem cloning the
     *                                    entity.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Provides serialization support.
     *
     * @param stream the output stream.
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtils.writeShape(getArea(), stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the input stream.
     * @throws IOException            if there is an I/O error.
     * @throws ClassNotFoundException if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        setArea(SerialUtils.readShape(stream));
    }

}
