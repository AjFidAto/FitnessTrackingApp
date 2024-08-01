package com.ZenFit;

import javax.swing.SwingUtilities;
//import com.ZenFit.NutritionixAPI;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodDataAPI foodDataAPI = new FoodDataAPI();

        try {
            // Prompt user for input
            String activityType = promptUser(scanner, "Enter Activity Type: ");
            int activityDuration = promptInteger(scanner, "Enter Activity Duration (minutes): ");
            int intensity = promptInteger(scanner, "Enter Intensity (1-10): "); // Changed from caloriesBurned to intensity
            int goalTarget = promptInteger(scanner, "Enter Goal Target: ");
            
            ProgressChart progressChart = new ProgressChart("Fitness Progress");
            // Create instances of your classes with runtime values
            Activity activity = new Activity(activityType, activityDuration, intensity, foodDataAPI);
            Goal goal = new Goal("Fitness Goal", goalTarget, progressChart);

            
         // Add progress to the goal based on the activity duration
            goal.addProgress(activityDuration, "Day 1"); // Assuming adding progress for Day 1
            
            // Display the collected data
            System.out.println("Activity Type: " + activity.getType());
            System.out.println("Activity Duration: " + activity.getDuration() + " minutes");
            System.out.println("Intensity: " + activity.getIntensity());
            System.out.println("Goal Target: " + goal.getTarget());
            String calorieInfo = activity.getCaloriesBurned() + " calories burned";
            System.out.println("Calorie Information: " + calorieInfo);
            System.out.println("Current Progress: " + goal.getProgress());
            
            // Check if the goal is completed
            if (goal.isCompleted()) {
                System.out.println("Congratulations! You have achieved your goal!");
            } else {
                System.out.println("Keep going! You have not yet achieved your goal.");
            }

            //System.out.println("Activity Nutritional Info: " + activity.getFoodNutritionInfo());

            // Display progress chart
            progressChart.displayChart();

            // Handle progress input
            handleProgressInput(progressChart);
            
            displayActivityDetails(activity);
            String foodQuery = promptUser(scanner, "Enter food item to get nutritional information: ");
           // FoodDataAPI foodDataAPI = new FoodDataAPI();
            String foodData = foodDataAPI.getFoodData(foodQuery);
            displayNutritionalInformation(foodData);

            //System.out.println("Nutritional Information:\n" + foodData);
            
            

            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    private static void displayActivityDetails(Activity activity) {
        System.out.println("Activity Type: " + activity.getType());
        System.out.println("Activity Duration: " + activity.getDuration() + " minutes");
        System.out.println("Intensity: " + activity.getIntensity());
        System.out.println("Calories Burned: " + activity.getCaloriesBurned());
    }
    private static void displayNutritionalInformation(String foodData) {
        System.out.println("\n--- Nutritional Information ---");
        System.out.println(foodData);
        System.out.println("-------------------------------\n");
    }


    private static String promptUser(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int promptInteger(Scanner scanner, String message) {
        int value = -1;
        while (value < 0) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please enter again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    private static void handleProgressInput(ProgressChart progressChart) {
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
            SwingUtilities.invokeLater(() -> progressChart.addProgress(day, value));
        }
        //scanner.close();
    }
}
