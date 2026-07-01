package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;

import java.util.HashMap;

/**
 * https://plotly.com/python/line-charts/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Jul 2026, 7:52 PM
 */
public class Line2 {
    static void main() {
        HashMap<String, Object[]> gapminder = Datasets.gapminder();
        String[] countryCol = (String[]) gapminder.get("country");
        String[] continents = (String[]) gapminder.get("continent");


        TimeSeries<String> australia = new TimeSeries<>("Australia");
        TimeSeries<String> newZealand = new TimeSeries<>("New Zealand");
        for (int i = 0; i < continents.length; i++) {
            String continent = continents[i];
            if (continent.equals("Oceania")) {
                Double lifeExp = (Double) gapminder.get("lifeExp")[i];
                Year year = new Year((Integer) gapminder.get("year")[i]);

                String country = countryCol[i];
                if (country.equals("Australia")) {
                    australia.add(year, lifeExp);
                } else if (country.equals("New Zealand")) {
                    newZealand.add(year, lifeExp);
                }
            }
        }

        IntervalXYDataset<String> dataset = Data.createTime(australia, newZealand);
        Chart chart = JChart.timeLine(dataset, "year", "lifeExp");
        chart.show();
    }
}
