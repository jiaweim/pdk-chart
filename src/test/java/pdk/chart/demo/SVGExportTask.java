package pdk.chart.demo;

import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SVGExportTask implements Runnable {
    Chart chart;
    int width;
    int height;
    File file;

    public SVGExportTask(Chart chart, int width, int height, File file) {
        this.chart = chart;
        this.file = file;
        this.width = width;
        this.height = height;
        chart.setBorderVisible(true);
        chart.setPadding(new RectangleInsets((double) 2.0F, (double) 2.0F, (double) 2.0F, (double) 2.0F));
    }

    public void run() {
        try {
            SVGGraphics2D g2 = new SVGGraphics2D(this.width, this.height);
            this.chart.draw(g2, new Rectangle(this.width, this.height));
            SVGUtils.writeToHTML(this.file, "title", g2.getSVGElement());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
