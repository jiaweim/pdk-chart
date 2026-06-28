package pdk.chart.fluent;

import pdk.chart.renderer.xy.*;

import java.awt.geom.Ellipse2D;

/**
 * XYChart types.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 12:45 PM
 */
public enum XYChartType {

    AREA,
    LINE,
    SPLINE,
    SCATTER,
    BUBBLE,
    HISTOGRAM,
    /**
     * Bar chart, This chart requires the IntervalXYDataset data type.
     */
    BAR,
    /**
     * displays bars for different series values at the same x next to each other.
     */
    BAR_CLUSTER,
    /**
     *
     */
    BAR_STACK,
    /**
     * box-and-whisker
     */
    BOX_WHISKER,
    PEAK;

    /**
     * Create {@link XYItemRenderer} for the given chart type.
     *
     * @return {@link XYItemRenderer}.
     */
    public XYItemRenderer getRenderer() {
        switch (this) {
            case LINE -> {
                return new XYLineAndShapeRenderer(true, false);
            }
            case SPLINE -> {
                return new XYSplineRenderer();
            }
            case AREA -> {
                return new XYAreaRenderer(XYAreaRenderer.AREA);
            }
            case SCATTER -> {
                return new XYLineAndShapeRenderer(false, true);
            }
            case BUBBLE -> {
                return new XYBubbleRenderer(XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
            }
            case BAR, HISTOGRAM -> {
                XYBarRenderer renderer = new XYBarRenderer();
                renderer.setShadowVisible(false);
                return renderer;
            }
            case BAR_STACK -> {
                StackedXYBarRenderer renderer = new StackedXYBarRenderer();
                renderer.setShadowVisible(false);
                return renderer;
            }
            case PEAK -> {
                YIntervalRenderer renderer = new YIntervalRenderer();
                Ellipse2D.Double shape = new Ellipse2D.Double(-0.5, 0.5, 1, 1);
                renderer.setSeriesShape(0, shape);
                renderer.setSeriesShape(1, shape);
                return renderer;
            }
            case BAR_CLUSTER -> {
                ClusteredXYBarRenderer renderer = new ClusteredXYBarRenderer();
                renderer.setShadowVisible(false);
                return renderer;
            }
            case BOX_WHISKER -> {
                return new XYBoxAndWhiskerRenderer();
            }
            default -> throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
