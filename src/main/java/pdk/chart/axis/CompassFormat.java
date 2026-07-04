package pdk.chart.axis;

import pdk.chart.util.Args;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * A formatter that displays numbers as directions.
 */
public class CompassFormat extends NumberFormat {

    /**
     * The directions.
     */
    public final String[] directions;

    /**
     * Creates a new formatter using English identifiers.
     */
    public CompassFormat() {
        this("N", "E", "S", "W");
    }

    /**
     * Creates a new formatter using the specified identifiers for
     * the base wind directions.
     *
     * @param n the code for NORTH.
     * @param e the code for EAST.
     * @param s the code for SOUTH.
     * @param w the code for WEST.
     */
    public CompassFormat(String n, String e, String s, String w) {
        this(new String[]{
                n, n + n + e, n + e, e + n + e, e, e + s + e, s + e, s + s + e, s,
                s + s + w, s + w, w + s + w, w, w + n + w, n + w, n + n + w
        });
    }

    /**
     * Creates a new formatter using the specified identifiers.
     *
     * @param directions an array containing 16 strings representing
     *                   the directions of a compass.
     */
    public CompassFormat(String[] directions) {
        super();
        Args.nullNotPermitted(directions, "directions");
        if (directions.length != 16) {
            throw new IllegalArgumentException("The 'directions' array must "
                    + "contain exactly 16 elements");
        }
        this.directions = directions;
    }

    /**
     * Returns a string representing the direction.
     *
     * @param direction the direction.
     * @return A string.
     */
    public String getDirectionCode(double direction) {
        direction = direction % 360;
        if (direction < 0.0) {
            direction = direction + 360.0;
        }
        int index = ((int) Math.floor(direction / 11.25) + 1) / 2;
        return directions[index];
    }

    /**
     * Formats a number into the specified string buffer.
     *
     * @param number     the number to format.
     * @param toAppendTo the string buffer.
     * @param pos        the field position (ignored here).
     * @return The string buffer.
     */
    @Override
    public StringBuffer format(double number, StringBuffer toAppendTo,
            FieldPosition pos) {
        return toAppendTo.append(getDirectionCode(number));
    }

    /**
     * Formats a number into the specified string buffer.
     *
     * @param number     the number to format.
     * @param toAppendTo the string buffer.
     * @param pos        the field position (ignored here).
     * @return The string buffer.
     */
    @Override
    public StringBuffer format(long number, StringBuffer toAppendTo,
            FieldPosition pos) {
        return toAppendTo.append(getDirectionCode(number));
    }

    /**
     * This method returns {@code null} for all inputs.  This class cannot
     * be used for parsing.
     *
     * @param source        the source string.
     * @param parsePosition the parse position.
     * @return {@code null}.
     */
    @Override
    public Number parse(String source, ParsePosition parsePosition) {
        return null;
    }

}
