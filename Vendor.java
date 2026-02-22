package com.turfbookingapp.model;

public class Vendor {
    private int id;
    private String name;
    private String email;
    public Vendor(int id, String name){
        this.id = id;
        this.name = name;
    }
   public Vendor(int i, String n, String e){
        this.id = i;
        this.name = n;
        this.email = e;
    }
    public String toString(){
        return name;
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;

    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
