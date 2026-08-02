package pdk.chart;

import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.PieDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.labels.PieSectionLabelGenerator;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.labels.StandardPieToolTipGenerator;
import pdk.chart.plot.CenterTextMode;
import pdk.chart.plot.RingPlot;
import pdk.chart.urls.StandardPieURLGenerator;

import java.awt.*;
import java.text.Format;
import java.util.Locale;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 1:49 PM
 */
public class RingChart extends Chart {

    private final RingPlot plot_;

    /**
     * Creates a ring chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link RingPlot}
     * instance as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param urls     configure chart to generate URLs?
     * @param locale   the locale ({@code null} not permitted).
     */
    public RingChart(PieDataset dataset, String title, boolean legend,
            boolean tooltips, boolean urls, Locale locale) {
        super(title, DEFAULT_TITLE_FONT, new RingPlot(dataset), legend);
        plot_ = getRingPlot();
        plot_.setLabelGenerator(new StandardPieSectionLabelGenerator<>(locale));
        plot_.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));

        if (tooltips) {
            plot_.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot_.setURLGenerator(new StandardPieURLGenerator<>());
        }

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a ring chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link RingPlot}
     * instance as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param urls     configure chart to generate URLs?
     */
    public RingChart(PieDataset dataset, String title, boolean legend,
            boolean tooltips, boolean urls) {
        this(dataset, title, legend, tooltips, urls, Locale.getDefault(Locale.Category.FORMAT));
    }

    /**
     * Sets the section label font and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param font the font ({@code null} not permitted).
     */
    public void setLabelFont(Font font) {
        plot_.setLabelFont(font);
    }

    /**
     * A flag indicating whether the pie chart is circular, or stretched into
     * an elliptical shape.
     *
     * @param flag the new value.
     */
    public void setCircular(boolean flag) {
        plot_.setCircular(flag);
    }

    /**
     * Sets the gap between the edge of the pie and the labels (expressed as a
     * percentage of the plot width) and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param gap the gap (a percentage, where 0.05 = five percent).
     */
    public void setLabelGap(double gap) {
        plot_.setLabelGap(gap);
    }

    /**
     * The section depth is given as proportion of the plot radius.
     * Specifying 1.0 results in a straightforward pie chart.
     *
     * @param sectionDepth the section depth.
     */
    public void setSectionDepth(double sectionDepth) {
        plot_.setSectionDepth(sectionDepth);
    }

    /**
     * Sets the flag that controls whether the outline is drawn for
     * each pie section, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param visible the flag.
     */
    public void setSectionOutlinesVisible(boolean visible) {
        plot_.setSectionOutlinesVisible(visible);
    }

    /**
     * Sets the shadow paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     */
    public void setShadowPaint(Paint paint) {
        plot_.setShadowPaint(paint);
    }

    /**
     * Sets the mode for displaying text in the center of the plot and sends
     * a change event to all registered listeners.  For
     * {@link CenterTextMode#FIXED}, the display text will come from the
     * {@code centerText} attribute (see {@link #getCenterText()}).
     * For {@link CenterTextMode#VALUE}, the center text will be the value from
     * the first section in the dataset.
     *
     * @param mode the mode ({@code null} not permitted).
     */
    public void setCenterTextMode(CenterTextMode mode) {
        plot_.setCenterTextMode(mode);
    }

    /**
     * Sets the color for the center text and sends a change event to all
     * registered listeners.
     *
     * @param color the color ({@code null} not permitted).
     */
    public void setCenterTextColor(Color color) {
        plot_.setCenterTextColor(color);
    }

    /**
     * Sets the font used to display the center text and sends a change event
     * to all registered listeners.
     *
     * @param font the font ({@code null} not permitted).
     */
    public void setCenterTextFont(Font font) {
        plot_.setCenterTextFont(font);
    }

    /**
     * Sets the formatter used to format the center text value and sends a
     * change event to all registered listeners.
     *
     * @param formatter the formatter ({@code null} not permitted).
     */
    public void setCenterTextFormatter(Format formatter) {
        plot_.setCenterTextFormatter(formatter);
    }

    /**
     * Sets the section label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public void setLabelGenerator(PieSectionLabelGenerator generator) {
        plot_.setLabelGenerator(generator);
    }

    /**
     * Sets the paint associated with the specified key, and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param key   the key ({@code null} not permitted).
     * @param paint the paint.
     * @throws IllegalArgumentException if {@code key} is
     *                                  {@code null}.
     */
    public void setSectionPaint(Comparable key, Paint paint) {
        plot_.setSectionPaint(key, paint);
    }
}
