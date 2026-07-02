package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.TableOrder;
import pdk.chart.axis.*;
import pdk.chart.color.JColorSequential;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.data.general.WaferMapDataset;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.*;
import pdk.chart.internal.ShapeUtils;
import pdk.chart.labels.*;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.*;
import pdk.chart.plot.pep.PSMDataset;
import pdk.chart.plot.pep.PSMPlot;
import pdk.chart.plot.pep.SpectrumDataset;
import pdk.chart.plot.pep.SpectrumPlot;
import pdk.chart.plot.pie.MultiplePiePlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.DefaultPolarItemRenderer;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.WaferMapRenderer;
import pdk.chart.renderer.category.*;
import pdk.chart.renderer.xy.*;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;
import pdk.chart.urls.*;

import java.awt.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

/**
 * A collection of utility methods for creating some standard charts.
 */
public abstract class JChart {

    /**
     * The chart theme.
     */
    private static ChartTheme currentTheme = new StandardChartTheme("PDK");

    private JChart() {}

    /**
     * Returns the current chart theme used by the factory.
     *
     * @return The chart theme.
     * @see #setChartTheme(ChartTheme)
     * @see ChartUtils#applyCurrentTheme(Chart)
     */
    public static ChartTheme getChartTheme() {
        return currentTheme;
    }

    /**
     * Sets the current chart theme.  This will be applied to all new charts
     * created via methods in this class.
     *
     * @param theme the theme ({@code null} not permitted).
     * @see #getChartTheme()
     * @see ChartUtils#applyCurrentTheme(Chart)
     */
    public static void setChartTheme(ChartTheme theme) {
        Objects.requireNonNull(theme, "theme");
        currentTheme = theme;

        // here we do a check to see if the user is installing the "Legacy"
        // theme, and reset the bar painters in that case...
        if (theme instanceof StandardChartTheme) {
            BarRenderer.setDefaultBarPainter(new StandardBarPainter());
            XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
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
     * @param locale   the locale ({@code null} not permitted).
     * @return A pie chart.
     */
    public static Chart pie(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a pie chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title   the chart title ({@code null} permitted).
     * @param dataset the dataset for the chart ({@code null} permitted).
     * @return A pie chart.
     */
    public static Chart pie(String title, PieDataset dataset) {
        return pie(title, dataset, true, true, false);
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
     * @return A pie chart.
     */
    public static Chart pie(String title, PieDataset dataset,
            boolean legend, boolean tooltips, boolean urls) {

        PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator<>());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator<>());
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
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
     * @param locale                 the locale ({@code null} not permitted).
     * @param subTitle               displays a subtitle with colour scheme if true
     * @param showDifference         create a new dataset that will show the %
     *                               difference between the two datasets.
     * @return A pie chart.
     */
    public static Chart pie(String title, PieDataset<String> dataset,
            PieDataset<String> previousDataset, int percentDiffForMaxScale,
            boolean greenForIncrease, boolean legend, boolean tooltips,
            Locale locale, boolean subTitle, boolean showDifference) {

        PiePlot<String> plot = new PiePlot<>(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator<>(locale));
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));

        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator<>(locale));
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
                    plot.setSectionPaint(key, Color.GREEN);
                } else {
                    plot.setSectionPaint(key, Color.RED);
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (+100%)", newValue);
                }
            } else {
                double percentChange = (newValue.doubleValue()
                        / oldValue.doubleValue() - 1.0) * 100.0;
                double shade
                        = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255
                        : Math.abs(percentChange) * colorPerPercent);
                if (greenForIncrease
                        && newValue.doubleValue() > oldValue.doubleValue()
                        || !greenForIncrease && newValue.doubleValue()
                        < oldValue.doubleValue()) {
                    plot.setSectionPaint(key, new Color(0, (int) shade, 0));
                } else {
                    plot.setSectionPaint(key, new Color((int) shade, 0, 0));
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "")
                            + NumberFormat.getPercentInstance().format(
                            percentChange / 100.0) + ")", newValue);
                }
            }
        }

        if (showDifference) {
            plot.setDataset(series);
        }

        Chart chart = new Chart(title,
                Chart.DEFAULT_TITLE_FONT, plot, legend);

        if (subTitle) {
            TextTitle subtitle = new TextTitle("Bright " + (greenForIncrease
                    ? "red" : "green") + "=change >=-" + percentDiffForMaxScale
                    + "%, Bright " + (!greenForIncrease ? "red" : "green")
                    + "=change >=+" + percentDiffForMaxScale + "%",
                    new Font("SansSerif", Font.PLAIN, 10));
            chart.addSubtitle(subtitle);
        }
        currentTheme.apply(chart);
        return chart;
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
     * @return A pie chart.
     */
    public static Chart pie(String title, PieDataset<String> dataset,
            PieDataset<String> previousDataset, int percentDiffForMaxScale,
            boolean greenForIncrease, boolean legend, boolean tooltips,
            boolean urls, boolean subTitle, boolean showDifference) {

        PiePlot<String> plot = new PiePlot<>(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator<>());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));

        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }

        List<String> keys = dataset.getKeys();
        DefaultPieDataset<String> series = null;
        if (showDifference) {
            series = new DefaultPieDataset();
        }

        double colorPerPercent = 255.0 / percentDiffForMaxScale;
        for (String key : keys) {
            Number newValue = dataset.getValue(key);
            Number oldValue = previousDataset.getValue(key);

            if (oldValue == null) {
                if (greenForIncrease) {
                    plot.setSectionPaint(key, Color.GREEN);
                } else {
                    plot.setSectionPaint(key, Color.RED);
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (+100%)", newValue);
                }
            } else {
                double percentChange = (newValue.doubleValue()
                        / oldValue.doubleValue() - 1.0) * 100.0;
                double shade
                        = (Math.abs(percentChange) >= percentDiffForMaxScale ? 255
                        : Math.abs(percentChange) * colorPerPercent);
                if (greenForIncrease
                        && newValue.doubleValue() > oldValue.doubleValue()
                        || !greenForIncrease && newValue.doubleValue()
                        < oldValue.doubleValue()) {
                    plot.setSectionPaint(key, new Color(0, (int) shade, 0));
                } else {
                    plot.setSectionPaint(key, new Color((int) shade, 0, 0));
                }
                if (showDifference) {
                    assert series != null; // suppresses compiler warning
                    series.setValue(key + " (" + (percentChange >= 0 ? "+" : "")
                            + NumberFormat.getPercentInstance().format(
                            percentChange / 100.0) + ")", newValue);
                }
            }
        }

        if (showDifference) {
            plot.setDataset(series);
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);

        if (subTitle) {
            TextTitle subtitle = new TextTitle("Bright " + (greenForIncrease
                    ? "red" : "green") + "=change >=-" + percentDiffForMaxScale
                    + "%, Bright " + (!greenForIncrease ? "red" : "green")
                    + "=change >=+" + percentDiffForMaxScale + "%",
                    new Font("SansSerif", Font.PLAIN, 10));
            chart.addSubtitle(subtitle);
        }
        currentTheme.apply(chart);
        return chart;
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
     * @param locale   the locale ({@code null} not permitted).
     * @return A ring chart.
     */
    public static Chart ring(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
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
     * @return A ring chart.
     */
    public static Chart ring(String title, PieDataset dataset,
            boolean legend, boolean tooltips, boolean urls) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator<>());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator<>());
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a chart that displays multiple pie plots.  The chart object
     * returned by this method uses a {@link MultiplePiePlot} instance as the
     * plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset ({@code null} permitted).
     * @param order    the order that the data is extracted (by row or by column)
     *                 ({@code null} not permitted).
     * @param legend   include a legend?
     * @param tooltips generate tooltips?
     * @param urls     generate URLs?
     * @return A chart.
     */
    public static Chart pieMultiple(String title,
            CategoryDataset dataset, TableOrder order, boolean legend,
            boolean tooltips, boolean urls) {
        Objects.requireNonNull(order, "order");
        MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
        plot.setBackgroundPaint(null);
        plot.setOutlineStroke(null);

        if (tooltips) {
            PieToolTipGenerator tooltipGenerator
                    = new StandardPieToolTipGenerator();
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
        }

        if (urls) {
            PieURLGenerator urlGenerator = new StandardPieURLGenerator();
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a bar chart.
     *
     * @param categories category values.
     * @param values     values
     * @return a bar chart.
     */
    public static Chart bar(String[] categories, double[] values) {
        CategoryDataset<String, String> dataset = Data.createCategory("", categories, values);
        return bar(null, null, null, dataset,
                PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Creates a bar chart.
     *
     * @param categories  category values.
     * @param values      values
     * @param orientation {@link PlotOrientation}
     * @return a bar chart.
     */
    public static Chart bar(String[] categories, double[] values,
            PlotOrientation orientation) {
        CategoryDataset<String, String> dataset = Data.createCategory("", categories, values);
        return bar(null, null, null, dataset, orientation, false, true, false);
    }

    /**
     * Creates a bar chart with a vertical orientation.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link BarRenderer} as the
     * renderer.
     *
     * @param categoryAxisLabel the label for the category axis.
     * @param valueAxisLabel    the label for the value axis.
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A bar chart.
     */
    public static Chart bar(@Nullable String categoryAxisLabel, @Nullable String valueAxisLabel,
            CategoryDataset dataset) {
        return bar(null, categoryAxisLabel, valueAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a bar chart with a vertical orientation.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link BarRenderer} as the
     * renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A bar chart.
     */
    public static Chart bar(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset) {
        return bar(title, categoryAxisLabel, valueAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a bar chart.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A bar chart.
     */
    public static Chart bar(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        BarRenderer renderer = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setDefaultNegativeItemLabelPosition(position2);
        }
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(
                    new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a bar chart.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A bar chart.
     */
    public static Chart barLayered(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        LayeredBarRenderer renderer = new LayeredBarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setDefaultNegativeItemLabelPosition(position2);
        }
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(
                    new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a category chart.
     *
     * @param dataset           {@link CategoryDataset} instance.
     * @param chartType         {@link CategoryChartType}.
     * @param title             chart title
     * @param categoryAxisTitle category axis title.
     * @param valueAxisTitle    value axis title.
     * @param orientation       {@link PlotOrientation}
     * @param legend            whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @return a chart.
     */
    public static Chart create(CategoryDataset dataset, CategoryChartType chartType,
            String title, String categoryAxisTitle, String valueAxisTitle, PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        CategoryAxis domainAxis = new CategoryAxis(categoryAxisTitle);
        NumberAxis valueAxis = new NumberAxis(valueAxisTitle);
        CategoryItemRenderer renderer = chartType.getRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }

        CategoryPlot plot = new CategoryPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a chart with {@link XYPlot}
     *
     * @param dataset         {@link XYDataset}.
     * @param chartType       {@link XYChartType}
     * @param domainAxisType  {@link AxisType} for domain-axis.
     * @param rangeAxisType   {@link AxisType} for range-axis.
     * @param title           chart title.
     * @param domainAxisTitle domain-axis name.
     * @param rangeAxisTitle  range-axis name.
     * @param orientation     {@link PlotOrientation}
     * @param legend          true if create legend.
     * @param tooltips        true if show tooltips.
     * @return a chart.
     */
    public static Chart createXY(@Nullable XYDataset dataset, XYChartType chartType,
            AxisType domainAxisType, AxisType rangeAxisType, String title,
            String domainAxisTitle, String rangeAxisTitle,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        Objects.requireNonNull(orientation, "orientation");

        ValueAxis domainAxis = domainAxisType.createInstance();
        domainAxis.setLabel(domainAxisTitle);

        ValueAxis rangeAxis = rangeAxisType.createInstance();
        rangeAxis.setLabel(rangeAxisTitle);

        XYItemRenderer renderer = chartType.getRenderer();

        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a clustered bar chart, returns a default instance of XY bar chart.
     *
     * @param title       chart title.
     * @param xAxisLabel  x-axis name.
     * @param dateAxis    true if the x-axis is of date time.
     * @param yAxisLabel  y-axis name.
     * @param dataset     the dataset for the chart.
     * @param orientation {@link PlotOrientation}
     * @param legend      whether show legend.
     * @param tooltips    whether create tool tips.
     * @param urls        whether create urls.
     * @return a clustered bar chart.
     */
    public static Chart barCluster(@Nullable String title,
            @Nullable String xAxisLabel, boolean dateAxis, String yAxisLabel,
            @Nullable IntervalXYDataset dataset,
            @NonNull PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {

        Objects.requireNonNull(orientation, "orientation");
        ValueAxis domainAxis;
        if (dateAxis) {
            domainAxis = new DateAxis(xAxisLabel);
        } else {
            NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
            domainAxis = axis;
        }
        ValueAxis valueAxis = new NumberAxis(yAxisLabel);

        ClusteredXYBarRenderer renderer = new ClusteredXYBarRenderer();
        renderer.setShadowVisible(false);
        if (tooltips) {
            XYToolTipGenerator tt;
            if (dateAxis) {
                tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
            } else {
                tt = new StandardXYToolTipGenerator();
            }
            renderer.setDefaultToolTipGenerator(tt);
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);

        return chart;
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis
     *                        ({@code null} permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @return A stacked bar chart.
     */
    public static Chart barStacked(String title,
            String domainAxisLabel, String rangeAxisLabel,
            CategoryDataset dataset) {
        return barStacked(title, domainAxisLabel, rangeAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis
     *                        ({@code null} permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @param orientation     the orientation of the chart (horizontal or
     *                        vertical) ({@code null} not permitted).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     * @param urls            configure chart to generate URLs?
     * @return A stacked bar chart.
     */
    public static Chart barStacked(String title,
            String domainAxisLabel, String rangeAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        CategoryAxis categoryAxis = new CategoryAxis(domainAxisLabel);
        ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);

        StackedBarRenderer renderer = new StackedBarRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link XYPlot} instance as the
     * plot, with a {@link NumberAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedXYBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title.
     * @param domainAxisLabel the label for the category axis.
     * @param rangeAxisLabel  the label for the value axis.
     * @param dataset         the dataset for the chart.
     * @param orientation     the orientation of the chart (horizontal or
     *                        vertical).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     * @param urls            configure chart to generate URLs?
     * @return A stacked bar chart.
     */
    public static Chart barStackedXY(@Nullable String title,
            @Nullable String domainAxisLabel, @Nullable String rangeAxisLabel,
            @Nullable XYDataset dataset, @NonNull PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis domainAxis = new NumberAxis(domainAxisLabel);
        ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);

        StackedXYBarRenderer renderer = new StackedXYBarRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates an area chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and an {@link AreaRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return An area chart.
     */
    public static Chart area(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset) {
        return area(title, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates an area chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and an {@link AreaRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation ({@code null} not
     *                          permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return An area chart.
     */
    public static Chart area(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        AreaRenderer renderer = new AreaRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stacked area chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer}
     * as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A stacked area chart.
     */
    public static Chart stackedArea(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset) {
        return stackedArea(title, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stacked area chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer}
     * as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A stacked area chart.
     */
    public static Chart stackedArea(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        StackedAreaRenderer renderer = new StackedAreaRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title             the chart title.
     * @param categoryAxisLabel the label for the category axis.
     * @param valueAxisLabel    the label for the value axis.
     * @param categories        categories of the data items
     * @param values            values of the data items.
     * @return A line chart.
     */
    public static Chart line(@Nullable String title,
            @Nullable String categoryAxisLabel, @Nullable String valueAxisLabel,
            String[] categories, double[] values) {
        return line(title, categoryAxisLabel, valueAxisLabel,
                Data.createCategory("", categories, values),
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param categories categories of the dataset
     * @param values     values of the dataset
     * @return A line chart.
     */
    public static Chart line(String[] categories, double[] values) {
        return line(null, null, null, Data.createCategory("", categories, values),
                PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A line chart.
     */
    public static Chart line(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset) {
        return line(title, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the chart orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A line chart.
     */
    public static Chart line(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link DateAxis} as the range axis, and a
     * {@link GanttRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A Gantt chart.
     */
    public static Chart gantt(String title,
            String categoryAxisLabel, String dateAxisLabel,
            IntervalCategoryDataset dataset) {
        return gantt(title, categoryAxisLabel, dateAxisLabel,
                dataset, true, true, false);
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link DateAxis} as the range axis, and a
     * {@link GanttRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A Gantt chart.
     */
    public static Chart gantt(String title,
            String categoryAxisLabel, String dateAxisLabel,
            IntervalCategoryDataset dataset, boolean legend, boolean tooltips,
            boolean urls) {

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        DateAxis dateAxis = new DateAxis(dateAxisLabel);

        CategoryItemRenderer renderer = new GanttRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(
                    new IntervalCategoryToolTipGenerator(
                            "{3} - {4}", DateFormat.getDateInstance()));
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, dateAxis,
                renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a waterfall chart.  The chart object returned by this method
     * uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link WaterfallBarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} NOT permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A waterfall chart.
     */
    public static Chart waterfall(String title,
            String categoryAxisLabel, String valueAxisLabel,
            CategoryDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        WaterfallBarRenderer renderer = new WaterfallBarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.CENTER,
                    TextAnchor.CENTER, Math.PI / 2.0);
            renderer.setDefaultPositiveItemLabelPosition(position);
            renderer.setDefaultNegativeItemLabelPosition(position);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.CENTER,
                    TextAnchor.CENTER, 0.0);
            renderer.setDefaultPositiveItemLabelPosition(position);
            renderer.setDefaultNegativeItemLabelPosition(position);
        }
        if (tooltips) {
            StandardCategoryToolTipGenerator generator
                    = new StandardCategoryToolTipGenerator();
            renderer.setDefaultToolTipGenerator(generator);
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.clearRangeMarkers();
        Marker baseline = new ValueMarker(0.0);
        baseline.setPaint(Color.BLACK);
        plot.addRangeMarker(baseline, Layer.FOREGROUND);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a polar plot for the specified dataset (x-values interpreted as
     * angles in degrees).  The chart object returned by this method uses a
     * {@link PolarPlot} instance as the plot, with a {@link NumberAxis} for
     * the radial axis.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset ({@code null} permitted).
     * @param legend   legend required?
     * @param tooltips tooltips required?
     * @param urls     URLs required?
     * @return A chart.
     */
    public static Chart polar(String title, XYDataset dataset,
            boolean legend, boolean tooltips, boolean urls) {

        PolarPlot plot = new PolarPlot();
        plot.setDataset(dataset);
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        plot.setAxis(rangeAxis);
        plot.setRenderer(new DefaultPolarItemRenderer());
        Chart chart = new Chart(
                title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Create a scatter chart.
     *
     * @param x x values.
     * @param y y values.
     * @return a scatter chart.
     */
    public static Chart scatter(double[] x, double[] y) {
        XYDataset<String> dataset = Data.createXY("", x, y);
        return scatter(null, null, null, dataset, PlotOrientation.VERTICAL,
                false, true, false);
    }


    /**
     * Create a scatter chart.
     *
     * @param xName name of x-axis.
     * @param x     x values.
     * @param yName name of y-axis.
     * @param y     y values.
     * @return a scatter chart.
     */
    public static Chart scatter(String xName, double[] x,
            String yName, double[] y) {
        XYDataset<String> dataset = Data.createXY("", x, y);
        Chart chart = scatter(null, xName, yName, dataset, PlotOrientation.VERTICAL,
                false, true, false);
        chart.getXYPlot().getLineAndShapeRenderer().seriesShape(0, ShapeUtils.createCircle(6));
        return chart;
    }

    /**
     * Create a scatter chart.
     *
     * @param xName name of x-axis.
     * @param x     x values.
     * @param yName name of y-axis.
     * @param y     y values.
     * @return a scatter chart.
     */
    public static Chart scatter(String xName, Double[] x,
            String yName, Double[] y) {
        XYDataset<String> dataset = Data.createXY("", x, y);
        Chart chart = scatter(null, xName, yName, dataset, PlotOrientation.VERTICAL,
                false, true, false);
        chart.getXYPlot().getLineAndShapeRenderer().seriesShape(0, ShapeUtils.createCircle(6));
        return chart;
    }

    /**
     * Create a scatter chart.
     *
     * @param x     x values.
     * @param y     y values.
     * @param color z values map to shape colors.
     * @param xName name of x-axis.
     * @param yName name of y-axis.
     * @return a scatter chart.
     */
    public static Chart scatter(Double[] x, Double[] y, Double[] color,
            String xName, String yName, String zName) {

        NumberAxis xAixs = new NumberAxis(xName);
        xAixs.setAutoRangeIncludesZero(false);

        NumberAxis yAixs = new NumberAxis(yName);
        yAixs.setAutoRangeIncludesZero(false);

        double min = Data.getMin(color);
        double max = Data.getMax(color);

        XYShapeRenderer renderer = new XYShapeRenderer();

        LookupPaintScale ps = new LookupPaintScale(min, max, Color.GRAY);
        Color[] colors = JColorSequential.Plasma();
        double stepSize = (max - min) / (colors.length - 1);
        for (int i = 0; i < colors.length; i++) {
            ps.add(min + i * stepSize, colors[i]);
        }
        renderer.setPaintScale(ps);

        XYZDataset<String> dataset = Data.createXYZ("", x, y, color);

        XYPlot plot = new XYPlot(dataset, xAixs, yAixs, renderer);

        NumberAxis zAxis = new NumberAxis(zName);
        zAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        PaintScaleLegend legend = new PaintScaleLegend(ps, zAxis);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        Chart chart = new Chart(null, plot);
        chart.removeLegend();
        chart.addSubtitle(legend);

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    /**
     * Creates a scatter plot with default settings.  The chart object
     * returned by this method uses an {@link XYPlot} instance as the plot,
     * with a {@link NumberAxis} for the domain axis, a  {@link NumberAxis}
     * as the range axis, and an {@link XYLineAndShapeRenderer} as the
     * renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A scatter plot.
     */
    public static Chart scatter(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset) {
        return scatter(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a scatter plot with default settings.  The chart object
     * returned by this method uses an {@link XYPlot} instance as the plot,
     * with a {@link NumberAxis} for the domain axis, a  {@link NumberAxis}
     * as the range axis, and an {@link XYLineAndShapeRenderer} as the
     * renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A scatter plot.
     */
    public static Chart scatter(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        ValueAxis xAxis;
        if (dataset instanceof TimeSeriesCollection<?>) {
            xAxis = new DateAxis(xAxisLabel);
        } else {
            xAxis = new NumberAxis(xAxisLabel);
            ((NumberAxis) xAxis).setAutoRangeIncludesZero(false);
        }
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            if (xAxis instanceof DateAxis) {
                toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
            } else {
                toolTipGenerator = new StandardXYToolTipGenerator();
            }
        }

        XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setDefaultToolTipGenerator(toolTipGenerator);
        renderer.setURLGenerator(urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param dateAxis   make the domain axis display dates?
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return An XY bar chart.
     */
    public static Chart bar(String title, String xAxisLabel,
            boolean dateAxis, String yAxisLabel, IntervalXYDataset dataset) {
        return bar(title, xAxisLabel, dateAxis, yAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param dateAxis    make the domain axis display dates?
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return An XY bar chart.
     */
    public static Chart bar(String title, String xAxisLabel,
            boolean dateAxis, String yAxisLabel, IntervalXYDataset dataset,
            PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        ValueAxis domainAxis;
        if (dateAxis) {
            domainAxis = new DateAxis(xAxisLabel);
        } else {
            NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
            domainAxis = axis;
        }
        ValueAxis valueAxis = new NumberAxis(yAxisLabel);

        XYBarRenderer renderer = new XYBarRenderer();
        if (tooltips) {
            XYToolTipGenerator tt;
            if (dateAxis) {
                tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
            } else {
                tt = new StandardXYToolTipGenerator();
            }
            renderer.setDefaultToolTipGenerator(tt);
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param xAxisDate   make the domain axis display dates?
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param yAxisDate   make the range axis display dates
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return An XY bar chart.
     */
    public static Chart bar(String title,
            String xAxisLabel, boolean xAxisDate,
            String yAxisLabel, boolean yAxisDate,
            IntervalXYDataset dataset,
            PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        ValueAxis domainAxis;
        if (xAxisDate) {
            domainAxis = new DateAxis(xAxisLabel);
        } else {
            NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
            domainAxis = axis;
        }
        ValueAxis valueAxis;
        if (yAxisDate) {
            valueAxis = new DateAxis(yAxisLabel);
        } else {
            valueAxis = new NumberAxis(yAxisLabel);
        }

        XYBarRenderer renderer = new XYBarRenderer();
        if (tooltips) {
            XYToolTipGenerator tt;
            if (xAxisDate) {
                tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
            } else {
                tt = new StandardXYToolTipGenerator();
            }
            renderer.setDefaultToolTipGenerator(tt);
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates an area chart using an {@link XYDataset}.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link NumberAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYAreaRenderer} as
     * the renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param <S>        the type for series keys.
     * @return An XY area chart.
     */
    public static <S extends Comparable<S>> Chart areaXY(
            String title, String xAxisLabel, String yAxisLabel,
            XYDataset<S> dataset) {
        return areaXY(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates an area chart using an {@link XYDataset}.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link NumberAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYAreaRenderer} as
     * the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @param <S>         the type for series keys.
     * @return An XY area chart.
     */
    public static <S extends Comparable<S>> Chart areaXY(
            String title, String xAxisLabel,
            String yAxisLabel, XYDataset<S> dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYPlot<S> plot = new XYPlot<>(dataset, xAxis, yAxis, null);
        plot.setOrientation(orientation);
        plot.setForegroundAlpha(0.5f);

        XYToolTipGenerator tipGenerator = null;
        if (tooltips) {
            tipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }

        plot.setRenderer(new XYAreaRenderer(XYAreaRenderer.AREA, tipGenerator,
                urlGenerator));
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A stacked XY area chart.
     */
    public static Chart stackedAreaXY(String title,
            String xAxisLabel, String yAxisLabel, TableXYDataset dataset) {
        return stackedAreaXY(title, xAxisLabel, yAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A stacked XY area chart.
     */
    public static Chart stackedAreaXY(String title,
            String xAxisLabel, String yAxisLabel, TableXYDataset dataset,
            PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(
                toolTipGenerator, urlGenerator);
        renderer.setOutline(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);

        plot.setRangeAxis(yAxis);  // forces recalculation of the axis range

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param x x values.
     * @param y y values.
     * @return The chart.
     */
    public static Chart line(double[] x, double[] y) {
        return line(Data.createXY("", x, y), null, null, null,
                false, PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     * @return The chart.
     */
    public static Chart line(XYDataset dataset) {
        return line(dataset, null, null, null,
                false, PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return The chart.
     */
    public static Chart line(XYDataset dataset, String xAxisLabel,
            String yAxisLabel, @Nullable String title, boolean smooth,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer;
        if (smooth) {
            renderer = new XYSplineRenderer();
        } else {
            renderer = new XYLineAndShapeRenderer(true, true);
        }

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return The chart.
     */
    public static Chart lineSmooth(XYDataset dataset, String xAxisLabel,
            String yAxisLabel, @Nullable String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer = new XYSplineRenderer();
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return The chart.
     */
    public static Chart line(XYDataset dataset,
            String xAxisLabel, String yAxisLabel) {
        return line(dataset, xAxisLabel, yAxisLabel, null);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title      the chart title.
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return The chart.
     */
    public static Chart line(XYDataset dataset, String xAxisLabel,
            String yAxisLabel, @Nullable String title) {
        return line(dataset, xAxisLabel, yAxisLabel, title,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return The chart.
     */
    public static Chart line(XYDataset dataset, String xAxisLabel,
            String yAxisLabel, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        return line(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return The chart.
     */
    public static Chart line(XYDataset dataset, String xAxisLabel,
            String yAxisLabel, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A chart.
     */
    public static Chart stepXY(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset) {
        return stepXY(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A chart.
     */
    public static Chart stepXY(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        DateAxis xAxis = new DateAxis(xAxisLabel);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        XYItemRenderer renderer = new XYStepRenderer(toolTipGenerator,
                urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A chart.
     */
    public static Chart stepAreaXY(String title,
            String xAxisLabel, String yAxisLabel, XYDataset dataset) {
        return stepAreaXY(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A chart.
     */
    public static Chart stepAreaXY(String title,
            String xAxisLabel, String yAxisLabel, XYDataset dataset,
            PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        XYItemRenderer renderer = new XYStepAreaRenderer(
                XYStepAreaRenderer.AREA_AND_SHAPES, toolTipGenerator,
                urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <p>
     * A convenient dataset to use with this chart is a
     * {@link TimeSeriesCollection}.
     *
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @return A time series chart.
     */
    public static Chart timeLine(XYDataset dataset, String timeAxisLabel, String valueAxisLabel) {
        return timeLine(null, timeAxisLabel, valueAxisLabel,
                dataset, true, true, false);
    }

    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <p>
     * A convenient dataset to use with this chart is a
     * {@link TimeSeriesCollection}.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis.
     * @param valueAxisLabel a label for the value axis.
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @return A time series chart.
     */
    public static Chart timeLine(XYDataset dataset, @Nullable String timeAxisLabel,
            @Nullable String valueAxisLabel, String title) {
        return timeLine(title, timeAxisLabel, valueAxisLabel,
                dataset, false, true, false);
    }

    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <p>
     * A convenient dataset to use with this chart is a
     * {@link TimeSeriesCollection}.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @return A time series chart.
     */
    public static Chart timeLine(String title,
            String timeAxisLabel, String valueAxisLabel, XYDataset dataset) {
        return timeLine(title, timeAxisLabel, valueAxisLabel,
                dataset, true, true, false);
    }

    /**
     * Creates and returns a time series chart.
     * <p>
     * A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <p>
     * A convenient dataset to use with this chart is a
     * {@link TimeSeriesCollection}.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @param tooltips       configure chart to generate tool tips?
     * @param urls           configure chart to generate URLs?
     * @return A time series chart.
     */
    public static Chart timeLine(String title,
            String timeAxisLabel, String valueAxisLabel, XYDataset dataset,
            boolean legend, boolean tooltips, boolean urls) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setLowerMargin(0.02);  // reduce the default margins
        timeAxis.setUpperMargin(0.02);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setDefaultToolTipGenerator(toolTipGenerator);
        renderer.setURLGenerator(urlGenerator);
        plot.setRenderer(renderer);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of a candlesticks chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @return A candlestick chart.
     */
    public static Chart candlestick(String title,
            String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset,
            boolean legend) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @return A high-low-open-close chart.
     */
    public static Chart highLow(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {

        DateAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        HighLowRenderer renderer = new HighLowRenderer();
        renderer.setDefaultToolTipGenerator(new HighLowItemLabelGenerator());
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a bubble chart.
     *
     * @param x     x values.
     * @param y     y values.
     * @param size  data for bubble size.
     * @param color series name for each entry.
     * @return a bubble chart.
     */
    public static Chart bubble(String xName, Double[] x,
            String yName, Double[] y, Double[] size, @Nullable String[] color) {
        double rangeY = Data.getRange(y);

        double maxZ = Data.getMax(size);
        double scale = Math.max(maxZ, rangeY) * 4;

        Double[] z = new Double[size.length];
        for (int i = 0; i < size.length; i++) {
            z[i] = size[i] / scale;
        }

        Data.XYZDatasetBuilder<String> xyz = Data.xyz();
        Map<String, ArrayList<Double>[]> map = new HashMap<>();
        if (color != null) {
            // create multiple series
            for (int i = 0; i < color.length; i++) {
                ArrayList<Double>[] list = map.get(color[i]);
                if (list == null) {
                    list = new ArrayList[3];
                    list[0] = new ArrayList<>();
                    list[1] = new ArrayList<>();
                    list[2] = new ArrayList<>();
                    map.put(color[i], list);
                }
                list[0].add(x[i]);
                list[1].add(y[i]);
                list[2].add(z[i]);
            }
            for (Map.Entry<String, ArrayList<Double>[]> entry : map.entrySet()) {
                ArrayList<Double>[] value = entry.getValue();
                xyz.addSeries(entry.getKey(), value[0].toArray(new Double[0]),
                        value[1].toArray(new Double[0]),
                        value[2].toArray(new Double[0]));
            }
        } else {
            // only one series
            xyz.addSeries("", x, y, z);
        }

        XYZDataset<String> dataset = xyz.build();
        Chart chart = bubble(null, xName, yName, dataset);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        XYPlot plot = chart.getXYPlot();
        XYBubbleRenderer renderer = (XYBubbleRenderer) plot.getRenderer();
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.seriesOutlinePaint(i, Color.WHITE);
        }

        return chart;
    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A bubble chart.
     */
    public static Chart bubble(String title, String xAxisLabel,
            String yAxisLabel, XYZDataset dataset) {
        return bubble(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A bubble chart.
     */
    public static Chart bubble(String title, String xAxisLabel,
            String yAxisLabel, XYZDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);

        XYItemRenderer renderer = new XYBubbleRenderer(
                XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYZToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYZURLGenerator());
        }
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a histogram chart.  This chart is constructed with an
     * {@link XYPlot} using an {@link XYBarRenderer}.  The domain and range
     * axes are {@link NumberAxis} instances.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel the x-axis label ({@code null} permitted).
     * @param yAxisLabel the y-axis label ({@code null} permitted).
     * @param dataset    the dataset ({@code null} permitted).
     * @return A chart.
     */
    public static Chart histogram(String title,
            String xAxisLabel, String yAxisLabel, IntervalXYDataset dataset) {
        return histogram(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a histogram chart.  This chart is constructed with an
     * {@link XYPlot} using an {@link XYBarRenderer}.  The domain and range
     * axes are {@link NumberAxis} instances.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  the x axis label ({@code null} permitted).
     * @param yAxisLabel  the y axis label ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      create a legend?
     * @param tooltips    display tooltips?
     * @param urls        generate URLs?
     * @return The chart.
     */
    public static Chart histogram(String title,
            String xAxisLabel, String yAxisLabel, IntervalXYDataset dataset,
            PlotOrientation orientation, boolean legend, boolean tooltips,
            boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        ValueAxis yAxis = new NumberAxis(yAxisLabel);

        XYItemRenderer renderer = new XYBarRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates and returns a default instance of a box and whisker chart
     * based on data from a {@link BoxAndWhiskerCategoryDataset}.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel a label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    a label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @return A box and whisker chart.
     */
    public static Chart boxAndWhisker(String title,
            String categoryAxisLabel, String valueAxisLabel,
            BoxAndWhiskerCategoryDataset dataset, boolean legend) {

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);

        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of a box and whisker chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @return A box and whisker chart.
     */
    public static Chart boxAndWhisker(String title,
            String timeAxisLabel, String valueAxisLabel,
            BoxAndWhiskerXYDataset dataset, boolean legend) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
        XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer();
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a wind plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the x-axis ({@code null} permitted).
     * @param yAxisLabel a label for the y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag that controls whether a legend is created.
     * @param tooltips   configure chart to generate tool tips?
     * @param urls       configure chart to generate URLs?
     * @return A wind plot.
     */
    public static Chart wind(String title, String xAxisLabel,
            String yAxisLabel, WindDataset dataset, boolean legend,
            boolean tooltips, boolean urls) {

        ValueAxis xAxis = new DateAxis(xAxisLabel);
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setRange(-12.0, 12.0);

        WindItemRenderer renderer = new WindItemRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

    /**
     * Creates a wafer map chart.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted.
     * @param legend      display a legend?
     * @param tooltips    generate tooltips?
     * @param urls        generate URLs?
     * @return A wafer map chart.
     */
    public static Chart waferMap(String title,
            WaferMapDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        WaferMapPlot plot = new WaferMapPlot(dataset);
        WaferMapRenderer renderer = new WaferMapRenderer();
        plot.setRenderer(renderer);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a chart for spectrum.
     *
     * @param dataset {@link SpectrumDataset}.
     * @return a spectrum.
     */
    public static Chart spectrum(SpectrumDataset dataset) {
        SpectrumPlot plot = new SpectrumPlot();
        plot.setDataset(dataset);
        return new Chart(null, Chart.DEFAULT_TITLE_FONT, plot, false);
    }

    /**
     * Create a chart to display peptide spectrum match.
     *
     * @param dataset {@link PSMDataset}
     * @return a PSM chart.
     */
    public static Chart psm(PSMDataset dataset) {
        PSMPlot plot = new PSMPlot();
        plot.setDataset(dataset);
        Chart chart = new Chart(null, Chart.DEFAULT_TITLE_FONT, plot, false);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

}
