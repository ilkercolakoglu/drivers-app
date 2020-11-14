package com.freenow.domainobject;

import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "driver",
        uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class DriverDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Embedded
    private GeoCoordinate coordinate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus;

    @OneToOne
    private CarDO carDO;


    private DriverDO()
    {
    }


    public DriverDO(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.deleted = false;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = OnlineStatus.OFFLINE;
    }

    /**
     * created for example instance for findbyexample method.
     * New params can be added for maintanance.
     * @param username
     * @param onlineStatus
     */
    public DriverDO(String username, OnlineStatus onlineStatus) {
        this.dateCreated = null;
        this.username = username;
        this.password = null;
        this.deleted = false;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = onlineStatus;
        this.carDO = null;
    }

    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }

    public CarDO getCarDO() {
        return carDO;
    }

    public void setCarDO(CarDO carDO) {
        this.carDO = carDO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDO driverDO = (DriverDO) o;
        return Objects.equals(id, driverDO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
