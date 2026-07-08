package pdk.chart.ms;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.*;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.*;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.util.ShadowGenerator;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Plot for spectrum.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:04 PM
 */
public class PSMPlot extends XYPlot<SeriesType> {

    private PeptideDataset peptideDataset;

    /**
     * Default font for amino acid residues.
     */
    public static final Font DEFAULT_RESIDUE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 32);
    /**
     * default font for label text.
     */
    public static final Font DEFAULT_LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Paint DEFAULT_RESIDUE_PAINT = Color.BLACK;

    public static final Paint DEFAULT_SPECIAL_RESIDUE_PAINT = Color.RED;

    public static final double DEFAULT_RESIDUE_SPACING = 16.0;

    public static final Stroke DEFAULT_LINE_STROKE = new BasicStroke(2.0f);

    public static final double DEFAULT_RESIDUE_LINE_SPACING = 2.0;

    public static final double DEFAULT_LABEL_LINE_SPACING = 2.0;

    /**
     * The font to write the residue.
     * Monospace fonts are commonly used to ensure alignment.
     */
    private Font aminoAcidFont = DEFAULT_RESIDUE_FONT;
    /**
     * Color that the value is written in.
     */
    private transient Paint aminoAcidPaint = DEFAULT_RESIDUE_PAINT;
    /**
     * Paint used for rendering special amino acids, such as modified amino acids.
     */
    private transient Paint markAminoAcidPaint = DEFAULT_SPECIAL_RESIDUE_PAINT;
    /**
     * The space between adjacent residues.
     */
    private double aminoAcidSpacing = DEFAULT_RESIDUE_SPACING;
    /**
     * The font used to render annotation label.
     * <p>
     * The annotation font is generally slightly smaller than the amino acid font.
     */
    private Font labelFont = DEFAULT_LABEL_FONT;

    private Stroke annotationLineStroke = DEFAULT_LINE_STROKE;

    /**
     * The space between amino acid residue and annotation line.
     */
    private double aminoAcidAnnotationLineGap = DEFAULT_RESIDUE_LINE_SPACING;

    private double labelLineSpacing = DEFAULT_LABEL_LINE_SPACING;

    private double peptideHeight = 100;

    private final PeakRenderer renderer = new PeakRenderer();

    public PSMPlot() {
        super();
        NumberAxis xAxis = new NumberAxis("m/z");
        NumberAxis yAxis = new NumberAxis("Relative Abundance");
        xAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRangeIncludesZero(true);

        setDomainAxis(xAxis);
        setRangeAxis(yAxis);
        setRenderer(renderer);

        setDomainGridlinesVisible(false);
        setRangeGridlinesVisible(false);

        renderer.setDefaultItemLabelGenerator((dataset, series, item) -> {
            SpectrumDataset seriesDataset = (SpectrumDataset) dataset;
            SeriesType seriesKey = seriesDataset.getSeriesKey(series);
            String[] labels = seriesDataset.getLabels(seriesKey);
            if (labels == null || labels.length == 0) {
                return "";
            }
            return labels[item];
        });
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    }

    public double getPeptideHeight() {
        return peptideHeight;
    }

    /**
     * Set the height of the area for rendering peptide sequences.
     *
     * @param peptideHeight height in pixels.
     */
    public void setPeptideHeight(double peptideHeight) {
        if (this.peptideHeight != peptideHeight) {
            this.peptideHeight = peptideHeight;
            fireChangeEvent();
        }
    }

    /**
     * Set the font used for rendering amino acid residues.
     *
     * @param font {@link Font}
     */
    public void setAminoAcidFont(@NonNull Font font) {
        Objects.requireNonNull(font, "font");
        if (!this.aminoAcidFont.equals(font)) {
            this.aminoAcidFont = font;
            fireChangeEvent();
        }
    }

    /**
     * Return the {@link Font } used for rendering amino acid residues.
     *
     * @return {@link Font}
     */
    public Font getAminoAcidFont() {
        return this.aminoAcidFont;
    }

    /**
     * Set the paint used to draw amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setAminoAcidPaint(@NonNull Paint paint) {
        Objects.requireNonNull(paint, "paint");
        this.aminoAcidPaint = paint;
        fireChangeEvent();
    }

    /**
     * Return the paint used to draw amino acid letters.
     *
     * @return {@link Paint}
     */
    public Paint getAminoAcidPaint() {
        return this.aminoAcidPaint;
    }


    /**
     * Return the paint used to draw marked amino acid letters.
     *
     * @return {@link Paint}
     */
    public Paint getMarkAminoAcidPaint() {
        return this.markAminoAcidPaint;
    }

    /**
     * Set the paint used to draw marked amino acid letters.
     *
     * @param paint {@link Paint}
     */
    public void setMarkAminoAcidPaint(@NonNull Paint paint) {
        Objects.requireNonNull(paint, "paint");
        if (!this.markAminoAcidPaint.equals(paint)) {
            this.markAminoAcidPaint = paint;
            fireChangeEvent();
        }
    }

    /**
     * Returns the gap width between adjacent residues.
     *
     * @return the gap width in pixels.
     */
    public double getAminoAcidSpacing() {
        return aminoAcidSpacing;
    }

    /**
     * Set the gap between adjacent residues and sends a {@link pdk.chart.event.PlotChangeEvent}
     * to all registered listeners.
     *
     * @param aminoAcidSpacing the gap in pixels.
     */
    public void setAminoAcidSpacing(double aminoAcidSpacing) {
        if (this.aminoAcidSpacing != aminoAcidSpacing) {
            this.aminoAcidSpacing = aminoAcidSpacing;
            fireChangeEvent();
        }
    }

    /**
     * Return the font used to draw annotation text.
     *
     * @return {@link Font}
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Set the font used to draw annotation text.
     *
     * @param font {@link Font}.
     */
    public void setLabelFont(@NonNull Font font) {
        Objects.requireNonNull(font, "font");
        if (!this.labelFont.equals(font)) {
            this.labelFont = font;
            fireChangeEvent();
        }
    }

    /**
     * Return the stroke used to draw annotation line.
     *
     * @return {@link Stroke}.
     */
    public Stroke getAnnotationLineStroke() {
        return annotationLineStroke;
    }

    /**
     * Set the stroke used to draw annotation line.
     *
     * @param stroke {@link Stroke}
     */
    public void setAnnotationLineStroke(Stroke stroke) {
        Objects.requireNonNull(stroke);
        if (!this.annotationLineStroke.equals(stroke)) {
            this.annotationLineStroke = stroke;
            fireChangeEvent();
        }
    }

    /**
     * Returns the vertical distance between amino acid residues and annotation lines.
     *
     * @return the gap between amino acid letters and annotation lines.
     */
    public double getAminoAcidAnnotationLineGap() {
        return this.aminoAcidAnnotationLineGap;
    }

    /**
     * Set the vertical distance between amino acid residues and annotation lines.
     *
     * @param gap the gap in pixels.
     */
    public void setAminoAcidAnnotationLineGap(double gap) {
        if (this.aminoAcidAnnotationLineGap != gap) {
            this.aminoAcidAnnotationLineGap = gap;
            fireChangeEvent();
        }
    }

    /**
     * Return the vertical distance between the annotation label and annotation line.
     *
     * @return gap between annotation text and line.
     */
    public double getLabelLineGap() {
        return this.labelLineSpacing;
    }

    /**
     * Set the vertical distance between the annotation label and annotation line.
     *
     * @param gap gap in pixel.
     */
    public void setLabelLineGap(double gap) {
        if (this.labelLineSpacing != gap) {
            this.labelLineSpacing = gap;
            fireChangeEvent();
        }
    }

    /**
     * Return the dataset for the plot.
     *
     * @return the dataset.
     */
    public @Nullable PeptideDataset getPeptideDataset() {
        return peptideDataset;
    }

    /**
     * Set the dataset to display.
     *
     * @param spectrumDataset {@link SpectrumDataset}.
     */
    public void setDataset(@Nullable PeptideDataset peptideDataset,
            SpectrumDataset spectrumDataset) {
        PeptideDataset existing = this.peptideDataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }
        this.peptideDataset = peptideDataset;
        if (peptideDataset != null) {
            peptideDataset.addChangeListener(this);
        }

        DatasetChangeEvent event = new DatasetChangeEvent(this, peptideDataset);
        datasetChanged(event);

        spectrumDataset.sort();
        setDataset(0, spectrumDataset);

        for (int i = 0; i < spectrumDataset.getSeriesCount(); i++) {
            SeriesType seriesKey = spectrumDataset.getSeriesKey(i);
            renderer.setSeriesShape(i, new Ellipse2D.Double(-0.5, 0.5, 1, 1)); // without ending shape
            renderer.setSeriesPaint(i, seriesKey.getColor());
            renderer.setSeriesStroke(i, new BasicStroke(seriesKey.getStokeWidth()));
            renderer.setSeriesItemLabelPaint(i, seriesKey.getColor());
        }
    }

    /**
     * Set the dataset to display.
     *
     * @param spectrumDataset {@link SpectrumDataset}.
     */
    public void setDataset(SpectrumDataset spectrumDataset) {
        setDataset(null, spectrumDataset);
    }

    public void setSeriesPaint(int series, Paint paint) {
        renderer.setSeriesPaint(series, paint);
    }

    /**
     * Draws the plot within the specified area on a graphics device.
     *
     * @param g2          the graphics device.
     * @param area        the plot area (in Java2D space).
     * @param anchor      an anchor point in Java2D space ({@code null}
     *                    permitted).
     * @param parentState the state from the parent plot, if there is one
     *                    ({@code null} permitted).
     * @param info        collects chart drawing information ({@code null}
     *                    permitted).
     */
    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
            PlotState parentState, PlotRenderingInfo info) {

        // if the plot area is too small, just return...
        if ((area.getWidth() <= MINIMUM_WIDTH_TO_DRAW)
                || (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW)) {
            return;
        }

        // record the plot area...
        if (info != null) {
            info.setPlotArea(area);
        }

        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);

        AxisSpace space = calculateAxisSpace(g2, area);
        Rectangle2D dataArea = space.shrink(area, null);
        drawBackground(g2, dataArea);
        Rectangle2D outline = new Rectangle2D.Double(
                dataArea.getX(),
                dataArea.getY(),
                dataArea.getWidth(),
                dataArea.getHeight()
        );

        if (peptideHeight > 0 && peptideDataset != null) {
            Rectangle2D peptideArea = new Rectangle2D.Double(
                    dataArea.getX(),
                    dataArea.getY(),
                    dataArea.getWidth(),
                    peptideHeight
            );
            drawPeptide(g2, peptideArea);

            dataArea.setRect(
                    dataArea.getX(),
                    dataArea.getY() + peptideHeight,
                    dataArea.getWidth(),
                    Math.max(1, dataArea.getHeight() - peptideHeight)
            );
        }

        RectangleInsets axisOffset = getAxisOffset();
        axisOffset.trim(dataArea);

        dataArea = integerise(dataArea); // Rounding will alter Y values
        if (dataArea.isEmpty()) {
            return;
        }

        // make the maxy of outline the same as data area
        double maxDataY = dataArea.getY() + dataArea.getHeight();
        outline.setRect(
                outline.getX(),
                outline.getY(),
                outline.getWidth(),
                maxDataY - outline.getY()
        );

        createAndAddEntity((Rectangle2D) dataArea.clone(), info, null, null);
        if (info != null) {
            info.setDataArea(dataArea);
        }

        // draw the plot background and axes...
        Map<Axis, AxisState> axisStateMap = drawAxes(g2, area, dataArea, info);

        PlotOrientation orient = getOrientation();

        // the anchor point is typically the point where the mouse last
        // clicked - the crosshairs will be driven off this point...
        if (anchor != null && !dataArea.contains(anchor)) {
            anchor = null;
        }
        CrosshairState crosshairState = new CrosshairState();
        crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
        crosshairState.setAnchor(anchor);

        crosshairState.setAnchorX(Double.NaN);
        crosshairState.setAnchorY(Double.NaN);
        if (anchor != null) {
            ValueAxis domainAxis = getDomainAxis();
            if (domainAxis != null) {
                double x;
                if (orient == PlotOrientation.VERTICAL) {
                    x = domainAxis.java2DToValue(anchor.getX(), dataArea,
                            getDomainAxisEdge());
                } else {
                    x = domainAxis.java2DToValue(anchor.getY(), dataArea,
                            getDomainAxisEdge());
                }
                crosshairState.setAnchorX(x);
            }
            ValueAxis rangeAxis = getRangeAxis();
            if (rangeAxis != null) {
                double y;
                if (orient == PlotOrientation.VERTICAL) {
                    y = rangeAxis.java2DToValue(anchor.getY(), dataArea,
                            getRangeAxisEdge());
                } else {
                    y = rangeAxis.java2DToValue(anchor.getX(), dataArea,
                            getRangeAxisEdge());
                }
                crosshairState.setAnchorY(y);
            }
        }
        crosshairState.setCrosshairX(getDomainCrosshairValue());
        crosshairState.setCrosshairY(getRangeCrosshairValue());
        Shape originalClip = g2.getClip();
        Composite originalComposite = g2.getComposite();

        g2.clip(dataArea);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                getForegroundAlpha()));

        AxisState domainAxisState = axisStateMap.get(getDomainAxis());
        if (domainAxisState == null) {
            if (parentState != null) {
                domainAxisState = parentState.getSharedAxisStates()
                        .get(getDomainAxis());
            }
        }

        AxisState rangeAxisState = axisStateMap.get(getRangeAxis());
        if (rangeAxisState == null) {
            if (parentState != null) {
                rangeAxisState = parentState.getSharedAxisStates()
                        .get(getRangeAxis());
            }
        }
        if (domainAxisState != null) {
            drawDomainTickBands(g2, dataArea, domainAxisState.getTicks());
        }
        if (rangeAxisState != null) {
            drawRangeTickBands(g2, dataArea, rangeAxisState.getTicks());
        }
        if (domainAxisState != null) {
            drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
            drawZeroDomainBaseline(g2, dataArea);
        }
        if (rangeAxisState != null) {
            drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
            drawZeroRangeBaseline(g2, dataArea);
        }

        Graphics2D savedG2 = g2;
        BufferedImage dataImage = null;
        boolean suppressShadow = Boolean.TRUE.equals(g2.getRenderingHint(
                Chart.KEY_SUPPRESS_SHADOW_GENERATION));
        ShadowGenerator shadowGenerator = getShadowGenerator();
        if (shadowGenerator != null && !suppressShadow) {
            dataImage = new BufferedImage((int) dataArea.getWidth(),
                    (int) dataArea.getHeight(), BufferedImage.TYPE_INT_ARGB);
            g2 = dataImage.createGraphics();
            g2.translate(-dataArea.getX(), -dataArea.getY());
            g2.setRenderingHints(savedG2.getRenderingHints());
        }

        // draw the markers that are associated with a specific dataset...
        Map<Integer, XYDataset<SeriesType>> datasets = getDatasets();
        for (XYDataset<SeriesType> dataset : datasets.values()) {
            int datasetIndex = indexOf(dataset);
            drawDomainMarkers(g2, dataArea, datasetIndex, Layer.BACKGROUND);
        }
        for (XYDataset<SeriesType> dataset : datasets.values()) {
            int datasetIndex = indexOf(dataset);
            drawRangeMarkers(g2, dataArea, datasetIndex, Layer.BACKGROUND);
        }

        // now draw annotations and render data items...
        boolean foundData = false;
        DatasetRenderingOrder order = getDatasetRenderingOrder();
        List<Integer> rendererIndices = getRendererIndices(order);
        List<Integer> datasetIndices = getDatasetIndices(order);

        // draw background annotations
        for (int i : rendererIndices) {
            XYItemRenderer renderer = getRenderer(i);
            if (renderer != null) {
                ValueAxis domainAxis = getDomainAxisForDataset(i);
                ValueAxis rangeAxis = getRangeAxisForDataset(i);
                renderer.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                        Layer.BACKGROUND, info);
            }
        }

        // render data items...
        for (int datasetIndex : datasetIndices) {
            foundData = render(g2, dataArea, datasetIndex, info,
                    crosshairState) || foundData;
        }

        // draw foreground annotations
        for (int i : rendererIndices) {
            XYItemRenderer renderer = getRenderer(i);
            if (renderer != null) {
                ValueAxis domainAxis = getDomainAxisForDataset(i);
                ValueAxis rangeAxis = getRangeAxisForDataset(i);
                renderer.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                        Layer.FOREGROUND, info);
            }
        }

        // draw domain crosshair if required...
        int datasetIndex = crosshairState.getDatasetIndex();
        ValueAxis xAxis = getDomainAxisForDataset(datasetIndex);
        RectangleEdge xAxisEdge = getDomainAxisEdge(getDomainAxisIndex(xAxis));
        if (!this.isDomainCrosshairLockedOnData() && anchor != null) {
            double xx;
            if (orient == PlotOrientation.VERTICAL) {
                xx = xAxis.java2DToValue(anchor.getX(), dataArea, xAxisEdge);
            } else {
                xx = xAxis.java2DToValue(anchor.getY(), dataArea, xAxisEdge);
            }
            crosshairState.setCrosshairX(xx);
        }
        setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
        if (isDomainCrosshairVisible()) {
            double x = getDomainCrosshairValue();
            Paint paint = getDomainCrosshairPaint();
            Stroke stroke = getDomainCrosshairStroke();
            drawDomainCrosshair(g2, dataArea, orient, x, xAxis, stroke, paint);
        }

        // draw range crosshair if required...
        ValueAxis yAxis = getRangeAxisForDataset(datasetIndex);
        RectangleEdge yAxisEdge = getRangeAxisEdge(getRangeAxisIndex(yAxis));
        if (!this.isRangeCrosshairLockedOnData() && anchor != null) {
            double yy;
            if (orient == PlotOrientation.VERTICAL) {
                yy = yAxis.java2DToValue(anchor.getY(), dataArea, yAxisEdge);
            } else {
                yy = yAxis.java2DToValue(anchor.getX(), dataArea, yAxisEdge);
            }
            crosshairState.setCrosshairY(yy);
        }
        setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
        if (isRangeCrosshairVisible()) {
            double y = getRangeCrosshairValue();
            Paint paint = getRangeCrosshairPaint();
            Stroke stroke = getRangeCrosshairStroke();
            drawRangeCrosshair(g2, dataArea, orient, y, yAxis, stroke, paint);
        }

        if (!foundData) {
            drawNoDataMessage(g2, dataArea);
        }

        for (int i : rendererIndices) {
            drawDomainMarkers(g2, dataArea, i, Layer.FOREGROUND);
        }
        for (int i : rendererIndices) {
            drawRangeMarkers(g2, dataArea, i, Layer.FOREGROUND);
        }

        drawAnnotations(g2, dataArea, info);
        if (shadowGenerator != null && !suppressShadow) {
            BufferedImage shadowImage
                    = shadowGenerator.createDropShadow(dataImage);
            g2 = savedG2;
            g2.drawImage(shadowImage,
                    (int) dataArea.getX() + shadowGenerator.calculateOffsetX(),
                    (int) dataArea.getY() + shadowGenerator.calculateOffsetY(),
                    null);
            g2.drawImage(dataImage, (int) dataArea.getX(),
                    (int) dataArea.getY(), null);
        }
        g2.setClip(originalClip);
        g2.setComposite(originalComposite);

        drawOutline(g2, outline);
    }

    protected void drawPeptide(Graphics2D g2, Rectangle2D plotArea) {
        if (this.peptideDataset.isEmpty()) {
            return;
        }

        // Save original settings
        Paint originalPaint = g2.getPaint();
        Stroke originalStroke = g2.getStroke();
        Font originalFont = g2.getFont();

        // 1. draw amino acid letters
        g2.setFont(aminoAcidFont);
        g2.setPaint(aminoAcidPaint);

        FontMetrics metrics = g2.getFontMetrics();
        int aaWidth = metrics.stringWidth("M");
        int aaDescent = metrics.getDescent();
        int aaHeight = metrics.getAscent() + aaDescent; // without leading

        // Font height from top to bottom: Leading+Ascent+Descent
        //  The y coordinate in drawString represents the baseline, which lies between Ascent and Descent.
        // ascent: the distance from the baseline upward to the top of most characters. Top-Y of text = baseline-Y − ascent
        // descent: the distance extending downward from the baseline to the bottom of character descenders
        // Bottom Y of text = baseline Y + descent
        double aaAndSpacing = aminoAcidSpacing + aaWidth;

        int length = peptideDataset.size();
        double totalWidth = length * aaAndSpacing;
        double halfSpacing = aminoAcidSpacing * 0.5;
        double halfAAAndSpacing = aaAndSpacing * 0.5;

        double startX = plotArea.getCenterX() - totalWidth / 2;
        double endX = plotArea.getCenterX() + totalWidth / 2;
        double centerY = plotArea.getCenterY();

        // 文本垂直居中时，底部位置为 centerY+aaHeight/2，而 baseline 比底部高 descent,因此位置为 centerY+aaHeight/2-descent
        float aaY = (float) (centerY + aaHeight / 2.0 - aaDescent);
        char[] value = peptideDataset.getValue();
        boolean[] marked = peptideDataset.getMarked();
        // first round.
        for (int i = 0; i < length; i++) {
            if (!marked[i]) {
                String letter = String.valueOf(value[i]);

                double centerX = startX + halfAAAndSpacing + i * aaAndSpacing;
                float textX = (float) (centerX - metrics.stringWidth(letter) / 2.0); // Horizontally centered
                g2.drawString(letter, textX, aaY); // textX
            }
        }
        // second round.
        g2.setPaint(markAminoAcidPaint);
        for (int i = 0; i < length; i++) {
            if (marked[i]) {
                String letter = String.valueOf(value[i]);

                double centerX = startX + halfAAAndSpacing + i * aaAndSpacing;
                float textX = (float) (centerX - metrics.stringWidth(letter) / 2.0); // Horizontally centered
                g2.drawString(letter, textX, aaY); // textX
            }
        }

        // 2. draw annotations
        List<PeptideAnnotation> annotations = peptideDataset.getAnnotations();
        if (!annotations.isEmpty()) {
            g2.setFont(labelFont);
            g2.setStroke(annotationLineStroke);
            metrics = g2.getFontMetrics();
            // 这里忽略线段宽度
            float offset = (float) (aaHeight / 2.0 + aminoAcidAnnotationLineGap + labelLineSpacing);

            List<PeptideAnnotation> nAnnotations = new ArrayList<>();
            List<PeptideAnnotation> cAnnotations = new ArrayList<>();
            for (PeptideAnnotation annotation : annotations) {
                if (annotation.isNTerminal()) {
                    nAnnotations.add(annotation);
                } else {
                    cAnnotations.add(annotation);
                }
            }

            HashSet<Integer> visited = new HashSet<>();
            // N 端注释残基上面
            if (!nAnnotations.isEmpty()) {
                // 文本底部在 centerY-offset，baseline 往上 descent
                float labelY = (float) (centerY - offset - metrics.getDescent()); // baseline for the text,

                int[] xPoints = new int[3];
                int[] yPoints = new int[3];
                yPoints[0] = (int) centerY;
                yPoints[1] = (int) (centerY - aaHeight / 2.0 - aminoAcidAnnotationLineGap);
                yPoints[2] = yPoints[1];

                for (PeptideAnnotation annotation : nAnnotations) {
                    int fragSize = annotation.getSize();
                    if (!visited.add(fragSize)) {
                        continue;
                    }

                    SeriesType seriesType = annotation.getSeriesType();
                    g2.setPaint(seriesType.getColor());

                    String label = annotation.getLabel();
                    int labelWidth = metrics.stringWidth(label);

                    float labelX = (float) (endX - (fragSize - 1) * aaAndSpacing - halfAAAndSpacing - labelWidth / 2.0);
                    // draw label
                    g2.drawString(label, labelX, labelY);

                    // draw annotation line
                    xPoints[0] = (int) (endX - fragSize * aaAndSpacing);
                    xPoints[1] = xPoints[0];
                    xPoints[2] = xPoints[0] + aaWidth + (int) halfSpacing;
                    g2.drawPolyline(xPoints, yPoints, 3);
                }
            }
            visited.clear();
            if (!cAnnotations.isEmpty()) {
                // 文本顶部在 centerY+offset，文本 baseline 需要加 ascent
                float labelY = (float) (centerY + offset + metrics.getAscent());

                int[] xPoints = new int[3];
                int[] yPoints = new int[3];
                yPoints[0] = (int) centerY;
                yPoints[1] = (int) (centerY + aaHeight / 2.0 + aminoAcidAnnotationLineGap);
                yPoints[2] = yPoints[1];

                for (PeptideAnnotation annotation : cAnnotations) {
                    int fragSize = annotation.getSize();
                    if (!visited.add(fragSize)) {
                        continue;
                    }

                    g2.setPaint(annotation.getSeriesType().getColor());

                    String label = annotation.getLabel();
                    int labelWidth = metrics.stringWidth(label);

                    // draw label
                    float labelX = (float) (startX + (fragSize - 1) * aaAndSpacing + halfAAAndSpacing - labelWidth / 2.0);
                    g2.drawString(label, labelX, labelY);

                    // draw line
                    xPoints[0] = (int) (startX + fragSize * aaAndSpacing);
                    xPoints[1] = xPoints[0];
                    xPoints[2] = (int) (xPoints[0] - aaWidth - halfSpacing);
                    g2.drawPolyline(xPoints, yPoints, 3);
                }
            }
        }

        // restore settings
        g2.setPaint(originalPaint);
        g2.setStroke(originalStroke);
        g2.setFont(originalFont);
    }
}
