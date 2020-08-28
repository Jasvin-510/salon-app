package com.example.saloonapp;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class object {

    private String id;
    private String password;
    private String name;
    private String number;
    private String address;
    private String city;
    private String link;
    private String total_client;
    private String total_vote;
    private String rating;
    private String m;
    private String w;

    private boolean verified;

    Map<String, String> m1;
    Map<String, String> g1;

    public object() {
    }

    public object(String id, String password, String name, String number, String address, String city,
                  String link, String total_client, String total_vote, String rating, String m, String w,
                  boolean verified, Map<String, String> m1, Map<String, String> g1) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.number = number;
        this.address = address;
        this.city = city;
        this.link = link;
        this.total_client = total_client;
        this.total_vote = total_vote;
        this.rating = rating;
        this.m = m;
        this.w = w;
        this.verified = verified;
        this.m1 = m1;
        this.g1 = g1;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTotal_client() {
        return total_client;
    }

    public void setTotal_client(String total_client) {
        this.total_client = total_client;
    }

    public String getTotal_vote() {
        return total_vote;
    }

    public void setTotal_vote(String total_vote) {
        this.total_vote = total_vote;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Map<String, String> getM1() {
        return m1;
    }

    public void setM1(Map<String, String> m1) {
        this.m1 = m1;
    }

    public Map<String, String> getG1() {
        return g1;
    }

    public void setG1(Map<String, String> g1) {
        this.g1 = g1;
    }
}
