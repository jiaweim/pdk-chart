package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Radial polar bar chart implemented with JFreeChart.
 *
 * Compatible with JFreeChart 1.5.x.
 */
public class RadialPolarBarDemo {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RadialPolarBarPlot plot = new RadialPolarBarPlot(
                    new String[]{"a", "b", "c", "d"},
                    new double[]{2.0, 1.2, 2.4, 3.6},
                    4.0,
                    75.0
            );

            Chart chart = new Chart(
                    "Radial Polar Bar Chart",
                    Chart.DEFAULT_TITLE_FONT,
                    plot,
                    false
            );

            ChartPanel panel = new ChartPanel(chart);
            panel.setPreferredSize(new java.awt.Dimension(760, 620));

            ApplicationFrame frame =
                    new ApplicationFrame("Radial Polar Bar Demo");
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(
                    javax.swing.WindowConstants.EXIT_ON_CLOSE
            );
            frame.setVisible(true);
        });
    }

    public static class RadialPolarBarPlot extends Plot {

        private final String[] categories;
        private final double[] values;
        private final double maxValue;
        private final double startAngle;

        /** Radius of the central hole, in pixels. */
        private final double innerRadius = 30.0;

        /** Angular gap between adjacent bars, in degrees. */
        private final double gapAngle = 3.0;

        private final Color gridColor = new Color(215, 215, 215);
        private final Color axisColor = new Color(90, 90, 90);
        private final Color labelColor = new Color(55, 55, 55);
        private final Color barColor = new Color(84, 112, 198);
        private final Color barOutlineColor = Color.WHITE;

        public RadialPolarBarPlot(
                String[] categories,
                double[] values,
                double maxValue,
                double startAngle) {

            if (categories == null || values == null
                    || categories.length == 0
                    || categories.length != values.length) {
                throw new IllegalArgumentException(
                        "Categories and values must have the same non-zero length."
                );
            }

            if (!(maxValue > 0.0) || !Double.isFinite(maxValue)) {
                throw new IllegalArgumentException(
                        "maxValue must be finite and positive."
                );
            }

            for (double value : values) {
                if (!Double.isFinite(value)
                        || value < 0.0
                        || value > maxValue) {
                    throw new IllegalArgumentException(
                            "Values must be finite and in [0, maxValue]."
                    );
                }
            }

            this.categories = categories.clone();
            this.values = values.clone();
            this.maxValue = maxValue;
            this.startAngle = startAngle;

            setBackgroundPaint(Color.WHITE);
        }

        @Override
        public String getPlotType() {
            return "Radial Polar Bar Plot";
        }

        @Override
        public void draw(
                Graphics2D g2,
                Rectangle2D area,
                java.awt.geom.Point2D anchor,
                PlotState parentState,
                PlotRenderingInfo info) {

            if (info != null) {
                info.setPlotArea((Rectangle2D) area.clone());
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

                g.setPaint(getBackgroundPaint());
                g.fill(area);

                // Reserve space for category labels outside the polar grid.
                double margin = 12.0;
                double width = Math.max(0.0, area.getWidth() - 2.0 * margin);
                double height = Math.max(0.0, area.getHeight() - 2.0 * margin);

                Rectangle2D dataArea = new Rectangle2D.Double(
                        area.getX() + margin,
                        area.getY() + margin,
                        width,
                        height
                );

                if (info != null) {
                    info.setDataArea((Rectangle2D) dataArea.clone());
                }

                if (width <= 0.0 || height <= 0.0) {
                    return;
                }

                double cx = dataArea.getCenterX();
                double cy = dataArea.getCenterY();

                // Leave room for labels beyond the outer circle.
                double outerRadius = Math.min(width, height) * 0.38;

                if (outerRadius <= innerRadius) {
                    return;
                }

                // Grid: concentric circles only; no category spokes.
                drawGrid(g, cx, cy, outerRadius);

                // Bars are placed after the grid and leave a gap at the y-axis.
                drawBars(g, cx, cy, outerRadius);

                // The single radial y-axis and its numeric tick labels.
                drawYAxis(g, cx, cy, outerRadius);

                // Category labels outside the outer circle.
                drawCategoryLabels(g, cx, cy, outerRadius);

            } finally {
                g.dispose();
            }
        }

        private void drawGrid(
                Graphics2D g,
                double cx,
                double cy,
                double outerRadius) {

            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();

            g.setPaint(gridColor);
            g.setStroke(new BasicStroke(1.0f));

            for (int tick = 0; tick <= (int) maxValue; tick++) {
                double radius = radiusForValue(tick, outerRadius);

                Path2D circle = new Path2D.Double();
                circle.append(
                        new java.awt.geom.Ellipse2D.Double(
                                cx - radius,
                                cy - radius,
                                radius * 2.0,
                                radius * 2.0
                        ),
                        false
                );
                g.draw(circle);
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
        }

        private void drawYAxis(
                Graphics2D g,
                double cx,
                double cy,
                double outerRadius) {

            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();
            Font oldFont = g.getFont();

            double angle = Math.toRadians(startAngle);

            // Screen coordinates: positive y points downward.
            double ux = Math.cos(angle);
            double uy = -Math.sin(angle);

            // Perpendicular direction used for ticks and label offsets.
            double nx = Math.sin(angle);
            double ny = Math.cos(angle);

            double x1 = cx + innerRadius * ux;
            double y1 = cy + innerRadius * uy;
            double x2 = cx + outerRadius * ux;
            double y2 = cy + outerRadius * uy;

            g.setPaint(axisColor);
            g.setStroke(new BasicStroke(1.2f));
            g.draw(new Line2D.Double(x1, y1, x2, y2));

            g.setFont(new Font("SansSerif", Font.PLAIN, 11));
            FontMetrics fm = g.getFontMetrics();

            double tickLength = 5.0;
            double labelOffset = 7.0;

            for (int tick = 0; tick <= (int) maxValue; tick++) {
                double radius = radiusForValue(tick, outerRadius);

                double x = cx + radius * ux;
                double y = cy + radius * uy;

                // Short tick mark, perpendicular to the y-axis.
                g.draw(new Line2D.Double(
                        x - nx * tickLength / 2.0,
                        y - ny * tickLength / 2.0,
                        x + nx * tickLength / 2.0,
                        y + ny * tickLength / 2.0
                ));

                String text = Integer.toString(tick);

                double tx = x + nx * labelOffset;
                double ty = y + ny * labelOffset;

                // Keep text outside the axis and center it vertically.
                float textX = (float) (
                        nx >= 0.0 ? tx : tx - fm.stringWidth(text)
                );
                float textY = (float) (
                        ty + (ny >= 0.0
                                ? fm.getAscent() / 2.0
                                : -fm.getDescent() / 2.0)
                );

                g.drawString(text, textX, textY);
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
            g.setFont(oldFont);
        }

        private void drawBars(
                Graphics2D g,
                double cx,
                double cy,
                double outerRadius) {

            Paint oldPaint = g.getPaint();
            Stroke oldStroke = g.getStroke();
            Font oldFont = g.getFont();

            double sectorAngle = 360.0 / categories.length;

            g.setFont(new Font("SansSerif", Font.PLAIN, 11));
            FontMetrics fm = g.getFontMetrics();

            for (int i = 0; i < values.length; i++) {
                double centerAngle =
                        startAngle - (i + 0.5) * sectorAngle;

                double extent = sectorAngle - gapAngle;

                if (extent <= 0.0) {
                    continue;
                }

                double barOuterRadius =
                        radiusForValue(values[i], outerRadius);

                if (barOuterRadius <= innerRadius) {
                    continue;
                }

                Path2D bar = createAnnularSector(
                        cx,
                        cy,
                        innerRadius,
                        barOuterRadius,
                        centerAngle,
                        extent
                );

                g.setPaint(barColor);
                g.fill(bar);

                g.setPaint(barOutlineColor);
                g.setStroke(new BasicStroke(1.0f));
                g.draw(bar);

                // Label at the radial midpoint of the bar.
                double labelRadius =
                        (innerRadius + barOuterRadius) / 2.0;

                double angle = Math.toRadians(centerAngle);
                double lx = cx + labelRadius * Math.cos(angle);
                double ly = cy - labelRadius * Math.sin(angle);

                String label = categories[i] + ": "
                        + formatValue(values[i]);

                float labelX = (float) (
                        lx - fm.stringWidth(label) / 2.0
                );
                float labelY = (float) (
                        ly + (fm.getAscent() - fm.getDescent()) / 2.0
                );

                g.setPaint(Color.WHITE);
                g.drawString(label, labelX, labelY);
            }

            g.setPaint(oldPaint);
            g.setStroke(oldStroke);
            g.setFont(oldFont);
        }

        private void drawCategoryLabels(
                Graphics2D g,
                double cx,
                double cy,
                double outerRadius) {

            Paint oldPaint = g.getPaint();
            Font oldFont = g.getFont();

            g.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g.setPaint(labelColor);

            FontMetrics fm = g.getFontMetrics();

            double sectorAngle = 360.0 / categories.length;
            double labelRadius = outerRadius + 18.0;

            for (int i = 0; i < categories.length; i++) {
                double centerAngle = Math.toRadians(
                        startAngle - (i + 0.5) * sectorAngle
                );

                double x = cx + labelRadius * Math.cos(centerAngle);
                double y = cy - labelRadius * Math.sin(centerAngle);

                String label = categories[i];

                // Center each category label on its corresponding bar.
                float textX = (float) (
                        x - fm.stringWidth(label) / 2.0
                );
                float textY = (float) (
                        y + (fm.getAscent() - fm.getDescent()) / 2.0
                );

                g.drawString(label, textX, textY);
            }

            g.setPaint(oldPaint);
            g.setFont(oldFont);
        }

        /**
         * Maps a numeric value to a radius.
         * Value zero corresponds to the inner radius.
         */
        private double radiusForValue(
                double value,
                double outerRadius) {

            return innerRadius
                    + (outerRadius - innerRadius) * value / maxValue;
        }

        /**
         * Creates a ring sector centered at centerAngle.
         *
         * Angles use mathematical convention:
         * 0 degrees points right, positive angles point counterclockwise.
         */
        private Path2D createAnnularSector(
                double cx,
                double cy,
                double innerR,
                double outerR,
                double centerAngle,
                double extent) {

            Path2D path = new Path2D.Double();

            double start = centerAngle + extent / 2.0;
            double end = centerAngle - extent / 2.0;

            // Outer arc, from start to end.
            int steps = Math.max(8, (int) Math.ceil(extent / 2.0));

            for (int i = 0; i <= steps; i++) {
                double angle = Math.toRadians(
                        start - extent * i / steps
                );

                double x = cx + outerR * Math.cos(angle);
                double y = cy - outerR * Math.sin(angle);

                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }

            // Inner arc, traversed in reverse to close the ring sector.
            for (int i = 0; i <= steps; i++) {
                double angle = Math.toRadians(
                        end + extent * i / steps
                );

                double x = cx + innerR * Math.cos(angle);
                double y = cy - innerR * Math.sin(angle);

                path.lineTo(x, y);
            }

            path.closePath();
            return path;
        }

        private String formatValue(double value) {
            return String.format(java.util.Locale.ROOT, "%.1f", value);
        }
    }
}
