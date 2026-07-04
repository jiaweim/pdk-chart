package pdk.chart.event;

import pdk.chart.Chart;
import pdk.chart.util.Args;

/**
 * An event that contains information about the drawing progress of a chart.
 */
public class ChartProgressEvent extends java.util.EventObject {

    /**
     * The type of event.
     */
    private ChartProgressEventType type;

    /**
     * The percentage of completion.
     */
    private int percent;

    /**
     * The chart that generated the event.
     */
    private Chart chart;

    /**
     * Creates a new chart change event.
     *
     * @param source  the source of the event (could be the chart, a title, an
     *                axis etc.)
     * @param chart   the chart that generated the event.
     * @param type    the type of event ({@code null} not permitted).
     * @param percent the percentage of completion.
     */
    public ChartProgressEvent(Object source, Chart chart,
            ChartProgressEventType type, int percent) {
        super(source);
        Args.nullNotPermitted(type, "type");
        this.chart = chart;
        this.type = type;
        this.percent = percent;
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
    public ChartProgressEventType getType() {
        return this.type;
    }

    /**
     * Sets the event type.
     *
     * @param type the event type ({@code null} not permitted).
     */
    public void setType(ChartProgressEventType type) {
        Args.nullNotPermitted(type, "type");
        this.type = type;
    }

    /**
     * Returns the percentage complete.
     *
     * @return The percentage complete.
     */
    public int getPercent() {
        return this.percent;
    }

    /**
     * Sets the percentage complete.
     *
     * @param percent the percentage.
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }

}
