package org.sci.finalproject.SportParkBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long sportID;
    private String sportName;

    public Sport() {
    }

    public Sport(String sportName) {
        this.sportName = sportName;
    }

    public Long getSportID() {
        return sportID;
    }

    public void setSportID(Long sportID) {
        this.sportID = sportID;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
