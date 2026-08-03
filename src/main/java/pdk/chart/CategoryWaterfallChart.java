package pdk.chart;

import pdk.chart.api.Layer;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.Marker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.ValueMarker;
import pdk.chart.renderer.category.WaterfallBarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.awt.*;

/**
 * A waterfall chart, useful for showing the cumulative effect of
 * sequentially introduced positive or negative values.
 * <p>
 * The chart uses a {@link WaterfallBarRenderer} and automatically adds a
 * baseline marker at zero.  Item label positions are centred and rotated
 * for horizontal orientation.
 * <p>
 * Tool‑tips and URLs can be enabled via constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 4:55 PM
 */
public class CategoryWaterfallChart extends CategoryBarChart {

    private WaterfallBarRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new WaterfallBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    @Override
    public WaterfallBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     * @param urls              {@code true} to generate URLs for data points
     */
    public CategoryWaterfallChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        CategoryAxis xAxis_ = new CategoryAxis(categoryAxisLabel);
        xAxis_.setCategoryMargin(0.0);
        NumberAxis yAxis_ = new NumberAxis(valueAxisLabel);

        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, Math.PI / 2.0);
            renderer2_.setDefaultPositiveItemLabelPosition(position);
            renderer2_.setDefaultNegativeItemLabelPosition(position);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
            renderer2_.setDefaultPositiveItemLabelPosition(position);
            renderer2_.setDefaultNegativeItemLabelPosition(position);
        }

        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer2_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        plot_.clearRangeMarkers();
        Marker baseline = new ValueMarker(0.0);
        baseline.setPaint(Color.BLACK);
        plot_.addRangeMarker(baseline, Layer.FOREGROUND);
        plot_.setOrientation(orientation);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a waterfall chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryWaterfallChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }
}
