package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.axis.DateAxis;
import pdk.chart.fluent.XYChart;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYItemLabelGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.renderer.xy.StackedXYBarRenderer;
import pdk.chart.renderer.xy.XYBarPainter;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.util.GradientPaintTransformer;

import java.awt.*;

/**
 * A class for configuring properties of {@link pdk.chart.renderer.xy.XYBarRenderer}, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:31 AM
 */
public class XYBarProps extends XYRendererProps {

    private final XYBarRenderer renderer_;

    public XYBarProps(XYChart chart, XYBarRenderer renderer) {
        super(chart, renderer);
        renderer_ = renderer;
    }

    /**
     * Configure chart to generate tool tips.
     *
     * @param addTooltip true if generate tool tips.
     * @return this.
     */
    public XYBarProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            if (chart_.getDomainAxis() instanceof DateAxis) {
                renderer_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            }
        }
        return this;
    }

    /**
     * Sets whether item labels are visible.
     *
     * @param visible the flag.
     */
    public XYBarProps defaultItemLabelsVisible(boolean visible) {
        renderer_.setDefaultItemLabelsVisible(visible);
        return this;
    }

    /**
     * Sets the default item label generator.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public XYBarProps defaultItemLabelGenerator(XYItemLabelGenerator generator) {
        renderer_.setDefaultItemLabelGenerator(generator);
        return this;
    }

    /**
     * Sets the default positive item label position.
     *
     * @param position the position ({@code null} not permitted).
     */
    public XYBarProps defaultPositiveItemLabelPosition(ItemLabelPosition position) {
        renderer_.setDefaultPositiveItemLabelPosition(position);
        return this;
    }

    /**
     * Sets whether bar outlines are drawn.
     *
     * @param draw the flag.
     */
    public XYBarProps drawBarOutline(boolean draw) {
        renderer_.setDrawBarOutline(draw);
        return this;
    }

    /**
     * Sets the default tool tip generator.
     *
     * @param generator the generator.
     */
    public XYBarProps defaultToolTipGenerator(@Nullable XYToolTipGenerator generator) {
        renderer_.setDefaultToolTipGenerator(generator);
        return this;
    }

    /**
     * Sets the bar painter.
     *
     * @param painter the painter ({@code null} not permitted).
     */
    public XYBarProps barPainter(@NonNull XYBarPainter painter) {
        renderer_.setBarPainter(painter);
        return this;
    }

    /**
     * Sets the flag that controls whether the renderer
     * draws shadows for the bars.
     *
     * @param visible the new flag value.
     */
    public XYBarProps shadowVisible(boolean visible) {
        renderer_.setShadowVisible(visible);
        return this;
    }

    /**
     * Sets the percentage amount by which the bars are trimmed.
     *
     * @param margin the new margin.
     */
    public XYBarProps margin(double margin) {
        renderer_.setMargin(margin);
        return this;
    }

    /**
     * Sets the paint used for a series outline.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public XYBarProps seriesOutlinePaint(int series, Paint paint) {
        renderer_.setSeriesOutlinePaint(series, paint);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public XYBarProps seriesPaint(int series, Paint paint) {
        renderer_.setSeriesPaint(series, paint);
        return this;
    }

    /**
     * Sets the flag that determines whether the y-interval from the dataset is
     * used to calculate the length of each bar.
     *
     * @param use the flag (default=false).
     */
    public XYBarProps useYInterval(boolean use) {
        renderer_.setUseYInterval(use);
        return this;
    }

    /**
     * Sets the gradient paint transformer.
     *
     * @param transformer the transformer.
     */
    public XYBarProps gradientPaintTransformer(
            @Nullable GradientPaintTransformer transformer) {
        renderer_.setGradientPaintTransformer(transformer);
        return this;
    }

    /**
     * Sets whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%).
     * <p>
     * This setting only takes effect for {@link pdk.chart.fluent.XYChartType#BAR_STACK}.
     *
     * @param asPercentages the flag.
     */
    public XYBarProps renderAsPercentages(boolean asPercentages) {
        if (renderer_ instanceof StackedXYBarRenderer barRenderer) {
            barRenderer.setRenderAsPercentages(asPercentages);
        }
        return this;
    }
}
