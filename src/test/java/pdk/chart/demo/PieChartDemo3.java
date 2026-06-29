package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class PieChartDemo3 extends ApplicationFrame {
    public PieChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(PieDataset dataset) {
        Chart chart = JChart.createPieChart("Pie Chart Demo 3", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available so we go into this really long spiel about what that means and it runs off the end of the line but what can you do about that!");
        plot.setNoDataMessageFont(new Font("Serif", Font.ITALIC, 10));
        plot.setNoDataMessagePaint(Color.RED);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new DefaultPieDataset());
        return new ChartPanel(chart);
    }

    public static void main() {
        PieChartDemo3 demo = new PieChartDemo3("Pie Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
