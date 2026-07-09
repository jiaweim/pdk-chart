package pdk.chart.ms.label;

import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Layout engine for spectrum peak labels.
 *
 * <p>The layout is calculated once during renderer initialization.
 * The renderer only paints the generated {@link PeakLabel} objects.</p>
 */
public class PeakLabelLayout {

    /**
     * Minimum relative intensity.
     *
     * <p>0.02 means only peaks higher than 2% of current visible maximum
     * are considered.</p>
     */
    private double minimumRelativeIntensity = 0.02;
    /**
     * Maximum number of labels.
     */
    private int maximumLabels = 100;

    /**
     * Distance between peak and label.
     */
    private double labelOffset = 5.0;

    /**
     * Maximum upward shifting steps.
     * 当测试标签与已有标签相交，就向上移动，直到不相交。
     * 每次移动 shiftStep，最多移动 maxShiftSteps 次。
     */
    private int maxShiftSteps = 8;
    /**
     * Shift distance per step.
     */
    private double shiftStep = 8.0;

    private NumberFormat mzFormat = new DecimalFormat("0.00");

    /**
     * collect and layout automatic labels.
     *
     * @param g2        {@link Graphics2D} instance.
     * @param labelFont {@link Font} used to calculate label size.
     * @param dataset   {@link XYDataset}
     * @param series    series index.
     * @param dataArea  {@link Rectangle2D} of data area.
     * @param plot      {@link XYPlot}
     * @return final labels.
     */
    public List<PeakLabel> layout(Graphics2D g2, Font labelFont,
            XYDataset dataset, int series,
            Rectangle2D dataArea, XYPlot plot) {

        List<PeakCandidate> candidates = collectCandidates(dataset, series,
                dataArea, plot);
        if (candidates.isEmpty()) {
            return List.of();
        }
        candidates.sort(Comparator.comparingDouble(PeakCandidate::intensity).reversed());

        List<PeakLabel> result = new ArrayList<>();
        // The bounds of the label have been determined.
        List<Rectangle2D> occupied = new ArrayList<>();

        FontMetrics fm = g2.getFontMetrics(labelFont);
        int count = 0;
        for (PeakCandidate candidate : candidates) {
            if (count >= maximumLabels) {
                break;
            }

            String text = mzFormat.format(candidate.mz);
            Rectangle2D textBounds = fm.getStringBounds(text, g2);

            double width = textBounds.getWidth();
            double height = textBounds.getHeight();

            double labelX = candidate.x;
            double labelY = candidate.y - labelOffset;

            Rectangle2D labelBounds = new Rectangle2D.Double(labelX - width / 2.0, labelY - height, width, height);
            boolean placed = false;
            for (int i = 0; i <= maxShiftSteps; i++) {
                if (!intersects(labelBounds, occupied)
                        && dataArea.contains(labelBounds)) {
                    placed = true;
                    break;
                }

                labelY -= shiftStep;
                labelBounds = new Rectangle2D.Double(labelX - width / 2.0,
                        labelY - height, width, height);
            }

            if (!placed) {
                continue;
            }

            occupied.add(labelBounds);
            // 这个算法比较简单，当标签相交时，只是上移标签，所以 anchorX 和 labelX 总是相同
            // 如果允许左移，则不同
            result.add(new PeakLabel(series,
                    candidate.item,
                    candidate.mz,
                    candidate.intensity,
                    candidate.relativeIntensity,
                    text,
                    candidate.x,
                    candidate.y,
                    labelX,
                    labelY));
            count++;
        }

        return result;
    }

    /**
     * Collect candidate peaks with intensity higher than the
     * specified threshold in the current view
     */
    private List<PeakCandidate> collectCandidates(XYDataset dataset, int series,
            Rectangle2D dataArea, XYPlot plot) {

        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();
        RectangleEdge xEdge = plot.getDomainAxisEdge();
        RectangleEdge yEdge = plot.getRangeAxisEdge();

        int itemCount = dataset.getItemCount(series);

        double upperBound = rangeAxis.getUpperBound();
        List<PeakCandidate> result = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            double x = dataset.getXValue(series, i);
            double y = dataset.getYValue(series, i);

            double relative = y / upperBound;
            if (relative < minimumRelativeIntensity) {
                continue;
            }

            double xx = domainAxis.valueToJava2D(x, dataArea, xEdge);
            double yy = rangeAxis.valueToJava2D(y, dataArea, yEdge);

            if (xx < dataArea.getMinX() || xx > dataArea.getMaxX()) {
                continue;
            }

            result.add(new PeakCandidate(i, x, y, relative, xx, yy));
        }

        return result;
    }

    /**
     * Return true if the {@code rect} intersects with existing Rectangle2D in {@code occupied}
     *
     * @param rect     {@link Rectangle2D} to test.
     * @param occupied existing {@link Rectangle2D}.
     * @return true if the {@code rect} intersects with exiting {@link Rectangle2D}.
     */
    private boolean intersects(Rectangle2D rect,
            List<Rectangle2D> occupied) {
        for (Rectangle2D r : occupied) {
            if (r.intersects(rect)) {
                return true;
            }
        }

        return false;
    }

    public void setMinimumRelativeIntensity(double value) {
        this.minimumRelativeIntensity = value;
    }

    public void setMaximumLabels(int value) {
        this.maximumLabels = value;
    }

    public void setLabelOffset(double value) {
        this.labelOffset = value;
    }

    /**
     * Data points with intensity exceeding the specified threshold
     *
     * @param item              item index in the dataset.
     * @param mz                x value
     * @param intensity         y value
     * @param relativeIntensity relative intensity in current view.
     * @param x                 x in Java2D coordinate.
     * @param y                 y in Java2D coordinate.
     */
    private record PeakCandidate(
            int item,
            double mz,
            double intensity,
            double relativeIntensity,
            double x,
            double y) {
    }
}