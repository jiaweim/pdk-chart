package pdk.chart.plot.pep;

import org.jspecify.annotations.NonNull;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.plot.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Plot for peptide spectrum match.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:09 PM
 */
public class PSMPlot extends Plot implements Zoomable {

    private final PeptidePlot peptidePlot = new PeptidePlot();
    private final SpectrumPlot spectrumPlot = new SpectrumPlot();

    private PSMDataset dataset;
    private double peptideHeight = 100;
    private double gap = 0;

    public PSMPlot() {
        peptidePlot.setOutlineVisible(false);
    }

    public SpectrumPlot getSpectrumPlot() {
        return spectrumPlot;
    }

    /**
     * Returns the domain axis with index 0.  If the domain axis for this plot
     * is {@code null}, then the method will return the parent plot's
     * domain axis (if there is a parent plot).
     *
     * @return The domain axis (possibly {@code null}).
     */
    public NumberAxis getDomainAxis() {
        return spectrumPlot.getDomainAxisAsNumber();
    }

    /**
     * Returns the range axis for the plot.  If the range axis for this plot is
     * {@code null}, then the method will return the parent plot's range
     * axis (if there is a parent plot).
     *
     * @return The range axis.
     */
    public NumberAxis getRangeAxis() {
        return spectrumPlot.getRangeAxisAsNumber();
    }

    /**
     * Set the paint used to draw amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setAminoAcidPaint(@NonNull Paint paint) {
        peptidePlot.setAminoAcidPaint(paint);
    }

    /**
     * Set the paint used to draw marked amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setMarkAminoAcidPaint(@NonNull Paint paint) {
        peptidePlot.setMarkAminoAcidPaint(paint);
    }

    /**
     * Set the {@link Font} used to draw amino acid letters.
     *
     * @param font {@link Font}
     */
    public void setAminoAcidFont(@NonNull Font font) {
        peptidePlot.setAminoAcidFont(font);
    }

    /**
     * Set the gap between adjacent residues and sends a {@link pdk.chart.event.PlotChangeEvent}
     * to all registered listeners.
     *
     * @param aminoAcidSpacing the gap in pixels.
     */
    public void setAminoAcidSpacing(double aminoAcidSpacing) {
        peptidePlot.setAminoAcidSpacing(aminoAcidSpacing);
    }

    /**
     * Set the font used to draw annotation text.
     *
     * @param font {@link Font}.
     */
    public void setAminoAcidLabelFont(@NonNull Font font) {
        peptidePlot.setLabelFont(font);
    }

    /**
     * Set the stroke used to draw annotation line.
     *
     * @param stroke {@link Stroke}
     */
    public void setAminoAcidAnnotationLineStroke(Stroke stroke) {
        peptidePlot.setAnnotationLineStroke(stroke);
    }

    /**
     * Set the vertical distance between amino acid
     * residues and annotation lines.
     *
     * @param gap the gap in pixels.
     */
    public void setAminoAcidAnnotationLineGap(double gap) {
        peptidePlot.setAminoAcidAnnotationLineGap(gap);
    }

    /**
     * Set the vertical distance between the
     * annotation text and annotation line.
     *
     * @param gap gap in pixel.
     */
    public void setAminoAcidLabelLineGap(double gap) {
        peptidePlot.setLabelLineGap(gap);
    }

    /**
     * Set the gap between peptide and spectrum plot.
     *
     * @param gap gap in pixel.
     */
    public void setPeptideSpectrumGap(double gap) {
        if (this.gap != gap) {
            this.gap = gap;
            fireChangeEvent();
        }
    }

    /**
     * Set the height of the area for rendering peptide sequences.
     *
     * @param height height in pixels.
     */
    public void setPeptideHeight(double height) {
        if (this.peptideHeight != height) {
            this.peptideHeight = height;
            fireChangeEvent();
        }
    }

    /**
     * Set the dataset to renderer.
     *
     * @param psmDataset {@link PSMDataset}.
     */
    public void setDataset(PSMDataset psmDataset) {
        PSMDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }

        this.dataset = psmDataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        DatasetChangeEvent event = new DatasetChangeEvent(this, psmDataset);
        datasetChanged(event);
    }

    @Override
    public String getPlotType() {
        return "PSMPlot";
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        if (area.getWidth() <= 0 || area.getHeight() <= 0)
            return;

        RectangleInsets insets = getInsets();
        insets.trim(area);

        if (area.isEmpty())
            return;

        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }

        drawBackground(g2, area);
        drawOutline(g2, area);

        double totalHeight = area.getHeight();
        double usableHeight = totalHeight - gap;

        double spectrumHeight = usableHeight - peptideHeight;

        Rectangle2D peptideArea = new Rectangle2D.Double(
                area.getX(), area.getY(),
                area.getWidth(), peptideHeight
        );

        Rectangle2D spectrumArea = new Rectangle2D.Double(
                area.getX(), peptideArea.getMaxY() + gap,
                area.getWidth(), spectrumHeight
        );

        PlotRenderingInfo spectrumInfo = null;
        if (info != null) {
            spectrumInfo = new PlotRenderingInfo(info.getOwner());
        }
        spectrumPlot.setDataset(dataset.getSpectrumDataset());
        spectrumPlot.draw(g2, spectrumArea, anchor, parentState, spectrumInfo);
        PlotRenderingInfo peptideInfo = null;
        if (info != null) {
            peptideInfo = new PlotRenderingInfo(info.getOwner());
        }
        peptidePlot.setDataset(dataset.getPeptideDataset());
        peptidePlot.draw(g2, peptideArea, anchor, parentState, peptideInfo);

        if (info != null) {
            info.addSubplotInfo(spectrumInfo);
            info.addSubplotInfo(peptideInfo);
        }

        drawOutline(g2, area);
    }

    @Override
    public boolean isDomainZoomable() {
        return true;
    }

    @Override
    public boolean isRangeZoomable() {
        return true;
    }

    @Override
    public PlotOrientation getOrientation() {
        return spectrumPlot.getOrientation();
    }

    @Override
    public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source) {
        zoomDomainAxes(factor, info, source, false);
    }

    @Override
    public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor) {
        // perform the zoom on each domain axis
        for (ValueAxis xAxis : this.spectrumPlot.getDomainAxes().values()) {
            if (xAxis == null) {
                continue;
            }
            if (useAnchor) {
                // get the relevant source coordinate given the plot orientation
                double sourceX = source.getX();
                if (this.spectrumPlot.getOrientation() == PlotOrientation.HORIZONTAL) {
                    sourceX = source.getY();
                }
                double anchorX = xAxis.java2DToValue(sourceX,
                        info.getDataArea(), this.spectrumPlot.getDomainAxisEdge());
                xAxis.resizeRange2(factor, anchorX);
            } else {
                xAxis.resizeRange(factor);
            }
        }
    }

    @Override
    public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
        for (ValueAxis xAxis : this.spectrumPlot.getDomainAxes().values()) {
            if (xAxis != null) {
                xAxis.zoomRange(lowerPercent, upperPercent);
            }
        }
    }

    @Override
    public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source) {
        zoomRangeAxes(factor, info, source, false);
    }

    @Override
    public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor) {
        // perform the zoom on each range axis
        for (ValueAxis yAxis : this.spectrumPlot.getRangeAxes().values()) {
            if (yAxis == null) {
                continue;
            }
            if (useAnchor) {
                // get the relevant source coordinate given the plot orientation
                double sourceY = source.getY();
                if (this.spectrumPlot.getOrientation() == PlotOrientation.HORIZONTAL) {
                    sourceY = source.getX();
                }
                double anchorY = yAxis.java2DToValue(sourceY,
                        info.getDataArea(), spectrumPlot.getRangeAxisEdge());
                yAxis.resizeRange2(factor, anchorY);
            } else {
                yAxis.resizeRange(factor);
            }
        }
    }

    @Override
    public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
        for (ValueAxis yAxis : this.spectrumPlot.getRangeAxes().values()) {
            if (yAxis != null) {
                yAxis.zoomRange(lowerPercent, upperPercent);
            }
        }
    }
}
