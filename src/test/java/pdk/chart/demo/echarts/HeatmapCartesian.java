package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.labels.XYZToolTipGenerator;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.GradientPaintScale;
import pdk.chart.renderer.xy.XYBlockRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=heatmap-cartesian
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jul 2026, 8:33 AM
 */
public class HeatmapCartesian extends ApplicationFrame {
    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public HeatmapCartesian(String title) {
        super(title);
    }

    // 与 ECharts 示例完全一致的数据
    private static final String[] hours = {
            "12a", "1a", "2a", "3a", "4a", "5a", "6a",
            "7a", "8a", "9a", "10a", "11a",
            "12p", "1p", "2p", "3p", "4p", "5p",
            "6p", "7p", "8p", "9p", "10p", "11p"
    };

    private static final String[] days = {
            "Saturday", "Friday", "Thursday",
            "Wednesday", "Tuesday", "Monday", "Sunday"
    };

    // 原始数据：[dayIndex, hourIndex, value] → 转换后：[hourIndex, dayIndex, value]
    private static final int[][] rawData = {
            {0, 0, 5}, {0, 1, 1}, {0, 2, 0}, {0, 3, 0}, {0, 4, 0}, {0, 5, 0},
            {0, 6, 0}, {0, 7, 0}, {0, 8, 0}, {0, 9, 0}, {0, 10, 0}, {0, 11, 2},
            {0, 12, 4}, {0, 13, 1}, {0, 14, 1}, {0, 15, 3}, {0, 16, 4}, {0, 17, 6},
            {0, 18, 4}, {0, 19, 4}, {0, 20, 3}, {0, 21, 3}, {0, 22, 2}, {0, 23, 5},
            {1, 0, 7}, {1, 1, 0}, {1, 2, 0}, {1, 3, 0}, {1, 4, 0}, {1, 5, 0},
            {1, 6, 0}, {1, 7, 0}, {1, 8, 0}, {1, 9, 0}, {1, 10, 5}, {1, 11, 2},
            {1, 12, 2}, {1, 13, 6}, {1, 14, 9}, {1, 15, 11}, {1, 16, 6}, {1, 17, 7},
            {1, 18, 8}, {1, 19, 12}, {1, 20, 5}, {1, 21, 5}, {1, 22, 7}, {1, 23, 2},
            {2, 0, 1}, {2, 1, 1}, {2, 2, 0}, {2, 3, 0}, {2, 4, 0}, {2, 5, 0},
            {2, 6, 0}, {2, 7, 0}, {2, 8, 0}, {2, 9, 0}, {2, 10, 3}, {2, 11, 2},
            {2, 12, 1}, {2, 13, 9}, {2, 14, 8}, {2, 15, 10}, {2, 16, 6}, {2, 17, 5},
            {2, 18, 5}, {2, 19, 5}, {2, 20, 7}, {2, 21, 4}, {2, 22, 2}, {2, 23, 4},
            {3, 0, 7}, {3, 1, 3}, {3, 2, 0}, {3, 3, 0}, {3, 4, 0}, {3, 5, 0},
            {3, 6, 0}, {3, 7, 0}, {3, 8, 1}, {3, 9, 0}, {3, 10, 5}, {3, 11, 4},
            {3, 12, 7}, {3, 13, 14}, {3, 14, 13}, {3, 15, 12}, {3, 16, 9}, {3, 17, 5},
            {3, 18, 5}, {3, 19, 10}, {3, 20, 6}, {3, 21, 4}, {3, 22, 4}, {3, 23, 1},
            {4, 0, 1}, {4, 1, 3}, {4, 2, 0}, {4, 3, 0}, {4, 4, 0}, {4, 5, 1},
            {4, 6, 0}, {4, 7, 0}, {4, 8, 0}, {4, 9, 2}, {4, 10, 4}, {4, 11, 4},
            {4, 12, 2}, {4, 13, 4}, {4, 14, 4}, {4, 15, 14}, {4, 16, 12}, {4, 17, 1},
            {4, 18, 8}, {4, 19, 5}, {4, 20, 3}, {4, 21, 7}, {4, 22, 3}, {4, 23, 0},
            {5, 0, 2}, {5, 1, 1}, {5, 2, 0}, {5, 3, 3}, {5, 4, 0}, {5, 5, 0},
            {5, 6, 0}, {5, 7, 0}, {5, 8, 2}, {5, 9, 0}, {5, 10, 4}, {5, 11, 1},
            {5, 12, 5}, {5, 13, 10}, {5, 14, 5}, {5, 15, 7}, {5, 16, 11}, {5, 17, 6},
            {5, 18, 0}, {5, 19, 5}, {5, 20, 3}, {5, 21, 4}, {5, 22, 2}, {5, 23, 0},
            {6, 0, 1}, {6, 1, 0}, {6, 2, 0}, {6, 3, 0}, {6, 4, 0}, {6, 5, 0},
            {6, 6, 0}, {6, 7, 0}, {6, 8, 0}, {6, 9, 0}, {6, 10, 1}, {6, 11, 0},
            {6, 12, 2}, {6, 13, 1}, {6, 14, 3}, {6, 15, 4}, {6, 16, 0}, {6, 17, 0},
            {6, 18, 0}, {6, 19, 0}, {6, 20, 1}, {6, 21, 2}, {6, 22, 2}, {6, 23, 6}
    };

    private static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[][] series = new double[3][rawData.length];
        for (int i = 0; i < rawData.length; i++) {
            series[0][i] = rawData[i][1]; // hour index
            series[1][i] = rawData[i][0]; // day index
            series[2][i] = rawData[i][2]; // value
        }
        dataset.addSeries("Punch Card", series);
        return dataset;
    }

    private static Chart createChart() {
        XYZDataset dataset = createDataset();

        // 颜色映射 (模仿 ECharts 默认热力色)
        GradientPaintScale paintScale = new GradientPaintScale(0, 10, Color.GRAY);
        paintScale.add(0, Color.WHITE);
        paintScale.add(10, Color.BLUE);

        // 超过10的值使用最后一个颜色（也可以设置更高上限）

        // X、Y 轴使用符号轴
        SymbolAxis xAxis = new SymbolAxis("Hour", hours);
        SymbolAxis yAxis = new SymbolAxis("Day", days);
        xAxis.setVerticalTickLabels(true); // 小时标签垂直显示，防止重叠
        xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 9));
        yAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));

        // 块渲染器
        XYBlockRenderer renderer = new XYBlockRenderer();
        renderer.setPaintScale(paintScale);
        renderer.setBlockWidth(1.0);
        renderer.setBlockHeight(1.0);
        renderer.setBlockAnchor(RectangleAnchor.CENTER);

        // 显示数值标签
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator((dataset1, series, item) -> {
            XYZDataset d = (XYZDataset) dataset1;
            return String.valueOf((int) d.getZValue(series, item));
        });

        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 8));

        // 提示框
        renderer.setDefaultToolTipGenerator(new XYZToolTipGenerator() {
            @Override
            public String generateToolTip(XYZDataset dataset, int series, int item) {
                int x = (int) dataset.getXValue(series, item);
                int y = (int) dataset.getYValue(series, item);
                double z = dataset.getZValue(series, item);
                return hours[x] + ", " + days[y] + " : " + (int) z;
            }

            @Override
            public String generateToolTip(XYDataset dataset, int series, int item) {
                return "";
            }
        });

        // 组装绘图区域
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);   // 显示分割线
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        // 坐标轴范围调整，使方块居中
        xAxis.setRange(-0.5, hours.length - 0.5);
        yAxis.setRange(-0.5, days.length - 0.5);

        // 颜色标尺（水平，放在底部）
        NumberAxis scaleAxis = new NumberAxis("Value");
        scaleAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 9));
        PaintScaleLegend legend = new PaintScaleLegend(paintScale, scaleAxis);
        legend.setPosition(RectangleEdge.BOTTOM);
        legend.setStripWidth(15);
        legend.setStripOutlineVisible(true);
        legend.setStripOutlinePaint(Color.DARK_GRAY);
        legend.setBackgroundPaint(Color.WHITE);
        legend.setWidth(120);
        legend.setPadding(new RectangleInsets(0, 100, 0, 100));

        Chart chart = new Chart("Punch Card", Chart.DEFAULT_TITLE_FONT, plot, false);
        chart.addSubtitle(legend);
        chart.setBackgroundPaint(Color.WHITE);
//        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            HeatmapCartesian frame = new HeatmapCartesian("Heatmap - Punch Card");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(createChart()));
            frame.pack();
            frame.setSize(900, 400);
            frame.setVisible(true);
        });
    }
}
