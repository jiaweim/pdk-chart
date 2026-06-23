package pdk.chart.demo;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 May 2026, 12:48 PM
 */
public class ArrowDemo {
    static void main() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Polygon p1 = new Polygon();
                p1.addPoint(0, 0);
                p1.addPoint(-2, 2);
                p1.addPoint(2, 2);
                g.drawPolygon(p1);
            }
        };
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
