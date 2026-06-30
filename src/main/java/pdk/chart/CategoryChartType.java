package pdk.chart;

import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.*;

/**
 * Supported categorized XYChart types.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:46 AM
 */
public enum CategoryChartType {

    LINE,
    AREA,
    BAR,
    /**
     * Use the IntervalCategoryDataset.
     * In this case, bars do not start from zero; instead, they are displayed within specified intervals.
     */
    BAR_INTERVAL,
    /**
     * Bars of different series under the same category are displayed overlapping.
     */
    BAR_LAYER,
    /**
     * A bar chart with a mean and a standard deviation line
     */
    BAR_STATISTICS,
    /**
     * waterfall bar charts
     */
    BAR_WATERFALL,
    BAR_STACK,
    BoxWhisker;

    public CategoryItemRenderer getRenderer() {
        if (this == BoxWhisker) {
            return new BoxAndWhiskerRenderer();
        } else if (this == AREA) {
            AreaRenderer areaRenderer = new AreaRenderer();
            areaRenderer.setEndType(AreaRendererEndType.LEVEL);
            return areaRenderer;
        } else if (this == BAR) {
            BarRenderer barRenderer = new BarRenderer();
            barRenderer.setShadowVisible(false);
            return barRenderer;
        } else if (this == BAR_INTERVAL) {
            return new IntervalBarRenderer();
        } else if (this == BAR_LAYER) {
            return new LayeredBarRenderer();
        } else if (this == BAR_STATISTICS) {
            return new StatisticalBarRenderer();
        } else if (this == BAR_WATERFALL) {
            return new WaterfallBarRenderer();
        } else if (this == BAR_STACK) {
            return new StackedBarRenderer();
        } else if (this == LINE) {
            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            return renderer;
        }
        return null;
    }
}
