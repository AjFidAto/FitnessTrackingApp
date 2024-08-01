package com.ZenFit;

public class Goal {
    private String description;
    private int target; // Target value, e.g., number of miles to run
    private int progress; // Current progress
    private boolean completed; // Indicates if the goal is completed
    private ProgressChart progressChart;

    // Constructor with a description
    public Goal(String description, int target, ProgressChart progressChart) {
        this.description = description != null && !description.isEmpty() ? description : "No description provided";
        this.target = target;
        this.progress = 0;
        this.completed = false;
        this.progressChart = progressChart;

    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        checkCompletion();
    }

    public boolean isCompleted() {
        return completed;
    }

    // Method to add to the current progress
    public void addProgress(int increment, String day) {
        this.progress += increment;
        checkCompletion();
        if (progressChart != null) {
            progressChart.addProgress(day, this.progress);
        }
    }

    // Method to check if the goal is completed
    private void checkCompletion() {
        this.completed = this.progress >= this.target;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "description='" + description + '\'' +
                ", target=" + target +
                ", progress=" + progress +
                ", completed=" + completed +
                '}';
    }
}
