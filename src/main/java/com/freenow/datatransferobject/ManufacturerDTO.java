package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Brand Name can not be null!")
    private String brandName;

    private Integer warrantyPeriod;


    private ManufacturerDTO() {
    }

    private ManufacturerDTO(Long id, String brandName, Integer warrantyPeriod) {
        this.id = id;
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
    }

    public static ManufacturerDTOBuilder newBuilder() {
        return new ManufacturerDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public static class ManufacturerDTOBuilder {
        private Long id;
        private String brandName;
        private Integer warrantyPeriod;


        public ManufacturerDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ManufacturerDTOBuilder setBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public ManufacturerDTOBuilder setWarrantyPeriod(Integer warrantyPeriod) {
            this.warrantyPeriod = warrantyPeriod;
            return this;
        }

        public ManufacturerDTO createManufacturerDTO() {
            return new ManufacturerDTO(id, brandName, warrantyPeriod);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDTO that = (ManufacturerDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
