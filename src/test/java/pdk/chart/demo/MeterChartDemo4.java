package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartRenderingInfo;
import pdk.chart.JChartUtils;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.plot.MeterPlot;

import java.awt.image.BufferedImage;
import java.io.*;

public class MeterChartDemo4 {
    public static void main(String[] args) {
        ValueDataset dataset = new DefaultValueDataset((double) 75.0F);
        MeterPlot plot = new MeterPlot(dataset);
        Chart chart = new Chart("Scaled Image Test", plot);
        JChartUtils.applyCurrentTheme(chart);

        try {
            File file1 = new File("meterchart100.png");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            BufferedImage image = chart.createBufferedImage(200, 200, (double) 400.0F, (double) 400.0F, (ChartRenderingInfo) null);
            JChartUtils.writeBufferedImageAsPNG(out, image);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
