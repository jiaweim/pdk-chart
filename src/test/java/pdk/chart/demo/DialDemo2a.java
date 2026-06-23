package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pdk.chart.Chart;
import pdk.chart.plot.dial.DialBackground;
import pdk.chart.plot.dial.DialCap;
import pdk.chart.plot.dial.DialPlot;
import pdk.chart.plot.dial.DialPointer;
import pdk.chart.plot.dial.DialTextAnnotation;
import pdk.chart.plot.dial.DialValueIndicator;
import pdk.chart.plot.dial.StandardDialFrame;
import pdk.chart.plot.dial.StandardDialRange;
import pdk.chart.plot.dial.StandardDialScale;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.data.general.ValueDataset;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

public class DialDemo2a extends JFrame {
    public DialDemo2a(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        JPanel demoPanel = new JPanel(new GridLayout(1, 2));
        demoPanel.add(new DemoPanelA());
        demoPanel.add(new DemoPanelB());
        return demoPanel;
    }

    public static void main(String[] args) {
        DialDemo2a app = new DialDemo2a("Chart: DialDemo2a.java");
        app.pack();
        app.setVisible(true);
    }

    static class DemoPanelA extends JPanel implements ChangeListener {
        JSlider slider;
        DefaultValueDataset dataset = new DefaultValueDataset((double)10.0F);

        public DemoPanelA() {
            super(new BorderLayout());
            Chart chart = createStandardDialChart("Dial Demo 1", "Temperature", this.dataset, (double)-40.0F, (double)60.0F, (double)10.0F, 4);
            DialPlot plot = (DialPlot)chart.getPlot();
            StandardDialRange range = new StandardDialRange((double)40.0F, (double)60.0F, Color.RED);
            range.setInnerRadius(0.52);
            range.setOuterRadius(0.55);
            plot.addLayer(range);
            StandardDialRange range2 = new StandardDialRange((double)10.0F, (double)40.0F, Color.orange);
            range2.setInnerRadius(0.52);
            range2.setOuterRadius(0.55);
            plot.addLayer(range2);
            StandardDialRange range3 = new StandardDialRange((double)-40.0F, (double)10.0F, Color.GREEN);
            range3.setInnerRadius(0.52);
            range3.setOuterRadius(0.55);
            plot.addLayer(range3);
            GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
            DialBackground db = new DialBackground(gp);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);
            plot.removePointer(0);
            plot.addPointer(new DialPointer.Pointer());
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
            StandardDialScale scale = new StandardDialScale(lowerBound, upperBound, (double)-120.0F, (double)-300.0F, (double)10.0F, 4);
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

    static class DemoPanelB extends JPanel implements ChangeListener {
        DefaultValueDataset dataset1 = new DefaultValueDataset((double)10.0F);
        DefaultValueDataset dataset2 = new DefaultValueDataset((double)50.0F);
        JSlider slider1;
        JSlider slider2;

        public DemoPanelB() {
            super(new BorderLayout());
            DialPlot plot = new DialPlot();
            plot.setView((double)0.0F, (double)0.0F, (double)1.0F, (double)1.0F);
            plot.setDataset(0, this.dataset1);
            plot.setDataset(1, this.dataset2);
            StandardDialFrame dialFrame = new StandardDialFrame();
            dialFrame.setBackgroundPaint(Color.LIGHT_GRAY);
            dialFrame.setForegroundPaint(Color.darkGray);
            plot.setDialFrame(dialFrame);
            GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
            DialBackground db = new DialBackground(gp);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);
            DialTextAnnotation annotation1 = new DialTextAnnotation("Temperature");
            annotation1.setFont(new Font("Dialog", 1, 14));
            annotation1.setRadius(0.7);
            plot.addLayer(annotation1);
            DialValueIndicator dvi = new DialValueIndicator(0);
            dvi.setFont(new Font("Dialog", 0, 10));
            dvi.setOutlinePaint(Color.darkGray);
            dvi.setRadius(0.6);
            dvi.setAngle((double)-103.0F);
            plot.addLayer(dvi);
            DialValueIndicator dvi2 = new DialValueIndicator(1);
            dvi2.setFont(new Font("Dialog", 0, 10));
            dvi2.setOutlinePaint(Color.RED);
            dvi2.setRadius(0.6);
            dvi2.setAngle((double)-77.0F);
            plot.addLayer(dvi2);
            StandardDialScale scale = new StandardDialScale((double)-40.0F, (double)60.0F, (double)-120.0F, (double)-300.0F, (double)10.0F, 4);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.15);
            scale.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(0, scale);
            StandardDialScale scale2 = new StandardDialScale((double)0.0F, (double)100.0F, (double)-120.0F, (double)-300.0F, (double)10.0F, 4);
            scale2.setTickRadius((double)0.5F);
            scale2.setTickLabelOffset(0.15);
            scale2.setTickLabelFont(new Font("Dialog", 0, 10));
            scale2.setMajorTickPaint(Color.RED);
            scale2.setMinorTickPaint(Color.RED);
            plot.addScale(1, scale2);
            plot.mapDatasetToScale(1, 1);
            StandardDialRange r1 = new StandardDialRange((double)90.0F, (double)100.0F, Color.BLUE);
            r1.setScaleIndex(1);
            r1.setInnerRadius(0.59);
            r1.setOuterRadius(0.59);
            plot.addLayer(r1);
            DialPointer needle2 = new DialPointer.Pin(1);
            needle2.setRadius(0.55);
            plot.addPointer(needle2);
            DialPointer needle = new DialPointer.Pointer(0);
            plot.addPointer(needle);
            DialCap cap = new DialCap();
            cap.setRadius(0.1);
            plot.setCap(cap);
            Chart chart1 = new Chart(plot);
            chart1.setTitle("Dial Demo 2");
            ChartPanel cp1 = new ChartPanel(chart1, false);
            cp1.setPreferredSize(new Dimension(400, 400));
            JPanel sliderPanel = new JPanel(new GridLayout(1, 2));
            this.slider1 = new JSlider(-40, 60);
            this.slider1.setMajorTickSpacing(20);
            this.slider1.setPaintTicks(false);
            this.slider1.setPaintLabels(true);
            this.slider1.addChangeListener(this);
            sliderPanel.add(this.slider1);
            sliderPanel.add(this.slider1);
            this.slider2 = new JSlider(0, 100);
            this.slider2.setMajorTickSpacing(20);
            this.slider2.setPaintTicks(false);
            this.slider2.setPaintLabels(true);
            this.slider2.addChangeListener(this);
            sliderPanel.add(this.slider2);
            this.add(cp1);
            this.add(sliderPanel, "South");
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset1.setValue(this.slider1.getValue());
            this.dataset2.setValue(this.slider2.getValue());
        }
    }
}
