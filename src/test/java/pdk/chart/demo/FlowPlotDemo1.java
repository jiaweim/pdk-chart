package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.data.flow.DefaultFlowDataset;
import pdk.chart.data.flow.FlowDataset;
import pdk.chart.plot.flow.FlowPlot;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

/**
 * This demo illustrates how to plot flows (represented in a FlowDataset).
 * <p>
 * The FlowPlot is a type of Sankey plot.
 * <p>
 * The data here is sourced from https://www.data-to-viz.com/graph/sankey.html.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:34 AM
 */
public class FlowPlotDemo1 extends JFrame {

    public FlowPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(760, 500));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        FlowDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    private static FlowDataset<String> createDataset() {
        DefaultFlowDataset<String> dataset = new DefaultFlowDataset<>();
        dataset.setFlow(0, "Africa", "Africa", 3.142471);
        dataset.setFlow(0, "Africa", "Europe", 2.107883);
        dataset.setFlow(0, "Africa", "North America", 0.540887);
        dataset.setFlow(0, "Africa", "West Asia", 0.673004);
        dataset.setFlow(0, "East Asia", "East Asia", 1.630997);
        dataset.setFlow(0, "East Asia", "Europe", 0.601265);
        dataset.setFlow(0, "East Asia", "North America", 0.97306);
        dataset.setFlow(0, "East Asia", "Oceania", 0.333608);
        dataset.setFlow(0, "East Asia", "South East Asia", 0.380388);
        dataset.setFlow(0, "East Asia", "West Asia", 0.869311);
        dataset.setFlow(0, "Europe", "Europe", 2.401476);
        dataset.setFlow(0, "Latin America", "Europe", 1.762587);
        dataset.setFlow(0, "Latin America", "Latin America", 0.879198);
        dataset.setFlow(0, "Latin America", "North America", 3.627847);
        dataset.setFlow(0, "North America", "Europe", 1.215929);
        dataset.setFlow(0, "North America", "North America", 0.276908);
        dataset.setFlow(0, "Oceania", "Europe", 0.17037);
        dataset.setFlow(0, "Oceania", "Oceania", 0.190706);
        dataset.setFlow(0, "South East Asia", "East Asia", 0.525881);
        dataset.setFlow(0, "South Asia", "Europe", 1.390272);
        dataset.setFlow(0, "South Asia", "North America", 1.508008);
        dataset.setFlow(0, "South Asia", "Oceania", 0.34742);
        dataset.setFlow(0, "South Asia", "South Asia", 1.307907);
        dataset.setFlow(0, "South Asia", "West Asia", 4.902081);
        dataset.setFlow(0, "South East Asia", "East Asia", 0.145264);
        dataset.setFlow(0, "South East Asia", "Europe", 0.468762);
        dataset.setFlow(0, "South East Asia", "North America", 1.057904);
        dataset.setFlow(0, "South East Asia", "Oceania", 0.278746);
        dataset.setFlow(0, "South East Asia", "South East Asia", 0.781316);
        dataset.setFlow(0, "Soviet Union", "Europe", 0.60923);
        dataset.setFlow(0, "Soviet Union", "Soviet Union", 1.870501);
        dataset.setFlow(0, "West Asia", "Europe", 0.449623);
        dataset.setFlow(0, "West Asia", "North America", 0.169274);
        dataset.setFlow(0, "West Asia", "West Asia", 0.927243);
        return dataset;
    }

    private static Chart createChart(FlowDataset dataset) {
        FlowPlot plot = new FlowPlot(dataset);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDefaultNodeLabelPaint(Color.WHITE);
        plot.setNodeColorSwatch(FlowColors.getSAPMultiColor());
        Chart chart = new Chart("Migration Patterns", plot);
        chart.addSubtitle(new TextTitle("Source: https://www.data-to-viz.com/graph/sankey.html"));
        return chart;
    }

    static void main() {
        FlowPlotDemo1 demo = new FlowPlotDemo1("JFreeChart: FlowPlotDemo1.java");
        demo.setDefaultCloseOperation(3);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
