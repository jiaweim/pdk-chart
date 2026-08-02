package pdk.chart.model;

import pdk.chart.Chart;

/**
 * A {@link ChartTheme} a class that can apply a style or 'theme' to a chart.
 * It can be implemented in an arbitrary manner, with the styling applied to
 * the chart via the {@code apply(JFreeChart)} method.  We provide one
 * implementation ({@link StandardChartTheme}) that just mimics the manual
 * process of calling methods to set various chart parameters.
 */
public interface ChartTheme {

    /**
     * Applies this theme to the supplied chart.
     *
     * @param chart the chart ({@code null} not permitted).
     */
    void apply(Chart chart);

}
