package org.sci.finalproject.SportParkBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlayGround {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long playGroundID;
    private String playGroundName;
    private String playGroundAddress;
    private String playGroundDescription;
    private long sportId;
    private int pricePerHour;

    public PlayGround() {
    }

    public PlayGround(String playGroundName, String playGroundAddress, String playGroundDescription, long sportId, int pricePerHour) {
        this.playGroundName = playGroundName;
        this.playGroundAddress = playGroundAddress;
        this.playGroundDescription = playGroundDescription;
        this.sportId = sportId;
        this.pricePerHour = pricePerHour;
    }

    public long getPlayGroundID() {
        return playGroundID;
    }

    public void setPlayGroundID(long playGroundID) {
        this.playGroundID = playGroundID;
    }

    public String getPlayGroundName() {
        return playGroundName;
    }

    public void setPlayGroundName(String playGroundName) {
        this.playGroundName = playGroundName;
    }

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getPlayGroundAddress() {return playGroundAddress; }

    public void setPlayGroundAddress(String playGroundAddress) {this.playGroundAddress = playGroundAddress; }

    public String getPlayGroundDescription() {return playGroundDescription; }

    public void setPlayGroundDescription(String playGroundDescription) {this.playGroundDescription = playGroundDescription; }
}
