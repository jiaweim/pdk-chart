package pdk.chart.ms.label;

import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.renderer.xy.XYItemRendererState;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Renderer state for {@link pdk.chart.ms.PeakRenderer}.
 *
 * <p>The state stores the peak labels calculated during renderer
 * initialization. Labels are calculated once per chart rendering cycle
 * and reused by {@code drawItem()}.</p>
 */
public class PeakRendererState extends XYItemRendererState {

    /**
     * Labels generated for current visible range.
     */
    private List<PeakLabel> labels = Collections.emptyList();

    /**
     * Fast lookup: item index -> label.
     *
     * <p>PeakRenderer normally uses one series, therefore item index is
     * sufficient as the key.</p>
     */
    private final Map<Integer, PeakLabel> labelMap = new HashMap<>();

    /**
     * Creates renderer state.
     *
     * @param info plot rendering info
     */
    public PeakRendererState(PlotRenderingInfo info) {
        super(info);
    }

    /**
     * Creates renderer state without rendering info.
     */
    public PeakRendererState() {
        super(null);
    }


    /**
     * Updates labels.
     *
     * @param labels calculated labels
     */
    public void setLabels(List<PeakLabel> labels) {

        if (labels == null || labels.isEmpty()) {
            clear();
            return;
        }

        this.labels = labels;

        labelMap.clear();

        for (PeakLabel label : labels) {
            labelMap.put(label.getItem(), label);
        }
    }

    /**
     * Returns all labels.
     *
     * @return immutable label list
     */
    public List<PeakLabel> getLabels() {
        return labels;
    }


    /**
     * Returns label for item.
     *
     * @param item dataset item index
     * @return label or {@code null}
     */
    public PeakLabel getLabel(int item) {
        return labelMap.get(item);
    }


    /**
     * Clears all labels.
     */
    public void clear() {
        labels = Collections.emptyList();
        labelMap.clear();
    }
}