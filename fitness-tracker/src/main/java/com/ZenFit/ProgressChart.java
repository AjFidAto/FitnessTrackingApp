package com.ZenFit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ProgressChart extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultCategoryDataset dataset;

    public ProgressChart(String title) {
        super(title);
        dataset = new DefaultCategoryDataset();
        // Initialize the chart with some data
        dataset.addValue(0, "Progress", "Initial");
    }

    public void displayChart() {
        // Create a chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Fitness Progress",
                "Days",
                "Progress",
                dataset
        );

        // Customize chart
        lineChart.setBackgroundPaint(Color.white);
        lineChart.getCategoryPlot().setDomainGridlinePaint(Color.gray);

        // Create and set up a chart panel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Set content pane of the JFrame to the chart panel
        setContentPane(chartPanel);

        // Set JFrame properties
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true); // Make the frame visible
    }

    public void addProgress(String day, Number value) {
        dataset.addValue(value, "Progress", day);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProgressChart progressChart = new ProgressChart("Fitness Progress");
            progressChart.displayChart();

            Scanner scanner = new Scanner(System.in);

            // Loop to accept multiple entries
            while (true) {
                System.out.print("Enter day label (or type 'exit' to finish): ");
                String day = scanner.nextLine();

                if (day.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter progress value for " + day + ": ");
                Number value;
                try {
                    value = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please try again.");
                    continue;
                }

                // Update the chart with new progress data
                progressChart.addProgress(day, value);
            }

            scanner.close();
        });
    }
}
