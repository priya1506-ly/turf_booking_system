package com.turfbookingapp.model;

public class Resource {
        private int resourceId;
        private Vendor vendor;
        private String name;
        private String location;

        public Resource(int resourceId, String name){
            this.resourceId = resourceId;
            this.name = name;
        }
      public  Resource(int resourceId, Vendor vendor, String n, String l){
            this.resourceId = resourceId;
            this.vendor = vendor;
            this.name = n;
            this.location = l;
        }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId ){
            this.resourceId =  resourceId;
    }


    public Vendor getVendor() {
        return vendor;
    }
    public void setVendorId( Vendor vendorId){
            this.vendor = vendorId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
            this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location){
            this.location = location;
    }


}
