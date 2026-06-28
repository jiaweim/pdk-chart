package pdk.chart.demo.plotly;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static void main() {
        HashMap<String, Object[]> iris = iris();
        for (Map.Entry<String, Object[]> entry : iris.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue().length);
        }

    }
}
