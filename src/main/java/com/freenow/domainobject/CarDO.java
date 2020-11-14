package com.freenow.domainobject;

import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.GearType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "license_plate_uc", columnNames = {"licensePlate"})
)
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column
    private Integer seatCount;

    @Column
    private Boolean convertible=false;

    @Column
    private Double rating;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    private String color;

    @Enumerated(EnumType.STRING)
    private GearType gearType;

    @Column
    private String modelYear;

    @Column
    private Double hp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private com.freenow.domainobject.ManufacturerDO manufacturerDO;

    @OneToOne
    private DriverDO driverDO;

    private CarDO()
    {
    }


    public CarDO(String licensePlate)
    {
        this.licensePlate = licensePlate;
        this.deleted=false;
        this.seatCount = null;
        this.convertible = null;
        this.rating = null;
        this.engineType = null;
        this.color = null;
        this.gearType = null;
        this.modelYear = null;
        this.hp = null;
        this.manufacturerDO=null;
        this.driverDO=null;
    }

    /**
     *
     * @param licensePlate
     * @param seatCount
     * @param convertible
     * @param rating
     * @param engineType
     * @param color
     * @param gearType
     * @param modelYear
     * @param hp
     * @param manufacturerDO
     */
    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, Double rating, EngineType engineType, String color, GearType gearType, String modelYear, Double hp, ManufacturerDO manufacturerDO) {
        this.licensePlate = licensePlate;
        this.deleted = false;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.color = color;
        this.gearType = gearType;
        this.modelYear = modelYear;
        this.hp = hp;
        this.manufacturerDO = manufacturerDO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public DriverDO getDriverDO() {
        return driverDO;
    }

    public void setDriverDO(DriverDO driverDO) {
        this.driverDO = driverDO;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public ManufacturerDO getManufacturerDO() {
        return manufacturerDO;
    }

    public void setManufacturerDO(ManufacturerDO manufacturerDO) {
        this.manufacturerDO = manufacturerDO;
    }

    /**
     * this setter created for findbyexample instance
     * @param dateCreated
     */
    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDO)) return false;
        CarDO carDO = (CarDO) o;
        return Objects.equals(getId(), carDO.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

}
