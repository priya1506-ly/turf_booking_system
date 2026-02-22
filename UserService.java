package com.turfbookingapp.service;

import com.turfbookingapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class UserService {
    private List<Resource> turfs;
    private List<Slot> slots;
    private List<User> users;
    private List<Booking> bookings;
    private List<Review> reviews;


    public UserService(List<Resource> turfs,List<Slot> slots,List<User> users,List<Booking> bookings,List<Review> reviews){
        this.turfs = turfs;
        this.slots = slots;
        this.users = users;
        this.bookings = bookings;
        this.reviews = reviews;
    }

    public void viewAvailableTurfs() {
        System.out.println("Available Turfs:");
        for(Resource turf : turfs) {
            System.out.println("Turf ID: " + turf.getResourceId() + ", Name: " + turf.getName() + ", Vendor Name : " + turf.getVendor() + "Turf Location : " + turf.getLocation() );
        }
    }
    public boolean viewTurfSlots(int turfId) {

        if (slots == null || slots.isEmpty()) {
            System.out.println("No Slots is added Yet!!");
            return false;
        }
        boolean slotsFound = false;

        for (Slot slot : slots) {
            if (slot.getResource() != null && slot.getResource().getResourceId() == turfId) {
                if (!slotsFound) {
                    System.out.println("Slots for : " + slot.getResource().getName() + " : ");
                    slotsFound = true;
                }
                System.out.println("SlotId : " + slot.getSlotId() + "', Time : " + slot.getStartTime() + " - " + slot.getEndTime() + ", Price : " + slot.getPrice() + ",Status : " + slot.getStatus());
            }
        }

        if (!slotsFound) {
            System.out.println("No Slots found for this turf !!");
          return false;
        }
        return true;


    }
    public void bookSlot(int userId, int turfId, int slotId){

        Slot selectedslot = null;

        if(users == null || users.isEmpty()){
            System.out.println("No users Available!");
            return;
        }
        if(slots == null || slots.isEmpty()){
            System.out.println("No slots Available!");
            return;
        }

        for(Slot slot : slots) {
            if (slot.getResource() != null && slot.getResource().getResourceId() == turfId && slot.getSlotId() == slotId) {
                selectedslot = slot;
                break;
            }
        }
        if(selectedslot == null){
            System.out.println("Slot not found for this turf!!");
            return;
        }
        System.out.println("Before change → " + selectedslot.getStatus());
        if(selectedslot.getStatus() != SlotStatus.AVAILABLE) {
            System.out.println("The Slot is Already Booked!");
            return;
        }
        int bookingId = bookings.size() + 1;

        User bookingUser = null;
        for (User u : users) {
            if (u.getId() == userId) {
                bookingUser = u;
                break;
            }
        }
        if (bookingUser == null) {
            System.out.println("User not found!");
            return;
        }
        Booking booking = new Booking(bookingId, bookingUser, selectedslot, LocalDate.now(), BookingStatus.CONFIRMED);
        bookings.add(booking);
        selectedslot.setStatus(SlotStatus.BOOKED);


        System.out.println("Slot Booked Successfully");

        System.out.println("After change → " + selectedslot.getStatus());

    }
    public void viewUserBooking(int userId){
        if(bookings == null || bookings.isEmpty() ){
            System.out.println("No Bookings Available");
            return;
        }
        boolean bookingsFound = false;
        System.out.println("Your Bookings: ");
        for(Booking booking : bookings){
            if(booking.getUser().getId() == userId){
                bookingsFound = true;

                Slot slot = booking.getSlot();
                Resource turf = slot.getResource();
                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("Turf       : " + turf.getName());
                System.out.println("Slot ID    : " + slot.getSlotId());
                System.out.println("Time       : " + slot.getStartTime() + " - " + slot.getEndTime());
                System.out.println("Date       : " + booking.getBookingDate());
                System.out.println("Status     : " + booking.getStatus());
            }
            System.out.println();
        }
        if(!bookingsFound){
            System.out.println("No Bookings found for this User!");
        }
    }
    public void cancelBooking(int userId,int bookingId){

        Booking bookingCancel = null;

        for(Booking b : bookings) {
            if (b.getBookingId() == bookingId && b.getUser().getId() == userId) {
                bookingCancel = b;
                break;

            }
        }
        if(bookingCancel == null){
            System.out.println("Booking not found for this user");
            return;
        }
        if(bookingCancel.getStatus() == BookingStatus.CANCELLED){
            System.out.println("The Booking is already Cancelled");
            return;
        }
        bookingCancel.setStatus(BookingStatus.CANCELLED);

        Slot bookingStatus = bookingCancel.getSlot();
        bookingStatus.setStatus(SlotStatus.AVAILABLE);
        System.out.println("Booking ID " + bookingId + " has been cancelled successfully!");


    }
    public void addReview(int userId, int turfid, int rating,String comment){

        User bookingUser = null;
        for (User u : users){
            if(u.getId() == userId){
                bookingUser = u;
                break;

            }
        }
        if(bookingUser == null){
            System.out.println("User not found");
            return;
        }
        Resource bookedTurf = null;
        for(Resource r : turfs) {
            if (r.getResourceId() == turfid) {
                bookedTurf = r;
                break;
            }
        }
        if(bookedTurf ==  null)
        {
            System.out.println("Turf not found");
            return;
        }

        boolean hasBooking  = false;
        for (Booking b : bookings){
            if(b.getUser().getId() == userId && b.getSlot().getResource().getResourceId() == turfid && b.getStatus() == BookingStatus.CONFIRMED){
                hasBooking = true;
                break;
            }
        }
        if(!hasBooking){
            System.out.println("You have not booked this turf yet. Cannot add review.");
            return;
        }
        int reviewId = reviews.size()+1;

        Review review = new Review(reviewId,bookingUser ,bookedTurf, rating, comment);
        reviews.add(review);
        System.out.println("Review Count : " + reviews.size());
        System.out.println("Review added successfully for " + bookedTurf.getName() );
    }
    public void viewturfReviews(int turfId){

        Resource turf = null;
        for(Resource r : turfs){
            if(r.getResourceId() == turfId){
                turf = r;
                break;
            }
        }
        if(turf == null){
            System.out.println("Turf not found");
            return;
        }


        boolean hasReviews = false;
        for(Review rev :reviews ){

            if(rev.getResource().getResourceId() == turfId){
                System.out.println("User : " + rev.getReviewUserId().getName());
                System.out.println("Rating : " + rev.getRating());
                System.out.println("Comment : " + rev.getComment());
                hasReviews = true;

            }
        }
        if(!hasReviews){
            System.out.println("No reviews yet for this Turf");
        }


    }



}
