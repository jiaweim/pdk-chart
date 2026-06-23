package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.plot.dial.*;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DialDemo2 extends JFrame {
    public DialDemo2(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        DialDemo2 app = new DialDemo2("Chart: DialDemo2.java");
        app.pack();
        app.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ChangeListener {
        DefaultValueDataset dataset1 = new DefaultValueDataset((double) 10.0F);
        DefaultValueDataset dataset2 = new DefaultValueDataset((double) 50.0F);
        JSlider slider1;
        JSlider slider2;

        public MyDemoPanel() {
            super(new BorderLayout());
            DialPlot plot = new DialPlot();
            plot.setView((double) 0.0F, (double) 0.0F, (double) 1.0F, (double) 1.0F);
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
            dvi.setAngle((double) -103.0F);
            plot.addLayer(dvi);
            DialValueIndicator dvi2 = new DialValueIndicator(1);
            dvi2.setFont(new Font("Dialog", 0, 10));
            dvi2.setOutlinePaint(Color.RED);
            dvi2.setRadius(0.6);
            dvi2.setAngle((double) -77.0F);
            plot.addLayer(dvi2);
            StandardDialScale scale = new StandardDialScale((double) -40.0F, (double) 60.0F, (double) -120.0F, (double) -300.0F, (double) 10.0F, 4);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.15);
            scale.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(0, scale);
            StandardDialScale scale2 = new StandardDialScale((double) 0.0F, (double) 100.0F, (double) -120.0F, (double) -300.0F, (double) 10.0F, 4);
            scale2.setTickRadius((double) 0.5F);
            scale2.setTickLabelOffset(0.15);
            scale2.setTickLabelFont(new Font("Dialog", 0, 10));
            scale2.setMajorTickPaint(Color.RED);
            scale2.setMinorTickPaint(Color.RED);
            plot.addScale(1, scale2);
            plot.mapDatasetToScale(1, 1);
            StandardDialRange r1 = new StandardDialRange((double) 90.0F, (double) 100.0F, Color.BLUE);
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
            this.addChart(chart1);
            JPanel sliderPanel = new JPanel(new GridLayout(2, 2));
            sliderPanel.add(new JLabel("Outer Needle:"));
            sliderPanel.add(new JLabel("Inner Needle:"));
            this.slider1 = new JSlider(-40, 60);
            this.slider1.setMajorTickSpacing(20);
            this.slider1.setPaintTicks(true);
            this.slider1.setPaintLabels(true);
            this.slider1.addChangeListener(this);
            sliderPanel.add(this.slider1);
            sliderPanel.add(this.slider1);
            this.slider2 = new JSlider(0, 100);
            this.slider2.setMajorTickSpacing(20);
            this.slider2.setPaintTicks(true);
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
