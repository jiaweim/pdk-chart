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
 * PSM Chart with mz error plot.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 10:37 AM
 */
public class PSMChartV2 extends XYChart {

    private Rectangle2D.Double rectangle = ShapeUtils.createRectangle(4);

    private final MSPlot msPlot;

    private final PSMPlot psmPlot;
    private final PeakRenderer peakRenderer;

    private final XYPlot errorPlot;
    private final XYLineAndShapeRenderer errorRenderer;

    public PSMChartV2(ToleranceType toleranceType) {
        super(null, new MSPlot<>(), false);
        this.msPlot = (MSPlot) getPlot();

        psmPlot = new PSMPlot();
        psmPlot.setDomainAxis(null);
        peakRenderer = (PeakRenderer) psmPlot.getRenderer();
        peakRenderer.setShowAutoPeakLabels(false);
        renderer0_ = peakRenderer;

        NumberAxis errorYAxis = new NumberAxis(toleranceType.getUnit());
        errorYAxis.setRange(0 - toleranceType.getValue(), toleranceType.getValue());

        errorRenderer = new XYLineAndShapeRenderer(false, true);
        errorRenderer.setDefaultShapesFilled(true);
        errorRenderer.setDrawOutlines(false);

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
     * Automatically generate labels for spectral peaks?
     *
     * @param showAutoPeakLabels true if generate labels.
     */
    public void setShowAutoPeakLabels(boolean showAutoPeakLabels) {
        peakRenderer.setShowAutoPeakLabels(showAutoPeakLabels);
    }
}
