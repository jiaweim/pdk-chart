package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockBorder;
import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.AxisType;
import pdk.chart.XYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYItemLabelGenerator;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A stacked bar chart using data from a TimeTableXYDataset.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 9:31 AM
 */
public class StackedXYBarChartDemo2 extends ApplicationFrame {
    public StackedXYBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset<String> createDataset() {
        Year[] years = new Year[]{
                new Year(1983), new Year(1984), new Year(1985), new Year(1986), new Year(1987),
                new Year(1988), new Year(1989), new Year(1990), new Year(1991), new Year(1992),
                new Year(1993), new Year(1994), new Year(1995), new Year(1996), new Year(1997),
                new Year(1998), new Year(1999), new Year(2000), new Year(2001), new Year(2002),
                new Year(2003)
        };

        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(years,
                new double[]{
                        0.0, 2.0, 1.0, 1.0, 2.0,
                        2.0, 1.0, 5.0, 5.0, 2.0,
                        4.0, 3.0, 2.0, 1.0, 2.0,
                        1.0, 4.0, 6.0, 5.0, 4.0,
                        2.0
                }, "Albatrosses");
        dataset.add(years,
                new double[]{
                        21.0, 24.0, 32.0, 20.0, 28.0,
                        17.0, 31.0, 32.0, 29.0, 31.0,
                        25.0, 44.0, 35.0, 40.0, 32.0,
                        32.0, 30.0, 29.0, 28.0, 39.0,
                        32.0
                }, "Aces");

        return dataset;
    }

    private static Chart createChart(TableXYDataset<String> dataset) {
        Chart chart = JChart.createXY(dataset, XYChartType.BAR_STACK, AxisType.DATE, AxisType.NUMBER,
                "Holes-In-One / Double Eagles", "Date", "Count",
                PlotOrientation.VERTICAL, true, true);
        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxisAsDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.1);
        plot.getBarRenderer()
                .drawBarOutline(false)
                .margin(0.15)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardXYItemLabelGenerator())
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER))
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2}",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("0")));

        chart.removeLegend();
        chart.addSubtitle(new TextTitle("PGA Tour, 1983 to 2003"));
        TextTitle source = new TextTitle("http://www.golfdigest.com/majors/masters/index.ssf?/majors/masters/gw20040402albatross.html", new Font("Dialog", 0, 8));
        chart.addSubtitle(source);
        chart.setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        LegendTitle legend = new LegendTitle(plot);
        legend.setBackgroundPaint(Color.WHITE);
        legend.setFrame(new BlockBorder());
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedXYBarChartDemo2 demo = new StackedXYBarChartDemo2("Stacked XY Bar Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
