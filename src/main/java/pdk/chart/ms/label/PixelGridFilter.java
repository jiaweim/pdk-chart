package pdk.chart.ms.label;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Screen-space bucketization.
 */
public final class PixelGridFilter {

    /**
     * Pixel width.
     */
    private double gridWidth = 40;

    public PixelGridFilter() {
    }

    public PixelGridFilter(double gridWidth) {
        this.gridWidth = gridWidth;
    }

    public double getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(double gridWidth) {

        if (gridWidth <= 1)
            throw new IllegalArgumentException();

        this.gridWidth = gridWidth;
    }

    /**
     * Buckets candidates.
     */
    public List<PixelColumn> filter(
            List<PeakLabelCandidate> candidates,
            Rectangle2D dataArea) {

        int count = Math.max(1, (int) Math.ceil(dataArea.getWidth() / gridWidth));

        List<PixelColumn> columns = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            columns.add(new PixelColumn());
        }

        double minX = dataArea.getMinX();

        for (PeakLabelCandidate candidate : candidates) {

            int column = (int)
                    ((candidate.getAnchorX() - minX) / gridWidth);

            if (column < 0 || column >= count)
                continue;

            columns.get(column).add(candidate);
        }

        for (PixelColumn column : columns) {
            if (!column.isEmpty()) {
                column.sort();
            }
        }

        return columns;
    }

}