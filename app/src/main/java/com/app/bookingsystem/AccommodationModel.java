package com.app.bookingsystem;

import android.net.Uri;

public class AccommodationModel {
    private String acc_name;
    private String address;
    //private boolean occupied;
    private String image;
    private String location;
    private String min_duration;
    private String rent;
    private String room;
    private String booking_time;
    private String booking_id;
    private String notice_period;
    private String parking;

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

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getNotice_period() {
        return notice_period;
    }

    public void setNotice_period(String notice_period) {
        this.notice_period = notice_period;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
