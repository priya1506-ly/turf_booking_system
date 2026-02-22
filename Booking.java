package com.turfbookingapp.model;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private User user;
    private Slot slot;
    private LocalDate bookingDate;
    private BookingStatus status;
    public Booking(int bookingId, User user, Slot slotId,
                   LocalDate bookingDate, BookingStatus status
                   ) {
        this.bookingId = bookingId;
        this.user = user;
        this.slot = slotId;
        this.bookingDate = bookingDate;
        this.status = status;


    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId){
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }
    public void setUserId(User user){
        this.user = user;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlotId(Slot slotId) {
        this.slot = slotId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate){
        this.bookingDate =bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
