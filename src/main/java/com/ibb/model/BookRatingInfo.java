/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.model;

public class BookRatingInfo {

    private double averageRating;
    private int totalVotes;

    public BookRatingInfo(double averageRating, int totalVotes) {
        this.averageRating = averageRating;
        this.totalVotes = totalVotes;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getTotalVotes() {
        return totalVotes;
    }
}
