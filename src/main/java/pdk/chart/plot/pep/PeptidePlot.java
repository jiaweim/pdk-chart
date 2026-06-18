package pdk.chart.plot.pep;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A plot that displays a peptide sequence.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:28 AM
 */
public class PeptidePlot extends Plot {

    /**
     * Default font for amino acid residues.
     */
    public static final Font DEFAULT_RESIDUE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);
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
     * The dataset for the plot.
     */
    private PeptideDataset dataset;
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

    private transient Stroke annotationLineStroke = DEFAULT_LINE_STROKE;

    /**
     * The space between amino acid residue and annotation line.
     */
    private double aminoAcidAnnotationLineGap = DEFAULT_RESIDUE_LINE_SPACING;

    private double labelLineSpacing = DEFAULT_LABEL_LINE_SPACING;

    /**
     * Create a new peptide plot.
     */
    public PeptidePlot() {
        this(new PeptideDataset());
    }

    /**
     * Create a new peptide plot.
     *
     * @param dataset {@link PeptideDataset}.
     */
    public PeptidePlot(PeptideDataset dataset) {
        super();
        this.dataset = dataset;
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
    public @Nullable PeptideDataset getDataset() {
        return dataset;
    }

    /**
     * Set the dataset and sends a {@link DatasetChangeEvent} to this.
     *
     * @param dataset {@link PeptideDataset}
     */
    public void setDataset(@Nullable PeptideDataset dataset) {
        // if there is an existing dataset, remove the plot from the list of
        // change listeners...
        PeptideDataset existing = this.dataset;
        if (existing != null) {
            existing.removeChangeListener(this);
        }

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
    }

    @Override
    public String getPlotType() {
        return "PeptidePlot";
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);

        if (area.isEmpty()) {
            return;
        }

        if (info != null) {
            info.setPlotArea(area);
            info.setDataArea(area);
        }

        drawBackground(g2, area);
        drawOutline(g2, area);

        Shape savedClip = g2.getClip();
        g2.clip(area);

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getForegroundAlpha()));
        if (!DatasetUtils.isEmptyOrNull(this.dataset)) {
            drawPeptide(g2, area, info);
        } else {
            drawNoDataMessage(g2, area);
        }

        g2.setClip(savedClip);
        g2.setComposite(originalComposite);

        drawOutline(g2, area);
    }

    protected void drawPeptide(Graphics2D g2, Rectangle2D plotArea, PlotRenderingInfo info) {
        if (this.dataset.isEmpty()) {
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

        int length = dataset.size();
        double totalWidth = length * aaAndSpacing;
        double halfSpacing = aminoAcidSpacing * 0.5;
        double halfAAAndSpacing = aaAndSpacing * 0.5;

        double startX = plotArea.getCenterX() - totalWidth / 2;
        double endX = plotArea.getCenterX() + totalWidth / 2;
        double centerY = plotArea.getCenterY();

        // 文本垂直居中时，底部位置为 centerY+aaHeight/2，而 baseline 比底部高 descent,因此位置为 centerY+aaHeight/2-descent
        float aaY = (float) (centerY + aaHeight / 2.0 - aaDescent);
        char[] value = dataset.getValue();
        boolean[] marked = dataset.getMarked();
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
        List<PeptideAnnotation> annotations = dataset.getAnnotations();
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
                    SeriesType seriesType = annotation.getSeriesType();
                    g2.setPaint(seriesType.getColor());

                    int fragSize = annotation.getSize();
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
            if (!cAnnotations.isEmpty()) {
                // 文本顶部在 centerY+offset，文本 baseline 需要加 ascent
                float labelY = (float) (centerY + offset + metrics.getAscent());

                int[] xPoints = new int[3];
                int[] yPoints = new int[3];
                yPoints[0] = (int) centerY;
                yPoints[1] = (int) (centerY + aaHeight / 2.0 + aminoAcidAnnotationLineGap);
                yPoints[2] = yPoints[1];

                for (PeptideAnnotation annotation : cAnnotations) {
                    g2.setPaint(annotation.getSeriesType().getColor());

                    int fragSize = annotation.getSize();
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
