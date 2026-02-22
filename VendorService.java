package com.turfbookingapp.service;

import com.turfbookingapp.model.*;


import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class VendorService {
    List<Resource> turfs;
    List<Vendor> vendors;
    List<Slot> slots;
    List<Booking> bookings;

    public VendorService(List<Vendor> vendors, List<Resource> turfs, List<Booking> bookings,List<Slot> slots){
        this.vendors = vendors;
        this.turfs = turfs;
        this.bookings = bookings;
        this.slots  = slots;
    }
    public Vendor loginVendor(Scanner scanner) {


        System.out.print("Enter Vendor ID: ");
        int vendorId = scanner.nextInt();

        for (Vendor vendor : vendors) {
            if (vendor.getId() == vendorId) {
                System.out.println("Login successful. Welcome " + vendor.getName());
                return vendor;
            }
        }

        System.out.println("Invalid Vendor ID!");
        return null;
    }
    public void viewVendorTurfs(int vendorId) {

        System.out.println("Your Turfs : ");

        boolean found = false;

        for (Resource turf : turfs) {
            if (turf.getVendor().getId() == vendorId) {
                System.out.println("Turf ID: " + turf.getResourceId()
                        + " | Name: " + turf.getName()
                        + " | Location: " + turf.getLocation());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No turfs found for this vendor.");
        }
    }

    public void addSlot(Scanner scanner, Vendor vendor) {

        System.out.println("Add Slot : ");

        // Show vendor turfs first
        viewVendorTurfs(vendor.getId());

        System.out.print("Enter Turf ID: ");
        int turfId = scanner.nextInt();

        Resource selectedTurf = null;

        for (Resource turf : turfs) {
            if (turf.getResourceId() == turfId &&
                    turf.getVendor().getId() == vendor.getId()) {

                selectedTurf = turf;
                break;
            }
        }

        if (selectedTurf == null) {
            System.out.println("Invalid Turf ID or not your turf!");
            return;
        }

        System.out.print("Enter Start Hour (0-23): ");
        int startHour = scanner.nextInt();

        System.out.print("Enter End Hour (0-23): ");
        int endHour = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        int newSlotId = slots.size() + 1;

        Slot newSlot = new Slot(
                newSlotId,
                selectedTurf,
                LocalTime.of(startHour, 0),
                LocalTime.of(endHour, 0),
                price,
                SlotStatus.AVAILABLE
        );

        slots.add(newSlot);

        System.out.println("Slot added successfully!");
    }
    public void viewVendorBookings(int vendorId) {

        System.out.println("\n===== YOUR BOOKINGS =====");

        boolean found = false;

        for (Booking booking : bookings) {

            if (booking.getSlot()
                    .getResource()
                    .getVendor()
                    .getId() == vendorId) {


                System.out.println("Booking ID : " + booking.getBookingId());
                System.out.println("User       : " + booking.getUser().getName());
                System.out.println("Turf       : " + booking.getSlot().getResource().getName());
                System.out.println("Slot Time  : "
                        + booking.getSlot().getStartTime()
                        + " - "
                        + booking.getSlot().getEndTime());
                System.out.println("Status     : " + booking.getStatus());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found.");
        }
    }
    public void addTurf(Scanner scanner, Vendor vendor) {

        System.out.println("Add Turf : ");
        scanner.nextLine();

        System.out.print("Enter Turf Name: ");
        String turfName = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        int newTurfId = turfs.size() + 1;

        Resource newTurf = new Resource(
                newTurfId,
                vendor,
                turfName,
                location);


        turfs.add(newTurf);

        System.out.println("Turf added successfully!");
    }
    public void removeTurf(Scanner scanner, Vendor vendor) {

        System.out.println("Remove Turf");

        viewVendorTurfs(vendor.getId());

        System.out.print("Enter Turf ID to remove: ");
        int turfId = scanner.nextInt();

        Resource turfToRemove = null;

        for (Resource turf : turfs) {
            if (turf.getResourceId() == turfId &&
                    turf.getVendor().getId() == vendor.getId()) {

                turfToRemove = turf;
                break;
            }
        }

        if (turfToRemove == null) {
            System.out.println("Invalid Turf ID or not your turf!");
            return;
        }

        slots.removeIf(slot ->
                slot.getResource().getResourceId() == turfId
        );

        turfs.remove(turfToRemove);

        System.out.println("Turf removed successfully!");
    }

}
