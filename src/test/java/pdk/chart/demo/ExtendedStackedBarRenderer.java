package pdk.chart.demo;

import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.entity.CategoryItemEntity;
import pdk.chart.entity.EntityCollection;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.CategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRendererState;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;

public class ExtendedStackedBarRenderer extends StackedBarRenderer {

    private boolean showPositiveTotal = true;
    private boolean showNegativeTotal = true;
    private Font totalLabelFont = new Font("SansSerif", 1, 12);
    private NumberFormat totalFormatter = NumberFormat.getInstance();

    public NumberFormat getTotalFormatter() {
        return this.totalFormatter;
    }

    public void setTotalFormatter(NumberFormat format) {
        if (format == null) {
            throw new IllegalArgumentException("Null format not permitted.");
        } else {
            this.totalFormatter = format;
        }
    }

    public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea,
            CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
        Number dataValue = dataset.getValue(row, column);
        if (dataValue != null) {
            double value = dataValue.doubleValue();
            PlotOrientation orientation = plot.getOrientation();
            double barW0 = domainAxis.getCategoryMiddle(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / (double)2.0F;
            double positiveBase = (double)0.0F;
            double negativeBase = (double)0.0F;

            for(int i = 0; i < row; ++i) {
                Number v = dataset.getValue(i, column);
                if (v != null) {
                    double d = v.doubleValue();
                    if (d > (double)0.0F) {
                        positiveBase += d;
                    } else {
                        negativeBase += d;
                    }
                }
            }

            RectangleEdge location = plot.getRangeAxisEdge();
            double translatedBase;
            double translatedValue;
            if (value > (double)0.0F) {
                translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, location);
                translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, location);
            } else {
                translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, location);
                translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, location);
            }

            double barL0 = Math.min(translatedBase, translatedValue);
            double barLength = Math.max(Math.abs(translatedValue - translatedBase), this.getMinimumBarLength());
            Rectangle2D bar = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
            } else {
                bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
            }

            Paint seriesPaint = this.getItemPaint(row, column);
            g2.setPaint(seriesPaint);
            g2.fill(bar);
            if (this.isDrawBarOutline() && state.getBarWidth() > (double)3.0F) {
                g2.setStroke(this.getItemStroke(row, column));
                g2.setPaint(this.getItemOutlinePaint(row, column));
                g2.draw(bar);
            }

            CategoryItemLabelGenerator generator = this.getItemLabelGenerator(row, column);
            if (generator != null && this.isItemLabelVisible(row, column)) {
                this.drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < (double)0.0F);
            }

            if (value > (double)0.0F) {
                if (this.showPositiveTotal && this.isLastPositiveItem(dataset, row, column)) {
                    g2.setPaint(Color.black);
                    g2.setFont(this.totalLabelFont);
                    double total = this.calculateSumOfPositiveValuesForCategory(dataset, column);
                    float labelX = (float)bar.getCenterX();
                    float labelY = (float)bar.getMinY() - 4.0F;
                    TextAnchor labelAnchor = TextAnchor.BOTTOM_CENTER;
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        labelX = (float)bar.getMaxX() + 4.0F;
                        labelY = (float)bar.getCenterY();
                        labelAnchor = TextAnchor.CENTER_LEFT;
                    }

                    TextUtils.drawRotatedString(this.totalFormatter.format(total), g2, labelX, labelY, labelAnchor, (double)0.0F, TextAnchor.CENTER);
                }
            } else if (this.showNegativeTotal && this.isLastNegativeItem(dataset, row, column)) {
                g2.setPaint(Color.black);
                g2.setFont(this.totalLabelFont);
                double total = this.calculateSumOfNegativeValuesForCategory(dataset, column);
                float labelX = (float)bar.getCenterX();
                float labelY = (float)bar.getMaxY() + 4.0F;
                TextAnchor labelAnchor = TextAnchor.TOP_CENTER;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    labelX = (float)bar.getMinX() - 4.0F;
                    labelY = (float)bar.getCenterY();
                    labelAnchor = TextAnchor.CENTER_RIGHT;
                }

                TextUtils.drawRotatedString(this.totalFormatter.format(total), g2, labelX, labelY, labelAnchor, (double)0.0F, TextAnchor.CENTER);
            }

            if (state.getInfo() != null) {
                EntityCollection entities = state.getEntityCollection();
                if (entities != null) {
                    String tip = null;
                    CategoryToolTipGenerator tipster = this.getToolTipGenerator(row, column);
                    if (tipster != null) {
                        tip = tipster.generateToolTip(dataset, row, column);
                    }

                    String url = null;
                    if (this.getItemURLGenerator(row, column) != null) {
                        url = this.getItemURLGenerator(row, column).generateURL(dataset, row, column);
                    }

                    CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
                    entities.add(entity);
                }
            }

        }
    }

    private boolean isLastPositiveItem(CategoryDataset dataset, int row, int column) {
        boolean result = true;
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return false;
        } else {
            for(int r = row + 1; r < dataset.getRowCount(); ++r) {
                dataValue = dataset.getValue(r, column);
                if (dataValue != null) {
                    result = result && dataValue.doubleValue() <= (double)0.0F;
                }
            }

            return result;
        }
    }

    private boolean isLastNegativeItem(CategoryDataset dataset, int row, int column) {
        boolean result = true;
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return false;
        } else {
            for(int r = row + 1; r < dataset.getRowCount(); ++r) {
                dataValue = dataset.getValue(r, column);
                if (dataValue != null) {
                    result = result && dataValue.doubleValue() >= (double)0.0F;
                }
            }

            return result;
        }
    }

    private double calculateSumOfPositiveValuesForCategory(CategoryDataset dataset, int column) {
        double result = (double)0.0F;

        for(int r = 0; r < dataset.getRowCount(); ++r) {
            Number dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                double v = dataValue.doubleValue();
                if (v > (double)0.0F) {
                    result += v;
                }
            }
        }

        return result;
    }

    private double calculateSumOfNegativeValuesForCategory(CategoryDataset dataset, int column) {
        double result = (double)0.0F;

        for(int r = 0; r < dataset.getRowCount(); ++r) {
            Number dataValue = dataset.getValue(r, column);
            if (dataValue != null) {
                double v = dataValue.doubleValue();
                if (v < (double)0.0F) {
                    result += v;
                }
            }
        }

        return result;
    }
}
