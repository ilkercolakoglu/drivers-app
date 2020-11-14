package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.GearType;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible = false;

    private Double rating;

    private EngineType engineType;

    private String color;

    private GearType gearType;

    private String modelYear;

    private Double hp;

    private ManufacturerDTO manufacturerDTO;


    private CarDTO() {
    }

    private CarDTO(Long id, String licensePlate, Integer seatCount, Boolean convertible, Double rating, EngineType engineType,
                  String color, GearType gearType, String modelYear, Double hp, ManufacturerDTO manufacturerDTO) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.color = color;
        this.gearType = gearType;
        this.modelYear = modelYear;
        this.hp = hp;
        this.manufacturerDTO = manufacturerDTO;
    }

    public static CarDTOBuilder newBuilder() {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public Double getRating() {
        return rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public String getColor() {
        return color;
    }

    public GearType getGearType() {
        return gearType;
    }

    public String getModelYear() {
        return modelYear;
    }

    public Double getHp() {
        return hp;
    }

    public ManufacturerDTO getManufacturerDTO() {
        return manufacturerDTO;
    }

    public static class CarDTOBuilder {
        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private Boolean convertible = false;
        private Double rating;
        private EngineType engineType;
        private String color;
        private GearType gearType;
        private String modelYear;
        private Double hp;
        private ManufacturerDTO manufacturerDTO;


        public CarDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(Boolean convertible) {
            this.convertible = convertible;
            return this;
        }

        public CarDTOBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarDTOBuilder setGearType(GearType gearType) {
            this.gearType = gearType;
            return this;
        }

        public CarDTOBuilder setModelYear(String modelYear) {
            this.modelYear = modelYear;
            return this;
        }

        public CarDTOBuilder setHp(Double hp) {
            this.hp = hp;
            return this;
        }

        public CarDTOBuilder setManufacturerDTO(ManufacturerDTO manufacturerDTO) {
            this.manufacturerDTO = manufacturerDTO;
            return this;
        }

        public CarDTO createCarDTO() {
            return new CarDTO(id, licensePlate, seatCount, convertible, rating, engineType, color, gearType, modelYear, hp, manufacturerDTO);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
