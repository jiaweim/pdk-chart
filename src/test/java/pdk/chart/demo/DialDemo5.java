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

public class DialDemo5 extends JFrame {
    public DialDemo5(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public static void main(String[] args) {
        DialDemo5 app = new DialDemo5("Chart: DialDemo5.java");
        app.pack();
        app.setVisible(true);
    }

    static class DemoPanel extends JPanel implements ChangeListener {
        DefaultValueDataset hoursDataset = new DefaultValueDataset((double) 6.0F);
        DefaultValueDataset dataset2 = new DefaultValueDataset((double) 15.0F);
        JSlider slider1;
        JSlider slider2;

        public DemoPanel() {
            super(new BorderLayout());
            DialPlot plot = new DialPlot();
            plot.setView((double) 0.0F, (double) 0.0F, (double) 1.0F, (double) 1.0F);
            plot.setDataset(0, this.hoursDataset);
            plot.setDataset(1, this.dataset2);
            StandardDialFrame dialFrame = new StandardDialFrame();
            dialFrame.setBackgroundPaint(Color.LIGHT_GRAY);
            dialFrame.setForegroundPaint(Color.darkGray);
            plot.setDialFrame(dialFrame);
            DialBackground db = new DialBackground(Color.WHITE);
            db.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.setBackground(db);
            StandardDialScale hourScale = new StandardDialScale((double) 0.0F, (double) 12.0F, (double) 90.0F, (double) -360.0F, (double) 10.0F, 4);
            hourScale.setFirstTickLabelVisible(false);
            hourScale.setMajorTickIncrement((double) 1.0F);
            hourScale.setTickRadius(0.88);
            hourScale.setTickLabelOffset(0.15);
            hourScale.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(0, hourScale);
            StandardDialScale scale2 = new StandardDialScale((double) 0.0F, (double) 60.0F, (double) 90.0F, (double) -360.0F, (double) 10.0F, 4);
            scale2.setVisible(false);
            scale2.setMajorTickIncrement((double) 5.0F);
            scale2.setTickRadius(0.68);
            scale2.setTickLabelOffset(0.15);
            scale2.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(1, scale2);
            DialPointer needle2 = new DialPointer.Pointer(0);
            needle2.setRadius(0.55);
            plot.addLayer(needle2);
            plot.mapDatasetToScale(1, 1);
            DialPointer needle = new DialPointer.Pointer(1);
            plot.addLayer(needle);
            DialCap cap = new DialCap();
            cap.setRadius(0.1);
            plot.setCap(cap);
            Chart chart1 = new Chart(plot);
            chart1.setTitle("Dial Demo 5");
            ChartPanel cp1 = new ChartPanel(chart1, false);
            cp1.setPreferredSize(new Dimension(400, 400));
            JPanel sliderPanel = new JPanel(new GridLayout(2, 2));
            sliderPanel.add(new JLabel("Hours:"));
            sliderPanel.add(new JLabel("Minutes:"));
            this.slider1 = new JSlider(0, 12);
            this.slider1.setMajorTickSpacing(2);
            this.slider1.setPaintTicks(true);
            this.slider1.setPaintLabels(true);
            this.slider1.addChangeListener(this);
            sliderPanel.add(this.slider1);
            sliderPanel.add(this.slider1);
            this.slider2 = new JSlider(0, 60);
            this.slider2.setValue(15);
            this.slider2.setMajorTickSpacing(10);
            this.slider2.setPaintTicks(true);
            this.slider2.setPaintLabels(true);
            this.slider2.addChangeListener(this);
            sliderPanel.add(this.slider2);
            this.add(cp1);
            this.add(sliderPanel, "South");
        }

        public void stateChanged(ChangeEvent e) {
            this.hoursDataset.setValue(this.slider1.getValue());
            this.dataset2.setValue(this.slider2.getValue());
        }
    }
}
