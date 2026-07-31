package pdk.chart;

import pdk.chart.axis.ValueAxis;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.plot.Plot;
import pdk.chart.plot.XYPlot;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 8:19 AM
 */
public class XYChart extends Chart {

    public enum Type {
        LINE,
        SCATTER,
        BUBBLE,
        BAR,
        PEAK
    }

    protected final XYPlot plot_;

    public XYChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, new XYPlot<>(), createLegend);
        this.plot_ = getXYPlot();
    }

    public XYChart(String title, Font titleFont, Plot plot, boolean createLegend) {
        super(title, titleFont, plot, createLegend);
        plot_ = getXYPlot();
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
}
