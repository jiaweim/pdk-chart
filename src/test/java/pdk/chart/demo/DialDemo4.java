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

public class DialDemo4 extends JFrame {
    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public DialDemo4(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static void main(String[] args) {
        DialDemo4 app = new DialDemo4("Chart: DialDemo4.java");
        app.pack();
        app.setVisible(true);
    }

    static class DemoPanel extends JPanel implements ChangeListener {
        JSlider slider;
        DefaultValueDataset dataset = new DefaultValueDataset((double) 50.0F);

        public DemoPanel() {
            super(new BorderLayout());
            DialPlot plot = new DialPlot();
            plot.setView(0.78, 0.37, 0.22, 0.26);
            plot.setDataset(this.dataset);
            ArcDialFrame dialFrame = new ArcDialFrame((double) -10.0F, (double) 20.0F);
            dialFrame.setInnerRadius(0.7);
            dialFrame.setOuterRadius(0.9);
            dialFrame.setForegroundPaint(Color.darkGray);
            dialFrame.setStroke(new BasicStroke(3.0F));
            plot.setDialFrame(dialFrame);
            GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(240, 240, 240));
            DialBackground sdb = new DialBackground(gp);
            sdb.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            plot.addLayer(sdb);
            StandardDialScale scale = new StandardDialScale((double) 0.0F, (double) 100.0F, (double) -8.0F, (double) 16.0F, (double) 10.0F, 4);
            scale.setTickRadius(0.82);
            scale.setTickLabelOffset(-0.04);
            scale.setMajorTickIncrement((double) 25.0F);
            scale.setTickLabelFont(new Font("Dialog", 0, 14));
            plot.addScale(0, scale);
            DialPointer needle = new DialPointer.Pin();
            needle.setRadius(0.84);
            plot.addLayer(needle);
            Chart chart1 = new Chart(plot);
            chart1.setTitle("Dial Demo 4");
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
