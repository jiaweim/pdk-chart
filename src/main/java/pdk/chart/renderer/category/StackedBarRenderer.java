package pdk.chart.renderer.category;

import pdk.chart.api.PublicCloneable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.DataUtils;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.Range;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.entity.EntityCollection;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.text.TextAnchor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A stacked bar renderer for use with the {@link CategoryPlot} class.
 */
public class StackedBarRenderer extends BarRenderer
        implements Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    @Serial
    static final long serialVersionUID = 6402943811500067531L;

    /**
     * A flag that controls whether the bars display values or percentages.
     */
    private boolean renderAsPercentages;

    /**
     * A map used to assign each series to a group.
     */
    private KeyToGroupMap seriesToGroupMap = null;

    /**
     * Creates a new renderer.  By default, the renderer has no tool tip
     * generator and no URL generator.  These defaults have been chosen to
     * minimise the processing required to generate a default chart.  If you
     * require tool tips or URLs, then you can easily add the required
     * generators.
     */
    public StackedBarRenderer() {
        this(false);
    }

    /**
     * Creates a new renderer.
     *
     * @param renderAsPercentages a flag that controls whether the data values
     *                            are rendered as percentages.
     */
    public StackedBarRenderer(boolean renderAsPercentages) {
        super();
        this.renderAsPercentages = renderAsPercentages;

        // set the default item label positions, which will only be used if
        // the user requests visible item labels...
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER,
                TextAnchor.CENTER);
        setDefaultPositiveItemLabelPosition(p);
        setDefaultNegativeItemLabelPosition(p);
        setPositiveItemLabelPositionFallback(null);
        setNegativeItemLabelPositionFallback(null);
    }

    /**
     * Returns {@code true} if the renderer displays each item value as
     * a percentage (so that the stacked bars add to 100%), and
     * {@code false} otherwise.
     *
     * @return A boolean.
     * @see #setRenderAsPercentages(boolean)
     */
    public boolean getRenderAsPercentages() {
        return this.renderAsPercentages;
    }

    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param asPercentages the flag.
     * @see #getRenderAsPercentages()
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
        fireChangeEvent();
    }

    /**
     * Updates the map used to assign each series to a group, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param map the map ({@code null} not permitted).
     */
    public void setSeriesToGroupMap(KeyToGroupMap map) {
        this.seriesToGroupMap = map;
        fireChangeEvent();
    }

    /**
     * Returns the number of passes ({@code 3}) required by this renderer.
     * The first pass is used to draw the bar shadows, the second pass is used
     * to draw the bars, and the third pass is used to draw the item labels
     * (if visible).
     *
     * @return The number of passes required by the renderer.
     */
    @Override
    public int getPassCount() {
        return 3;
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return The range (or {@code null} if the dataset is empty).
     */
    @Override
    public Range findRangeBounds(CategoryDataset dataset) {
        if (dataset == null) {
            return null;
        }
        if (seriesToGroupMap == null) {
            if (this.renderAsPercentages) {
                return new Range(0.0, 1.0);
            } else {
                return DatasetUtils.findStackedRangeBounds(dataset, getBase());
            }
        } else {
            return DatasetUtils.findStackedRangeBounds(dataset, this.seriesToGroupMap);
        }
    }

    /**
     * Calculates the bar width and stores it in the renderer state.
     *
     * @param plot          the plot.
     * @param dataArea      the data area.
     * @param rendererIndex the renderer index.
     * @param state         the renderer state.
     */
    @Override
    protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea,
            int rendererIndex, CategoryItemRendererState state) {

        // calculate the bar width
        CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
        CategoryDataset data = plot.getDataset(rendererIndex);
        if (data != null) {
            PlotOrientation orientation = plot.getOrientation();
            double space = 0.0;
            if (orientation == PlotOrientation.HORIZONTAL) {
                space = dataArea.getHeight();
            } else if (orientation == PlotOrientation.VERTICAL) {
                space = dataArea.getWidth();
            }
            double maxWidth = space * getMaximumBarWidth();

            int groupCount = (seriesToGroupMap == null) ? 1 : seriesToGroupMap.getGroupCount();
            int categories = data.getColumnCount();
            int columns = groupCount * categories;
            double categoryMargin = 0.0;
            if (categories > 1) {
                categoryMargin = xAxis.getCategoryMargin();
            }
            double itemMargin = (groupCount > 1) ? getItemMargin() : 0.0;

            double used = space * (1 - xAxis.getLowerMargin()
                    - xAxis.getUpperMargin()
                    - categoryMargin - itemMargin);
            if (columns > 0) {
                state.setBarWidth(Math.min(used / columns, maxWidth));
            } else {
                state.setBarWidth(Math.min(used, maxWidth));
            }
        }
    }

    /**
     * Calculates the coordinate of the first "side" of a bar.  This will be
     * the minimum x-coordinate for a vertical bar, and the minimum
     * y-coordinate for a horizontal bar.
     *
     * @param plot        the plot.
     * @param orientation the plot orientation.
     * @param dataArea    the data area.
     * @param domainAxis  the domain axis.
     * @param state       the renderer state (has the bar width precalculated).
     * @param row         the row index.
     * @param column      the column index.
     * @return The coordinate.
     */
    @Override
    protected double calculateBarW0(CategoryPlot plot,
            PlotOrientation orientation, Rectangle2D dataArea,
            CategoryAxis domainAxis, CategoryItemRendererState state,
            int row, int column) {
        if (seriesToGroupMap == null) {
            return super.calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
        }
        // calculate bar width...
        double space;
        if (orientation == PlotOrientation.HORIZONTAL) {
            space = dataArea.getHeight();
        } else {
            space = dataArea.getWidth();
        }
        double barW0 = domainAxis.getCategoryStart(column, getColumnCount(),
                dataArea, plot.getDomainAxisEdge());
        int groupCount = this.seriesToGroupMap.getGroupCount();
        int groupIndex = this.seriesToGroupMap.getGroupIndex(
                this.seriesToGroupMap.getGroup(plot.getDataset(
                        plot.getIndexOf(this)).getRowKey(row)));
        int categoryCount = getColumnCount();
        if (groupCount > 1) {
            double groupGap = space * getItemMargin()
                    / (categoryCount * (groupCount - 1));
            double groupW = calculateSeriesWidth(space, domainAxis,
                    categoryCount, groupCount);
            barW0 = barW0 + groupIndex * (groupW + groupGap)
                    + (groupW / 2.0) - (state.getBarWidth() / 2.0);
        } else {
            barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(),
                    dataArea, plot.getDomainAxisEdge())
                    - state.getBarWidth() / 2.0;
        }
        return barW0;
    }

    /**
     * Draws a stacked bar for a specific item.
     *
     * @param g2         the graphics device.
     * @param state      the renderer state.
     * @param dataArea   the plot area.
     * @param plot       the plot.
     * @param domainAxis the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
     * @param dataset    the data.
     * @param row        the row index (zero-based).
     * @param column     the column index (zero-based).
     * @param pass       the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row,
            int column, int pass) {

        if (!isSeriesVisible(row)) {
            return;
        }

        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return;
        }
        PlotOrientation orientation = plot.getOrientation();
        double value = dataValue.doubleValue();

        double positiveBase;
        double negativeBase;
        double barW0;
        if (seriesToGroupMap != null) {
            Comparable group = this.seriesToGroupMap.getGroup(dataset.getRowKey(row));
            barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis,
                    state, row, column);

            positiveBase = 0.0;
            negativeBase = 0.0;
            for (int i = 0; i < row; i++) {
                if (group.equals(this.seriesToGroupMap.getGroup(
                        dataset.getRowKey(i)))) {
                    Number v = dataset.getValue(i, column);
                    if (v != null && isSeriesVisible(i)) {
                        double d = v.doubleValue();
                        if (d > 0) {
                            positiveBase = positiveBase + d;
                        } else {
                            negativeBase = negativeBase + d;
                        }
                    }
                }
            }
        } else {
            double total = 0.0;  // only needed if calculating percentages
            if (this.renderAsPercentages) {
                total = DataUtils.calculateColumnTotal(dataset, column,
                        state.getVisibleSeriesArray());
                value = value / total;
            }

            positiveBase = getBase();
            negativeBase = positiveBase;
            for (int i = 0; i < row; i++) {
                Number v = dataset.getValue(i, column);
                if (v != null && isSeriesVisible(i)) {
                    double d = v.doubleValue();
                    if (this.renderAsPercentages) {
                        d = d / total;
                    }
                    if (d > 0) {
                        positiveBase = positiveBase + d;
                    } else {
                        negativeBase = negativeBase + d;
                    }
                }
            }
            barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(),
                    dataArea, plot.getDomainAxisEdge())
                    - state.getBarWidth() / 2.0;
        }

        double translatedBase;
        double translatedValue;
        boolean positive = (value > 0.0);
        boolean inverted = rangeAxis.isInverted();
        RectangleEdge barBase;
        if (orientation == PlotOrientation.HORIZONTAL) {
            if (positive && inverted || !positive && !inverted) {
                barBase = RectangleEdge.RIGHT;
            } else {
                barBase = RectangleEdge.LEFT;
            }
        } else {
            if (positive && !inverted || !positive && inverted) {
                barBase = RectangleEdge.BOTTOM;
            } else {
                barBase = RectangleEdge.TOP;
            }
        }
        RectangleEdge location = plot.getRangeAxisEdge();
        if (positive) {
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value,
                    dataArea, location);
        } else {
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value,
                    dataArea, location);
        }
        double barL0 = Math.min(translatedBase, translatedValue);
        double barLength = Math.max(Math.abs(translatedValue - translatedBase),
                getMinimumBarLength());

        Rectangle2D bar;
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(barL0, barW0, barLength,
                    state.getBarWidth());
        } else {
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(),
                    barLength);
        }

        if (seriesToGroupMap == null) {
            if (pass == 0) {
                if (getShadowsVisible()) {
                    boolean pegToBase = (positive && (positiveBase == getBase()))
                            || (!positive && (negativeBase == getBase()));
                    getBarPainter().paintBarShadow(g2, this, row, column, bar,
                            barBase, pegToBase);
                }
            } else if (pass == 1) {
                getBarPainter().paintBar(g2, this, row, column, bar, barBase);

                // add an item entity, if this information is being collected
                EntityCollection entities = state.getEntityCollection();
                if (entities != null) {
                    addItemEntity(entities, dataset, row, column, bar);
                }
            } else if (pass == 2) {
                CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                        column);
                if (generator != null && isItemLabelVisible(row, column)) {
                    drawItemLabel(g2, dataset, row, column, plot, generator, bar,
                            (value < 0.0));
                }
            }
        } else {
            if (pass == 0) {
                // no shadows
            } else if (pass == 1) {
                getBarPainter().paintBar(g2, this, row, column, bar, barBase);
                // collect entity and tool tip information...
                if (state.getInfo() != null) {
                    EntityCollection entities = state.getEntityCollection();
                    if (entities != null) {
                        addItemEntity(entities, dataset, row, column, bar);
                    }
                }
            } else if (pass == 2) {
                CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                        column);
                if (generator != null && isItemLabelVisible(row, column)) {
                    drawItemLabel(g2, dataset, row, column, plot, generator, bar,
                            (value < 0.0));
                }
            }
        }
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
        if (!(obj instanceof StackedBarRenderer that)) {
            System.out.println("In instance");
            return false;
        }
        if (this.renderAsPercentages != that.renderAsPercentages) {
            System.out.println("In percentage");
            return false;
        }
        if (!Objects.equals(seriesToGroupMap, that.seriesToGroupMap)) {
            System.out.println("In Series map");
            return false;
        }
        return super.equals(obj);
    }

}
