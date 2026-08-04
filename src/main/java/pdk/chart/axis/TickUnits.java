package pdk.chart.axis;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A collection of tick units, used by the {@link DateAxis} and
 * {@link NumberAxis} classes.
 */
public class TickUnits implements TickUnitSource, Cloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1134174035901467545L;

    /**
     * Storage for the tick units.
     */
    private List<TickUnit> tickUnits;

    /**
     * Constructs a new collection of tick units.
     */
    public TickUnits() {
        this.tickUnits = new ArrayList<>();
    }

    /**
     * Adds a tick unit to the collection.  The tick units are maintained in
     * ascending order.
     *
     * @param unit the tick unit to add ({@code null} not permitted).
     */
    public void add(TickUnit unit) {
        Objects.requireNonNull(unit);
        this.tickUnits.add(unit);
        Collections.sort(this.tickUnits);
    }

    /**
     * Returns the number of tick units in this collection.
     * <p>
     * This method is required for the XML writer.
     *
     * @return The number of units in this collection.
     */
    public int size() {
        return this.tickUnits.size();
    }

    /**
     * Returns the tickunit on the given position.
     * <p>
     * This method is required for the XML writer.
     *
     * @param pos the position in the list.
     * @return The tickunit.
     */
    public TickUnit get(int pos) {
        return this.tickUnits.get(pos);
    }

    /**
     * Returns a tick unit that is larger than the supplied unit.
     *
     * @param unit the unit.
     * @return A tick unit that is larger than the supplied unit.
     */
    @Override
    public TickUnit getLargerTickUnit(TickUnit unit) {
        int index = Collections.binarySearch(this.tickUnits, unit);
        if (index >= 0) {
            index = index + 1;
        } else {
            index = -index;
        }

        return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to (in size) the specified unit.
     *
     * @param unit the unit.
     * @return A unit from the collection.
     */
    @Override
    public TickUnit getCeilingTickUnit(TickUnit unit) {
        int index = Collections.binarySearch(this.tickUnits, unit);
        if (index >= 0) {
            return this.tickUnits.get(index);
        } else {
            index = -(index + 1);
            return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
        }
    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to the specified size.
     *
     * @param size the size.
     * @return A unit from the collection.
     */
    @Override
    public TickUnit getCeilingTickUnit(double size) {
        return getCeilingTickUnit(new NumberTickUnit(size,
                NumberFormat.getInstance()));
    }

    /**
     * Returns a clone of the collection.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if an item in the collection does not
     *                                    support cloning.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        TickUnits clone = (TickUnits) super.clone();
        clone.tickUnits = new ArrayList<>(this.tickUnits);
        return clone;
    }

    /**
     * Tests an object for equality with this instance.
     *
     * @param obj the object to test ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TickUnits that)) {
            return false;
        }
        return that.tickUnits.equals(this.tickUnits);
    }

}
