package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.block.BlockBorder;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.LabelBlock;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class LegendWrapperDemo1 extends ApplicationFrame {
    public LegendWrapperDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static PieDataset createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("One", 43.2);
        dataset.setValue("Two", 10.0);
        dataset.setValue("Three", 27.5);
        dataset.setValue("Four", 17.5);
        dataset.setValue("Five", 11.0);
        dataset.setValue("Six", 19.4);
        return dataset;
    }

    private static Chart createChart(PieDataset dataset) {
        Chart chart = JChart.pie(dataset, "Legend Wrapper Demo 1", false, true);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        LegendTitle legend = new LegendTitle(chart.getPlot());
        BlockContainer wrapper = new BlockContainer(new BorderArrangement());
        wrapper.setFrame(new BlockBorder(1.0, 1.0, 1.0, 1.0));
        LabelBlock title = new LabelBlock("Legend Items:", new Font("SansSerif", Font.BOLD, 12));
        title.setPadding(5.0, 5.0, 5.0F, 5.0F);
        wrapper.add(title, RectangleEdge.TOP);
        LabelBlock subtitle = new LabelBlock("Source: http://www.jfree.org");
        subtitle.setPadding(8.0, 20.0, 2.0, 5.0);
        wrapper.add(subtitle, RectangleEdge.BOTTOM);
        BlockContainer items = legend.getItemContainer();
        items.setPadding(2.0, 10.0, 5.0, 2.0);
        wrapper.add(items);
        legend.setWrapper(wrapper);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.addSubtitle(legend);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        LegendWrapperDemo1 demo = new LegendWrapperDemo1("LegendWrapperDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
