package pdk.chart.ms.label;

/**
 * Visible item range.
 */
public final class VisibleRange {

    public static final VisibleRange EMPTY = new VisibleRange(-1, -1);

    private final int first;

    private final int last;

    public VisibleRange(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public boolean isEmpty() {
        return first < 0;
    }

    public int size() {
        return isEmpty() ? 0 : last - first + 1;
    }

}