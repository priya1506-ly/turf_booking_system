package com.turfbookingapp.model;


import java.time.LocalTime;

public class Payment {
    private int paymentId;
    private Booking bookingId;
    private double amount;
    private String method;
    private PaymentStatus status;
    private LocalTime paymentTime;


    public Payment(Booking bookingId, double amount, String method, PaymentStatus status) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paymentTime = LocalTime.now();
    }

    public Payment(int paymentId, Booking bookingId, double amount,
                   String method, PaymentStatus status, LocalTime paymentTime) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paymentTime = paymentTime;
    }

    public int getPaymentId() {

        return paymentId;
    }
    public void setPaymentId(int paymentId){
        this. paymentId = paymentId;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalTime paymentTime) {
        this.paymentTime = paymentTime;
    }
}


