package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.ms.*;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.util.ShapeUtils;

import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

/**
 * A PSM (peptide‑spectrum match) chart that includes an additional
 * <em>m/z error</em> sub‑plot.
 * <p>
 * The chart is composed of three plots arranged vertically by an
 * {@link MSPlot}:
 * <ol>
 *   <li><strong>PSM plot</strong> – displays peptide coverage
 *       (fragment ion matching) using a {@link PSMPlot} and a
 *       {@link PeakRenderer}.</li>
 *   <li><strong>Error plot</strong> – shows the mass‑to‑charge
 *       deviation for matched peaks using an {@link XYPlot} and an
 *       {@link XYLineAndShapeRenderer} with fixed shapes.</li>
 * </ol>
 * The domain axis (m/z) is shared across the two plots.
 * Automatic peak labels are disabled by default but can be
 * re‑enabled via {@link #setShowAutoPeakLabels(boolean)}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 10:37 AM
 */
public class PSMChartV2 extends XYChart {

    /**
     * Shape used for data points in the error plot.
     */
    private Rectangle2D.Double rectangle = ShapeUtils.createRectangle(4);

    /**
     * The master plot that arranges the sub‑plots.
     */
    private final MSPlot msPlot;
    /**
     * The peptide‑spectrum match (PSM) sub‑plot.
     */
    private final PSMPlot psmPlot;
    /**
     * Renderer used by the PSM sub‑plot.
     */
    private PeakRenderer peakRenderer;

    /**
     * The m/z error sub‑plot.
     */
    private final XYPlot errorPlot;
    /**
     * Renderer for the error sub‑plot (shapes only, no lines).
     */
    private XYLineAndShapeRenderer errorRenderer;

    /**
     * Initialises the renderer by obtaining the {@link PeakRenderer}
     * from the PSM plot.  Automatic peak labels are turned off.
     */
    @Override
    protected void initRenderer() {
        peakRenderer = (PeakRenderer) psmPlot.getRenderer();
        peakRenderer.setShowAutoPeakLabels(false);
        renderer0_ = peakRenderer;

        errorRenderer = new XYLineAndShapeRenderer(false, true);
        errorRenderer.setDefaultShapesFilled(true);
        errorRenderer.setDrawOutlines(false);
    }

    /**
     * Constructs an empty chart (no datasets) with the given mass
     * tolerance for the error axis.
     * <p>
     * The error axis range is fixed to
     * {@code [-tolerance.getValue(), +tolerance.getValue()]} and the axis
     * label displays the tolerance unit (e.g. "ppm" or "Da").
     *
     * @param toleranceType the mass tolerance to use for the error
     *                      axis (must not be {@code null})
     */
    public PSMChartV2(ToleranceType toleranceType) {
        super(null, new MSPlot<>(), false);
        this.msPlot = (MSPlot) getPlot();

        psmPlot = new PSMPlot();
        psmPlot.setDomainAxis(null);

        NumberAxis errorYAxis = new NumberAxis(toleranceType.getUnit());
        errorYAxis.setRange(0 - toleranceType.getValue(), toleranceType.getValue());

        errorPlot = new XYPlot(null, null, errorYAxis, errorRenderer);

        NumberAxis mzAxis = new NumberAxis("m/z");
        DecimalFormat format = new DecimalFormat("0.######");
        format.setGroupingUsed(false);   // 不使用千位分隔符
        mzAxis.setNumberFormatOverride(format);

        msPlot.setDomainAxis(mzAxis);
        msPlot.setGap(10);
        msPlot.add(psmPlot, 1);
        msPlot.add(errorPlot, 125.0);

        JChart.applyCurrentTheme(this);

        psmPlot.setAxisOffset(RectangleInsets.ZERO_INSETS);

        NumberAxis peakYAxis = (NumberAxis) psmPlot.getRangeAxis();
        peakYAxis.setLowerMargin(0);

        errorYAxis.setAutoRange(false);
        errorYAxis.setLowerMargin(0);

        errorPlot.setAxisOffset(RectangleInsets.ZERO_INSETS);
    }

    /**
     * Constructs a chart with both peptide and spectrum data, using the
     * given mass tolerance for the error plot.
     * <p>
     * The error plot’s series colours and shapes are taken from the
     * spectrum dataset’s {@link SeriesType} definitions.
     *
     * @param dataset       the PSM dataset containing peptide and spectrum
     *                      information (must not be {@code null})
     * @param toleranceType the mass tolerance for the error axis
     */
    public PSMChartV2(PSMDataset dataset, ToleranceType toleranceType) {
        this(toleranceType);

        XYDataset<SeriesType> mzErrorDataset = dataset.getSpectrumDataset().getMZErrorDataset();
        for (int i = 0; i < mzErrorDataset.getSeriesCount(); i++) {
            SeriesType seriesKey = mzErrorDataset.getSeriesKey(i);
            errorRenderer.setSeriesPaint(i, seriesKey.getColor());
            errorRenderer.setSeriesShape(i, rectangle);
        }

        errorPlot.setDataset(mzErrorDataset);
        psmPlot.setDataset(dataset.getPeptideDataset(), dataset.getSpectrumDataset());
    }

    /**
     * Toggles automatic peak labelling in the PSM plot.
     *
     * @param showAutoPeakLabels {@code true} to display automatically
     *                           generated labels above spectral peaks
     */
    public void setShowAutoPeakLabels(boolean showAutoPeakLabels) {
        peakRenderer.setShowAutoPeakLabels(showAutoPeakLabels);
    }
}
