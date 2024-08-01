package com.ZenFit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NutritionalInfoApp extends JFrame {
    private JTextField activityText;
    private JTextArea nutritionalInfoArea;

    public NutritionalInfoApp() {
        super("Nutritional Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel activityLabel = new JLabel("Enter Food Item:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(activityLabel, constraints);

        activityText = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(activityText, constraints);

        JButton submitButton = new JButton("Get Nutritional Info");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(submitButton, constraints);

        nutritionalInfoArea = new JTextArea(10, 40);
        nutritionalInfoArea.setLineWrap(true);
        nutritionalInfoArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(nutritionalInfoArea);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodItem = activityText.getText();
                FoodDataAPI foodDataAPI = new FoodDataAPI();
                String foodData = foodDataAPI.getFoodData(foodItem);
                nutritionalInfoArea.setText(foodData);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NutritionalInfoApp().setVisible(true);
            }
        });
    }
}
