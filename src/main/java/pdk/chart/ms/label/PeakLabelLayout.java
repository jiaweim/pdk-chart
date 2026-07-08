package pdk.chart.ms.label;

import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
     */
    private int maxShiftSteps = 8;

    /**
     * Shift distance per step.
     */
    private double shiftStep = 8.0;

    public List<PeakLabel> layout(
            Graphics2D g2,
            Font labelFont,
            XYDataset dataset,
            int series,
            Rectangle2D dataArea,
            XYPlot plot,
            ValueAxis domainAxis,
            ValueAxis rangeAxis) {

        List<PeakCandidate> candidates = collectCandidates(
                dataset, series, dataArea,
                plot, domainAxis, rangeAxis);
        if (candidates.isEmpty()) {
            return List.of();
        }
        candidates.sort(Comparator.comparingDouble(PeakCandidate::intensity).reversed());

        List<PeakLabel> result = new ArrayList<>();
        List<Rectangle2D> occupied = new ArrayList<>();

        FontMetrics fm = g2.getFontMetrics(labelFont);

        int count = 0;
        for (PeakCandidate candidate : candidates) {
            if (count >= maximumLabels) {
                break;
            }

            String text = formatMZ(candidate.mz());

            Rectangle2D textBounds = fm.getStringBounds(text, g2);

            double width = textBounds.getWidth();

            double height =
                    textBounds.getHeight();


            double labelX = candidate.x();
            double labelY = candidate.y() - labelOffset;

            Rectangle2D labelBounds =
                    createBounds(
                            labelX - width / 2.0,
                            labelY,
                            width,
                            height);


            boolean placed = false;


            for (int i = 0;
                 i <= maxShiftSteps;
                 i++) {


                if (!intersects(
                        labelBounds,
                        occupied)
                        &&
                        inside(
                                labelBounds,
                                dataArea)) {


                    placed = true;
                    break;
                }


                labelY -= shiftStep;
                labelBounds =
                        createBounds(
                                labelX - width / 2.0,
                                labelY,
                                width,
                                height);
            }


            if (!placed) {
                continue;
            }


            occupied.add(labelBounds);


            result.add(
                    new PeakLabel(
                            series,
                            candidate.item(),
                            candidate.mz(),
                            candidate.intensity(),
                            candidate.relativeIntensity(),
                            text,
                            candidate.x(),
                            candidate.y(),
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
    private List<PeakCandidate> collectCandidates(XYDataset dataset,
            int series,
            Rectangle2D dataArea,
            XYPlot plot,
            ValueAxis domainAxis,
            ValueAxis rangeAxis) {

        RectangleEdge xEdge = plot.getDomainAxisEdge();
        RectangleEdge yEdge = plot.getRangeAxisEdge();

        int itemCount = dataset.getItemCount(series);

        double maxIntensity = 0;
        // First pass: calculate current visible maximum.
        for (int i = 0; i < itemCount; i++) {
            double x = dataset.getXValue(series, i);
            double y = dataset.getYValue(series, i);

            double xx = domainAxis.valueToJava2D(x, dataArea, xEdge);
            if (xx < dataArea.getMinX() || xx > dataArea.getMaxX()) {
                continue;
            }

            maxIntensity = Math.max(maxIntensity, y);
        }

        if (maxIntensity <= 0) {
            return List.of();
        }

        List<PeakCandidate> result = new ArrayList<>();
        // Second pass: create candidates.
        for (int i = 0; i < itemCount; i++) {
            double x = dataset.getXValue(series, i);
            double y = dataset.getYValue(series, i);

            double relative = y / maxIntensity;
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


    private Rectangle2D createBounds(
            double x,
            double y,
            double width,
            double height) {


        return new Rectangle2D.Double(
                x,
                y - height,
                width,
                height);
    }


    private boolean intersects(
            Rectangle2D rect,
            List<Rectangle2D> occupied) {


        for (Rectangle2D r : occupied) {

            if (r.intersects(rect)) {
                return true;
            }
        }

        return false;
    }


    private boolean inside(Rectangle2D rect, Rectangle2D area) {
        return area.contains(rect);
    }


    private String formatMZ(double mz) {
        return String.format("%.2f", mz);
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