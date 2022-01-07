package com.example.logicdesign_project_final;

public class PlacesHelper {
    String building, room, time;

    public PlacesHelper() {
    }

    public PlacesHelper(String building, String room, String time) {
        this.building = building;
        this.room = room;
        this.time = time;
    }
    public String getbuilding() {
        return building;
    }
    public void setbuilding(String name) {
        this.building = name;
    }

    public String getroom() {
        return room;
    }
    public void setroom(String name) {
        this.room = name;
    }

    public String gettime() {
        return time;
    }
    public void settime(String name) {
        this.time = name;
    }
}
