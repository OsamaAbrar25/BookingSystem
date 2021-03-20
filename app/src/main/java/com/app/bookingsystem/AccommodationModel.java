package com.app.bookingsystem;

public class AccommodationModel {
    private String acc_name;
    private String address;
    //private boolean occupied;
    private String image;
    private String location;
    private String min_duration;
    private String rent;
    private String room;

    public AccommodationModel() {}

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMin_duration() {
        return min_duration;
    }

    public void setMin_duration(String min_duration) {
        this.min_duration = min_duration;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
