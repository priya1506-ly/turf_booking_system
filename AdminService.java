package com.turfbookingapp.service;

import com.turfbookingapp.model.*;

import java.util.*;

public class AdminService {
   private List<Resource> turfs;
   private List<Vendor> vendors;
   private List<Booking> bookings;
   private List<User> users;
   private  List<Slot> slots;

   public AdminService(List<Resource> turfs,List<Vendor> vendors,List<Booking> bookings,List<User> users,List<Slot> slots){
       this.turfs = turfs;
       this.vendors = vendors;
       this.users = users;
       this.bookings = bookings;
       this.slots =slots;
   }
   public  void addVendors(Scanner scanner) {

       scanner.nextLine();
           String vendorName;

           do {
               System.out.println("Enter Vendor name");
               vendorName = scanner.nextLine();
               if (vendorName.trim().isEmpty()) {
                   System.out.println("Vendor name cannot be empty ");
               }
           } while (vendorName.trim().isEmpty());

           boolean duplicate = false;
           for(Vendor v : vendors) {
               if(v.getName().equalsIgnoreCase(vendorName.trim())){
                   duplicate = true;
                   break;

               }
           }
           if(duplicate) {
               System.out.println("Duplicate Vendor! A vendor with this name already exists.");
               return;
           }
       int vendorId = vendors.size()+1;
       Vendor vendor = new Vendor(vendorId,vendorName.trim());
       vendors.add(vendor);

       System.out.println("Vendor added successfully");
       System.out.println("Vendor Id : " + vendorId);
       System.out.println("Vendor Name : " + vendorName);

   }
    public void removeVendors(Scanner scanner) {

        if (vendors == null || vendors.isEmpty()) {
            System.out.println("No vendors available to remove.");
            return;
        }

        System.out.println("Enter Vendor ID to remove:");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }

        int vendorId = scanner.nextInt();
        scanner.nextLine();

        Vendor vendorToRemove = null;

        for (Vendor v : vendors) {
            if (v.getId() == vendorId) {
                vendorToRemove = v;
                break;
            }
        }

        if (vendorToRemove == null) {
            System.out.println("Vendor not found.");
            return;
        }

        System.out.println("Vendor found: " + vendorToRemove.getName());
        System.out.println("Are you sure you want to remove this vendor? (Y/N)");

        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Vendor removal cancelled.");
            return;
        }

        int removedTurfs = 0;
        Iterator<Resource> turfIterator = turfs.iterator();

        while (turfIterator.hasNext()) {
            Resource turf = turfIterator.next();

            if (turf.getVendor().getId() == vendorId) {
                turfIterator.remove();
                removedTurfs++;
            }
        }
        vendors.remove(vendorToRemove);
        System.out.println("Vendor removed successfully.");
        System.out.println("Associated turfs removed: " + removedTurfs);
    }
    public void addTurfs(Scanner scanner) {

        if (vendors == null || vendors.isEmpty()) {
            System.out.println("No vendors available. Please add vendor first.");
            return;
        }


        System.out.println("Available Vendors:");
        for (Vendor v : vendors) {
            System.out.println("Vendor ID : " + v.getId() + ", Name : " + v.getName());
        }


        System.out.println("Enter Vendor ID to assign turf:");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input!");
            scanner.next();
            return;
        }

        int vendorId = scanner.nextInt();
        scanner.nextLine();

        Vendor selectedVendor = null;
        for (Vendor v : vendors) {
            if (v.getId() == vendorId) {
                selectedVendor = v;
                break;
            }
        }

        if (selectedVendor == null) {
            System.out.println("Vendor not found!");
            return;
        }


        String turfName;
        do {
            System.out.println("Enter Turf Name:");
            turfName = scanner.nextLine();
            if (turfName.trim().isEmpty()) {
                System.out.println("Turf name cannot be empty!");
            }
        } while (turfName.trim().isEmpty());


        String location;
        do {
            System.out.println("Enter Turf Location:");
            location = scanner.nextLine();
            if (location.trim().isEmpty()) {
                System.out.println("Location cannot be empty!");
            }
        } while (location.trim().isEmpty());


        int turfId = turfs.size() + 1;

        Resource turf = new Resource(turfId,selectedVendor, turfName, location );

        turfs.add(turf);


        System.out.println("Turf added successfully!");
        System.out.println("Turf ID : " + turfId);
        System.out.println("Assigned Vendor : " + selectedVendor.getName());
    }
    public  void removeturfs(Scanner scanner){
        if (turfs == null || turfs.isEmpty()) {
            System.out.println("No turfs available to remove!");
            return;
        }

        System.out.println("Available Turfs:");
        for (Resource r : turfs) {
            System.out.println("Turf ID : " + r.getResourceId() + ", Name : " + r.getName());
        }

        System.out.println("Enter Turf ID to remove:");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input!");
            scanner.next();
            return;
        }

        int turfId = scanner.nextInt();
        scanner.nextLine();
        Resource turfToRemove = null;

        for (Resource r : turfs) {
            if (r.getResourceId() == turfId) {
                turfToRemove = r;
                break;
            }
        }

        if (turfToRemove == null) {
            System.out.println("Turf not found!");
            return;
        }

        System.out.println("Turf found: " + turfToRemove.getName());
        System.out.println("Are you sure you want to remove this turf? (Y/N)");

        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Turf removal cancelled.");
            return;
        }


        turfs.remove(turfToRemove);


        int removedSlots = 0;
        Iterator<Slot> slotIterator = slots.iterator();

        while (slotIterator.hasNext()) {
            Slot s = slotIterator.next();
            if (s.getResource().getResourceId() == turfId) {
                slotIterator.remove();
                removedSlots++;
            }
        }

        System.out.println("Turf removed successfully.");
        System.out.println("Associated slots removed: " + removedSlots);
    }
    public void viewAllUsers() {

        if (users == null || users.isEmpty()) {
            System.out.println("No users registered in the system.");
            return;
        }

        System.out.println("Registered Users:");

        for (User u : users) {

            System.out.println("User ID : " + u.getId() + " | Name : " + u.getName() + "  | Email : " + u.getEmail() + " | Contact Number : " + u.getMobile());

        }
    }
    public void viewBookings(){
       if(bookings == null){
           System.out.println("No Bookings available in the system");
           return;
       }
        System.out.println("All Bookings");
        System.out.println("Booking list size: " + bookings.size());

       for(Booking b : bookings){
           System.out.println("Booking ID : " + b.getBookingId() + "  | User Name :" + b.getUser().getName() + "| Slot :" + b.getSlot().getStartTime() + " - "+ b.getSlot().getEndTime() + "| Booking Date : " + b.getBookingDate() + "| Booking Status : " + b.getStatus());

       }
    }





}

