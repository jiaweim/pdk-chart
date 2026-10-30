package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.NumberTickUnit;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;
import pdk.chart.plot.PolarPlot;
import pdk.chart.renderer.DefaultPolarItemRenderer;
import pdk.chart.swing.ChartPanel;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Radial polar bar chart using PolarPlot and a custom renderer.
 *
 * Target API: JFreeChart 1.5.x.
 *
 * Angle convention used by custom drawing:
 *   0 degrees points right.
 *   Positive angles rotate counterclockwise.
 *
 * This example uses one XY series and maps item index to category angle.
 */
public class RadialPolarBarExample extends JFrame {

    private static final String[] CATEGORIES = {"a", "b", "c", "d"};
    private static final double[] VALUES = {2.0, 1.2, 2.4, 3.6};

    /** Direction of the radial y-axis, in mathematical degrees. */
    private static final double START_ANGLE = 75.0;

    /** Angular width removed from each 90-degree category sector. */
    private static final double GAP = 15.0;

    private static final double MAX_VALUE = 4.0;
    private static final double INNER_RADIUS = 30.0;

    private static final Color BAR_COLOR = new Color(84, 112, 198);
    private static final Color GRID_COLOR = new Color(220, 220, 220);
    private static final Color AXIS_COLOR = new Color(60, 60, 60);

    public RadialPolarBarExample() {
        super("Radial Polar Bar Label Position (middle)");

        XYSeries series = new XYSeries("data");

        // X values are category indices; rendering uses the item index.
        for (int i = 0; i < CATEGORIES.length; i++) {
            series.add(i, VALUES[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Radius axis supplies the numeric range. The renderer performs
        // its own mapping so that value 0 starts at INNER_RADIUS.
        NumberAxis radiusAxis = new NumberAxis();
        radiusAxis.setRange(0.0, MAX_VALUE);
        radiusAxis.setTickUnit(new NumberTickUnit(1.0));
        radiusAxis.setTickLabelsVisible(false);
        radiusAxis.setTickMarksVisible(false);
        radiusAxis.setAxisLineVisible(false);

        RadialBarRenderer renderer =
                new RadialBarRenderer(CATEGORIES, MAX_VALUE,
                        INNER_RADIUS, GAP, BAR_COLOR);

        renderer.setShapesVisible(false);
        renderer.setDrawOutlineWhenFilled(false);

        RadialBarPlot plot = new RadialBarPlot(
                dataset, radiusAxis, renderer,
                MAX_VALUE, INNER_RADIUS, START_ANGLE
        );

        // Reserve room between the circular plot boundary and the
        // data-area boundary for category labels.
        plot.setMargin(35);

        // We draw the grid and axes ourselves to keep their radii
        // consistent with the annular bars.
        plot.setAngleGridlinesVisible(false);
        plot.setRadiusGridlinesVisible(false);
        plot.setAngleLabelsVisible(false);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);

        Chart chart = new Chart(
                "Radial Polar Bar Label Position (middle)",
                Chart.DEFAULT_TITLE_FONT,
                plot,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(700, 560));

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RadialPolarBarExample().setVisible(true)
        );
    }

    /**
     * Draws annular bars and the concentric grid.
     *
     * The standard PolarPlot still manages the chart and data-area layout.
     * This renderer does not draw the y-axis or category labels.
     */
    private static final class RadialBarRenderer
            extends DefaultPolarItemRenderer {

        private final String[] categories;
        private final double maxValue;
        private final double innerRadius;
        private final double gapAngle;
        private final Paint barPaint;

        RadialBarRenderer(
                String[] categories,
                double maxValue,
                double innerRadius,
                double gapAngle,
                Paint barPaint) {

            this.categories = categories.clone();
            this.maxValue = maxValue;
            this.innerRadius = innerRadius;
            this.gapAngle = gapAngle;
            this.barPaint = barPaint;
        }

        @Override
        public void drawSeries(
                Graphics2D g2,
                Rectangle2D dataArea,
                PlotRenderingInfo info,
                PolarPlot plot,
                XYDataset dataset,
                int seriesIndex) {

            if (seriesIndex < 0
                    || seriesIndex >= dataset.getSeriesCount()) {
                return;
            }

            Graphics2D g = (Graphics2D) g2.create();

            try {
                g.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                g.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                );

                Geometry geo = new Geometry(
                        dataArea, plot.getMargin(),
                        maxValue, innerRadius
                );

                // Draw the grid once, before the first series' bars.
                if (seriesIndex == 0) {
                    drawGrid(g, geo);
                }

                drawBars(g, geo, dataset, seriesIndex);

            } finally {
                g.dispose();
            }
        }

        private void drawGrid(Graphics2D g, Geometry geo) {
            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();

            g.setPaint(GRID_COLOR);
            g.setStroke(new BasicStroke(1.0f));

            // Grid rings correspond to values 1, 2, 3, and 4.
            for (int tick = 1; tick <= (int) maxValue; tick++) {
                double r = geo.radiusForValue(tick);

                g.draw(new Ellipse2D.Double(
                        geo.cx - r, geo.cy - r,
                        2.0 * r, 2.0 * r
                ));
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
        }

        private void drawBars(
                Graphics2D g,
                Geometry geo,
                XYDataset dataset,
                int seriesIndex) {

            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();
            Font oldFont = g.getFont();

            int itemCount = dataset.getItemCount(seriesIndex);
            double sectorAngle = 360.0 / categories.length;

            g.setFont(new Font("SansSerif", Font.PLAIN, 12));

            for (int i = 0; i < itemCount; i++) {
                double value = dataset.getYValue(seriesIndex, i);

                if (!Double.isFinite(value)) {
                    continue;
                }

                value = Math.max(0.0, Math.min(maxValue, value));

                // Category interval:
                // [START_ANGLE + i * sectorAngle,
                //  START_ANGLE + (i + 1) * sectorAngle]
                //
                // Shrink both sides by gapAngle / 2.
                double centerAngle =
                        START_ANGLE + (i + 0.5) * sectorAngle;

                double extent = sectorAngle - gapAngle;

                if (extent <= 0.0) {
                    continue;
                }

                double outerR = geo.radiusForValue(value);

                if (outerR <= innerRadius) {
                    continue;
                }

                Shape bar = createAnnularSector(
                        geo.cx, geo.cy,
                        innerRadius, outerR,
                        centerAngle, extent
                );

                g.setPaint(barPaint);
                g.fill(bar);

                g.setPaint(Color.WHITE);
                g.setStroke(new BasicStroke(1.0f));
                g.draw(bar);

                // Place the label halfway through the visible bar.
                double labelR = (innerRadius + outerR) / 2.0;
                Point2D labelPoint =
                        geo.toPoint(centerAngle, labelR);

                String label = categories[i] + ": "
                        + formatValue(value);

                g.setPaint(Color.BLACK);
                TextUtils.drawAlignedString(
                        label,
                        g,
                        (float) labelPoint.getX(),
                        (float) labelPoint.getY(),
                        TextAnchor.CENTER
                );
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
            g.setFont(oldFont);
        }
    }

    /**
     * Adds the y-axis and outer category labels after PolarPlot
     * has finished drawing its grid and data series.
     */
    private static final class RadialBarPlot extends PolarPlot {

        private final double maxValue;
        private final double innerRadius;
        private final double startAngle;

        RadialBarPlot(
                XYDataset dataset,
                ValueAxis radiusAxis,
                DefaultPolarItemRenderer renderer,
                double maxValue,
                double innerRadius,
                double startAngle) {

            super(dataset, radiusAxis, renderer);

            this.maxValue = maxValue;
            this.innerRadius = innerRadius;
            this.startAngle = startAngle;
        }

        @Override
        public void draw(
                Graphics2D g2,
                Rectangle2D area,
                Point2D anchor,
                PlotState parentState,
                PlotRenderingInfo info) {

            super.draw(g2, area, anchor, parentState, info);

            if (info == null || info.getDataArea() == null) {
                return;
            }

            Graphics2D g = (Graphics2D) g2.create();

            try {
                g.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                g.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                );

                Rectangle2D dataArea = info.getDataArea();

                Geometry geo = new Geometry(
                        dataArea, getMargin(),
                        maxValue, innerRadius
                );

                drawYAxis(g, geo);
                drawCategoryLabels(g, geo);

            } finally {
                g.dispose();
            }
        }

        private void drawYAxis(Graphics2D g, Geometry geo) {
            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();
            Font oldFont = g.getFont();

            double angle = Math.toRadians(startAngle);
            double ux = Math.cos(angle);
            double uy = -Math.sin(angle);

            // A perpendicular unit vector in screen coordinates.
            double nx = -uy;
            double ny = ux;

            Point2D p1 = geo.toPoint(startAngle, innerRadius);
            Point2D p2 = geo.toPoint(startAngle, geo.outerRadius);

            g.setPaint(AXIS_COLOR);
            g.setStroke(new BasicStroke(1.5f));
            g.draw(new Line2D.Double(p1, p2));

            // Emphasize the outer boundary.
            g.draw(new Ellipse2D.Double(
                    geo.cx - geo.outerRadius,
                    geo.cy - geo.outerRadius,
                    2.0 * geo.outerRadius,
                    2.0 * geo.outerRadius
            ));

            g.setFont(new Font("SansSerif", Font.PLAIN, 12));

            double tickHalfLength = 4.0;
            double labelOffset = 12.0;

            for (int tick = 1; tick <= (int) maxValue; tick++) {
                Point2D p = geo.toPoint(
                        startAngle,
                        geo.radiusForValue(tick)
                );

                double x = p.getX();
                double y = p.getY();

                // Tick mark perpendicular to the y-axis.
                g.draw(new Line2D.Double(
                        x - nx * tickHalfLength,
                        y - ny * tickHalfLength,
                        x + nx * tickHalfLength,
                        y + ny * tickHalfLength
                ));

                // Put numeric labels on the same side of the axis.
                float tx = (float) (x + nx * labelOffset);
                float ty = (float) (y + ny * labelOffset);

                TextUtils.drawAlignedString(
                        Integer.toString(tick),
                        g,
                        tx,
                        ty,
                        TextAnchor.CENTER
                );
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
            g.setFont(oldFont);
        }

        private void drawCategoryLabels(Graphics2D g, Geometry geo) {
            Paint oldPaint = g.getPaint();
            Font oldFont = g.getFont();

            g.setFont(new Font("SansSerif", Font.PLAIN, 13));
            g.setPaint(Color.BLACK);

            double sectorAngle = 360.0 / CATEGORIES.length;
            double labelRadius = geo.outerRadius + 17.0;

            for (int i = 0; i < CATEGORIES.length; i++) {
                double centerAngle =
                        startAngle + (i + 0.5) * sectorAngle;

                Point2D p = geo.toPoint(centerAngle, labelRadius);

                double rad = Math.toRadians(centerAngle);
                double cos = Math.cos(rad);
                double sin = Math.sin(rad);

                TextAnchor anchor;

                if (Math.abs(cos) > 0.5) {
                    anchor = cos > 0.0
                            ? TextAnchor.CENTER_LEFT
                            : TextAnchor.CENTER_RIGHT;
                } else {
                    anchor = sin > 0.0
                            ? TextAnchor.BOTTOM_CENTER
                            : TextAnchor.TOP_CENTER;
                }

                TextUtils.drawAlignedString(
                        CATEGORIES[i],
                        g,
                        (float) p.getX(),
                        (float) p.getY(),
                        anchor
                );
            }

            g.setPaint(oldPaint);
            g.setFont(oldFont);
        }
    }

    /**
     * Shared polar geometry used by the renderer and the plot.
     */
    private static final class Geometry {

        final double cx;
        final double cy;
        final double outerRadius;
        final double innerRadius;
        final double maxValue;

        Geometry(
                Rectangle2D dataArea,
                int margin,
                double maxValue,
                double innerRadius) {

            this.cx = dataArea.getCenterX();
            this.cy = dataArea.getCenterY();
            this.maxValue = maxValue;
            this.innerRadius = innerRadius;

            this.outerRadius = Math.max(
                    innerRadius + 1.0,
                    Math.min(dataArea.getWidth(),
                            dataArea.getHeight()) / 2.0 - margin
            );
        }

        /**
         * Maps value 0 to the inner radius and maxValue to outerRadius.
         */
        double radiusForValue(double value) {
            double fraction = value / maxValue;
            fraction = Math.max(0.0, Math.min(1.0, fraction));

            return innerRadius
                    + fraction * (outerRadius - innerRadius);
        }

        Point2D toPoint(double angleDegrees, double radius) {
            double radians = Math.toRadians(angleDegrees);

            return new Point2D.Double(
                    cx + radius * Math.cos(radians),
                    cy - radius * Math.sin(radians)
            );
        }
    }

    /**
     * Constructs a ring sector centered on centerAngle.
     *
     * The angular extent is symmetric around centerAngle.
     * Positive angles follow the mathematical convention.
     */
    private static Shape createAnnularSector(
            double cx,
            double cy,
            double innerRadius,
            double outerRadius,
            double centerAngle,
            double extent) {

        Path2D path = new Path2D.Double();

        double startAngle = centerAngle + extent / 2.0;
        double endAngle = centerAngle - extent / 2.0;

        int steps = Math.max(8, (int) Math.ceil(extent / 2.0));

        // Outer arc, traversed from the larger angle to the smaller angle.
        for (int i = 0; i <= steps; i++) {
            double angle = Math.toRadians(
                    startAngle - extent * i / steps
            );

            double x = cx + outerRadius * Math.cos(angle);
            double y = cy - outerRadius * Math.sin(angle);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        // Inner arc, traversed in reverse.
        for (int i = 0; i <= steps; i++) {
            double angle = Math.toRadians(
                    endAngle + extent * i / steps
            );

            double x = cx + innerRadius * Math.cos(angle);
            double y = cy - innerRadius * Math.sin(angle);

            path.lineTo(x, y);
        }

        path.closePath();
        return path;
    }

    private static String formatValue(double value) {
        return String.format(java.util.Locale.ROOT, "%.1f", value);
    }
}
