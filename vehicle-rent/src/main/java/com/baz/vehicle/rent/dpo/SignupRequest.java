package com.baz.vehicle.rent.dpo;

public class SignupRequest {

    private String name;
    private int age;
    private String email;
    private String address;
    private int phoneNumber;
    private String password;


    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public int getAge(){ return age; }
    public void setAge(int age){ this.age = age;}

    public String getAddress(){ return address;}
    public void  setAddress(String address){ this.address = address; }

    public int getPhoneNumber(){ return phoneNumber; }
    public void setPhoneNumber(int phoneNumber){ this.phoneNumber = phoneNumber; }


    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password;}

}
