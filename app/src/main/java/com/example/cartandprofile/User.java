package com.example.cartandprofile;

public class User {
    private String name;
    private String age;
    private String bio;
    private String school;

    public User(){

    }

    public User(String name, String age, String bio, String school) {
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
