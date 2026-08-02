package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.labels.BoxAndWhiskerToolTipGenerator;
import pdk.chart.renderer.category.BoxAndWhiskerRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:44 PM
 */
public class CategoryBoxChart extends CategoryChart {

    private final BoxAndWhiskerRenderer renderer1_;
    private final CategoryAxis domainAxis_;
    private final NumberAxis rangeAxis_;

    /**
     * Creates and returns a default instance of a box and whisker chart.
     *
     * @param domainAxisLabel a label for the category axis.
     * @param rangeAxisLabel  a label for the value axis.
     * @param dataset         the dataset for the chart.
     * @param legend          a flag specifying whether a legend is required.
     */
    public CategoryBoxChart(BoxAndWhiskerCategoryDataset dataset,
            String domainAxisLabel, String rangeAxisLabel, boolean legend) {
        super(null, legend);

        renderer1_ = new BoxAndWhiskerRenderer();
        renderer1_.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        renderer0_ = renderer1_;

        domainAxis_ = new CategoryAxis(domainAxisLabel);

        rangeAxis_ = new NumberAxis(rangeAxisLabel);
        rangeAxis_.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

}

