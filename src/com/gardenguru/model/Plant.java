package com.gardenguru.model;

public class Plant {
    private int id;
    private String name;
    private String scientificName;
    private String careTips;
    private String imageUrl;

    public Plant() {
        // Default constructor
    }

    public Plant(int id, String name, String scientificName, String careTips, String imageUrl) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.careTips = careTips;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCareTips() {
        return careTips;
    }

    public void setCareTips(String careTips) {
        this.careTips = careTips;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
