package com.example.ecocoleta.models;

public class CollectionItem {
    private String day;
    private String time;
    private String type;
    private String region;
    private boolean isNext;

    public CollectionItem(String day, String time, String type, String region, boolean isNext) {
        this.day = day;
        this.time = time;
        this.type = type;
        this.region = region;
        this.isNext = isNext;
    }

    public String getDay() { return day; }
    public String getTime() { return time; }
    public String getType() { return type; }
    public String getRegion() { return region; }
    public boolean isNext() { return isNext; }
}
