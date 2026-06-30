package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.axis.ValueAxis;

/**
 * Y-axis type.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 10:58 AM
 */
public enum AxisType {
    /**
     * Classic numerical axis.
     */
    NUMBER,
    /**
     * A standard linear value axis that replaces integer values with symbols.
     */
    SYMBOL,

    DATE;

    public ValueAxis createInstance() {
        return switch (this) {
            case NUMBER -> new NumberAxis();
            case SYMBOL -> new SymbolAxis(null, new String[0]);
            case DATE -> new DateAxis();
        };
    }
}
