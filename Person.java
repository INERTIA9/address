package com.bridgelabz;

public class Person {

    private String Fname;
    private String Lname;
    private String city;
    private String state;
    private Integer zip;
    private Long pnum;

    public String getFname() {
        return Fname;
    }
    public void setFname(String fname) {
        Fname = fname;
    }
    public String getLname() {
        return Lname;
    }
    public void setLname(String lname) {
        Lname = lname;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Integer getZip() {
        return zip;
    }
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    public Long getPnum() {
        return pnum;
    }
    public void setPnum(Long pnum) {
        this.pnum = pnum;
    }
    @Override
    public String toString() {
        return "Person [Fname=" + Fname + ", Lname=" + Lname + ", city=" + city + ", state=" + state + ", zip=" + zip
                + ", pnum=" + pnum + "]";
    }


}

