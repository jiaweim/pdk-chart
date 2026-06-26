package pdk.chart.swing.editor;

import pdk.chart.Chart;

/**
 * A default implementation of the {@link ChartEditorFactory} interface.
 */
public class DefaultChartEditorFactory implements ChartEditorFactory {

    /**
     * Creates a new instance.
     */
    public DefaultChartEditorFactory() {}

    /**
     * Returns a new instance of a {@link ChartEditor}.
     *
     * @param chart the chart.
     * @return A chart editor for the given chart.
     */
    @Override
    public ChartEditor createEditor(Chart chart) {
        return new DefaultChartEditor(chart);
    }
}
