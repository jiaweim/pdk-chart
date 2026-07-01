package pdk.chart.swing;

import java.util.EventObject;

/**
 * A change event for an {@link Overlay}.
 */
public class OverlayChangeEvent extends EventObject {

    /**
     * Creates a new change event.
     *
     * @param source the event source ({@code null} not permitted).
     */
    public OverlayChangeEvent(Object source) {
        super(source); // null check is in here
    }

}
