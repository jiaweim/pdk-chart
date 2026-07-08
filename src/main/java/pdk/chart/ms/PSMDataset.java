package pdk.chart.ms;

import pdk.chart.data.general.AbstractDataset;
import pdk.chart.data.general.DatasetChangeEvent;

/**
 * Dataset for {@link PSMPlot}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 10 Jun 2026, 9:11 AM
 */
public class PSMDataset extends AbstractDataset {

    private PeptideDataset peptideDataset;
    private SpectrumDataset spectrumDataset;

    public PSMDataset() {
        this(null, null);
    }

    public PSMDataset(PeptideDataset peptideDataset, SpectrumDataset spectrumDataset) {
        super();
        this.peptideDataset = peptideDataset;
        this.spectrumDataset = spectrumDataset;
    }

    public PeptideDataset getPeptideDataset() {
        return peptideDataset;
    }

    public SpectrumDataset getSpectrumDataset() {
        return spectrumDataset;
    }

    public void setPeptideDataset(PeptideDataset peptideDataset) {
        this.peptideDataset = peptideDataset;
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Set the {@link SpectrumDataset}.
     *
     * @param spectrumDataset {@link SpectrumDataset}
     */
    public void setSpectrumDataset(SpectrumDataset spectrumDataset) {
        this.spectrumDataset = spectrumDataset;
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Whether this dataset is empty.
     *
     * @return true if this dataset is empty.
     */
    public boolean isEmpty() {
        return peptideDataset == null && spectrumDataset == null;
    }
}
