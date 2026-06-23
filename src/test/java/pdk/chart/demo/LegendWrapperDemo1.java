package pdk.chart.demo;

import java.awt.Font;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
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

public class LegendWrapperDemo1 extends ApplicationFrame {
    public LegendWrapperDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static PieDataset createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("One", 43.2);
        dataset.setValue("Two", (double)10.0F);
        dataset.setValue("Three", (double)27.5F);
        dataset.setValue("Four", (double)17.5F);
        dataset.setValue("Five", (double)11.0F);
        dataset.setValue("Six", 19.4);
        return dataset;
    }

    private static Chart createChart(PieDataset dataset) {
        Chart chart = ChartFactory.createPieChart("Legend Wrapper Demo 1", dataset, false, true, false);
        PiePlot plot = (PiePlot)chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", 0, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        LegendTitle legend = new LegendTitle(chart.getPlot());
        BlockContainer wrapper = new BlockContainer(new BorderArrangement());
        wrapper.setFrame(new BlockBorder((double)1.0F, (double)1.0F, (double)1.0F, (double)1.0F));
        LabelBlock title = new LabelBlock("Legend Items:", new Font("SansSerif", 1, 12));
        title.setPadding((double)5.0F, (double)5.0F, (double)5.0F, (double)5.0F);
        wrapper.add(title, RectangleEdge.TOP);
        LabelBlock subtitle = new LabelBlock("Source: http://www.jfree.org");
        subtitle.setPadding((double)8.0F, (double)20.0F, (double)2.0F, (double)5.0F);
        wrapper.add(subtitle, RectangleEdge.BOTTOM);
        BlockContainer items = legend.getItemContainer();
        items.setPadding((double)2.0F, (double)10.0F, (double)5.0F, (double)2.0F);
        wrapper.add(items);
        legend.setWrapper(wrapper);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.addSubtitle(legend);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        LegendWrapperDemo1 demo = new LegendWrapperDemo1("Chart: LegendWrapperDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
