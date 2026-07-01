package pdk.chart.demo.plotly;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 28 Jun 2026, 10:29
 */
public class Datasets {

    public static HashMap<String, Object[]> iris() {
        URL url = Datasets.class.getResource("iris.data");
        HashMap<String, Object[]> dataMap = new HashMap<>();
        try {
            Path path = Path.of(url.toURI());
            List<String> lines = Files.readAllLines(path);

            int N = 150;
            Double[] sepalLengths = new Double[N];
            Double[] sepalWidths = new Double[N];
            Double[] petalLengths = new Double[N];
            Double[] petalWidths = new Double[N];
            String[] classes = new String[N];

            int i = 0;
            for (String line : lines) {
                if (line.isBlank())
                    break;
                String[] vals = line.split(",");

                sepalLengths[i] = Double.parseDouble(vals[0]);
                sepalWidths[i] = Double.parseDouble(vals[1]);
                petalLengths[i] = Double.parseDouble(vals[2]);
                petalWidths[i] = Double.parseDouble(vals[3]);

                classes[i] = vals[4];
                i++;
            }

            dataMap.put("Sepal Length", sepalLengths);
            dataMap.put("Sepal Width", sepalWidths);
            dataMap.put("Petal Length", petalLengths);
            dataMap.put("Petal Width", petalWidths);
            dataMap.put("Class", classes);

            return dataMap;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, Object[]> gapminder() {
        // https://www.kaggle.com/datasets/albertovidalrod/gapminder-dataset?resource=download&select=gapminder_data_graphs.csv

        URL url = Datasets.class.getResource("gapminder_data_graphs.csv");
        HashMap<String, Object[]> dataMap = new HashMap<>();
        try (CsvReader<NamedCsvRecord> records = CsvReader.builder()
                .ofNamedCsvRecord(Path.of(url.toURI()))) {
            int N = 3675;
            String[] country = new String[N];
            String[] continent = new String[N];
            Integer[] year = new Integer[N];
            Double[] life_exp = new Double[N];
            Double[] hdi_index = new Double[N];
            Double[] co2_consump = new Double[N];
            Double[] gdp = new Double[N];
            Double[] services = new Double[N];

            int i = 0;
            for (NamedCsvRecord csvRecord : records) {
                country[i] = csvRecord.getField("country"); // country name
                continent[i] = csvRecord.getField("continent"); // the continent to which the country belongs
                year[i] = Integer.parseInt(csvRecord.getField("year"));
                life_exp[i] = Double.parseDouble(csvRecord.getField("life_exp"));
                String hdiIndex = csvRecord.getField("hdi_index");
                if (hdiIndex.isEmpty()) {
                    hdi_index[i] = Double.NaN;
                } else {
                    hdi_index[i] = Double.parseDouble(hdiIndex);
                }

                String co2Consump = csvRecord.getField("co2_consump");
                if (co2Consump.isEmpty()) {
                    co2_consump[i] = Double.NaN;
                } else {
                    co2_consump[i] = Double.parseDouble(co2Consump);
                }
                String gdp1 = csvRecord.getField("gdp");
                if (gdp1.isEmpty()) {
                    gdp[i] = Double.NaN;
                } else {
                    gdp[i] = Double.parseDouble(gdp1);
                }
                String services1 = csvRecord.getField("services");
                if (services1.isEmpty()) {
                    services[i] = Double.NaN;
                } else {
                    services[i] = Double.parseDouble(services1);
                }

                i++;
            }

            dataMap.put("country", country);
            dataMap.put("continent", continent); // Continent (continent). Describes the continent to which the country belongs
            dataMap.put("year", year); // Year (year). Describes the year to which the data belongs
            dataMap.put("lifeExp", life_exp); // Life expectancy (life_exp). Describes the life expectancy for a given country in a given year
            dataMap.put("hdiIndex", hdi_index); // Human Development Index (hdi_index). Describes the HDI index value for a given country in a given year
            dataMap.put("co2Consump", co2_consump); // Describes the CO2 emissions in tonnes per person for a given country in a given year
            dataMap.put("gdp", gdp); // Describes the GDP per capita in dollars for a given country in a given year
            dataMap.put("services", services); // Describes the % of service workers for a given country in a given year

            return dataMap;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() {
        HashMap<String, Object[]> dataMap = gapminder();
        System.out.println(dataMap.size());
    }
}
