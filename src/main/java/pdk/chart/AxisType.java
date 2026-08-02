package pdk.chart;

import pdk.chart.axis.*;

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

    DATE,
    /**
     * 基于规则时间周期生成日期刻度的坐标轴，仅支持水平放置于图表顶部/底部，垂直左右放置会显示异常。
     */
    PERIOD;

    public ValueAxis createInstance(String label) {
        return switch (this) {
            case NUMBER -> new NumberAxis(label);
            case SYMBOL -> new SymbolAxis(label, new String[0]);
            case DATE -> new DateAxis(label);
            case PERIOD -> new PeriodAxis(label);
        };
    }

    public ValueAxis createInstance() {
        return createInstance(null);
    }


}
