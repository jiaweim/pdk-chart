package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://plotly.com/python/line-charts/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Jul 2026, 7:42 PM
 */
public class Line1 {
    static void main() {
        HashMap<String, Object[]> gapminder = Datasets.gapminder();
        String[] countryCol = (String[]) gapminder.get("country");

        List<Year> yearList = new ArrayList<>();
        List<Double> lifeExpList = new ArrayList<>();
        for (int i = 0; i < countryCol.length; i++) {
            String country = countryCol[i];
            if (country.equals("Canada")) {
                Double lifeExp = (Double) gapminder.get("lifeExp")[i];
                lifeExpList.add(lifeExp);
                Year year = new Year((Integer) gapminder.get("year")[i]);
                yearList.add(year);
            }
        }

        double[] lifeExpArray = new double[lifeExpList.size()];
        for (int i = 0; i < lifeExpList.size(); i++) {
            lifeExpArray[i] = lifeExpList.get(i);
        }

        IntervalXYDataset<String> dataset = Data.createTime("", yearList.toArray(new Year[0]), lifeExpArray);

        Chart chart = JChart.timeLine(dataset, "year", "lifeExp", "Life expectancy in Canada");
        chart.show();
    }
}
