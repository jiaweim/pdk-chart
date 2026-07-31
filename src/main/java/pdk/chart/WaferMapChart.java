package pdk.chart;

import pdk.chart.plot.Plot;
import pdk.chart.plot.WaferMapPlot;

import java.awt.*;

/**
 * WaferMapChart was designed for yield analysis of semiconductor wafers,
 * it essentially functions as a circular grid heatmap and can also
 * be used to visualize general two-dimensional matrix data.
 * The final rendering effect resembles:
 * <pre>
 *           ○ ○ ○ ○ ○
 *         ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *       ○ ○ ○ ○ ○ ○ ○ ○ ○
 *         ○ ○ ○ ○ ○ ○ ○
 *            ○ ○ ○ ○ ○
 * </pre>
 * <p>
 * Each die cell is filled with a distinct color mapped from its corresponding numeric value.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 12:28 PM
 */
public class WaferMapChart extends Chart {

    public WaferMapChart() {
        super(null, DEFAULT_TITLE_FONT, new WaferMapPlot(), false);
    }


}
