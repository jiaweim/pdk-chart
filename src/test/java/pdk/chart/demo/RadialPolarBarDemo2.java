package pdk.chart.demo;


import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.PlotState;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;

public class RadialPolarBarDemo2 extends ApplicationFrame {

    public RadialPolarBarDemo2() {
        super("Radial Polar Bar");

        List<String> categories = List.of("a", "b", "c", "d");
        double[] values = {2.0, 1.2, 2.4, 3.6};

        RadialPolarBarPlot plot =
                new RadialPolarBarPlot(categories, values);

        Chart chart = new Chart(
                "Radial Polar Bar Label Position (middle)",
                new Font("SansSerif", Font.BOLD, 16),
                plot,
                false
        );

        chart.setBackgroundPaint(Color.WHITE);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(720, 620));
        panel.setMouseWheelEnabled(false);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            RadialPolarBarDemo2 demo = new RadialPolarBarDemo2();
            demo.setDefaultCloseOperation(EXIT_ON_CLOSE);
            demo.setVisible(true);
        });
    }

    static class RadialPolarBarPlot extends Plot {

        private final List<String> categories;
        private final double[] values;

        private final double maxValue = 4.0;

        // 与 ECharts radius: [30, '80%'] 对应
        private final double innerRadiusPixels = 30.0;
        private final double outerRadiusRatio = 0.80;

        // ECharts startAngle: 75
        private final double startAngle = 75.0;

        private final Color barColor = new Color(84, 112, 198);
        private final Color gridColor = new Color(220, 220, 220);
        private final Color labelColor = new Color(45, 45, 45);

        RadialPolarBarPlot(List<String> categories, double[] values) {
            if (categories.size() != values.length) {
                throw new IllegalArgumentException(
                        "Categories and values must have equal lengths."
                );
            }

            this.categories = List.copyOf(categories);
            this.values = values.clone();

            setBackgroundPaint(Color.WHITE);
            setInsets(new RectangleInsets(8, 8, 8, 8));
        }

        @Override
        public String getPlotType() {
            return "Radial Polar Bar Plot";
        }

        @Override
        public void draw(
                Graphics2D g2,
                Rectangle2D area,
                Point2D anchor,
                PlotState parentState,
                PlotRenderingInfo info) {

            if (info != null) {
                info.setPlotArea(area);
                info.setDataArea(area);
            }

            g2.setPaint(Color.WHITE);
            g2.fill(area);

            Rectangle2D drawingArea = getInsets().createInsetRectangle(area);

            double diameter = Math.min(
                    drawingArea.getWidth(),
                    drawingArea.getHeight()
            );

            double outerRadius = diameter * outerRadiusRatio / 2.0;
            double innerRadius = Math.min(
                    innerRadiusPixels,
                    outerRadius * 0.35
            );

            double cx = drawingArea.getCenterX();
            double cy = drawingArea.getCenterY();

            drawGrid(g2, cx, cy, innerRadius, outerRadius);
            drawBars(g2, cx, cy, innerRadius, outerRadius);
        }

        private void drawGrid(Graphics2D g2, double cx, double cy,
                double innerRadius, double outerRadius) {
            Stroke oldStroke = g2.getStroke();
            Paint oldPaint = g2.getPaint();
            Font oldFont = g2.getFont();

            g2.setStroke(new BasicStroke(1.0f));
            g2.setPaint(new Color(210, 210, 210));

            // 仅绘制同心圆，不绘制从圆心向外的径向分隔线
            for (int value = 1; value <= maxValue; value++) {
                double radius = innerRadius
                        + (outerRadius - innerRadius) * value / maxValue;

                Ellipse2D circle = new Ellipse2D.Double(
                        cx - radius,
                        cy - radius,
                        radius * 2,
                        radius * 2
                );
                g2.draw(circle);
            }

            // y 轴：放在起始角度对应的径向方向上
            double axisAngle = Math.toRadians(startAngle);
            double axisX = Math.cos(axisAngle);
            double axisY = -Math.sin(axisAngle);

            g2.setPaint(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(1.2f));

            // 绘制 y 轴线
            g2.draw(new Line2D.Double(
                    cx + innerRadius * axisX,
                    cy + innerRadius * axisY,
                    cx + outerRadius * axisX,
                    cy + outerRadius * axisY
            ));

            // y 轴刻度及标签
            g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
            FontMetrics fm = g2.getFontMetrics();

            for (int value = 0; value <= maxValue; value++) {
                double radius = innerRadius
                        + (outerRadius - innerRadius) * value / maxValue;

                double x = cx + radius * axisX;
                double y = cy + radius * axisY;

                // 短刻度线
                double tickLength = 4.0;
                double perpX = -axisY;
                double perpY = axisX;

                g2.draw(new Line2D.Double(
                        x - perpX * tickLength / 2,
                        y - perpY * tickLength / 2,
                        x + perpX * tickLength / 2,
                        y + perpY * tickLength / 2
                ));

                String label = Integer.toString(value);

                // 标签偏移到轴线外侧
                double labelOffset = 7.0;
                float labelX = (float) (x + axisX * labelOffset);
                float labelY = (float) (y + axisY * labelOffset);

                // 根据轴方向调整文本对齐
                float drawX = axisX >= 0
                        ? labelX
                        : labelX - fm.stringWidth(label);

                float drawY = axisY < 0
                        ? labelY - 2
                        : labelY + fm.getAscent();

                g2.drawString(label, drawX, drawY);
            }

            g2.setStroke(oldStroke);
            g2.setPaint(oldPaint);
            g2.setFont(oldFont);
        }

        private void drawBars(
                Graphics2D g2,
                double cx,
                double cy,
                double innerRadius,
                double outerRadius) {

            int count = categories.size();
            double sectorAngle = 360.0 / count;

            // 柱形间隙，单位为角度
            double gapAngle = 4.0;
            double barAngle = sectorAngle - gapAngle;

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            for (int i = 0; i < count; i++) {
                double value = Math.max(
                        0,
                        Math.min(maxValue, values[i])
                );

                double barOuterRadius = innerRadius
                        + value / maxValue * (outerRadius - innerRadius);

                // 类别中心角
                double centerAngle = startAngle - i * sectorAngle;

                // Java Arc2D 的角度正方向与屏幕坐标系不同，
                // 这里通过路径构造控制顺时针扇区方向。
                double angle1 = centerAngle + barAngle / 2.0;
                double angle2 = centerAngle - barAngle / 2.0;

                Shape bar = createAnnularSector(
                        cx,
                        cy,
                        innerRadius,
                        barOuterRadius,
                        angle1,
                        angle2
                );

                g2.setPaint(barColor);
                g2.fill(bar);

                g2.setPaint(Color.WHITE);
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(bar);

                // 标签位于柱形径向中点
                double labelRadius =
                        (innerRadius + barOuterRadius) / 2.0;

                double rad = Math.toRadians(centerAngle);

                double labelX = cx + labelRadius * Math.cos(rad);
                double labelY = cy - labelRadius * Math.sin(rad);

                String label = categories.get(i) + ": "
                        + formatValue(value);

                drawCenteredLabel(
                        g2,
                        label,
                        labelX,
                        labelY,
                        centerAngle
                );
            }
        }

        private Shape createAnnularSector(
                double cx,
                double cy,
                double innerRadius,
                double outerRadius,
                double start,
                double end) {

            Path2D path = new Path2D.Double();

            // 逆时针采样外弧
            int steps = Math.max(
                    8,
                    (int) Math.ceil(Math.abs(start - end) / 3.0)
            );

            for (int i = 0; i <= steps; i++) {
                double angle = start
                        + (end - start) * i / steps;

                double rad = Math.toRadians(angle);
                double x = cx + outerRadius * Math.cos(rad);
                double y = cy - outerRadius * Math.sin(rad);

                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }

            // 内弧反向返回
            for (int i = steps; i >= 0; i--) {
                double angle = start
                        + (end - start) * i / steps;

                double rad = Math.toRadians(angle);
                double x = cx + innerRadius * Math.cos(rad);
                double y = cy - innerRadius * Math.sin(rad);

                path.lineTo(x, y);
            }

            path.closePath();
            return path;
        }

        private void drawCenteredLabel(
                Graphics2D g2,
                String text,
                double x,
                double y,
                double angle) {

            Font font = new Font("SansSerif", Font.PLAIN, 12);
            g2.setFont(font);
            g2.setPaint(labelColor);

            var metrics = g2.getFontMetrics();
            float textWidth = metrics.stringWidth(text);
            float textHeight = metrics.getHeight();

            Graphics2D copy = (Graphics2D) g2.create();

            try {
                copy.translate(x, y);

                // 使标签沿径向方向排列
                double rotation = -Math.toRadians(angle);
                if (angle < -90 || angle > 90) {
                    rotation += Math.PI;
                }

                copy.rotate(rotation);

                copy.drawString(
                        text,
                        -textWidth / 2,
                        (float) (textHeight / 3.0)
                );
            } finally {
                copy.dispose();
            }
        }

        private String formatValue(double value) {
            return String.format("%.1f", value);
        }
    }
}
