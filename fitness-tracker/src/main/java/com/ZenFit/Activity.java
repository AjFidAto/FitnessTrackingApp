package com.ZenFit;

public class Activity {
    private String type;
    private int duration; // in minutes
    private int intensity; // scale of 1-10
    private double caloriesBurned;
   // private String foodNutritionInfo; // New field for nutritional info


    public Activity(String type, int duration, int intensity, FoodDataAPI foodDataAPI) {
        this.type = type;
        this.duration = duration;
        this.intensity = intensity;
        this.caloriesBurned = calculateCaloriesBurned();
        //this.foodNutritionInfo = foodDataAPI.getFoodData(type); // Fetch nutritional info

    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    // Private method to calculate calories burned
    private double calculateCaloriesBurned() {
        // Simple example calculation; in reality, this should be more sophisticated
        return (duration * intensity * 0.1);
    }
    
    @Override
    public String toString() {
        return "Activity{" +
                "type='" + type + '\'' +
                ", duration=" + duration +
                ", intensity=" + intensity +
                ", caloriesBurned=" + caloriesBurned +
                '}';
    }
}
