package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.plot.dial.*;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DialDemo1 extends JFrame {

    public DialDemo1(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    static void main() {
        DialDemo1 app = new DialDemo1("JFreeChart: DialDemo1.java");
        app.pack();
        app.setVisible(true);
    }

    static class DemoPanel extends JPanel implements ChangeListener {
        JSlider slider;
        DefaultValueDataset dataset = new DefaultValueDataset(10.0F);

        public DemoPanel() {
            super(new BorderLayout());
            Chart chart = createStandardDialChart("Dial Demo 1", "Temperature", this.dataset,
                    -40.0, 60.0, 10.0, 4);
            DialPlot plot = (DialPlot) chart.getPlot();
            StandardDialRange range = new StandardDialRange(40.0, 60.0, Color.RED);
            range.setInnerRadius(0.52);
            range.setOuterRadius(0.55);
            plot.addLayer(range);
            StandardDialRange range2 = new StandardDialRange(10.0, 40.0, Color.orange);
            range2.setInnerRadius(0.52);
            range2.setOuterRadius(0.55);
            plot.addLayer(range2);
            StandardDialRange range3 = new StandardDialRange(-40.0, 10.0, Color.GREEN);
            range3.setInnerRadius(0.52);
            range3.setOuterRadius(0.55);
            plot.addLayer(range3);
            GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
            DialBackground db = new DialBackground(gp);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);
            plot.removePointer(0);
            DialPointer.Pointer p = new DialPointer.Pointer();
            plot.addPointer(p);
            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.slider = new JSlider(-40, 60);
            this.slider.setMajorTickSpacing(10);
            this.slider.setPaintLabels(true);
            this.slider.addChangeListener(this);
            this.add(cp1);
            this.add(this.slider, "South");
        }

        public static Chart createStandardDialChart(String chartTitle, String dialLabel, ValueDataset dataset, double lowerBound, double upperBound, double increment, int minorTickCount) {
            DialPlot plot = new DialPlot();
            plot.setDataset(dataset);
            plot.setDialFrame(new StandardDialFrame());
            plot.setBackground(new DialBackground());
            DialTextAnnotation annotation1 = new DialTextAnnotation(dialLabel);
            annotation1.setFont(new Font("Dialog", 1, 14));
            annotation1.setRadius(0.7);
            plot.addLayer(annotation1);
            DialValueIndicator dvi = new DialValueIndicator(0);
            plot.addLayer(dvi);
            StandardDialScale scale = new StandardDialScale(lowerBound, upperBound, -120.0, -300.0, 10.0F, 4);
            scale.setMajorTickIncrement(increment);
            scale.setMinorTickCount(minorTickCount);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.15);
            scale.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(0, scale);
            plot.addPointer(new DialPointer.Pin());
            DialCap cap = new DialCap();
            plot.setCap(cap);
            return new Chart(chartTitle, plot);
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setValue(this.slider.getValue());
        }
    }
}
