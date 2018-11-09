package com.stackroute.showservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Theatre {

    private int theatreId;
    private String theatreName;
    private String theatreCity;
    private String theatreAddress;
    private List<Timing> timings;

    public Theatre() {
    }

    public Theatre(int theatreId, String theatreName, String theatreCity, String theatreAddress, List<Timing> timings) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.theatreCity = theatreCity;
        this.theatreAddress = theatreAddress;
        this.timings = timings;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getTheatreCity() {
        return theatreCity;
    }

    public void setTheatreCity(String theatreCity) {
        this.theatreCity = theatreCity;
    }

    public String getTheatreAddress() {
        return theatreAddress;
    }

    public void setTheatreAddress(String theatreAddress) {
        this.theatreAddress = theatreAddress;
    }

    public List<Timing> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing> timings) {
        this.timings = timings;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "theatreId=" + theatreId +
                ", theatreName='" + theatreName + '\'' +
                ", theatreCity='" + theatreCity + '\'' +
                ", theatreAddress='" + theatreAddress + '\'' +
                ", timings=" + timings +
                '}';
    }
}
