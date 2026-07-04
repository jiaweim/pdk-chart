package pdk.chart.entity;

import pdk.chart.util.Args;
import pdk.chart.util.HashUtils;
import pdk.chart.util.SerialUtils;
import pdk.chart.plot.Plot;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * A class that captures information about a plot during rendering.
 */
public class PlotEntity extends ChartEntity {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -4445994133561919083L;
    //same as for ChartEntity!

    /**
     * The plot.
     */
    private final Plot plot;

    /**
     * Creates a new plot entity.
     *
     * @param area the area ({@code null} not permitted).
     * @param plot the plot ({@code null} not permitted).
     */
    public PlotEntity(Shape area, Plot plot) {
        // defer argument checks...
        this(area, plot, null);
    }

    /**
     * Creates a new plot entity.
     *
     * @param area        the area ({@code null} not permitted).
     * @param plot        the plot ({@code null} not permitted).
     * @param toolTipText the tool tip text ({@code null} permitted).
     */
    public PlotEntity(Shape area, Plot plot, String toolTipText) {
        // defer argument checks...
        this(area, plot, toolTipText, null);
    }

    /**
     * Creates a new plot entity.
     *
     * @param area        the area ({@code null} not permitted).
     * @param plot        the plot ({@code null} not permitted).
     * @param toolTipText the tool tip text ({@code null} permitted).
     * @param urlText     the URL text for HTML image maps ({@code null}
     *                    permitted).
     */
    public PlotEntity(Shape area, Plot plot, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        Args.nullNotPermitted(plot, "plot");
        this.plot = plot;
    }

    /**
     * Returns the plot that occupies the entity area.
     *
     * @return The plot (never {@code null}).
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Returns a string representation of the plot entity, useful for
     * debugging.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlotEntity: ");
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
        if (!(obj instanceof PlotEntity)) {
            return false;
        }
        PlotEntity that = (PlotEntity) obj;
        if (!getArea().equals(that.getArea())) {
            return false;
        }
        if (!Objects.equals(getToolTipText(), that.getToolTipText())) {
            return false;
        }
        if (!Objects.equals(getURLText(), that.getURLText())) {
            return false;
        }
        if (!(this.plot.equals(that.plot))) {
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
