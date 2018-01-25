package com.example.laurentgander.physiodatavisualization.Measures;

import java.io.Serializable;

/**
 * Created by laurent.gander on 28/10/2017.
 */

public class Measures implements Serializable{

    // Attributs privé
    private long id;
    private int cenestesisIndex;
    private int sleepingHours;
    private int position;
    private String comments;
    private String date;
    private int heartBeat;
    private int stressIndex;

    // Constructeur

    public Measures(int cenestecisIndex, int sleepingHours, int position, String comments, int heartBeat, int stressIndex, String date)
    {
        this.cenestesisIndex = cenestecisIndex;
        this.sleepingHours = sleepingHours;
        this.position = position;
        this.heartBeat = heartBeat;
        this.date = date;
        this.comments = comments;
        this.stressIndex = stressIndex;
    }

    // Getters
    public long getId(){return id;}

    public int getCenestesisIndex(){return cenestesisIndex;}

    public int getSleepingHours() {return sleepingHours; }

    public int getPosition() {return position;}

    public String getComments() {return comments;}

    public String getDate() {return date;}

    public int getHeartBeat() {return heartBeat;}

    public int getStressIndex() {return stressIndex;}


    //Setters
    public void setId(long id){this.id = id;}

    public void setCenestecisIndex(int cenestecisIndex) {this.cenestesisIndex = cenestecisIndex;}

    public void setSleepingHours(int sleepingHours) {this.sleepingHours = sleepingHours;}

    public void setPosition(int position) {this.position = position;}

    public void setComments(String comments) {this.comments = comments;}

    public void setDate(String date) {this.date = date;}

    public void setHeartBeat(int heartBeat) {this.heartBeat = heartBeat;}

    public void setStressIndex(int stressIndex) {this.stressIndex = stressIndex;}



}
