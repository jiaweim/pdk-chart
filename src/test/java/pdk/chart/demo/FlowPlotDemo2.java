package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.entity.ChartEntity;
import pdk.chart.entity.NodeEntity;
import pdk.chart.plot.flow.FlowPlot;
import pdk.chart.swing.ChartMouseEvent;
import pdk.chart.swing.ChartMouseListener;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.flow.DefaultFlowDataset;
import pdk.chart.data.flow.FlowDataset;
import pdk.chart.data.flow.FlowDatasetUtils;
import pdk.chart.data.flow.FlowKey;
import pdk.chart.data.flow.NodeKey;

public class FlowPlotDemo2 extends JFrame {
    private DefaultFlowDataset<String> dataset;

    public FlowPlotDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(760, 500));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        DefaultFlowDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart, false);
        panel.addChartMouseListener(new MyChartMouseListener(dataset));
        return panel;
    }

    private static DefaultFlowDataset<String> createDataset() {
        DefaultFlowDataset<String> dataset = new DefaultFlowDataset();
        dataset.setFlow(0, "Goods", "Australia", (double)2101.0F);
        dataset.setFlow(0, "Services", "Australia", (double)714.0F);
        dataset.setFlow(0, "Goods", "China", (double)3397.0F);
        dataset.setFlow(0, "Services", "China", (double)391.0F);
        dataset.setFlow(0, "Goods", "USA", (double)1748.0F);
        dataset.setFlow(0, "Services", "USA", (double)583.0F);
        dataset.setFlow(0, "Goods", "United Kingdom", (double)363.0F);
        dataset.setFlow(0, "Services", "United Kingdom", (double)178.0F);
        dataset.setFlow(1, "Australia", "Dairy", (double)165.0F);
        dataset.setFlow(1, "Australia", "Travel", (double)198.0F);
        dataset.setFlow(1, "Australia", "Beverages", (double)170.0F);
        dataset.setFlow(1, "Australia", "Other Goods", (double)2282.0F);
        dataset.setFlow(1, "China", "Dairy", (double)848.0F);
        dataset.setFlow(1, "China", "Meat", (double)463.0F);
        dataset.setFlow(1, "China", "Travel", (double)343.0F);
        dataset.setFlow(1, "China", "Fruit & Nuts", (double)296.0F);
        dataset.setFlow(1, "China", "Wood", (double)706.0F);
        dataset.setFlow(1, "China", "Other Goods", (double)1132.0F);
        dataset.setFlow(1, "United Kingdom", "Dairy", (double)18.0F);
        dataset.setFlow(1, "United Kingdom", "Meat", (double)71.0F);
        dataset.setFlow(1, "United Kingdom", "Travel", (double)59.0F);
        dataset.setFlow(1, "United Kingdom", "Fruit & Nuts", (double)13.0F);
        dataset.setFlow(1, "United Kingdom", "Beverages", (double)154.0F);
        dataset.setFlow(1, "United Kingdom", "Other Goods", (double)226.0F);
        dataset.setFlow(1, "USA", "Dairy", (double)95.0F);
        dataset.setFlow(1, "USA", "Meat", (double)367.0F);
        dataset.setFlow(1, "USA", "Travel", (double)90.0F);
        dataset.setFlow(1, "USA", "Wood", (double)83.0F);
        dataset.setFlow(1, "USA", "Beverages", (double)157.0F);
        dataset.setFlow(1, "USA", "Other Goods", (double)1539.0F);
        return dataset;
    }

    private static Chart createChart(FlowDataset dataset) {
        FlowPlot plot = new FlowPlot(dataset);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDefaultNodeLabelPaint(Color.WHITE);
        plot.setNodeColorSwatch(FlowColors.createPastelColors());
        Chart chart = new Chart("Selected NZ Exports Sept 2020", plot);
        chart.addSubtitle(new TextTitle("Source: https://statisticsnz.shinyapps.io/trade_dashboard/"));
        return chart;
    }

    public static void main(String[] args) {
        FlowPlotDemo2 demo = new FlowPlotDemo2("Chart: FlowPlotDemo2.java");
        demo.setDefaultCloseOperation(3);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyChartMouseListener implements ChartMouseListener {
        private DefaultFlowDataset<String> dataset;

        public MyChartMouseListener(DefaultFlowDataset dataset) {
            this.dataset = dataset;
        }

        public void chartMouseClicked(ChartMouseEvent event) {
            ChartEntity entity = event.getEntity();
            if (entity instanceof NodeEntity) {
                NodeEntity nodeEntity = (NodeEntity)entity;
                NodeKey<String> clickeNodeKey = nodeEntity.getKey();
                boolean selected = Boolean.TRUE.equals(this.dataset.getNodeProperty(clickeNodeKey, "selected"));
                if (selected) {
                    if (FlowDatasetUtils.selectedNodeCount(this.dataset) <= 1) {
                        for(NodeKey<String> nodeKey : this.dataset.getAllNodes()) {
                            this.dataset.setNodeProperty(nodeKey, "selected", true);
                        }
                    } else {
                        for(NodeKey<String> nodeKey : this.dataset.getAllNodes()) {
                            this.dataset.setNodeProperty(nodeKey, "selected", false);
                        }

                        this.dataset.setNodeProperty(clickeNodeKey, "selected", true);
                    }
                } else {
                    for(NodeKey<String> nodeKey : this.dataset.getAllNodes()) {
                        this.dataset.setNodeProperty(nodeKey, "selected", false);
                    }

                    this.dataset.setNodeProperty(clickeNodeKey, "selected", true);
                }

                for(FlowKey<String> flowKey : this.dataset.getAllFlows()) {
                    this.dataset.setFlowProperty(flowKey, "selected", this.isSelected(flowKey, this.dataset));
                }
            }

        }

        public void chartMouseMoved(ChartMouseEvent event) {
        }

        private boolean isSelected(FlowKey<String> flowKey, FlowDataset<String> dataset) {
            NodeKey<String> sourceKey = new NodeKey(flowKey.getStage(), (String)flowKey.getSource());
            if (Boolean.TRUE.equals(dataset.getNodeProperty(sourceKey, "selected"))) {
                return true;
            } else {
                NodeKey<String> destinationKey = new NodeKey(flowKey.getStage() + 1, (String)flowKey.getDestination());
                return Boolean.TRUE.equals(dataset.getNodeProperty(destinationKey, "selected"));
            }
        }
    }
}
