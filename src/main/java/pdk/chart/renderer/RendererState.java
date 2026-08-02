package pdk.chart.renderer;

import pdk.chart.model.ChartRenderingInfo;
import pdk.chart.entity.EntityCollection;
import pdk.chart.plot.PlotRenderingInfo;

/**
 * Represents the current state of a renderer.
 */
public class RendererState {

    /**
     * The plot rendering info.
     */
    private PlotRenderingInfo info;

    /**
     * A flag that indicates whether rendering hints should be added to
     * identify chart elements.  It is initialised from the corresponding flag
     * in the JFreeChart instance.
     */
    private boolean elementHinting;

    /**
     * Creates a new state object.
     *
     * @param info the plot rendering info.
     */
    public RendererState(PlotRenderingInfo info) {
        this.info = info;
        this.elementHinting = false;
    }

    /**
     * Returns the flag that controls whether the renderer should
     * add rendering hints to the output that identify chart elements.
     *
     * @return A boolean.
     */
    public boolean getElementHinting() {
        return this.elementHinting;
    }

    /**
     * Sets the elementHinting flag.
     *
     * @param hinting the new flag value.
     */
    public void setElementHinting(boolean hinting) {
        this.elementHinting = hinting;
    }

    /**
     * Returns the plot rendering info.
     *
     * @return The info.
     */
    public PlotRenderingInfo getInfo() {
        return this.info;
    }

    /**
     * A convenience method that returns a reference to the entity
     * collection (may be {@code null}) being used to record
     * chart entities.
     *
     * @return The entity collection (possibly {@code null}).
     */
    public EntityCollection getEntityCollection() {
        EntityCollection result = null;
        if (this.info != null) {
            ChartRenderingInfo owner = this.info.getOwner();
            if (owner != null) {
                result = owner.getEntityCollection();
            }
        }
        return result;
    }

}
