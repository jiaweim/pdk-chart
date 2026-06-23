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

public class DialDemo3 extends JFrame {
    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public DialDemo3(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static void main(String[] args) {
        DialDemo3 app = new DialDemo3("Chart: DialDemo3.java");
        app.pack();
        app.setVisible(true);
    }

    static class DemoPanel extends JPanel implements ChangeListener {
        JSlider slider;
        DefaultValueDataset dataset = new DefaultValueDataset((double) 50.0F);

        public DemoPanel() {
            super(new BorderLayout());
            DialPlot plot = new DialPlot();
            plot.setView(0.21, (double) 0.0F, 0.58, 0.3);
            plot.setDataset(this.dataset);
            ArcDialFrame dialFrame = new ArcDialFrame((double) 60.0F, (double) 60.0F);
            dialFrame.setInnerRadius(0.6);
            dialFrame.setOuterRadius(0.9);
            dialFrame.setForegroundPaint(Color.darkGray);
            dialFrame.setStroke(new BasicStroke(3.0F));
            plot.setDialFrame(dialFrame);
            GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(240, 240, 240));
            DialBackground sdb = new DialBackground(gp);
            sdb.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.addLayer(sdb);
            StandardDialScale scale = new StandardDialScale((double) 0.0F, (double) 100.0F, (double) 115.0F, (double) -50.0F, (double) 10.0F, 4);
            scale.setTickRadius(0.88);
            scale.setTickLabelOffset(0.07);
            scale.setMajorTickIncrement((double) 25.0F);
            plot.addScale(0, scale);
            DialPointer needle = new DialPointer.Pin();
            needle.setRadius(0.82);
            plot.addLayer(needle);
            Chart chart1 = new Chart(plot);
            chart1.setTitle("Dial Demo 3");
            ChartPanel cp1 = new ChartPanel(chart1, false);
            cp1.setPreferredSize(new Dimension(400, 250));
            this.slider = new JSlider(0, 100);
            this.slider.setMajorTickSpacing(10);
            this.slider.setPaintLabels(true);
            this.slider.addChangeListener(this);
            this.add(cp1);
            this.add(this.slider, "South");
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setValue(this.slider.getValue());
        }
    }
}
