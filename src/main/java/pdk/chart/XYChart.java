package pdk.chart;

import pdk.chart.annotations.XYAnnotation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.plot.Plot;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 8:19 AM
 */
public class XYChart extends Chart {

    public enum AxisType {
        NUMBER,
        DATE
    }

    public enum ChartType {
        LINE,
        AREA,
        SCATTER,
        BUBBLE,
        BAR,
        PEAK;

        XYItemRenderer getRenderer() {
            switch (this) {
                case AREA -> {
                    return new XYAreaRenderer(XYAreaRenderer.AREA);
                }
                default -> {
                    return null;
                }
            }
        }
    }

    protected final XYPlot plot_;
    private XYItemRenderer defaultRenderer_;

    public XYChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, new XYPlot<>(), createLegend);
        this.plot_ = getXYPlot();
    }

    public XYChart(String title, Font titleFont, Plot plot, boolean createLegend) {
        super(title, titleFont, plot, createLegend);
        plot_ = getXYPlot();
    }

    protected void setDefaultRenderer(XYItemRenderer renderer) {
        this.defaultRenderer_ = renderer;
    }

    public void addDataset(XYDataset dataset, ChartType chartType) {
        int datasetCount = plot_.getDatasetCount();
        plot_.setDataset(datasetCount, dataset);
        plot_.setRenderer(datasetCount, chartType.getRenderer());
    }

    /**
     * Add a new dataset to this chart.
     *
     * @param dataset {@link XYDataset}.
     */
    public void addDataset(XYDataset dataset, XYItemRenderer renderer) {
        int datasetCount = plot_.getDatasetCount();
        plot_.setDataset(datasetCount, dataset);
        plot_.setRenderer(datasetCount, renderer);
    }

    /**
     * Sets the alpha-transparency for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param alpha the new alpha transparency.
     */
    public void setForegroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
    }

    /**
     * Sets the flag that enables or disables panning of the plot along the
     * domain axes.
     *
     * @param pannable the new flag value.
     */
    public void setDomainPannable(boolean pannable) {
        plot_.setDomainPannable(pannable);
    }

    /**
     * Sets the flag that enables or disables panning of the plot along
     * the range axis/axes.
     *
     * @param pannable the new flag value.
     */
    public void setRangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
    }

    /**
     * Sets the flag that enables or disables panning of the plot along
     * the range axis/axes.
     *
     * @param domainPannable the new flag value for domain pannable.
     * @param rangePannable  the new flag for range pannable.
     */
    public void setPannable(boolean domainPannable, boolean rangePannable) {
        plot_.setDomainPannable(domainPannable);
        plot_.setRangePannable(rangePannable);
    }

    /**
     * Sets the lower margin and upper margin for the domain axis (as a percentage of the axis range).
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param lowerMargin the margin percentage (for example, 0.05 is five percent).
     * @param upperMargin the margin percentage (for example, 0.05 is five percent).
     */
    public void setDomainAxisMargin(double lowerMargin, double upperMargin) {
        ValueAxis domainAxis = plot_.getDomainAxis();
        if (domainAxis != null) {
            domainAxis.setLowerMargin(lowerMargin);
            domainAxis.setUpperMargin(upperMargin);
        }
    }

    /**
     * Sets the lower margin and upper margin for the axis (as a percentage of the axis range).
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param lowerMargin the margin percentage (for example, 0.05 is five percent).
     * @param upperMargin the margin percentage (for example, 0.05 is five percent).
     */
    public void setRangeAxisMargin(double lowerMargin, double upperMargin) {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis != null) {
            rangeAxis.setLowerMargin(lowerMargin);
            rangeAxis.setUpperMargin(upperMargin);
        }
    }

    /**
     * Sets the label for the X axis.
     *
     * @param label the axis label text
     */
    public void setDomainAxisLabel(String label) {
        plot_.getDomainAxis().setLabel(label);
    }

    /**
     * Sets the label for the Y axis.
     *
     * @param label the axis label text
     */
    public void setRangeAxisLabel(String label) {
        plot_.getRangeAxis().setLabel(label);
    }

    /**
     * Sets the lower and upper bounds of the X axis.
     *
     * @param lower the lower bound
     * @param upper the upper bound
     */
    public void setDomainAxisRange(double lower, double upper) {
        plot_.getDomainAxis().setRange(lower, upper);
    }

    /**
     * Sets the lower and upper bounds of the Y axis.
     *
     * @param lower the lower bound
     * @param upper the upper bound
     */
    public void setRangeAxisRange(double lower, double upper) {
        plot_.getRangeAxis().setRange(lower, upper);
    }

    /**
     * Sets the paint for the grid lines plotted against the domain axis, and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDomainGridlinePaint(Paint paint) {
        plot_.setDomainGridlinePaint(paint);
    }

    /**
     * Sets the flag that controls whether the domain grid-lines are
     * visible.
     * <p>
     * If the flag value is changed, a {@link PlotChangeEvent} is sent to all
     * registered listeners.
     *
     * @param visible the new value of the flag.
     */
    public void setDomainGridlinesVisible(boolean visible) {
        plot_.setDomainGridlinesVisible(visible);
    }

    /**
     * Sets the paint for the grid lines plotted against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setRangeGridlinePaint(Paint paint) {
        plot_.setRangeGridlinePaint(paint);
    }

    public NumberAxis getDomainAxis() {
        ValueAxis domainAxis = plot_.getDomainAxis();
        if (domainAxis instanceof NumberAxis nAxis) {
            return nAxis;
        }
        return null;
    }

    /**
     * Return the range axis as {@link NumberAxis}.
     *
     * @return range axis.
     */
    public NumberAxis getRangeAxis() {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis instanceof NumberAxis nAxis) {
            return nAxis;
        }
        return null;
    }

    /**
     * Set axis names.
     *
     * @param xLabel x-axis name.
     * @param yLabel y-axis name.
     */
    public void setAxisLabels(String xLabel, String yLabel) {
        plot_.getDomainAxis().setLabel(xLabel);
        plot_.getRangeAxis().setLabel(yLabel);
    }

    /**
     * Adds an annotation to the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param annotation the annotation ({@code null} not permitted).
     */
    public void addAnnotation(XYAnnotation annotation) {
        plot_.addAnnotation(annotation);
    }

}
