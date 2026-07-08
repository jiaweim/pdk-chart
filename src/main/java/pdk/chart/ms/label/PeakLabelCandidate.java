package pdk.chart.ms.label;

import java.awt.geom.Rectangle2D;
import java.util.Comparator;
import java.util.Objects;

/**
 * A candidate peak label used during label layout.
 * <p>
 * Unlike {@link PeakLabel}, this class is mutable. The layout engine
 * continuously adjusts the label position and bounding box until the
 * final layout is obtained.
 */
public final class PeakLabelCandidate {

    /**
     * Descending intensity.
     */
    public static final Comparator<PeakLabelCandidate> BY_INTENSITY =
            Comparator.comparingDouble(PeakLabelCandidate::getIntensity)
                    .reversed();

    /**
     * Ascending m/z.
     */
    public static final Comparator<PeakLabelCandidate> BY_MZ =
            Comparator.comparingDouble(PeakLabelCandidate::getMz);

    /**
     * Ascending screen x.
     */
    public static final Comparator<PeakLabelCandidate> BY_SCREEN_X =
            Comparator.comparingDouble(PeakLabelCandidate::getAnchorX);

    /**
     * Dataset series.
     */
    private final int series;

    /**
     * Dataset item.
     */
    private final int item;

    /**
     * Peak m/z.
     */
    private final double mz;

    /**
     * Peak intensity.
     */
    private final double intensity;

    /**
     * Relative intensity in current visible range.
     */
    private final double relativeIntensity;

    /**
     * Label text.
     */
    private final String text;

    /**
     * Peak position (Java2D).
     */
    private final double anchorX;

    private final double anchorY;

    /**
     * Label position (Java2D).
     */
    private double labelX;

    private double labelY;

    /**
     * Label bounds.
     */
    private Rectangle2D.Double bounds;

    /**
     * Whether the candidate survives layout.
     */
    private boolean visible = true;

    /**
     * Creates a candidate.
     */
    public PeakLabelCandidate(
            int series,
            int item,
            double mz,
            double intensity,
            double relativeIntensity,
            String text,
            double anchorX,
            double anchorY) {

        this.series = series;
        this.item = item;
        this.mz = mz;
        this.intensity = intensity;
        this.relativeIntensity = relativeIntensity;
        this.text = Objects.requireNonNull(text, "text");

        this.anchorX = anchorX;
        this.anchorY = anchorY;

        // 初始位置放在峰顶，由 LayoutEngine 调整
        this.labelX = anchorX;
        this.labelY = anchorY;
    }

    public int getSeries() {
        return series;
    }

    public int getItem() {
        return item;
    }

    public double getMz() {
        return mz;
    }

    public double getIntensity() {
        return intensity;
    }

    public double getRelativeIntensity() {
        return relativeIntensity;
    }

    public String getText() {
        return text;
    }

    public double getAnchorX() {
        return anchorX;
    }

    public double getAnchorY() {
        return anchorY;
    }

    public double getLabelX() {
        return labelX;
    }

    public void setLabelX(double labelX) {
        this.labelX = labelX;
    }

    public double getLabelY() {
        return labelY;
    }

    public void setLabelY(double labelY) {
        this.labelY = labelY;
    }

    public Rectangle2D.Double getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle2D.Double bounds) {
        this.bounds = bounds;
    }

    /**
     * Convenience method used by the layout engine.
     */
    public void setBounds(double x, double y, double width, double height) {
        if (bounds == null) {
            bounds = new Rectangle2D.Double();
        }
        bounds.setRect(x, y, width, height);
    }

    /**
     * Returns true if the label intersects another candidate.
     */
    public boolean intersects(PeakLabelCandidate other) {
        if (bounds == null || other.bounds == null) {
            return false;
        }
        return bounds.intersects(other.bounds);
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Converts to immutable PeakLabel.
     */
    public PeakLabel toPeakLabel() {
        return new PeakLabel(
                series,
                item,
                mz,
                intensity,
                relativeIntensity,
                text,
                anchorX,
                anchorY,
                labelX,
                labelY);
    }
}