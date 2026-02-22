package com.turfbookingapp.model;

public class UserRole {
    private final int userId;
    private final int roleId;

   public  UserRole(int uI, int rI){
        this.userId = uI;
        this.roleId = rI;
    }
    public int getUserId(){
        return  userId;
    }
    public int getRoleId(){
        return roleId;
    }
}
