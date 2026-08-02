package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.labels.StandardPieToolTipGenerator;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.title.TextTitle;
import pdk.chart.urls.StandardPieURLGenerator;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 4:07 PM
 */
public class PieChart extends Chart {

    private final PiePlot plot_;

    public PieChart(String title, boolean createLegend, Locale locale) {
        super(title, DEFAULT_TITLE_FONT, new PiePlot<>(), createLegend);
        plot_ = (PiePlot) getPlot();
        if (locale == null) {
            locale = Locale.getDefault(Locale.Category.FORMAT);
        }
        plot_.setLabelGenerator(new StandardPieSectionLabelGenerator<>(locale));
        plot_.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a pie chart with default settings that compares 2 datasets.
     * The colour of each section will be determined by the move from the value
     * for the same key in {@code previousDataset}. ie if value1 &gt;
     * value2 then the section will be in green (unless
     * {@code greenForIncrease} is {@code false}, in which case it
     * would be {@code red}). Each section can have a shade of red or
     * green as the difference can be tailored between 0% (black) and
     * percentDiffForMaxScale% (bright red/green).
     * <p>
     * For instance if {@code percentDiffForMaxScale} is 10 (10%), a
     * difference of 5% will have a half shade of red/green, a difference of
     * 10% or more will have a maximum shade/brightness of red/green.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title                  the chart title ({@code null} permitted).
     * @param dataset                the dataset for the chart ({@code null} permitted).
     * @param previousDataset        the dataset for the last run, this will be used
     *                               to compare each key in the dataset
     * @param percentDiffForMaxScale scale goes from bright red/green to black,
     *                               percentDiffForMaxScale indicate the change
     *                               required to reach top scale.
     * @param greenForIncrease       an increase since previousDataset will be
     *                               displayed in green (decrease red) if true.
     * @param legend                 a flag specifying whether a legend is required.
     * @param tooltips               configure chart to generate tool tips?
     * @param locale                 the locale ({@code null} not permitted).
     * @param subTitle               displays a subtitle with colour scheme if true
     * @param showDifference         create a new dataset that will show the %
     *                               difference between the two datasets.
     */
    public PieChart(String title, PieDataset<String> dataset, PieDataset<String> previousDataset,
            int percentDiffForMaxScale, boolean greenForIncrease,
            boolean legend, boolean tooltips, Locale locale, boolean subTitle, boolean showDifference) {
        this(title, dataset, previousDataset, percentDiffForMaxScale, greenForIncrease, legend, tooltips, false, locale,
                subTitle, showDifference);
    }

    /**
     * Creates a pie chart with default settings that compares 2 datasets.
     * The colour of each section will be determined by the move from the value
     * for the same key in {@code previousDataset}. ie if value1 &gt;
     * value2 then the section will be in green (unless
     * {@code greenForIncrease} is {@code false}, in which case it
     * would be {@code red}). Each section can have a shade of red or
     * green as the difference can be tailored between 0% (black) and
     * percentDiffForMaxScale% (bright red/green).
     * <p>
     * For instance if {@code percentDiffForMaxScale} is 10 (10%), a
     * difference of 5% will have a half shade of red/green, a difference of
     * 10% or more will have a maximum shade/brightness of red/green.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     * <p>
     * Written by <a href="mailto:opensource@objectlab.co.uk">Benoit
     * Xhenseval</a>.
     *
     * @param title                  the chart title ({@code null} permitted).
     * @param dataset                the dataset for the chart ({@code null} permitted).
     * @param previousDataset        the dataset for the last run, this will be used
     *                               to compare each key in the dataset
     * @param percentDiffForMaxScale scale goes from bright red/green to black,
     *                               percentDiffForMaxScale indicate the change
     *                               required to reach top scale.
     * @param greenForIncrease       an increase since previousDataset will be
     *                               displayed in green (decrease red) if true.
     * @param legend                 a flag specifying whether a legend is required.
     * @param tooltips               configure chart to generate tool tips?
     * @param urls                   configure chart to generate URLs?
     * @param subTitle               displays a subtitle with colour scheme if true
     * @param showDifference         create a new dataset that will show the %
     *                               difference between the two datasets.
     */
    public PieChart(String title, PieDataset<String> dataset, PieDataset<String> previousDataset,
            int percentDiffForMaxScale, boolean greenForIncrease, boolean legend, boolean tooltips, boolean urls,
            Locale locale, boolean subTitle, boolean showDifference) {
        this(title, legend, locale);

        if (tooltips) {
            plot_.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot_.setURLGenerator(new StandardPieURLGenerator<>());
        }

        List<String> keys = dataset.getKeys();
        DefaultPieDataset<String> series = null;
        if (showDifference) {
            series = new DefaultPieDataset<>();
        }

        double colorPerPercent = 255.0 / percentDiffForMaxScale;
        for (String key : keys) {
            Number newValue = dataset.getValue(key);
            Number oldValue = previousDataset.getValue(key);

            if (oldValue == null) {
                if (greenForIncrease) {
                    plot_.setSectionPaint(key, Color.GREEN);
                } else {
                    plot_.setSectionPaint(key, Color.RED);
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (+100%)", newValue);
                }
            } else {
                double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0) * 100.0;
                double shade = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255 : Math.abs(percentChange) * colorPerPercent);
                if (greenForIncrease && newValue.doubleValue() > oldValue.doubleValue() || !greenForIncrease && newValue.doubleValue() < oldValue.doubleValue()) {
                    plot_.setSectionPaint(key, new Color(0, (int) shade, 0));
                } else {
                    plot_.setSectionPaint(key, new Color((int) shade, 0, 0));
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0) + ")", newValue);
                }
            }
        }

        if (showDifference) {
            plot_.setDataset(series);
        } else {
            plot_.setDataset(dataset);
        }

        if (subTitle) {
            TextTitle subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (!greenForIncrease ? "red" : "green") + "=change >=+" + percentDiffForMaxScale + "%", new Font("SansSerif", Font.PLAIN, 10));
            addSubtitle(subtitle);
        }
    }

    /**
     * Creates a pie chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param urls     configure chart to generate URLs?
     */
    public PieChart(PieDataset dataset, String title, boolean legend, boolean tooltips, boolean urls, Locale locale) {
        this(title, legend, locale);
        if (tooltips) {
            plot_.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot_.setURLGenerator(new StandardPieURLGenerator<>());
        }
        plot_.setDataset(dataset);
    }

    /**
     * Creates a pie chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param urls     configure chart to generate URLs?
     */
    public PieChart(PieDataset dataset, String title, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, title, legend, tooltips, urls, null);
    }

    /**
     * Creates a pie chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     */
    public PieChart(PieDataset dataset, String title, boolean legend, boolean tooltips) {
        this(dataset, title, legend, tooltips, false, null);
    }

    /**
     * Creates a pie chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title   the chart title ({@code null} permitted).
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public PieChart(PieDataset dataset, String title) {
        this(dataset, title, true, false);
    }
}
