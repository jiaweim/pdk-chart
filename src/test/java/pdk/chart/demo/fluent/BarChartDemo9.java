package pdk.chart.demo.fluent;

import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.title.TextTitle;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import java.awt.*;

public class BarChartDemo9 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("Network Traffic",
                new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"},
                new double[]{410.0, 680.0, 530.0, 570.0, 330.0});
        return dataset;
    }

    private static Paint[] createPaint() {
        Paint[] colors = new Paint[5];
        colors[0] = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.WHITE);
        colors[1] = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, Color.WHITE);
        colors[2] = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.WHITE);
        colors[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.WHITE);
        colors[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.WHITE);
        return colors;
    }

    static void main() {
        TextTitle title = new TextTitle("Bar Chart Demo 9");
        title.setBorder(0.0, 0.0, 1.0, 0.0);
        title.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.RED, 350.0F, 0.0F, Color.WHITE, true));
        title.setExpandToFitSpace(true);

        Paint[] colors = createPaint();
        CustomBarRenderer renderer = new CustomBarRenderer(colors);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(true);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));


        CategoryXYChart chart = CategoryXYChart.create()
                .title(title)
                .axisNames(null, "Value")
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .backgroundPaint(new GradientPaint(0.0F, 0.0F, Color.YELLOW, 350.0F, 0.0F, Color.WHITE, true))
                .setNoDataMessage("NO DATA!")
                .plotBackgroundPaint(null)
                .plotInsets(new RectangleInsets(10.0, 5.0, 5.0, 5.0))
                .plotOutlinePaint(Color.BLACK)
                .rangeGridlinePaint(Color.GRAY)
                .rangeGridlineStroke(new BasicStroke(1.0f))
                .setRenderer(0, renderer);

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .range(0, 800)
                .tickMarkPaint(Color.BLACK);

        chart.show(500, 270);
    }

    static class CustomBarRenderer extends BarRenderer {
        private Paint[] colors;

        public CustomBarRenderer(Paint[] colors) {
            this.colors = colors;
        }

        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }
}
