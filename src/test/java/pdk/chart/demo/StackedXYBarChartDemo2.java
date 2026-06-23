package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockBorder;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYItemLabelGenerator;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;

public class StackedXYBarChartDemo2 extends ApplicationFrame {
    public StackedXYBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset createDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(new Year(1983), (double)0.0F, "Albatrosses");
        dataset.add(new Year(1984), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1985), (double)1.0F, "Albatrosses");
        dataset.add(new Year(1986), (double)1.0F, "Albatrosses");
        dataset.add(new Year(1987), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1988), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1989), (double)1.0F, "Albatrosses");
        dataset.add(new Year(1990), (double)5.0F, "Albatrosses");
        dataset.add(new Year(1991), (double)5.0F, "Albatrosses");
        dataset.add(new Year(1992), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1993), (double)4.0F, "Albatrosses");
        dataset.add(new Year(1994), (double)3.0F, "Albatrosses");
        dataset.add(new Year(1995), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1996), (double)1.0F, "Albatrosses");
        dataset.add(new Year(1997), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1998), (double)1.0F, "Albatrosses");
        dataset.add(new Year(1999), (double)4.0F, "Albatrosses");
        dataset.add(new Year(2000), (double)6.0F, "Albatrosses");
        dataset.add(new Year(2001), (double)5.0F, "Albatrosses");
        dataset.add(new Year(2002), (double)4.0F, "Albatrosses");
        dataset.add(new Year(2003), (double)2.0F, "Albatrosses");
        dataset.add(new Year(1983), (double)21.0F, "Aces");
        dataset.add(new Year(1984), (double)24.0F, "Aces");
        dataset.add(new Year(1985), (double)32.0F, "Aces");
        dataset.add(new Year(1986), (double)20.0F, "Aces");
        dataset.add(new Year(1987), (double)28.0F, "Aces");
        dataset.add(new Year(1988), (double)17.0F, "Aces");
        dataset.add(new Year(1989), (double)31.0F, "Aces");
        dataset.add(new Year(1990), (double)32.0F, "Aces");
        dataset.add(new Year(1991), (double)29.0F, "Aces");
        dataset.add(new Year(1992), (double)31.0F, "Aces");
        dataset.add(new Year(1993), (double)25.0F, "Aces");
        dataset.add(new Year(1994), (double)44.0F, "Aces");
        dataset.add(new Year(1995), (double)35.0F, "Aces");
        dataset.add(new Year(1996), (double)40.0F, "Aces");
        dataset.add(new Year(1997), (double)32.0F, "Aces");
        dataset.add(new Year(1998), (double)32.0F, "Aces");
        dataset.add(new Year(1999), (double)30.0F, "Aces");
        dataset.add(new Year(2000), (double)29.0F, "Aces");
        dataset.add(new Year(2001), (double)28.0F, "Aces");
        dataset.add(new Year(2002), (double)39.0F, "Aces");
        dataset.add(new Year(2003), (double)32.0F, "Aces");
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        NumberAxis rangeAxis = new NumberAxis("Count");
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.1);
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.15);
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0")));
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        Chart chart = new Chart("Holes-In-One / Double Eagles", plot);
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

    public static void main(String[] args) {
        StackedXYBarChartDemo2 demo = new StackedXYBarChartDemo2("Chart: Stacked XY Bar Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
