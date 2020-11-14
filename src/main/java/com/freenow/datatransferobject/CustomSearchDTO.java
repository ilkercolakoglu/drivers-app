package com.freenow.datatransferobject;

import com.freenow.domainvalue.OnlineStatus;

import java.util.Objects;

/**
 * This DTO created for searching drivers. Any field, all of them can be filled
 * or none of them can be filled.
 * DriverDTO is not added here. Because, DriverDTO has not all fields for access constraints.
 */
public class CustomSearchDTO {

    private String username;
    private OnlineStatus onlineStatus;
    private com.freenow.datatransferobject.CarDTO carDTO;


    public com.freenow.datatransferobject.CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomSearchDTO that = (CustomSearchDTO) o;
        return Objects.equals(username, that.username) &&
                onlineStatus == that.onlineStatus &&
                Objects.equals(carDTO, that.carDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, onlineStatus, carDTO);
    }
}
