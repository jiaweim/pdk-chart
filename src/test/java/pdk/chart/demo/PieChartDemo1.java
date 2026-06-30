package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.util.ExportUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * This demo shows a simple pie chart created from a <code>DefaultPieDataset</code>.
 * The chart can be rotated using the mouse wheel.
 * 2025-06-17⭐
 */
public class PieChartDemo1 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title the frame title.
     */
    public PieChartDemo1(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static PieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("NAFTA", 105.6);
        dataset.setValue("Europe", 37.8);
        dataset.setValue("Far East", 59.5);
        dataset.setValue("Other", 13.6);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return a chart.
     */
    private static Chart createChart(PieDataset<String> dataset) {
        Chart chart = JChart.pie("Cognac Exports 2019", dataset);
        PiePlot plot = chart.getPiePlot();
        plot.setBackgroundPaint(new Color(169, 191, 191));

        plot.sectionPaint("NAFTA", new Color(175, 115, 75))
                .sectionPaint("Europe", new Color(71, 70, 76))
                .sectionPaint("Far East", new Color(161, 152, 94))
                .sectionPaint("Other", new Color(241, 208, 158))
                .labelGenerator(new StandardPieSectionLabelGenerator<>("{0} = {1} ({2})",
                        new DecimalFormat("0.0"), new DecimalFormat("0.0%")));

        chart.addSubtitle(new TextTitle("Millions of Bottles"));
        chart.addSubtitle(new TextTitle("https://www.cognac.fr/en/press/cognac-shipments-and-harvest-2019/",
                new Font("Courier New", Font.PLAIN, 10)));
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4.0, 8.0, 2.0, 2.0));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        ExportUtils.writeAsSVG(chart, 600, 400, new File("D:\\a.svg"));
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     */
    static void main() {
        PieChartDemo1 demo = new PieChartDemo1("Pie Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
