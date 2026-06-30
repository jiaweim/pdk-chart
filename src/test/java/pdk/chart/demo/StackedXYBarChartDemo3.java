package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.AxisType;
import pdk.chart.XYChartType;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A stacked bar chart using data from a TimeTableXYDataset, and displaying the values as percentages.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 9:56 AM
 */
public class StackedXYBarChartDemo3 extends ApplicationFrame {
    public StackedXYBarChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset<String> createDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        Year[] years = new Year[]{new Year(2002), new Year(2003), new Year(2004),
                new Year(2005), new Year(2006)};
        dataset.add(years, new double[]{41287.0, 41096.0, 39603.0, 39693.0, 37227.0}, "Landfilled");
        dataset.add(years, new double[]{7717.0, 8317.0, 9493.0, 11228.0, 14941.0}, "Recycled");
        return dataset;
    }

    private static Chart createChart(TableXYDataset<String> dataset) {
        Chart chart = JChart.createXY(dataset, XYChartType.BAR_STACK,
                AxisType.DATE, AxisType.NUMBER, "Waste Management",
                "Year", "Tonnes",
                PlotOrientation.VERTICAL, true, true);
        chart.backgroundPaint(Color.WHITE);
        chart.addSubtitle(new TextTitle("St Albans City and District Council"));

        XYPlot plot = chart.getXYPlot();
        plot.backgroundPaint(Color.LIGHT_GRAY)
                .domainGridlinePaint(Color.WHITE)
                .rangeGridlinePaint(Color.WHITE);

        plot.getDomainAxisAsDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01);
        plot.getRangeAxisAsNumber()
                .numberFormatOverride(new DecimalFormat("0.0%"));
        plot.getBarRenderer()
                .margin(0.3)
                .renderAsPercentages(true)
                .drawBarOutline(false)
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2} tonnes",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("#,##0")))
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, new Color(64, 0, 0), 0.0F, 0.0F, Color.RED))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, new Color(0, 64, 0), 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedXYBarChartDemo3 demo = new StackedXYBarChartDemo3("StackedXYBarChartDemo3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
