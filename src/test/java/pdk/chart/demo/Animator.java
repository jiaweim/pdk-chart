package pdk.chart.demo;

import pdk.chart.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animator extends Timer implements ActionListener {

    private DefaultCategoryDataset dataset;

    public Animator(DefaultCategoryDataset dataset) {
        super(20, null);
        this.dataset = dataset;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        int r = (int) (Math.random() * (double) this.dataset.getRowCount());
        Comparable rowKey = this.dataset.getRowKey(r);
        int c = (int) (Math.random() * (double) this.dataset.getColumnCount());
        Comparable columnKey = this.dataset.getColumnKey(c);
        int change = Math.random() - (double) 0.5F < (double) 0.0F ? -5 : 5;
        this.dataset.setValue(Math.max(0.0, this.dataset.getValue(r, c).doubleValue() + (double) change), rowKey, columnKey);
    }
}