package com.learning.randomuser;


public class ContentManger {

    private int age;
    private String name;
    private String number;
    private String image_url;
    private String location;
    private String birthdate;

    public ContentManger(String name, String number, int age, String image_url, String location, String birthdate) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.image_url = image_url;
        this.location = location;
        this.birthdate = birthdate;
    }


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getLocation() {
        return location;
    }

    public String getBirthdate() {
        return birthdate;
    }
}
