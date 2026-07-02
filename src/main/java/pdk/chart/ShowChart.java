package pdk.chart;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;
import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Add display and save functions for the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 9:05 AM
 */
public interface ShowChart {

    ChartTheme DEFAULT_THEME = new StandardChartTheme("PDK");

    /**
     * Display the specified chart.
     *
     * @param chart {@link Chart} to show.
     */
    static void show(Chart chart, String frameTitle) {
        SwingUtilities.invokeLater(() -> {
            ApplicationFrame frame = new ApplicationFrame(frameTitle);
            ChartPanel panel = new ChartPanel(chart);

            // Used to prevent stretching distortion
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            panel.setMaximumDrawHeight((int) screenSize.getHeight());
            panel.setMaximumDrawWidth((int) screenSize.getWidth());

            panel.setInitialDelay(200);
            panel.setMouseWheelEnabled(true);
            frame.setContentPane(panel);
            frame.pack();
            UIUtils.centerFrameOnScreen(frame);
            frame.setVisible(true);
        });
    }


    /**
     * Display the specified chart.
     *
     * @param chart {@link Chart} to show.
     */
    static void show(Chart chart, String frameTitle, int width, int height) {
        SwingUtilities.invokeLater(() -> {
            ApplicationFrame frame = new ApplicationFrame(frameTitle);
            ChartPanel panel = new ChartPanel(chart);
            // Used to prevent stretching distortion
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            panel.setMaximumDrawHeight((int) screenSize.getHeight());
            panel.setMaximumDrawWidth((int) screenSize.getWidth());

            panel.setPreferredSize(new Dimension(width, height));
            panel.setInitialDelay(200);
            panel.setMouseWheelEnabled(true);
            frame.setContentPane(panel);
            frame.pack();
            UIUtils.centerFrameOnScreen(frame);
            frame.setVisible(true);
        });
    }

    /**
     * Save the chart in the specified format.
     *
     * @param chart  {@link Chart} to save.
     * @param file   target {@link Path} to save.
     * @param format {@link FileFormat}.
     * @param width  chart width.
     * @param height chart height.
     */
    static void save(Chart chart, Path file, FileFormat format, int width, int height) {
        if (format == FileFormat.SVG) {
            SVGGraphics2D g2 = new SVGGraphics2D(width, height);
            // we suppress shadow generation, because SVG is a vector format and
            // the shadow effect is applied via bitmap effects...
            g2.setRenderingHint(Chart.KEY_SUPPRESS_SHADOW_GENERATION, true);
            Rectangle2D drawArea = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, drawArea);
            String svgElement = g2.getSVGElement();
            try {
                SVGUtils.writeToSVG(file.toFile(), svgElement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (format == FileFormat.PNG) {
            try {
                JChartUtils.saveChartAsPNG(file.toFile(), chart, width, height);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (format == FileFormat.JPEG) {
            try {
                JChartUtils.saveChartAsJPEG(file.toFile(), chart, width, height);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (format == FileFormat.PDF) {
            PDFDocument doc = new PDFDocument();
            Rectangle2D rect = new Rectangle(width, height);
            Page page = doc.createPage(rect);
            PDFGraphics2D g2 = page.getGraphics2D();

            // we suppress shadow generation, because PDF is a vector format and
            // the shadow effect is applied via bitmap effects...
            g2.setRenderingHint(Chart.KEY_SUPPRESS_SHADOW_GENERATION, true);
            Rectangle2D drawArea = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, drawArea);
            doc.writeToFile(file.toFile());
        } else {
            throw new UnsupportedOperationException("Unsupported file format");
        }
    }
}
