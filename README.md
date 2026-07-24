Proteomics chart library
==========

Overview
--------
pdk-chart is a chart library modified from JFreeChart. It adds features related to proteomics data visualization and fluent-style APIs.

pdk-chart requires JDK 25 or later.

For Developers
--------------

### Using pdk-chart
To use pdk-chart in your projects, add the following dependency to your build tool:

```xml
<dependency>
    <groupId>io.github.jiaweim</groupId>
    <artifactId>pdk-chart</artifactId>
    <version>2.10.0</version>
</dependency>
```

## Chart Types

### Scatter

```java
XYChart chart = JChart.scatter(
                new double[]{0, 1, 2, 3, 4},
                new double[]{0, 1, 4, 9, 16})
        .axisNames("x", "y");
chart.show();
```

### Line

- Basic Line Chart

#### Category Line

The `pdk.chart.renderer.category.LineAndShapeRenderer` class is used to render category line charts.

##### Line Properties

##### Shape Properties

```java
public void setSeriesShapesVisible(int series, boolean visible)
```

Set whether to draw shapes for every data point of the designated series.

```java
public void setDefaultShapesVisible(boolean flag)
```

Use this property value to control shape visibility for series that do not have an explicit `seriesShapeVisible` configuration.

```java
public void setDrawOutlines(boolean flag)
```

Whether to render the outline stroke of data point shapes.

```java
public void setUseFillPaint(boolean flag)
```

Whether to apply an independent fill paint. When undefined, the fill color defaults to be the series paint.

```java
public void setDefaultFillPaint(Paint paint);
```

Default fill color of the shape.

```java
public void setDefaultShape(Shape shape);
```

Set the default shape. The default shape will only take effect when no series shape is specified and AutoPopulateSeriesShape is disabled.







### Bar







## Chart

### Title

`Chart` support setting one main title and multiple subtitles.

The class diagram of `Title` is shown below.

<img src="./images/image-20260625114037180.png" alt="image-20260625114037180" width="600" />

The main title is of the `TextTitle` type, while subtitles can be any subclass of `Title`. The chart's legend is also implemented as a subtitle.

#### Main title

Methods provided by `Chart` for setting main title:

```java
public void setTitle(TextTitle title);
public void setTitle(String text); // Convenient methods for the previous
```

#### Legend

-  Custom Legend

```java
LegendItemCollection items = new LegendItemCollection();
items.add(new LegendItem("Against all torture", null, null, null,
        new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.GREEN));
items.add(new LegendItem("Some degree permissible", null, null, null,
        new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.RED));
plot.setFixedLegendItems(items);
```



## Plot

### Gridline

Grid lines fall into two types: 

- domain gridlines
- range gridlines

Domain grid lines run perpendicular to the domain axis, while range grid lines run perpendicular to the range axis.

Grid lines have three properties: 

- `visible`, whether to display grid lines
- `stroke`, `Stroke` used for rendering grid lines
- `paint`, `Paint` used to rendering grid lines

Methods provided by `XYPlot` for configuring grid lines:

```java
public void setDomainGridlinesVisible(boolean visible);
public void setDomainMinorGridlinesVisible(boolean visible);

public void setDomainGridlineStroke(Stroke stroke);
public void setDomainMinorGridlineStroke(Stroke stroke);

public void setDomainGridlinePaint(Paint paint);
public void setDomainMinorGridlinePaint(Paint paint);
```

The same methods are available for range grid line; simply replace "Domain" with "Range".

### Crosshair

## Axis

- Remove axis titles

```java
CategoryAxis domainAxis = plot.getDomainAxis();
domainAxis.setLabel(null);
```

