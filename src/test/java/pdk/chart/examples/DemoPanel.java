package pdk.chart.examples;

import pdk.chart.Chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DemoPanel extends JPanel {

    List<Chart> charts = new ArrayList();

    public DemoPanel(LayoutManager layout) {
        super(layout);
    }

    public void addChart(Chart chart) {
        this.charts.add(chart);
    }

    public Chart[] getCharts() {
        int chartCount = this.charts.size();
        Chart[] charts = new Chart[chartCount];

        for (int i = 0; i < chartCount; ++i) {
            charts[i] = (Chart) this.charts.get(i);
        }

        return charts;
    }
}
