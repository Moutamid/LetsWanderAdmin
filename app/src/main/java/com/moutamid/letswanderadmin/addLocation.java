package com.moutamid.letswanderadmin;

public class addLocation {
    private String id;
    private double latitude;
    private double longitude;
    private String title;
    private String description;
    private Boolean isStar;

    public addLocation(double latitude, double longitude, String title, String description, Boolean isStar) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.isStar = isStar;
    }

    public addLocation() {
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStar() {
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}