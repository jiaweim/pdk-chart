package pdk.chart.swing;

import pdk.chart.event.ChartChangeEvent;

import javax.swing.event.EventListenerList;
import java.util.Objects;

/**
 * A base class for implementing overlays for a {@link ChartPanel}.
 */
public class AbstractOverlay {

    /**
     * Storage for registered change listeners.
     */
    private final transient EventListenerList changeListeners;

    /**
     * Default constructor.
     */
    public AbstractOverlay() {
        this.changeListeners = new EventListenerList();
    }

    /**
     * Registers an object for notification of changes to the overlay.
     *
     * @param listener the listener ({@code null} not permitted).
     * @see #removeChangeListener(OverlayChangeListener)
     */
    public void addChangeListener(OverlayChangeListener listener) {
        Objects.requireNonNull(listener, "listener");
        this.changeListeners.add(OverlayChangeListener.class, listener);
    }

    /**
     * Deregisters an object for notification of changes to the overlay.
     *
     * @param listener the listener ({@code null} not permitted)
     * @see #addChangeListener(OverlayChangeListener)
     */
    public void removeChangeListener(OverlayChangeListener listener) {
        Objects.requireNonNull(listener, "listener");
        this.changeListeners.remove(OverlayChangeListener.class, listener);
    }

    /**
     * Sends a default {@link ChartChangeEvent} to all registered listeners.
     * <p>
     * This method is for convenience only.
     */
    public void fireOverlayChanged() {
        OverlayChangeEvent event = new OverlayChangeEvent(this);
        notifyListeners(event);
    }

    /**
     * Sends a {@link ChartChangeEvent} to all registered listeners.
     *
     * @param event information about the event that triggered the
     *              notification.
     */
    protected void notifyListeners(OverlayChangeEvent event) {
        Object[] listeners = this.changeListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == OverlayChangeListener.class) {
                ((OverlayChangeListener) listeners[i + 1]).overlayChanged(
                        event);
            }
        }
    }

}

