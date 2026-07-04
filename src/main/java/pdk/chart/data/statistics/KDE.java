package pdk.chart.data.statistics;

import pdk.chart.data.function.Function2D;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.util.Args;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Jul 2026, 2:12 PM
 */
public class KDE {

    private Function2D kernel;
    private final double bandWidth;

    public KDE(double bandWidth) {
        Args.requireNonNegative(bandWidth, "bandWidth");
        this.bandWidth = bandWidth;
        this.kernel = new NormalDistributionFunction2D(0, bandWidth);
    }

    public void setKernel(Function2D kernel) {
        this.kernel = kernel;
    }

    /**
     * Estimate bandwidth using Silverman's rule of thumb.
     * <p>
     * h=1.06×σ×n^{-1/5}
     *
     * @param data data
     * @return bandwidth
     */
    public static double silverman(double[] data) {
        int n = data.length;
        double sd = Statistics.standardDeviation(data);
        return 1.06 * sd * Math.pow(n, -0.2);
    }
}
