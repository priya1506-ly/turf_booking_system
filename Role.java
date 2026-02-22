package com.turfbookingapp.model;

public enum Role {
    ADMIN(1),
    VENDOR(2),
    USER(3);
    private final int  id ;
    Role(int id ){
        this.id = id;
    }
    public int getId(){
        return id;
    }

}
