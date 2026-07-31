package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.labels.BoxAndWhiskerToolTipGenerator;
import pdk.chart.renderer.category.BoxAndWhiskerRenderer;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:44 PM
 */
public class CategoryBoxChart extends CategoryChart {

    private final BoxAndWhiskerRenderer renderer_;
    private final CategoryAxis domainAxis_;
    private final NumberAxis rangeAxis_;

    public CategoryBoxChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, createLegend);
        renderer_ = new BoxAndWhiskerRenderer();
        renderer_.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        setDefaultRenderer(renderer_);

        domainAxis_ = new CategoryAxis();

        rangeAxis_ = new NumberAxis();
        rangeAxis_.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer_);
        JChartUtils.applyCurrentTheme(this);
    }

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
        this(null, DEFAULT_TITLE_FONT, legend);

        domainAxis_.setLabel(domainAxisLabel);
        rangeAxis_.setLabel(rangeAxisLabel);
        plot_.setDataset(dataset);
    }

}

