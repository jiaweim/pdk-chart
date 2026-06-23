package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StackedXYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

public class StackedXYBarChartDemo3 extends ApplicationFrame {
    public StackedXYBarChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static TableXYDataset createDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(new Year(2002), (double)41287.0F, "Landfilled");
        dataset.add(new Year(2003), (double)41096.0F, "Landfilled");
        dataset.add(new Year(2004), (double)39603.0F, "Landfilled");
        dataset.add(new Year(2005), (double)39693.0F, "Landfilled");
        dataset.add(new Year(2006), (double)37227.0F, "Landfilled");
        dataset.add(new Year(2002), (double)7717.0F, "Recycled");
        dataset.add(new Year(2003), (double)8317.0F, "Recycled");
        dataset.add(new Year(2004), (double)9493.0F, "Recycled");
        dataset.add(new Year(2005), (double)11228.0F, "Recycled");
        dataset.add(new Year(2006), (double)14941.0F, "Recycled");
        return dataset;
    }

    private static Chart createChart(TableXYDataset dataset) {
        DateAxis domainAxis = new DateAxis("Year");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        NumberAxis rangeAxis = new NumberAxis("Tonnes");
        rangeAxis.setNumberFormatOverride(new DecimalFormat("0.0%"));
        StackedXYBarRenderer renderer = new StackedXYBarRenderer(0.3);
        renderer.setRenderAsPercentages(true);
        renderer.setDrawBarOutline(false);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2} tonnes", new SimpleDateFormat("yyyy"), new DecimalFormat("#,##0")));
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        Chart chart = new Chart("Waste Management", plot);
        chart.setBackgroundPaint(Color.WHITE);
        chart.addSubtitle(new TextTitle("St Albans City and District Council"));
        ChartUtils.applyCurrentTheme(chart);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, new Color(64, 0, 0), 0.0F, 0.0F, Color.RED);
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, new Color(0, 64, 0), 0.0F, 0.0F, Color.GREEN);
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedXYBarChartDemo3 demo = new StackedXYBarChartDemo3("Chart: StackedXYBarChartDemo3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
