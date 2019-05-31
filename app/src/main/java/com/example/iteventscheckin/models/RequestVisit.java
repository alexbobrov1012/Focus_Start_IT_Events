package com.example.iteventscheckin.models;

public class RequestVisit {

    private int id;

    private boolean isVisited;

    private String visitedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        this.visitedDate = visitedDate;
    }
}
