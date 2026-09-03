package com.example.ecocoleta.models;

import java.io.Serializable;

public class StatusHistory implements Serializable {
    private String status;
    private String date;

    public StatusHistory(String status, String date) {
        this.status = status;
        this.date = date;
    }

    public String getStatus() { return status; }
    public String getDate() { return date; }
}
