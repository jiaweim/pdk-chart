package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.ms.*;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.util.Args;
import pdk.chart.util.ShapeUtils;

import java.awt.geom.Rectangle2D;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 10:04 AM
 */
public class PSMChart extends XYChart {

    private final PSMPlot plot;
    private final PeakRenderer renderer;

    public PSMChart() {
        super(null, DEFAULT_TITLE_FONT, new PSMPlot(), false);
        plot = (PSMPlot) plot_;
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        renderer = (PeakRenderer) plot.getRenderer();
        setDefaultRenderer(renderer);
    }

    public PSMChart(PSMDataset dataset) {
        this(dataset.getPeptideDataset(), dataset.getSpectrumDataset());
    }

    public PSMChart(PeptideDataset peptideDataset, SpectrumDataset spectrumDataset) {
        this();
        JChartUtils.applyCurrentTheme(this);
        int seriesCount = spectrumDataset.getSeriesCount();
        if (seriesCount > 1) {
            renderer.setShowAutoPeakLabels(false);
        }
        plot.setDataset(peptideDataset, spectrumDataset);
    }

    public PSMChart(SpectrumDataset spectrumDataset) {
        this(null, spectrumDataset);
    }

    public PSMChart(PSMDataset dataset, ToleranceType toleranceType) {
        this();
        Args.nullNotPermitted(toleranceType, "toleranceType");

        plot_.setDomainAxis(null);
        setShowAutoPeakLabels(false);

        NumberAxis errorYAxis = new NumberAxis(toleranceType.getUnit());
        errorYAxis.setRange(0 - toleranceType.getValue(), toleranceType.getValue());
        errorYAxis.setAutoRange(false);

        XYDataset<SeriesType> mzErrorDataset = dataset.getSpectrumDataset().getMZErrorDataset();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setDefaultShapesFilled(true);
        renderer.drawOutlines(false);
        Rectangle2D.Double rectangle = ShapeUtils.createRectangle(4);
        for (int i = 0; i < mzErrorDataset.getSeriesCount(); i++) {
            SeriesType seriesKey = mzErrorDataset.getSeriesKey(i);
            renderer.setSeriesPaint(i, seriesKey.getColor());
            renderer.setSeriesShape(i, rectangle);
        }
        XYPlot mzErrorPlot = new XYPlot(mzErrorDataset, null, errorYAxis, null);

    }

    /**
     * Automatically generate labels for spectral peaks?
     *
     * @param showAutoPeakLabels true if generate labels.
     */
    public void setShowAutoPeakLabels(boolean showAutoPeakLabels) {
        renderer.setShowAutoPeakLabels(showAutoPeakLabels);
    }

}
