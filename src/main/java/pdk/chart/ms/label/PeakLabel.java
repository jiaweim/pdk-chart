package pdk.chart.ms.label;

/**
 * Represents a final laid-out peak label.
 *
 * <p>This class is immutable. The layout calculation is performed before
 * creating this object. Renderer only needs to draw the label.</p>
 */
public final class PeakLabel {

    /**
     * Dataset series index.
     */
    private final int series;

    /**
     * Dataset item index.
     */
    private final int item;

    /**
     * Peak m/z value.
     */
    private final double mz;

    /**
     * Raw peak intensity.
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
     * Peak anchor position (Java2D coordinate).
     */
    private final double anchorX;

    private final double anchorY;

    /**
     * Label baseline position (Java2D coordinate).
     */
    private final double labelX;

    private final double labelY;

    /**
     *
     * @param series            series index
     * @param item              item index
     * @param mz                x value
     * @param intensity         y values
     * @param relativeIntensity relative intensity compare the y-range in current view
     * @param text              label text
     * @param anchorX           x of the peak in Java2D coordinate.
     * @param anchorY           y of the peak in Java2D coordinate.
     * @param labelX            x of the label in Java2D coordinate.
     * @param labelY            y of the label in Java2D coordinate.
     */
    public PeakLabel(
            int series,
            int item,
            double mz,
            double intensity,
            double relativeIntensity,
            String text,
            double anchorX,
            double anchorY,
            double labelX,
            double labelY) {

        this.series = series;
        this.item = item;
        this.mz = mz;
        this.intensity = intensity;
        this.relativeIntensity = relativeIntensity;
        this.text = text;

        this.anchorX = anchorX;
        this.anchorY = anchorY;

        this.labelX = labelX;
        this.labelY = labelY;
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


    /**
     * Returns relative intensity in current visible range.
     *
     * @return value between 0 and 1
     */
    public double getRelativeIntensity() {
        return relativeIntensity;
    }


    public String getText() {
        return text;
    }


    /**
     * Peak x coordinate.
     */
    public double getAnchorX() {
        return anchorX;
    }


    /**
     * Peak y coordinate.
     */
    public double getAnchorY() {
        return anchorY;
    }


    /**
     * Label baseline x coordinate.
     */
    public double getLabelX() {
        return labelX;
    }


    /**
     * Label baseline y coordinate.
     */
    public double getLabelY() {
        return labelY;
    }


    @Override
    public String toString() {
        return "PeakLabel{" +
                "series=" + series +
                ", item=" + item +
                ", mz=" + mz +
                ", intensity=" + intensity +
                ", relativeIntensity=" + relativeIntensity +
                ", text='" + text + '\'' +
                ", anchorX=" + anchorX +
                ", anchorY=" + anchorY +
                ", labelX=" + labelX +
                ", labelY=" + labelY +
                '}';
    }
}