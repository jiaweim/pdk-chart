package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.labels.BoxAndWhiskerToolTipGenerator;
import pdk.chart.renderer.category.BoxAndWhiskerRenderer;

/**
 * A box‑and‑whisker chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * This chart displays the distribution of a dataset via its minimum, first
 * quartile, median, third quartile, and maximum values.  The renderer is a
 * {@link BoxAndWhiskerRenderer}, which is automatically configured with a
 * {@link BoxAndWhiskerToolTipGenerator}.  The domain axis is a
 * {@link CategoryAxis} and the range axis is a {@link NumberAxis} with
 * {@code autoRangeIncludesZero} set to {@code false}.
 * <p>
 * The dataset must implement {@link BoxAndWhiskerCategoryDataset}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:44 PM
 */
public class CategoryBoxChart extends CategoryChart {

    private BoxAndWhiskerRenderer renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new BoxAndWhiskerRenderer();
        renderer1_.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        renderer0_ = renderer1_;
    }

    @Override
    public BoxAndWhiskerRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a box‑and‑whisker chart.
     *
     * @param dataset         the dataset ({@code null} permitted)
     * @param domainAxisLabel the label for the category axis ({@code null}
     *                        permitted)
     * @param rangeAxisLabel  the label for the value axis ({@code null}
     *                        permitted)
     * @param legend          {@code true} to include a legend
     */
    public CategoryBoxChart(BoxAndWhiskerCategoryDataset dataset,
            String domainAxisLabel, String rangeAxisLabel, boolean legend) {
        super(null, legend);

        CategoryAxis domainAxis_ = new CategoryAxis(domainAxisLabel);

        NumberAxis rangeAxis_ = new NumberAxis(rangeAxisLabel);
        rangeAxis_.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }
}

