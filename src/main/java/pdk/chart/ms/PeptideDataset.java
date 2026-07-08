package pdk.chart.ms;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.general.AbstractDataset;
import pdk.chart.data.general.DatasetChangeEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A dataset that stores a character sequence.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:52 AM
 */
public class PeptideDataset extends AbstractDataset
        implements Cloneable, PublicCloneable, Serializable {

    /**
     * The value.
     */
    private char[] value;
    private boolean[] marked;
    /**
     * {@link PeptideAnnotation}
     */
    private final List<PeptideAnnotation> annotations = new ArrayList<>();

    /**
     * Create a new dataset, initially empty.
     */
    public PeptideDataset() {
        this(null);
    }

    /**
     * Create a new dataset with the specified value.
     *
     * @param value the initial value.
     */
    public PeptideDataset(char[] value) {
        super();
        this.value = value;
        if (value != null) {
            this.marked = new boolean[value.length];
            Arrays.fill(this.marked, false);
        }
    }

    /**
     * Create a new dataset with the specified value.
     *
     * @param value the initial value.
     */
    public PeptideDataset(char[] value, boolean[] marked) {
        super();
        this.value = value;
        this.marked = marked;
    }

    /**
     * Add a new {@link PeptideAnnotation}.
     *
     * @param annotation {@link PeptideAnnotation}.
     */
    public void addAnnotation(PeptideAnnotation annotation) {
        this.annotations.add(annotation);
    }

    /**
     * Return annotations.
     *
     * @return list of {@link PeptideAnnotation}.
     */
    public List<PeptideAnnotation> getAnnotations() {
        return annotations;
    }

    /**
     * Return the value.
     *
     * @return the value (possibly {@code null}).
     */
    public char[] getValue() {
        return value;
    }

    /**
     * Return the mark array.
     *
     * @return mark array.
     */
    public boolean[] getMarked() {
        return marked;
    }

    /**
     * Set the value and sends a {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value the new value ({@code null} permitted).
     */
    public void setValue(char[] value) {
        this.value = value;
        if (value != null) {
            this.marked = new boolean[value.length];
            Arrays.fill(this.marked, false);
        }
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Set the value and sends a {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value  values
     * @param marked Whether to perform marking
     */
    public void setValue(char[] value, boolean[] marked) {
        this.value = value;
        this.marked = marked;
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Return the dataset size.
     *
     * @return dataset size.
     */
    public int size() {
        if (value == null)
            return 0;
        return value.length;
    }

    /**
     * Whether the dataset is empty
     *
     * @return true if this dataset is empty.
     */
    public boolean isEmpty() {
        return value == null || value.length == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PeptideDataset that)) return false;
        return Objects.deepEquals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public PeptideDataset clone() {
        return new PeptideDataset(value);
    }
}
