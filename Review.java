package com.turfbookingapp.model;

public class Review {
    private int reviewId;
    private  User reviewUser;
    private Resource resource;
    private int rating;
    private  String comment;
    public Review(int reviewId,User reviewUser,Resource resource, int rating,String comment){
        this.reviewId = reviewId;
        this.reviewUser= reviewUser;
        this.resource = resource;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId){
        this.reviewId = reviewId;
    }

    public User getReviewUserId() {
        return reviewUser;
    }
    public void setReviewUserId(User reviewUserId){
        this.reviewUser = reviewUserId;
    }

    public Resource getResource() {
        return resource;
    }
    public void setResourceId(Resource resourceId ){
        this.resource = resource;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating ){
        this.rating = rating;

    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
}
