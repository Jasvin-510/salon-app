package com.example.saloonapp;

public class saloon_request_object {

    private String name;
    private String address;
    private String client_email;
    private String id;
    private String mobile_no;

    public saloon_request_object() {
    }

    public saloon_request_object(String name, String address, String client_email, String id, String mobile_no) {
        this.name = name;
        this.address = address;
        this.client_email = client_email;
        this.id = id;
        this.mobile_no = mobile_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
}
