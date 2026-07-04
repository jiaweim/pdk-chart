package pdk.chart.event;

import pdk.chart.Chart;
import pdk.chart.util.Args;

import java.util.EventObject;

/**
 * A change event that encapsulates information about a change to a chart.
 */
public class ChartChangeEvent extends EventObject {

    /**
     * The type of event.
     */
    private ChartChangeEventType type;

    /**
     * The chart that generated the event.
     */
    private Chart chart;

    /**
     * Creates a new chart change event.
     *
     * @param source the source of the event (could be the chart, a title,
     *               an axis etc., {@code null} not permitted).
     */
    public ChartChangeEvent(Object source) {
        this(source, null, ChartChangeEventType.GENERAL);
    }

    /**
     * Creates a new chart change event.
     *
     * @param source the source of the event (could be the chart, a title, an
     *               axis etc., {@code null} not permitted).
     * @param chart  the chart that generated the event.
     */
    public ChartChangeEvent(Object source, Chart chart) {
        this(source, chart, ChartChangeEventType.GENERAL);
    }

    /**
     * Creates a new chart change event.
     *
     * @param source the source of the event (could be the chart, a title, an
     *               axis etc., {@code null} not permitted).
     * @param chart  the chart that generated the event.
     * @param type   the type of event ({@code null} not permitted).
     */
    public ChartChangeEvent(Object source, Chart chart,
            ChartChangeEventType type) {
        super(source);
        Args.nullNotPermitted(type, "type");
        this.chart = chart;
        this.type = type;
    }

    /**
     * Returns the chart that generated the change event.
     *
     * @return The chart that generated the change event.
     */
    public Chart getChart() {
        return this.chart;
    }

    /**
     * Sets the chart that generated the change event.
     *
     * @param chart the chart that generated the event.
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * Returns the event type.
     *
     * @return The event type (never {@code null}).
     */
    public ChartChangeEventType getType() {
        return this.type;
    }

    /**
     * Sets the event type.
     *
     * @param type the event type ({@code null} not permitted).
     */
    public void setType(ChartChangeEventType type) {
        Args.nullNotPermitted(type, "type");
        this.type = type;
    }

}
