package pdk.chart.renderer.xy;

import pdk.chart.api.PublicCloneable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.entity.EntityCollection;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.legend.LegendItem;
import pdk.chart.plot.CrosshairState;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.XYPlot;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * A renderer that draws a circle at each data point with a diameter that is
 * determined by the z-value in the dataset (the renderer requires the dataset
 * to be an instance of {@link XYZDataset}.
 */
public class XYBubbleRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, PublicCloneable {

    /**
     * For serialization.
     */
    public static final long serialVersionUID = -5221991598674249125L;

    /**
     * A constant to specify that the bubbles drawn by this renderer should be
     * scaled on both axes (see {@link #XYBubbleRenderer(int)}).
     */
    public static final int SCALE_ON_BOTH_AXES = 0;

    /**
     * A constant to specify that the bubbles drawn by this renderer should be
     * scaled on the domain axis (see {@link #XYBubbleRenderer(int)}).
     */
    public static final int SCALE_ON_DOMAIN_AXIS = 1;

    /**
     * A constant to specify that the bubbles drawn by this renderer should be
     * scaled on the range axis (see {@link #XYBubbleRenderer(int)}).
     */
    public static final int SCALE_ON_RANGE_AXIS = 2;

    /**
     * Controls how the width and height of the bubble are scaled.
     */
    private int scaleType;

    /**
     * Constructs a new renderer.
     */
    public XYBubbleRenderer() {
        this(SCALE_ON_BOTH_AXES);
    }

    /**
     * Constructs a new renderer with the specified type of scaling.
     *
     * @param scaleType the type of scaling (must be one of:
     *                  {@link #SCALE_ON_BOTH_AXES}, {@link #SCALE_ON_DOMAIN_AXIS},
     *                  {@link #SCALE_ON_RANGE_AXIS}).
     */
    public XYBubbleRenderer(int scaleType) {
        super();
        if (scaleType < 0 || scaleType > 2) {
            throw new IllegalArgumentException("Invalid 'scaleType'.");
        }
        this.scaleType = scaleType;
        setDefaultLegendShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
    }

    /**
     * Returns the scale type that was set when the renderer was constructed.
     *
     * @return The scale type (one of: {@link #SCALE_ON_BOTH_AXES},
     * {@link #SCALE_ON_DOMAIN_AXIS}, {@link #SCALE_ON_RANGE_AXIS}).
     */
    public int getScaleType() {
        return this.scaleType;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2             the graphics device.
     * @param state          the renderer state.
     * @param dataArea       the area within which the data is being drawn.
     * @param info           collects information about the drawing.
     * @param plot           the plot (can be used to obtain standard color
     *                       information etc).
     * @param domainAxis     the domain (horizontal) axis.
     * @param rangeAxis      the range (vertical) axis.
     * @param dataset        the dataset (an {@link XYZDataset} is expected).
     * @param series         the series index (zero-based).
     * @param item           the item index (zero-based).
     * @param crosshairState crosshair information for the plot
     *                       ({@code null} permitted).
     * @param pass           the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
                         Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
                         ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
                         int series, int item, CrosshairState crosshairState, int pass) {

        // return straight away if the item is not visible
        if (!getItemVisible(series, item)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();

        // get the data point...
        double x = dataset.getXValue(series, item);
        double y = dataset.getYValue(series, item);
        double z = Double.NaN;
        if (dataset instanceof XYZDataset) {
            XYZDataset xyzData = (XYZDataset) dataset;
            z = xyzData.getZValue(series, item);
        }
        if (!Double.isNaN(z)) {
            RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
            RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
            double transX = domainAxis.valueToJava2D(x, dataArea,
                    domainAxisLocation);
            double transY = rangeAxis.valueToJava2D(y, dataArea,
                    rangeAxisLocation);

            double transDomain;
            double transRange;
            double zero;

            switch (getScaleType()) {
                case SCALE_ON_DOMAIN_AXIS:
                    zero = domainAxis.valueToJava2D(0.0, dataArea,
                            domainAxisLocation);
                    transDomain = domainAxis.valueToJava2D(z, dataArea,
                            domainAxisLocation) - zero;
                    transRange = transDomain;
                    break;
                case SCALE_ON_RANGE_AXIS:
                    zero = rangeAxis.valueToJava2D(0.0, dataArea,
                            rangeAxisLocation);
                    transRange = zero - rangeAxis.valueToJava2D(z, dataArea,
                            rangeAxisLocation);
                    transDomain = transRange;
                    break;
                default:
                    double zero1 = domainAxis.valueToJava2D(0.0, dataArea,
                            domainAxisLocation);
                    double zero2 = rangeAxis.valueToJava2D(0.0, dataArea,
                            rangeAxisLocation);
                    transDomain = domainAxis.valueToJava2D(z, dataArea,
                            domainAxisLocation) - zero1;
                    transRange = zero2 - rangeAxis.valueToJava2D(z, dataArea,
                            rangeAxisLocation);
            }
            transDomain = Math.abs(transDomain);
            transRange = Math.abs(transRange);
            Ellipse2D circle;
            if (orientation == PlotOrientation.VERTICAL) {
                circle = new Ellipse2D.Double(transX - transDomain / 2.0,
                        transY - transRange / 2.0, transDomain, transRange);
            } else if (orientation == PlotOrientation.HORIZONTAL) {
                circle = new Ellipse2D.Double(transY - transRange / 2.0,
                        transX - transDomain / 2.0, transRange, transDomain);
            } else {
                throw new IllegalStateException();
            }
            g2.setPaint(getItemPaint(series, item));
            g2.fill(circle);
            g2.setStroke(getItemOutlineStroke(series, item));
            g2.setPaint(getItemOutlinePaint(series, item));
            g2.draw(circle);

            if (isItemLabelVisible(series, item)) {
                if (orientation == PlotOrientation.VERTICAL) {
                    drawItemLabel(g2, orientation, dataset, series, item,
                            transX, transY, false);
                } else if (orientation == PlotOrientation.HORIZONTAL) {
                    drawItemLabel(g2, orientation, dataset, series, item,
                            transY, transX, false);
                }
            }

            // add an entity if this info is being collected
            if (info != null) {
                EntityCollection entities
                        = info.getOwner().getEntityCollection();
                if (entities != null && circle.intersects(dataArea)) {
                    addEntity(entities, circle, dataset, series, item,
                            circle.getCenterX(), circle.getCenterY());
                }
            }

            int datasetIndex = plot.indexOf(dataset);
            updateCrosshairValues(crosshairState, x, y, datasetIndex,
                    transX, transY, orientation);
        }
    }

    /**
     * Returns a legend item for the specified series.  The default method
     * is overridden so that the legend displays circles for all series.
     *
     * @param datasetIndex the dataset index (zero-based).
     * @param series       the series index (zero-based).
     * @return A legend item for the series.
     */
    @Override
    public LegendItem getLegendItem(int datasetIndex, int series) {
        LegendItem result = null;
        XYPlot plot = getPlot();
        if (plot == null) {
            return null;
        }

        XYDataset dataset = plot.getDataset(datasetIndex);
        if (dataset != null) {
            if (getItemVisible(series, 0)) {
                String label = getLegendItemLabelGenerator().generateLabel(
                        dataset, series);
                String description = label;
                String toolTipText = null;
                if (getLegendItemToolTipGenerator() != null) {
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
                }
                String urlText = null;
                if (getLegendItemURLGenerator() != null) {
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
                }
                Shape shape = lookupLegendShape(series);
                Paint paint = lookupSeriesPaint(series);
                Paint outlinePaint = lookupSeriesOutlinePaint(series);
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, toolTipText,
                        urlText, shape, paint, outlineStroke, outlinePaint);
                result.setLabelFont(lookupLegendTextFont(series));
                Paint labelPaint = lookupLegendTextPaint(series);
                if (labelPaint != null) {
                    result.setLabelPaint(labelPaint);
                }
                result.setDataset(dataset);
                result.setDatasetIndex(datasetIndex);
                result.setSeriesKey(dataset.getSeriesKey(series));
                result.setSeriesIndex(series);
            }
        }
        return result;
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYBubbleRenderer that)) {
            return false;
        }
        if (this.scaleType != that.scaleType) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if the renderer cannot be cloned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Sets the paint used for a series outline and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @see #getSeriesOutlinePaint(int)
     */
    public XYBubbleRenderer seriesOutlinePaint(int series, Paint paint) {
        setSeriesOutlinePaint(series, paint, true);
        return this;
    }

    /**
     * Sets the default outline paint and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     * @see #getDefaultOutlinePaint()
     */
    public XYBubbleRenderer defaultOutlinePaint(Paint paint) {
        // defer argument checking...
        setDefaultOutlinePaint(paint, false);
        return this;
    }

    /**
     * Sets the default tool tip generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     * @see #getDefaultToolTipGenerator()
     */
    public XYBubbleRenderer defaultToolTipGenerator(XYToolTipGenerator generator) {
        setDefaultToolTipGenerator(generator);
        return this;
    }
}
