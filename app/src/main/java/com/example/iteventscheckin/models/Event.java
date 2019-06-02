package com.example.iteventscheckin.models;

import androidx.room.Entity;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_table")
public class Event {

    @PrimaryKey
    private int id;

    private String title;

    private String description;

    private String cardImage;

    private String eventFormat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    @Override
    public String toString() {
        return "EventNet{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cardImage='" + cardImage + '\'' +
                ", eventFormat='" + eventFormat + '\'' +
                '}';
    }
}
