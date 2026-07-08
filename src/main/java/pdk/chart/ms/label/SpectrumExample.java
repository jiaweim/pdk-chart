package pdk.chart.ms.label;


import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.ms.PeakRenderer;
import pdk.chart.plot.XYPlot;

public class SpectrumExample {

    private static XYSeriesCollection createDataset() {

        XYSeries series = new XYSeries("MS/MS Spectrum");

        series.add(100.123, 120);
        series.add(150.234, 800);
        series.add(200.345, 5000);
        series.add(250.456, 300);
        series.add(300.567, 15000);
        series.add(350.678, 1000);
        series.add(400.789, 8000);
        series.add(450.890, 200);

        return new XYSeriesCollection(series);
    }

    private static Chart createSpectrumChart() {
        XYDataset dataset = createDataset();

        NumberAxis mzAxis = new NumberAxis("m/z");
        mzAxis.setAutoRangeIncludesZero(false);

        NumberAxis intensityAxis = new NumberAxis("Intensity");

        PeakRenderer renderer = new PeakRenderer();

        /*
         * 设置标签参数
         */
        renderer.getLabelLayout().setMinimumRelativeIntensity(0.05);
        renderer.getLabelLayout().setMaximumLabels(20);

        XYPlot plot = new XYPlot(dataset, mzAxis, intensityAxis, renderer);

        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        return new Chart("MS/MS Spectrum", Chart.DEFAULT_TITLE_FONT, plot, false);
    }

    static void main() {
        Chart chart = createSpectrumChart();
        chart.show();
    }
}