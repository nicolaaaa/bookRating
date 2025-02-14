/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.model;

public class BookRatingInfo {

    private double averageRating;
    private int totalVotes;
    private int userRating;

    public BookRatingInfo(double averageRating, int totalVotes, int userRating) {
        this.averageRating = averageRating;
        this.totalVotes = totalVotes;
        this.userRating = userRating;

    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public int getUserRating() {
        return userRating;
    }

    @Override
    public String toString() {
        return "BookRatingInfo{" + "averageRating=" + averageRating + ", totalVotes=" + totalVotes + ", userRating=" + userRating + '}';
    }
}
