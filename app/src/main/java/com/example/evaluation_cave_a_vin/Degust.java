package com.example.evaluation_cave_a_vin;

public class Degust {
    private String id;
    private String date_degust;
    private String note;
    private String comment;
    private String exploitation;

    public Degust(String id, String date_degust, String note, String comment, String exploitation) {
        this.id = id;
        this.date_degust = date_degust;
        this.note = note;
        this.comment = comment;
        this.exploitation = exploitation;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_degust() {
        return date_degust;
    }

    public void setDate_degust(String date_degust) {
        this.date_degust = date_degust;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getExploitation() {
        return exploitation;
    }

    public void setExploitation(String exploitation) {
        this.exploitation = exploitation;
    }
}
