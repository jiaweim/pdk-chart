package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.XYChart;
import pdk.chart.legend.LegendTitle;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 4:14 PM
 */
public class LegendTitleProps {

    private final Chart chart;
    private final LegendTitle legend;

    public LegendTitleProps(Chart chart, LegendTitle legend) {
        this.chart = chart;
        this.legend = legend;
    }

    public XYChart doneXY() {
        return (XYChart) chart;
    }

    public CategoryXYChart doneCateogry() {
        return (CategoryXYChart) chart;
    }

    /**
     * Sets the position for the title.
     *
     * @param position the position.
     */
    public LegendTitleProps position(@NonNull RectangleEdge position) {
        legend.setPosition(position);
        return this;
    }
}
