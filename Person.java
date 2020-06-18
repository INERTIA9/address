package com.bridgelabz;

public class Person {

    private String Fname;
    private String Lname;
    private String city;
    private String state;
    private Integer zip;
    private Long pnum;
   // private String cityState;

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

    //public String getCityState() { return cityState; }
    //public void setCityState(String cityState) { this.cityState = cityState; }

    @Override
    public String toString() {
        return "Person [Fname=" + Fname + ", Lname=" + Lname + ", city=" + city + ", state=" + state + ", zip=" + zip
                + ", pnum=" + pnum + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (Fname == null) {
            if (other.Fname != null)
                return false;
        } else if (!Fname.equals(other.Fname))
            return false;
        if (Lname == null) {
            if (other.Lname != null)
                return false;
        } else if (!Lname.equals(other.Lname))
            return false;
        return true;
    }

}

