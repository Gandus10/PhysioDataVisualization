package com.example.laurentgander.physiodatavisualization;

import java.util.Date;

/**
 * Created by laurent.gander on 09.01.2018.
 */

public class Log {

    //attributs privé
    private long id;
    private String type;
    private Date date;

    //constructeur
    public Log(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    //getter
    public long getId() {return id;}

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    //setter
    public void setId(long id) {this.id = id;}

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
