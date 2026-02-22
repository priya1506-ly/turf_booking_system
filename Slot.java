package com.turfbookingapp.model;

import java.time.LocalTime;

public class Slot {
    private int slotId;
    private Resource resource;
    private LocalTime startTime;
    private  LocalTime endTime;
    private double price;
    private SlotStatus status;


    public Slot(int slotId, Resource resource, LocalTime startTime,
                LocalTime endTime, double price, SlotStatus status) {
        this.slotId = slotId;
        this.resource = resource;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
    }

    public int getSlotId() {
        return slotId;
    }
    public void setSlotId(int slotId){
        this.slotId = slotId;
    }

    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource){
        this.resource = resource;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime){
        this.startTime =  startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime){
        this.endTime =  endTime;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}

