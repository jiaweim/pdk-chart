package pdk.chart.demo;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;
import pdk.chart.*;
import pdk.chart.api.RectangleInsets;
import pdk.chart.plot.*;
import pdk.chart.plot.pie.MultiplePiePlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Objects;

public class ChartDemo extends ApplicationFrame implements ActionListener, TreeSelectionListener, ChangeListener {

    private static final long serialVersionUID = 1L;
    public static final String EXIT_COMMAND = "EXIT";
    private JPanel displayPanel;
    private JPanel chartContainer;
    private JPanel descriptionContainer;
    private JTextPane descriptionPane;
    private JEditorPane editorPane;
    private TreePath defaultChartPath;
    JTabbedPane tabs;
    private JMenuItem exportToPDFMenuItem;
    private JMenuItem exportToSVGMenuItem;
    private JMenu editMenu;
    private JMenu themeMenu;

    public ChartDemo(String title) {
        super(title);
        this.setContentPane(this.createContent());
        this.setJMenuBar(this.createMenuBar());
    }

    private JComponent createContent() {
        JPanel content = new JPanel(new BorderLayout());
        this.tabs = new JTabbedPane();
        this.tabs.addChangeListener(this);
        JPanel content1 = new JPanel(new BorderLayout());
        content1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JSplitPane splitter = new JSplitPane(1);
        JTree tree = new JTree(this.createTreeModel());
        tree.addTreeSelectionListener(this);
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(300, 100));
        splitter.setLeftComponent(treePane);
        splitter.setRightComponent(this.createChartDisplayPanel());
        content1.add(splitter);
        splitter.setDividerLocation(0.2);
        this.tabs.add("Demos", content1);
        MemoryUsageDemo memUse = new MemoryUsageDemo(300000);
        Objects.requireNonNull(memUse);
        (new MemoryUsageDemo.DataGenerator(memUse, 1000)).start();
        this.tabs.add("Memory Usage", memUse);
        this.tabs.add("Source Code", this.createSourceCodePanel());
        this.tabs.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        content.add(this.tabs);
        tree.setSelectionPath(this.defaultChartPath);
        return content;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File", true);
        fileMenu.setMnemonic('F');
        this.exportToPDFMenuItem = new JMenuItem("Export to PDF...", 112);
        this.exportToPDFMenuItem.setActionCommand("EXPORT_TO_PDF");
        this.exportToPDFMenuItem.addActionListener(this);
        fileMenu.add(this.exportToPDFMenuItem);
        this.exportToSVGMenuItem = new JMenuItem("Export to SVG...", 106);
        this.exportToSVGMenuItem.setActionCommand("EXPORT_TO_SVG");
        this.exportToSVGMenuItem.addActionListener(this);
        fileMenu.add(this.exportToSVGMenuItem);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit", 120);
        exitItem.setActionCommand("EXIT");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        this.editMenu = new JMenu("Edit", false);
        menuBar.add(this.editMenu);
        JMenuItem copyItem = new JMenuItem("Copy", 67);
        copyItem.setActionCommand("COPY");
        copyItem.addActionListener(this);
        this.editMenu.add(copyItem);
        this.themeMenu = new JMenu("Theme", true);
        this.themeMenu.setMnemonic('T');
        JCheckBoxMenuItem jfree = new JCheckBoxMenuItem("JFree", true);
        jfree.setActionCommand("JFREE_THEME");
        jfree.addActionListener(this);
        this.themeMenu.add(jfree);
        JCheckBoxMenuItem jfreeshadow = new JCheckBoxMenuItem("JFree/Shadow", false);
        jfreeshadow.setActionCommand("JFREE_SHADOW_THEME");
        jfreeshadow.addActionListener(this);
        this.themeMenu.add(jfreeshadow);
        JCheckBoxMenuItem darkness = new JCheckBoxMenuItem("Darkness", false);
        darkness.setActionCommand("DARKNESS_THEME");
        darkness.addActionListener(this);
        this.themeMenu.add(darkness);
        JCheckBoxMenuItem legacy = new JCheckBoxMenuItem("Legacy", false);
        legacy.setActionCommand("LEGACY_THEME");
        legacy.addActionListener(this);
        this.themeMenu.add(legacy);
        ButtonGroup g = new ButtonGroup();
        g.add(jfree);
        g.add(jfreeshadow);
        g.add(darkness);
        g.add(legacy);
        menuBar.add(this.themeMenu);
        return menuBar;
    }

    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.editorPane = new JEditorPane();
        this.editorPane.setEditable(false);
        this.editorPane.setFont(new Font("Monospaced", 0, 12));
        this.updateSourceCodePanel("source.html");
        JScrollPane editorScrollPane = new JScrollPane(this.editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(20);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        panel.add(editorScrollPane);
        return panel;
    }

    private void updateSourceCodePanel(String sourceFilename) {
        URL sourceURL = null;
        if (sourceFilename != null) {
            sourceURL = ChartDemo.class.getResource(sourceFilename);
        }

        if (sourceURL == null) {
            sourceURL = ChartDemo.class.getResource("source.html");
        }

        if (sourceURL != null) {
            try {
                this.editorPane.setPage(sourceURL);
            } catch (IOException var4) {
                System.err.println("Attempted to read a bad URL: " + sourceURL);
            }
        } else {
            System.err.println("Couldn't find file: source.html");
        }

    }

    private void copyToClipboard() {
        if (this.tabs.getSelectedIndex() == 0) {
            Chart chart = null;
            int w = 0;
            int h = 0;
            Component c = this.chartContainer.getComponent(0);
            if (c instanceof ChartPanel) {
                ChartPanel cp = (ChartPanel) c;
                chart = cp.getChart();
                w = cp.getWidth();
                h = cp.getHeight();
            } else if (c instanceof DemoPanel) {
                DemoPanel dp = (DemoPanel) c;
                chart = (Chart) dp.charts.get(0);
                w = dp.getWidth();
                h = dp.getHeight();
            }

            if (chart != null) {
                Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                ChartTransferable selection = new ChartTransferable(chart, w, h);
                systemClipboard.setContents(selection, (ClipboardOwner) null);
            }
        } else if (this.tabs.getSelectedIndex() == 2) {
            this.editorPane.copy();
        }

    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("EXPORT_TO_PDF")) {
            this.exportToPDF();
        } else if (command.equals("EXPORT_TO_SVG")) {
            this.exportToSVG();
        } else if (command.equals("COPY")) {
            this.copyToClipboard();
        } else if (command.equals("LEGACY_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
            this.applyThemeToChart();
        } else if (command.equals("JFREE_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createJFreeTheme());
            this.applyThemeToChart();
        } else if (command.equals("JFREE_SHADOW_THEME")) {
            ChartFactory.setChartTheme(new StandardChartTheme("JFreeChart/Shadow", true));
            this.applyThemeToChart();
        } else if (command.equals("DARKNESS_THEME")) {
            ChartFactory.setChartTheme(StandardChartTheme.createDarknessTheme());
            this.applyThemeToChart();
        } else if (command.equals("EXIT")) {
            System.exit(0);
        }

    }

    private void applyThemeToChart() {
        Component c = this.chartContainer.getComponent(0);
        if (c instanceof ChartPanel) {
            ChartPanel cp = (ChartPanel) c;
            ChartUtils.applyCurrentTheme(cp.getChart());
        } else if (c instanceof DemoPanel) {
            DemoPanel dp = (DemoPanel) c;
            Chart[] charts = dp.getCharts();

            for (Chart chart : charts) {
                ChartUtils.applyCurrentTheme(chart);
            }
        }

    }

    private void exportToSVG() {
        if (this.tabs.getSelectedIndex() == 0) {
            Chart chart = null;
            int w = 0;
            int h = 0;
            Component c = this.chartContainer.getComponent(0);
            if (c instanceof ChartPanel) {
                ChartPanel cp = (ChartPanel) c;
                chart = cp.getChart();
                w = cp.getWidth();
                h = cp.getHeight();
            } else if (c instanceof DemoPanel) {
                DemoPanel dp = (DemoPanel) c;
                chart = (Chart) dp.charts.get(0);
                w = dp.getWidth();
                h = dp.getHeight();
            }

            if (chart != null) {
                JFileChooser fc = new JFileChooser();
                fc.setName("untitled.html");
                fc.setFileFilter(new FileFilter() {
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().endsWith(".html");
                    }

                    public String getDescription() {
                        return "HTML (HTML)";
                    }
                });
                int result = fc.showSaveDialog(this);
                if (result == 0) {
                    try {
                        Chart chartClone = (Chart) chart.clone();
                        this.disableShadowGeneration(chartClone);
                        SVGExportTask t = new SVGExportTask(chartClone, w, h, fc.getSelectedFile());
                        Thread thread = new Thread(t);
                        thread.start();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                String message = "Unable to export the selected item.  There is ";
                message = message + "either no chart selected,\nor else the chart is not ";
                message = message + "at the expected location in the component hierarchy\n";
                message = message + "(future versions of the demo may include code to ";
                message = message + "handle these special cases).";
                JOptionPane.showMessageDialog(this, message, "SVG Export", 1);
            }

        }
    }

    private void exportToPDF() {
        if (this.tabs.getSelectedIndex() == 0) {
            Chart chart = null;
            int w = 0;
            int h = 0;
            Component c = this.chartContainer.getComponent(0);
            if (c instanceof ChartPanel) {
                ChartPanel cp = (ChartPanel) c;
                chart = cp.getChart();
                w = cp.getWidth();
                h = cp.getHeight();
            } else if (c instanceof DemoPanel) {
                DemoPanel dp = (DemoPanel) c;
                chart = (Chart) dp.charts.get(0);
                w = dp.getWidth();
                h = dp.getHeight();
            }

            if (chart != null) {
                JFileChooser fc = new JFileChooser();
                fc.setName("untitled.pdf");
                fc.setFileFilter(new FileFilter() {
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().endsWith(".pdf");
                    }

                    public String getDescription() {
                        return "Portable Document Format (PDF)";
                    }
                });
                int result = fc.showSaveDialog(this);
                if (result == 0) {
                    try {
                        Chart chartClone = (Chart) chart.clone();
                        this.disableShadowGeneration(chartClone);
                        PDFExportTask t = new PDFExportTask(chartClone, w, h, fc.getSelectedFile());
                        Thread thread = new Thread(t);
                        thread.start();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                String message = "Unable to export the selected item.  There is ";
                message = message + "either no chart selected,\nor else the chart is not ";
                message = message + "at the expected location in the component hierarchy\n";
                message = message + "(future versions of the demo may include code to ";
                message = message + "handle these special cases).";
                JOptionPane.showMessageDialog(this, message, "PDF Export", 1);
            }
        }

    }

    private void disableShadowGeneration(Chart chart) {
        Plot plot = chart.getPlot();
        if (plot instanceof CategoryPlot) {
            ((CategoryPlot) plot).setShadowGenerator(null);
        } else if (plot instanceof XYPlot) {
            ((XYPlot) plot).setShadowGenerator(null);
        } else if (plot instanceof PiePlot) {
            ((PiePlot) plot).setShadowGenerator(null);
        } else if (plot instanceof MultiplePiePlot) {
            this.disableShadowGeneration(((MultiplePiePlot) plot).getPieChart());
        } else if (plot instanceof CombinedDomainCategoryPlot) {
            ((CombinedDomainCategoryPlot) plot).setShadowGenerator(null);
        } else if (plot instanceof CombinedRangeCategoryPlot) {
            ((CombinedRangeCategoryPlot) plot).setShadowGenerator(null);
        } else if (plot instanceof CombinedDomainXYPlot) {
            ((CombinedDomainXYPlot) plot).setShadowGenerator(null);
        } else if (plot instanceof CombinedRangeXYPlot) {
            ((CombinedRangeXYPlot) plot).setShadowGenerator(null);
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JTabbedPane) {
            JTabbedPane p = (JTabbedPane) e.getSource();
            if (this.themeMenu != null) {
                this.themeMenu.setEnabled(p.getSelectedIndex() == 0);
            }

            if (this.editMenu != null) {
                this.editMenu.setEnabled(p.getSelectedIndex() == 0 || p.getSelectedIndex() == 2);
            }

            if (this.exportToSVGMenuItem != null) {
                this.exportToSVGMenuItem.setEnabled(p.getSelectedIndex() == 0);
            }

            if (this.exportToPDFMenuItem != null) {
                this.exportToPDFMenuItem.setEnabled(p.getSelectedIndex() == 0);
            }
        }

    }

    public static void saveChartAsPDF(File file, Chart chart, int width, int height) throws IOException {
        PDFDocument pdfDoc = new PDFDocument();
        Page page = pdfDoc.createPage(new Rectangle(width, height));
        PDFGraphics2D g2 = page.getGraphics2D();
        chart.draw(g2, new Rectangle(width, height));
        pdfDoc.writeToFile(file);
    }

    private JPanel createChartDisplayPanel() {
        this.displayPanel = new JPanel(new BorderLayout());
        this.displayPanel.setPreferredSize(new Dimension(600, 400));
        this.chartContainer = new JPanel(new BorderLayout());
        this.chartContainer.setPreferredSize(new Dimension(600, 500));
        this.chartContainer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));
        this.chartContainer.add(this.createNoDemoSelectedPanel());
        this.descriptionContainer = new JPanel(new BorderLayout());
        this.descriptionContainer.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.descriptionContainer.setPreferredSize(new Dimension(600, 140));
        this.descriptionPane = new JTextPane();
        this.descriptionPane.setEditable(false);
        JScrollPane scroller = new JScrollPane(this.descriptionPane, 20, 31);
        this.descriptionContainer.add(scroller);
        this.displayDescription("select.html");
        final JSplitPane splitter = new JSplitPane(0);
        splitter.setTopComponent(this.chartContainer);
        splitter.setBottomComponent(this.descriptionContainer);
        this.displayPanel.add(splitter);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                splitter.setDividerLocation(0.6);
            }
        });
        return this.displayPanel;
    }

    private TreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JFreeChart");
        MutableTreeNode showcase = this.createShowcaseNode(root);
        root.add(showcase);
        root.add(this.createAreaChartsNode());
        root.add(this.createBarChartsNode());
        root.add(this.createStackedBarChartsNode());
        root.add(this.createCombinedAxisChartsNode());
        root.add(this.createFinancialChartsNode());
        root.add(this.createGanttChartsNode());
        root.add(this.createLineChartsNode());
        root.add(this.createMeterChartsNode());
        root.add(this.createMultipleAxisChartsNode());
        root.add(this.createOverlaidChartsNode());
        root.add(this.createPieChartsNode());
        root.add(this.createSankeyChartsNode());
        root.add(this.createStatisticalChartsNode());
        root.add(this.createTimeSeriesChartsNode());
        root.add(this.createXYChartsNode());
        root.add(this.createMiscellaneousChartsNode());
        return new DefaultTreeModel(root);
    }

    private MutableTreeNode createPieChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Pie Charts");
        root.add(this.createNode(PieChartDemo1.class, "PieChartDemo1.java"));
        root.add(this.createNode(PieChartDemo2.class, "PieChartDemo2.java"));
        root.add(this.createNode(PieChartDemo3.class, "PieChartDemo3.java"));
        root.add(this.createNode(PieChartDemo4.class, "PieChartDemo4.java"));
        root.add(this.createNode(PieChartDemo5.class, "PieChartDemo5.java"));
        root.add(this.createNode(PieChartDemo6.class, "PieChartDemo6.java"));
        root.add(this.createNode(PieChartDemo7.class, "PieChartDemo7.java"));
        root.add(this.createNode(PieChartDemo8.class, "PieChartDemo8.java"));
        root.add(this.createNode(MultiplePieChartDemo1.class, "MultiplePieChartDemo1.java"));
        root.add(this.createNode(MultiplePieChartDemo2.class, "MultiplePieChartDemo2.java"));
        root.add(this.createNode(RingChartDemo1.class, "RingChartDemo1.java"));
        root.add(this.createNode(RingChartDemo2.class, "RingChartDemo2.java"));
        return root;
    }

    private MutableTreeNode createOverlaidChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Overlaid Charts");
        root.add(this.createNode(OverlaidChartDemo1.class, "OverlaidChartDemo1.java"));
        root.add(this.createNode(OverlaidBarChartDemo1.class, "OverlaidBarChartDemo1.java"));
        root.add(this.createNode(OverlaidBarChartDemo2.class, "OverlaidBarChartDemo2.java"));
        root.add(this.createNode(OverlaidXYPlotDemo1.class, "OverlaidXYPlotDemo1.java"));
        root.add(this.createNode(OverlaidXYPlotDemo2.class, "OverlaidXYPlotDemo2.java"));
        return root;
    }

    private MutableTreeNode createBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts");
        root.add(this.createCategoryBarChartsNode());
        root.add(this.createXYBarChartsNode());
        return root;
    }

    private MutableTreeNode createStackedBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts - Stacked");
        root.add(this.createNode(PopulationChartDemo1.class, "PopulationChartDemo1.java"));
        root.add(this.createNode(StackedBarChartDemo1.class, "StackedBarChartDemo1.java"));
        root.add(this.createNode(StackedBarChartDemo2.class, "StackedBarChartDemo2.java"));
        root.add(this.createNode(StackedBarChartDemo3.class, "StackedBarChartDemo3.java"));
        root.add(this.createNode(StackedBarChartDemo4.class, "StackedBarChartDemo4.java"));
        root.add(this.createNode(StackedBarChartDemo5.class, "StackedBarChartDemo5.java"));
        root.add(this.createNode(StackedBarChartDemo6.class, "StackedBarChartDemo6.java"));
        root.add(this.createNode(StackedBarChartDemo7.class, "StackedBarChartDemo7.java"));
        root.add(this.createNode(StackedBarChart3DDemo4.class, "StackedBarChart3DDemo4.java"));
        root.add(this.createNode(StackedBarChart3DDemo5.class, "StackedBarChart3DDemo5.java"));
        return root;
    }

    private MutableTreeNode createCategoryBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("CategoryPlot");
        root.add(this.createNode(BarChartDemo1.class, "BarChartDemo1.java"));
        root.add(this.createNode(BarChartDemo2.class, "BarChartDemo2.java"));
        root.add(this.createNode(BarChartDemo3.class, "BarChartDemo3.java"));
        root.add(this.createNode(BarChartDemo4.class, "BarChartDemo4.java"));
        root.add(this.createNode(BarChartDemo5.class, "BarChartDemo5.java"));
        root.add(this.createNode(BarChartDemo6.class, "BarChartDemo6.java"));
        root.add(this.createNode(BarChartDemo7.class, "BarChartDemo7.java"));
        root.add(this.createNode(BarChartDemo8.class, "BarChartDemo8.java"));
        root.add(this.createNode(BarChartDemo9.class, "BarChartDemo9.java"));
        root.add(this.createNode(BarChartDemo10.class, "BarChartDemo10.java"));
        root.add(this.createNode(BarChartDemo11.class, "BarChartDemo11.java"));
        root.add(this.createNode(IntervalBarChartDemo1.class, "IntervalBarChartDemo1.java"));
        root.add(this.createNode(LayeredBarChartDemo1.class, "LayeredBarChartDemo1.java"));
        root.add(this.createNode(LayeredBarChartDemo2.class, "LayeredBarChartDemo2.java"));
        root.add(this.createNode(SlidingCategoryDatasetDemo1.class, "SlidingCategoryDatasetDemo1.java"));
        root.add(this.createNode(SlidingCategoryDatasetDemo2.class, "SlidingCategoryDatasetDemo2.java"));
        root.add(this.createNode(StatisticalBarChartDemo1.class, "StatisticalBarChartDemo1.java"));
        root.add(this.createNode(SurveyResultsDemo1.class, "SurveyResultsDemo1.java"));
        root.add(this.createNode(SurveyResultsDemo2.class, "SurveyResultsDemo2.java"));
        root.add(this.createNode(SurveyResultsDemo3.class, "SurveyResultsDemo3.java"));
        root.add(this.createNode(WaterfallChartDemo1.class, "WaterfallChartDemo1.java"));
        return root;
    }

    private MutableTreeNode createXYBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XYPlot");
        root.add(this.createNode(XYBarChartDemo1.class, "XYBarChartDemo1.java"));
        root.add(this.createNode(XYBarChartDemo2.class, "XYBarChartDemo2.java"));
        root.add(this.createNode(XYBarChartDemo3.class, "XYBarChartDemo3.java"));
        root.add(this.createNode(XYBarChartDemo4.class, "XYBarChartDemo4.java"));
        root.add(this.createNode(XYBarChartDemo5.class, "XYBarChartDemo5.java"));
        root.add(this.createNode(XYBarChartDemo6.class, "XYBarChartDemo6.java"));
        root.add(this.createNode(XYBarChartDemo7.class, "XYBarChartDemo7.java"));
        root.add(this.createNode(ClusteredXYBarRendererDemo1.class, "ClusteredXYBarRendererDemo1.java"));
        root.add(this.createNode(StackedXYBarChartDemo1.class, "StackedXYBarChartDemo1.java"));
        root.add(this.createNode(StackedXYBarChartDemo2.class, "StackedXYBarChartDemo2.java"));
        root.add(this.createNode(StackedXYBarChartDemo3.class, "StackedXYBarChartDemo3.java"));
        root.add(this.createNode(RelativeDateFormatDemo1.class, "RelativeDateFormatDemo1.java"));
        root.add(this.createNode(RelativeDateFormatDemo2.class, "RelativeDateFormatDemo2.java"));
        return root;
    }

    private MutableTreeNode createLineChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Line Charts");
        root.add(this.createNode(AnnotationDemo1.class, "AnnotationDemo1.java"));
        root.add(this.createNode(LineChartDemo1.class, "LineChartDemo1.java"));
        root.add(this.createNode(LineChartDemo2.class, "LineChartDemo2.java"));
        root.add(this.createNode(LineChartDemo3.class, "LineChartDemo3.java"));
        root.add(this.createNode(LineChartDemo4.class, "LineChartDemo4.java"));
        root.add(this.createNode(LineChartDemo5.class, "LineChartDemo5.java"));
        root.add(this.createNode(LineChartDemo6.class, "LineChartDemo6.java"));
        root.add(this.createNode(LineChartDemo7.class, "LineChartDemo7.java"));
        root.add(this.createNode(LineChartDemo8.class, "LineChartDemo8.java"));
        root.add(this.createNode(StatisticalLineChartDemo1.class, "StatisticalLineChartDemo1.java"));
        root.add(this.createNode(XYSplineRendererDemo1.class, "XYSplineRendererDemo1.java"));
        root.add(this.createNode(XYStepRendererDemo1.class, "XYStepRendererDemo1.java"));
        root.add(this.createNode(XYStepRendererDemo2.class, "XYStepRendererDemo2.java"));
        return root;
    }

    private MutableTreeNode createNode(Class demoClass, String file) {
        return new DefaultMutableTreeNode(new DemoDescription(demoClass, file));
    }

    private MutableTreeNode createShowcaseNode(DefaultMutableTreeNode r) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("*** Showcase Charts ***");
        MutableTreeNode defaultNode = this.createNode(BarChartDemo1.class, "BarChartDemo1.java");
        this.defaultChartPath = new TreePath(new Object[]{r, root, defaultNode});
        root.add(defaultNode);
        root.add(this.createNode(CrosshairOverlayDemo2.class, "CrosshairOverlayDemo2.java"));
        root.add(this.createNode(CrosshairDemo2.class, "CrosshairDemo2.java"));
        root.add(this.createNode(CrossSectionDemo1.class, "CrossSectionDemo1.java"));
        root.add(this.createNode(DeviationRendererDemo2.class, "DeviationRendererDemo2.java"));
        root.add(this.createNode(DifferenceChartDemo1.class, "DifferenceChartDemo1.java"));
        root.add(this.createNode(DifferenceChartDemo2.class, "DifferenceChartDemo2.java"));
        root.add(this.createNode(DialDemo2a.class, "DialDemo2a.java"));
        root.add(this.createNode(FlowPlotDemo1.class, "FlowPlotDemo1.java"));
        root.add(this.createNode(FlowPlotDemo2.class, "FlowPlotDemo2.java"));
        root.add(this.createNode(HistogramDemo1.class, "HistogramDemo1.java"));
        root.add(this.createNode(LineChartDemo1.class, "LineChartDemo1.java"));
        root.add(this.createNode(MultipleAxisDemo1.class, "MultipleAxisDemo1.java"));
        root.add(this.createNode(MultiplePieChartDemo1.class, "MultiplePieChartDemo1.java"));
        root.add(this.createNode(NormalDistributionDemo2.class, "NormalDistributionDemo2.java"));
        root.add(this.createNode(OverlaidChartDemo1.class, "OverlaidChartDemo1.java"));
        root.add(this.createNode(ParetoChartDemo1.class, "ParetoChartDemo1.java"));
        root.add(this.createNode(PieChartDemo1.class, "PieChartDemo1.java"));
        root.add(this.createNode(PieChartDemo2.class, "PieChartDemo2.java"));
        root.add(this.createNode(PieChartDemo4.class, "PieChartDemo4.java"));
        root.add(this.createNode(PriceVolumeDemo1.class, "PriceVolumeDemo1.java"));
        root.add(this.createNode(RingChartDemo2.class, "RingChartDemo2.java"));
        root.add(this.createNode(ScatterPlotDemo4.class, "ScatterPlotDemo4.java"));
        root.add(this.createNode(SlidingCategoryDatasetDemo2.class, "SlidingCategoryDatasetDemo2.java"));
        root.add(this.createNode(StackedBarChartDemo2.class, "StackedBarChartDemo2.java"));
        root.add(this.createNode(StackedXYBarChartDemo2.class, "StackedXYBarChartDemo2.java"));
        root.add(this.createNode(StatisticalBarChartDemo1.class, "StatisticalBarChartDemo1.java"));
        root.add(this.createNode(TimeSeriesDemo6.class, "TimeSeriesDemo6.java"));
        root.add(this.createNode(TimeSeriesDemo14.class, "TimeSeriesDemo14.java"));
        root.add(this.createNode(VectorPlotDemo1.class, "VectorPlotDemo1.java"));
        root.add(this.createNode(WaterfallChartDemo1.class, "WaterfallChartDemo1.java"));
        root.add(this.createNode(XYDrawableAnnotationDemo1.class, "XYDrawableAnnotationDemo1.java"));
        root.add(this.createNode(XYSplineRendererDemo1.class, "XYSplineRendererDemo1.java"));
        root.add(this.createNode(XYTaskDatasetDemo2.class, "XYTaskDatasetDemo2.java"));
        root.add(this.createNode(YieldCurveDemo1.class, "YieldCurveDemo1.java"));
        return root;
    }

    private MutableTreeNode createAreaChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Area Charts");
        root.add(this.createNode(AreaChartDemo1.class, "AreaChartDemo1.java"));
        root.add(this.createNode(StackedAreaChartDemo1.class, "StackedAreaChartDemo1.java"));
        root.add(this.createNode(StackedXYAreaChartDemo1.class, "StackedXYAreaChartDemo1.java"));
        root.add(this.createNode(StackedXYAreaChartDemo2.class, "StackedXYAreaChartDemo2.java"));
        root.add(this.createNode(StackedXYAreaRendererDemo1.class, "StackedXYAreaRendererDemo1.java"));
        root.add(this.createNode(XYAreaChartDemo1.class, "XYAreaChartDemo1.java"));
        root.add(this.createNode(XYAreaChartDemo2.class, "XYAreaChartDemo2.java"));
        root.add(this.createNode(XYAreaRenderer2Demo1.class, "XYAreaRenderer2Demo1.java"));
        root.add(this.createNode(XYStepAreaRendererDemo1.class, "XYStepAreaRendererDemo1.java"));
        return root;
    }

    private MutableTreeNode createStatisticalChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Statistical Charts");
        root.add(this.createNode(BoxAndWhiskerChartDemo1.class, "BoxAndWhiskerChartDemo1.java"));
        root.add(this.createNode(BoxAndWhiskerChartDemo2.class, "BoxAndWhiskerChartDemo2.java"));
        root.add(this.createNode(HistogramDemo1.class, "HistogramDemo1.java"));
        root.add(this.createNode(HistogramDemo2.class, "HistogramDemo2.java"));
        root.add(this.createNode(MinMaxCategoryPlotDemo1.class, "MinMaxCategoryPlotDemo1.java"));
        root.add(this.createNode(NormalDistributionDemo1.class, "NormalDistributionDemo1.java"));
        root.add(this.createNode(NormalDistributionDemo2.class, "NormalDistributionDemo2.java"));
        root.add(this.createNode(RegressionDemo1.class, "RegressionDemo1.java"));
        root.add(this.createNode(ScatterPlotDemo1.class, "ScatterPlotDemo1.java"));
        root.add(this.createNode(ScatterPlotDemo2.class, "ScatterPlotDemo2.java"));
        root.add(this.createNode(ScatterPlotDemo3.class, "ScatterPlotDemo3.java"));
        root.add(this.createNode(ScatterPlotDemo4.class, "ScatterPlotDemo4.java"));
        root.add(this.createNode(ScatterPlotDemo5.class, "ScatterPlotDemo5.java"));
        root.add(this.createNode(XYErrorRendererDemo1.class, "XYErrorRendererDemo1.java"));
        root.add(this.createNode(XYErrorRendererDemo2.class, "XYErrorRendererDemo2.java"));
        return root;
    }

    private MutableTreeNode createTimeSeriesChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Time Series Charts");
        root.add(this.createNode(TimeSeriesDemo1.class, "TimeSeriesDemo1.java"));
        root.add(this.createNode(TimeSeriesDemo2.class, "TimeSeriesDemo2.java"));
        root.add(this.createNode(TimeSeriesDemo3.class, "TimeSeriesDemo3.java"));
        root.add(this.createNode(TimeSeriesDemo4.class, "TimeSeriesDemo4.java"));
        root.add(this.createNode(TimeSeriesDemo5.class, "TimeSeriesDemo5.java"));
        root.add(this.createNode(TimeSeriesDemo6.class, "TimeSeriesDemo6.java"));
        root.add(this.createNode(TimeSeriesDemo7.class, "TimeSeriesDemo7.java"));
        root.add(this.createNode(TimeSeriesDemo8.class, "TimeSeriesDemo8.java"));
        root.add(this.createNode(TimeSeriesDemo9.class, "TimeSeriesDemo9.java"));
        root.add(this.createNode(TimeSeriesDemo10.class, "TimeSeriesDemo10.java"));
        root.add(this.createNode(TimeSeriesDemo11.class, "TimeSeriesDemo11.java"));
        root.add(this.createNode(TimeSeriesDemo12.class, "TimeSeriesDemo12.java"));
        root.add(this.createNode(TimeSeriesDemo13.class, "TimeSeriesDemo13.java"));
        root.add(this.createNode(TimeSeriesDemo14.class, "TimeSeriesDemo14.java"));
        root.add(this.createNode(PeriodAxisDemo1.class, "PeriodAxisDemo1.java"));
        root.add(this.createNode(PeriodAxisDemo2.class, "PeriodAxisDemo2.java"));
        root.add(this.createNode(PeriodAxisDemo3.class, "PeriodAxisDemo3.java"));
        root.add(this.createNode(RelativeDateFormatDemo1.class, "RelativeDateFormatDemo1.java"));
        root.add(this.createNode(DeviationRendererDemo1.class, "DeviationRendererDemo1.java"));
        root.add(this.createNode(DeviationRendererDemo2.class, "DeviationRendererDemo2.java"));
        root.add(this.createNode(DifferenceChartDemo1.class, "DifferenceChartDemo1.java"));
        root.add(this.createNode(DifferenceChartDemo2.class, "DifferenceChartDemo2.java"));
        root.add(this.createNode(CompareToPreviousYearDemo.class, "CompareToPreviousYearDemo.java"));
        return root;
    }

    private MutableTreeNode createFinancialChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Financial Charts");
        root.add(this.createNode(CandlestickChartDemo1.class, "CandlestickChartDemo1.java"));
        root.add(this.createNode(HighLowChartDemo1.class, "HighLowChartDemo1.java"));
        root.add(this.createNode(HighLowChartDemo2.class, "HighLowChartDemo2.java"));
        root.add(this.createNode(HighLowChartDemo3.class, "HighLowChartDemo3.java"));
        root.add(this.createNode(MovingAverageDemo1.class, "MovingAverageDemo1.java"));
        root.add(this.createNode(PriceVolumeDemo1.class, "PriceVolumeDemo1.java"));
        root.add(this.createNode(PriceVolumeDemo2.class, "PriceVolumeDemo2.java"));
        root.add(this.createNode(YieldCurveDemo1.class, "YieldCurveDemo1.java"));
        return root;
    }

    private MutableTreeNode createSankeyChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sankey Charts");
        root.add(this.createNode(FlowPlotDemo1.class, "FlowPlotDemo1.java"));
        root.add(this.createNode(FlowPlotDemo2.class, "FlowPlotDemo2.java"));
        return root;
    }

    private MutableTreeNode createXYChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XY Charts");
        root.add(this.createNode(ScatterPlotDemo1.class, "ScatterPlotDemo1.java"));
        root.add(this.createNode(ScatterPlotDemo2.class, "ScatterPlotDemo2.java"));
        root.add(this.createNode(ScatterPlotDemo3.class, "ScatterPlotDemo3.java"));
        root.add(this.createNode(LogAxisDemo1.class, "LogAxisDemo1.java"));
        root.add(this.createNode(Function2DDemo1.class, "Function2DDemo1.java"));
        root.add(this.createNode(XYBlockChartDemo1.class, "XYBlockChartDemo1.java"));
        root.add(this.createNode(XYBlockChartDemo2.class, "XYBlockChartDemo2.java"));
        root.add(this.createNode(XYBlockChartDemo3.class, "XYBlockChartDemo3.java"));
        root.add(this.createNode(XYLineAndShapeRendererDemo1.class, "XYLineAndShapeRendererDemo1.java"));
        root.add(this.createNode(XYLineAndShapeRendererDemo2.class, "XYLineAndShapeRendererDemo2.java"));
        root.add(this.createNode(XYSeriesDemo1.class, "XYSeriesDemo1.java"));
        root.add(this.createNode(XYSeriesDemo2.class, "XYSeriesDemo2.java"));
        root.add(this.createNode(XYSeriesDemo3.class, "XYSeriesDemo3.java"));
        root.add(this.createNode(XYShapeRendererDemo1.class, "XYShapeRendererDemo1.java"));
        root.add(this.createNode(VectorPlotDemo1.class, "VectorPlotDemo1.java"));
        return root;
    }

    private MutableTreeNode createMeterChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Meter & Dial Charts");
        root.add(this.createNode(DialDemo1.class, "DialDemo1.java"));
        root.add(this.createNode(DialDemo2.class, "DialDemo2.java"));
        root.add(this.createNode(DialDemo2a.class, "DialDemo2a.java"));
        root.add(this.createNode(DialDemo3.class, "DialDemo3.java"));
        root.add(this.createNode(DialDemo4.class, "DialDemo4.java"));
        root.add(this.createNode(DialDemo5.class, "DialDemo5.java"));
        root.add(this.createNode(MeterChartDemo1.class, "MeterChartDemo1.java"));
        root.add(this.createNode(MeterChartDemo2.class, "MeterChartDemo2.java"));
        root.add(this.createNode(MeterChartDemo3.class, "MeterChartDemo3.java"));
        root.add(this.createNode(ThermometerDemo1.class, "ThermometerDemo1.java"));
        return root;
    }

    private MutableTreeNode createMultipleAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Multiple Axis Charts");
        root.add(this.createNode(DualAxisDemo1.class, "DualAxisDemo1.java"));
        root.add(this.createNode(DualAxisDemo2.class, "DualAxisDemo2.java"));
        root.add(this.createNode(DualAxisDemo3.class, "DualAxisDemo3.java"));
        root.add(this.createNode(DualAxisDemo4.class, "DualAxisDemo4.java"));
        root.add(this.createNode(DualAxisDemo5.class, "DualAxisDemo5.java"));
        root.add(this.createNode(MultipleAxisDemo1.class, "MultipleAxisDemo1.java"));
        root.add(this.createNode(MultipleAxisDemo2.class, "MultipleAxisDemo2.java"));
        root.add(this.createNode(MultipleAxisDemo3.class, "MultipleAxisDemo3.java"));
        root.add(this.createNode(ParetoChartDemo1.class, "ParetoChartDemo1.java"));
        return root;
    }

    private MutableTreeNode createCombinedAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Combined Axis Charts");
        root.add(this.createNode(CombinedCategoryPlotDemo1.class, "CombinedCategoryPlotDemo1.java"));
        root.add(this.createNode(CombinedCategoryPlotDemo2.class, "CombinedCategoryPlotDemo2.java"));
        root.add(this.createNode(CombinedTimeSeriesDemo1.class, "CombinedTimeSeriesDemo1.java"));
        root.add(this.createNode(CombinedXYPlotDemo1.class, "CombinedXYPlotDemo1.java"));
        root.add(this.createNode(CombinedXYPlotDemo2.class, "CombinedXYPlotDemo2.java"));
        root.add(this.createNode(CombinedXYPlotDemo3.class, "CombinedXYPlotDemo3.java"));
        root.add(this.createNode(CombinedXYPlotDemo4.class, "CombinedXYPlotDemo4.java"));
        return root;
    }

    private MutableTreeNode createGanttChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Gantt Charts");
        root.add(this.createNode(GanttDemo1.class, "GanttDemo1.java"));
        root.add(this.createNode(GanttDemo2.class, "GanttDemo2.java"));
        root.add(this.createNode(SlidingGanttDatasetDemo1.class, "SlidingGanttDatasetDemo1.java"));
        root.add(this.createNode(XYTaskDatasetDemo1.class, "XYTaskDatasetDemo1"));
        root.add(this.createNode(XYTaskDatasetDemo2.class, "XYTaskDatasetDemo2"));
        return root;
    }

    private MutableTreeNode createMiscellaneousChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Miscellaneous");
        root.add(this.createAnnotationsNode());
        root.add(this.createCrosshairChartsNode());
        root.add(this.createDynamicChartsNode());
        root.add(this.createItemLabelsNode());
        root.add(this.createLegendNode());
        root.add(this.createMarkersNode());
        root.add(this.createOrientationNode());
        root.add(this.createNode(AxisOffsetsDemo1.class, "AxisOffsetsDemo1.java"));
        root.add(this.createNode(BubbleChartDemo1.class, "BubbleChartDemo1.java"));
        root.add(this.createNode(BubbleChartDemo2.class, "BubbleChartDemo2.java"));
        root.add(this.createNode(CategoryLabelPositionsDemo1.class, "CategoryLabelPositionsDemo1.java"));
        root.add(this.createNode(CategoryStepChartDemo1.class, "CategoryStepChartDemo1.java"));
        root.add(this.createNode(CompassDemo1.class, "CompassDemo1.java"));
        root.add(this.createNode(CompassFormatDemo1.class, "CompassFormatDemo1.java"));
        root.add(this.createNode(CompassFormatDemo2.class, "CompassFormatDemo2.java"));
        root.add(this.createNode(EventFrequencyDemo1.class, "EventFrequencyDemo1.java"));
        root.add(this.createNode(GradientPaintTransformerDemo1.class, "GradientPaintTransformerDemo1.java"));
        root.add(this.createNode(GridBandDemo1.class, "GridBandDemo1.java"));
        root.add(this.createNode(HideSeriesDemo1.class, "HideSeriesDemo1.java"));
        root.add(this.createNode(HideSeriesDemo2.class, "HideSeriesDemo2.java"));
        root.add(this.createNode(HideSeriesDemo3.class, "HideSeriesDemo3.java"));
        root.add(this.createNode(MultipleDatasetDemo1.class, "MultipleDatasetDemo1.java"));
        root.add(this.createNode(PolarChartDemo1.class, "PolarChartDemo1.java"));
        root.add(this.createNode(ScatterRendererDemo1.class, "ScatterRendererDemo1.java"));
        root.add(this.createNode(SpiderWebChartDemo1.class, "SpiderWebChartDemo1.java"));
        root.add(this.createNode(SymbolAxisDemo1.class, "SymbolAxisDemo1.java"));
        root.add(this.createNode(ThermometerDemo1.class, "ThermometerDemo1.java"));
        root.add(this.createNode(ThermometerDemo2.class, "ThermometerDemo2.java"));
        root.add(this.createNode(ThumbnailDemo1.class, "ThumbnailDemo1.java"));
        root.add(this.createNode(TranslateDemo1.class, "TranslateDemo1.java"));
        root.add(this.createNode(WindChartDemo1.class, "WindChartDemo1.java"));
        root.add(this.createNode(YIntervalChartDemo1.class, "YIntervalChartDemo1.java"));
        root.add(this.createNode(YIntervalChartDemo2.class, "YIntervalChartDemo2.java"));
        return root;
    }

    private MutableTreeNode createAnnotationsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Annotations");
        root.add(this.createNode(AnnotationDemo1.class, "AnnotationDemo1.java"));
        root.add(this.createNode(AnnotationDemo2.class, "AnnotationDemo2.java"));
        root.add(this.createNode(CategoryPointerAnnotationDemo1.class, "CategoryPointerAnnotationDemo1.java"));
        root.add(this.createNode(XYBoxAnnotationDemo1.class, "XYBoxAnnotationDemo1.java"));
        root.add(this.createNode(XYPolygonAnnotationDemo1.class, "XYPolygonAnnotationDemo1.java"));
        root.add(this.createNode(XYTitleAnnotationDemo1.class, "XYTitleAnnotationDemo1.java"));
        return root;
    }

    private MutableTreeNode createCrosshairChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Crosshairs");
        root.add(this.createNode(CrosshairOverlayDemo1.class, "CrosshairOverlayDemo1.java"));
        root.add(this.createNode(CrosshairOverlayDemo2.class, "CrosshairOverlayDemo2.java"));
        root.add(this.createNode(CrosshairDemo1.class, "CrosshairDemo1.java"));
        root.add(this.createNode(CrosshairDemo2.class, "CrosshairDemo2.java"));
        root.add(this.createNode(CrosshairDemo3.class, "CrosshairDemo3.java"));
        root.add(this.createNode(CrosshairDemo4.class, "CrosshairDemo4.java"));
        return root;
    }

    private MutableTreeNode createDynamicChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Dynamic Charts");
        root.add(this.createNode(DynamicDataDemo1.class, "DynamicDataDemo1.java"));
        root.add(this.createNode(DynamicDataDemo2.class, "DynamicDataDemo2.java"));
        root.add(this.createNode(DynamicDataDemo3.class, "DynamicDataDemo3.java"));
        root.add(this.createNode(MouseOverDemo1.class, "MouseOverDemo1.java"));
        return root;
    }

    private MutableTreeNode createItemLabelsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Item Labels");
        root.add(this.createNode(ItemLabelDemo1.class, "ItemLabelDemo1.java"));
        root.add(this.createNode(ItemLabelDemo2.class, "ItemLabelDemo2.java"));
        root.add(this.createNode(ItemLabelDemo3.class, "ItemLabelDemo3.java"));
        root.add(this.createNode(ItemLabelDemo4.class, "ItemLabelDemo4.java"));
        root.add(this.createNode(ItemLabelDemo5.class, "ItemLabelDemo5.java"));
        return root;
    }

    private MutableTreeNode createLegendNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Legends");
        root.add(this.createNode(LegendWrapperDemo1.class, "LegendWrapperDemo1.java"));
        return root;
    }

    private MutableTreeNode createMarkersNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Markers");
        root.add(this.createNode(CategoryMarkerDemo1.class, "CategoryMarkerDemo1.java"));
        root.add(this.createNode(CategoryMarkerDemo2.class, "CategoryMarkerDemo2.java"));
        root.add(this.createNode(MarkerDemo1.class, "MarkerDemo1.java"));
        root.add(this.createNode(MarkerDemo2.class, "MarkerDemo2.java"));
        return root;
    }

    private MutableTreeNode createOrientationNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Plot Orientation");
        root.add(this.createNode(PlotOrientationDemo1.class, "PlotOrientationDemo1.java"));
        root.add(this.createNode(PlotOrientationDemo2.class, "PlotOrientationDemo2.java"));
        return root;
    }

    private void displayDescription(String fileName) {
        URL descriptionURL = ChartDemo.class.getResource(fileName);
        if (descriptionURL != null) {
            try {
                this.descriptionPane.setPage(descriptionURL);
            } catch (IOException var4) {
                System.err.println("Attempted to read a bad URL: " + descriptionURL);
            }
        } else {
            System.err.println("Couldn't find file: " + fileName);
        }

    }

    public void valueChanged(TreeSelectionEvent event) {
        String sourceFilename = null;
        TreePath path = event.getPath();
        Object obj = path.getLastPathComponent();
        if (obj != null) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) obj;
            Object userObj = n.getUserObject();
            if (userObj instanceof DemoDescription) {
                DemoDescription dd = (DemoDescription) userObj;
                sourceFilename = dd.getDescription();
                this.updateSourceCodePanel(sourceFilename);
                SwingUtilities.invokeLater(new DisplayDemo(this, dd));
            } else {
                this.chartContainer.removeAll();
                this.chartContainer.add(this.createNoDemoSelectedPanel());
                this.displayPanel.validate();
                this.displayDescription("select.html");
                this.updateSourceCodePanel((String) null);
            }
        }

    }

    private JPanel createNoDemoSelectedPanel() {
        JPanel panel = new JPanel(new FlowLayout()) {
            public String getToolTipText() {
                return "(" + this.getWidth() + ", " + this.getHeight() + ")";
            }
        };
        ToolTipManager.sharedInstance().registerComponent(panel);
        panel.add(new JLabel("No demo selected"));
        panel.setPreferredSize(new Dimension(600, 400));
        return panel;
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception var4) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            ChartDemo demo = new ChartDemo("JFreeChart 1.5.6 Demo Collection");
            demo.pack();
            UIUtils.centerFrameOnScreen(demo);
            demo.setVisible(true);
        });
    }

    public static Image getTestImage() {
        URL imageURL = ChartDemo.class.getClassLoader().getResource("org/jfree/chart/demo/gorilla.jpg");
        ImageIcon temp = new ImageIcon(imageURL);
        return temp.getImage();
    }

    static class PDFExportTask implements Runnable {
        Chart chart;
        int width;
        int height;
        File file;

        public PDFExportTask(Chart chart, int width, int height, File file) {
            this.chart = chart;
            this.file = file;
            this.width = width;
            this.height = height;
            chart.setBorderVisible(true);
            chart.setPadding(new RectangleInsets((double) 2.0F, (double) 2.0F, (double) 2.0F, (double) 2.0F));
        }

        public void run() {
            try {
                ChartDemo.saveChartAsPDF(this.file, this.chart, this.width, this.height);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    static class DisplayDemo implements Runnable {

        private ChartDemo app;
        private DemoDescription demoDescription;

        public DisplayDemo(ChartDemo app, DemoDescription d) {
            this.app = app;
            this.demoDescription = d;
        }

        public void run() {
            try {
                Class c = this.demoDescription.getDemoClass();
                Method m = c.getDeclaredMethod("createDemoPanel", (Class[]) null);
                JPanel panel = (JPanel) m.invoke((Object) null, (Object[]) null);
                this.app.chartContainer.removeAll();
                this.app.chartContainer.add(panel);
                this.app.displayPanel.validate();
                String className = c.getName();
                String fileName = className;
                int i = className.lastIndexOf(46);
                if (i > 0) {
                    fileName = className.substring(i + 1);
                }

                fileName = fileName + ".html";
                this.app.displayDescription(fileName);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            }

        }
    }
}
