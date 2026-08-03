package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.ms.*;

/**
 * A specialised chart for visualising peptide‑spectrum matches (PSMs) in
 * mass spectrometry data.
 * <p>
 * The chart uses a {@link PSMPlot} as the underlying plot and a
 * {@link PeakRenderer} to draw spectral peaks and optionally their labels.
 * The plot layout is controlled by the {@link PSMPlot}, which handles both
 * a {@link PeptideDataset} (peptide sequence coverage) and a
 * {@link SpectrumDataset} (fragment ion peaks).
 * <p>
 * Typical usage:
 * <pre>{@code
 * PSMChart chart = new PSMChart(psmDataset);
 * }</pre>
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 10:04 AM
 */
public class PSMChart extends XYChart {

    /**
     * The PSM plot (specialised XYPlot).
     */
    private PSMPlot plot;
    private PeakRenderer renderer1_;

    /**
     * Initializes the renderer by retrieving it from the {@link PSMPlot}.
     * The plot must already have been configured with a
     * {@link PeakRenderer}.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = (PeakRenderer) plot_.getRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the peak renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public PeakRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates an empty PSM chart.  Datasets must be added later via
     * {@link PSMPlot#setDataset(PeptideDataset, SpectrumDataset)}.
     */
    public PSMChart() {
        super(null, new PSMPlot(), false);
        plot = (PSMPlot) plot_;
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
    }

    /**
     * Creates a PSM chart with both peptide and spectrum data.
     * <p>
     * If the spectrum dataset contains more than one series, automatic
     * peak labels are disabled to avoid visual clutter.
     *
     * @param peptideDataset  the peptide coverage data ({@code null}
     *                        permitted if only spectrum data is shown)
     * @param spectrumDataset the fragment ion spectrum data (must not be
     *                        {@code null})
     */
    public PSMChart(PeptideDataset peptideDataset, SpectrumDataset spectrumDataset) {
        super(null, new PSMPlot(), false);
        plot = (PSMPlot) plot_;
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);

        JChart.applyCurrentTheme(this);
        int seriesCount = spectrumDataset.getSeriesCount();
        if (seriesCount > 1) {
            renderer1_.setShowAutoPeakLabels(false);
        }
        plot.setDataset(peptideDataset, spectrumDataset);
    }

    /**
     * Creates a PSM chart from a combined {@link PSMDataset}.
     *
     * @param dataset the PSM dataset (must not be {@code null})
     */
    public PSMChart(PSMDataset dataset) {
        this(dataset.getPeptideDataset(), dataset.getSpectrumDataset());
    }

    /**
     * Creates a PSM chart that displays only spectrum data without
     * peptide coverage information.
     *
     * @param spectrumDataset the fragment ion spectrum data (must not be
     *                        {@code null})
     */
    public PSMChart(SpectrumDataset spectrumDataset) {
        this(null, spectrumDataset);
    }

    /**
     * Controls whether peak labels are automatically generated and
     * displayed for spectral peaks.
     *
     * @param showAutoPeakLabels {@code true} to show auto‑generated labels
     */
    public void setShowAutoPeakLabels(boolean showAutoPeakLabels) {
        renderer1_.setShowAutoPeakLabels(showAutoPeakLabels);
    }

}
