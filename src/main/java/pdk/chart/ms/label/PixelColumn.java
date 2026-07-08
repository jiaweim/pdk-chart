package pdk.chart.ms.label;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A pixel grid column.
 */
public final class PixelColumn {

    private final List<PeakLabelCandidate> candidates = new ArrayList<>();

    public void add(PeakLabelCandidate candidate) {
        candidates.add(candidate);
    }

    /**
     * Sort descending by intensity.
     */
    public void sort() {
        candidates.sort(
                Comparator.comparingDouble(
                                PeakLabelCandidate::getIntensity)
                        .reversed());
    }

    public List<PeakLabelCandidate> getCandidates() {
        return candidates;
    }

    public boolean isEmpty() {
        return candidates.isEmpty();
    }
}