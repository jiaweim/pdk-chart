package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.RingChart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.plot.CenterTextMode;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * This demo shows a simple ring chart with text displayed in the middle of the ring. The mouse wheel can be used to rotate the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 9:41 AM
 */
public class RingChartDemo2 extends ApplicationFrame {
    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public RingChartDemo2(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    private static PieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("A", 0.653);
        dataset.setValue("B", 0.347);
        return dataset;
    }

    private static Chart createChart(PieDataset<String> dataset) {
        RingChart chart = new RingChart(dataset, "Machine Capacity", true, true, false);
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));
        chart.setCenterTextMode(CenterTextMode.VALUE);
        chart.setCenterTextColor(Color.LIGHT_GRAY);
        chart.setCenterTextFont(new Font("SansSerif", Font.BOLD, 24));
        chart.setCenterTextFormatter(new DecimalFormat("0.0%"));
        chart.setPlotBackgroundPaint(null);
        chart.setPlotOutlineVisible(false);
        chart.setLabelGenerator(null);
        chart.setSectionPaint("A", Color.ORANGE);
        chart.setSectionPaint("B", new Color(100, 100, 100));
        chart.setSectionDepth(0.05);
        chart.setSectionOutlinesVisible(false);

        chart.setShadowPaint(null);

        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        return chart;
    }


    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4.0, 8.0, 2.0, 2.0));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    static void main() {
        RingChartDemo2 demo2 = new RingChartDemo2("Ring Chart Demo 2");
        demo2.pack();
        UIUtils.centerFrameOnScreen(demo2);
        demo2.setVisible(true);
    }
}
