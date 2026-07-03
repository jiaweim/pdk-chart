package pdk.chart.demo.pep;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.plot.pep.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jul 2026, 11:04 AM
 */
public class PSMDemo {

    private static SpectrumDataset createDataset() {
        SpectrumDataset dataset = new SpectrumDataset();
        dataset.addSeries(SeriesType.mz,
                new double[]{
                        108.8904,
                        108.8989,
                        109.1423,
                        110.2423,
                        114.1308,
                        117.1023,
                        126.0916,
                        130.0864,
                        135.7945,
                        153.6947,
                        159.3061,
                        178.2171,
                        186.0856,
                        211.1082,
                        217.4034,
                        228.9214,
                        283.9395,
                        297.1198,
                        299.1709,
                        314.1448,
                        340.2496,
                        368.1932,
                        382.2067,
                        385.2217,
                        389.2206,
                        395.2038,
                        396.1847,
                        398.2417,
                        410.2011,
                        427.2313,
                        428.216,
                        481.2777,
                        498.3054,
                        536.3236,
                        539.2479,
                        543.2471,
                        545.3076,
                        556.2755,
                        637.3248,
                        638.3091,
                        692.4168,
                        758.3186,
                        763.4496,
                        837.9556,
                        839.3943,
                        856.4341,
                        866.4677,
                        866.9691,
                        871.423,
                        921.4898,
                        930.4941,
                        930.9938,
                        962.5176,
                        970.4943,
                        971.0331,
                        971.517,
                        979.5316,
                        980.5255,
                        1041.523,
                        1080.532,
                        1081.522,
                        1179.605,
                        1180.586,
                        1250.641,
                        1290.612,
                        1305.72,
                        1415.753,
                        1433.363,
                        1434.776,
                        1547.868,
                        1732.943
                }, new double[]{
                        28965.21,
                        8937.908,
                        4097.858,
                        3818.061,
                        4212.728,
                        22802.75,
                        9449.421,
                        6899.435,
                        4759.786,
                        5695.327,
                        5054.938,
                        53549.16,
                        8744.761,
                        24633.79,
                        5483.462,
                        5852.076,
                        6281.209,
                        11717.5,
                        23427.03,
                        23951.6,
                        6005.451,
                        11423.48,
                        7975.514,
                        28080.61,
                        9619.738,
                        7733.466,
                        10309.18,
                        27206.47,
                        44277.61,
                        11438.2,
                        9872.839,
                        8834.677,
                        32194.99,
                        12867.98,
                        24114.31,
                        12966.37,
                        7378.402,
                        10571.57,
                        10476.05,
                        8463.272,
                        9452.187,
                        8837.067,
                        13478.58,
                        8308.741,
                        11274.63,
                        8058.063,
                        150735.8,
                        12108.55,
                        25381.49,
                        23175.88,
                        65683.38,
                        10277.26,
                        46260.55,
                        41894.57,
                        106725.5,
                        93123.02,
                        37611.89,
                        205187.3,
                        12294.16,
                        26808.9,
                        14028.99,
                        28642.09,
                        9701.605,
                        11528.85,
                        7874.292,
                        24402.94,
                        22364.17,
                        13167.73,
                        10463.09,
                        10858.71,
                        10689.76
                });
        dataset.addSeries(SeriesType.p, new double[]{980.0314},
                new double[]{1094222}, new double[]{0.48774384583663}, new String[]{"[M]²⁺"});
        dataset.addSeries(SeriesType.b,
                new double[]{
                        228.1341,
                        285.1545,
                        342.1753,
                        413.2128,
                        526.2987,
                        655.34,
                        712.8742,
                        770.3787,
                        857.414,
                        985.4645,
                        1098.542,
                        1197.613,
                        1268.649,
                        1325.671,
                        1424.735,
                        1495.793,
                        1642.826
                },
                new double[]{
                        112008.6,
                        43334.66,
                        8376.556,
                        62263.06,
                        67977.81,
                        72482.51,
                        8750.058,
                        56773.65,
                        13201.99,
                        51972.33,
                        83754.65,
                        131718.9,
                        69352.54,
                        23037.11,
                        64890.68,
                        30063.89,
                        9145.891
                },
                new double[]{
                        -0.735910876,
                        -4.319068644,
                        -5.539024502,
                        -3.652131559,
                        0.621146612,
                        -1.474316957,
                        2.201729754,
                        14.00732726,
                        16.40120331,
                        6.07319817,
                        -0.527147231,
                        1.675829851,
                        0.70406214,
                        1.078312758,
                        -2.094720357,
                        11.96823454,
                        -10.65964393

                },
                new String[]{
                        "b₂⁺",
                        "b₃⁺",
                        "b₄⁺",
                        "b₅⁺",
                        "b₆⁺",
                        "b₇⁺",
                        "b₁₅²⁺",
                        "b₈⁺",
                        "b₉⁺",
                        "b₁₀⁺",
                        "b₁₁⁺",
                        "b₁₂⁺",
                        "b₁₃⁺",
                        "b₁₄⁺",
                        "b₁₅⁺",
                        "b₁₆⁺",
                        "b₁₇⁺"
                });
        dataset.addSeries(SeriesType.y,
                new double[]{
                        147.1126,
                        317.2188,
                        464.2874,
                        535.3242,
                        634.3874,
                        691.4147,
                        762.451,
                        861.5209,
                        974.6055,
                        1102.667,
                        1189.69,
                        1304.723,
                        1433.76,
                        1546.85,
                        1617.882,
                        1674.907,
                        1731.93
                },
                new double[]{
                        29957.03,
                        96978.47,
                        106502.3,
                        283045.4,
                        40711.02,
                        236354.8,
                        346259.9,
                        199971.4,
                        118410.1,
                        62309.86,
                        139539.8,
                        221273.3,
                        227456.6,
                        119288.1,
                        28702.7,
                        9805.489,
                        121513.2
                },
                new double[]{
                        -1.387818008,
                        1.475750088,
                        1.409088063,
                        0.63594356,
                        -7.682121162,
                        1.392510662,
                        0.195443222,
                        1.897928285,
                        2.2276994,
                        4.61938277,
                        -3.307394611,
                        1.626545905,
                        -2.420831901,
                        1.59364195,
                        -1.637115496,
                        0.529949276,
                        1.399535769
                },
                new String[]{
                        "y₁⁺",
                        "y₂⁺",
                        "y₃⁺",
                        "y₄⁺",
                        "y₅⁺",
                        "y₆⁺",
                        "y₇⁺",
                        "y₈⁺",
                        "y₉⁺",
                        "y₁₀⁺",
                        "y₁₁⁺",
                        "y₁₂⁺",
                        "y₁₃⁺",
                        "y₁₄⁺",
                        "y₁₅⁺",
                        "y₁₆⁺",
                        "y₁₇⁺"
                }
        );
        return dataset;
    }

    private static PeptideDataset createPeptideDataset() {
        PeptideDataset dataset = new PeptideDataset();
        String pep = "VQGGALEDSQLVAGVAFKK";
        char[] charArray = pep.toCharArray();
        boolean[] markArray = new boolean[charArray.length];
        markArray[17] = true;
        dataset.setValue(charArray, markArray);

        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 1, "y1"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 10, "y10"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 11, "y11"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 12, "y12"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 13, "y13"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 14, "y14"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 15, "y15"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 16, "y16"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 17, "y17"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 2, "y2"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 3, "y3"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 4, "y4"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 5, "y5"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 6, "y6"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 7, "y7"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 8, "y8"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.y, 9, "y9"));

        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 10, "b10"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 11, "b11"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 12, "b12"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 13, "b13"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 14, "b14"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 15, "b15"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 16, "b16"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 17, "b17"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 2, "b2"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 3, "b3"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 4, "b4"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 5, "b5"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 6, "b6"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 7, "b7"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 8, "b8"));
        dataset.addAnnotation(new PeptideAnnotation(SeriesType.b, 9, "b9"));

        return dataset;
    }

    static void main() {
        SpectrumDataset spectrumDataset = createDataset();
        PeptideDataset peptideDataset = createPeptideDataset();

        Chart chart = JChart.psm2(new PSMDataset(peptideDataset, spectrumDataset), ToleranceType.ppm(20));
        chart.show();
    }
}
