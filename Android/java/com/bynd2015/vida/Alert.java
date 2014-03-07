package com.bynd2015.vida;

/**
 * Created by alberto on 10/09/13.
 */
public class Alert {
    private double radius;
    private int id;
    private String description;
    private String symptoms;
    private String prevent;
    private String help;

    public Alert(double radius, int id, String description, String symptoms, String prevent, String help) {
        this.radius = radius;
        this.id = id;
        this.description = description;
        this.symptoms = symptoms;
        this.prevent = prevent;
        this.help = help;
    }

    public double getRadius() {
        return radius;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
