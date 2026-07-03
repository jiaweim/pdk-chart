package pdk.chart.plot.pep;

import pdk.chart.internal.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * An annotation for a {@link PSMPlot} that marks an identified fragment.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026
 */
public class PeptideAnnotation implements Serializable, Cloneable {

    private final SeriesType seriesType;
    /**
     * size of the fragment.
     */
    private final int size;
    /**
     * The annotation label.
     */
    private final String label;

    /**
     * Create a new annotation.
     *
     * @param seriesType {@link SeriesType}.
     * @param size       size of the fragment.
     * @param label      label text.
     */
    public PeptideAnnotation(SeriesType seriesType, int size, String label) {
        Objects.requireNonNull(seriesType, "seriesType");
        Args.requireNonNegative(size, "size");
        Objects.requireNonNull(label, "label");

        this.seriesType = seriesType;
        this.size = size;
        this.label = label;
    }

    /**
     * Return the {@link SeriesType} of this annotation.
     *
     * @return {@link SeriesType}
     */
    public SeriesType getSeriesType() {
        return seriesType;
    }

    /**
     * Return true if it is an N-terminal fragment annotation.
     *
     * @return true if it is an n-TERMINAL FRAGMENT.
     */
    public boolean isNTerminal() {
        return this.seriesType.isNTerminal();
    }

    /**
     * Return the size of the fragment.
     *
     * @return size of the fragment.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the annotation label.
     *
     * @return the annotation label.
     */
    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PeptideAnnotation that)) return false;
        return size == that.size && seriesType == that.seriesType && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesType, size, label);
    }

    @Override
    public PeptideAnnotation clone() {
        try {
            return (PeptideAnnotation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
