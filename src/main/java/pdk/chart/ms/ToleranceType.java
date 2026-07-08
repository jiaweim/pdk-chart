package pdk.chart.ms;

/**
 * Tolerance.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jul 2026, 12:35 PM
 */
public class ToleranceType {

    public static ToleranceType ppm(double value) {
        return new ToleranceType(value, "ppm");
    }

    public static ToleranceType da(double value) {
        return new ToleranceType(value, "Da");
    }

    private final double value;
    private final String unit;

    public ToleranceType(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
