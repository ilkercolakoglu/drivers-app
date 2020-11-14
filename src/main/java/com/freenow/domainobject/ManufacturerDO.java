package com.freenow.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "manufacturer",
        uniqueConstraints = @UniqueConstraint(name = "brand_name_uc", columnNames = {"brandName"})
)
public class ManufacturerDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = false)
    @NotNull(message = "Brand Name can not be null!")
    private String brandName;

    @Column
    private Integer warrantyPeriod;

    @OneToMany(mappedBy = "manufacturerDO", fetch = FetchType.LAZY)
    private Set<CarDO> carDOList;

    private ManufacturerDO()
    {
    }

    public ManufacturerDO(String brandName)
    {
        this.brandName=brandName;
        this.deleted=false;
        this.warrantyPeriod=null;
        this.carDOList=null;
    }

    /**
     *
     * @param brandName
     * @param warrantyPeriod
     */
    public ManufacturerDO(String brandName, Integer warrantyPeriod) {
        this.deleted = false;
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
        this.carDOList = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDO that = (ManufacturerDO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<CarDO> getCarDOList() {
        return carDOList;
    }

    public void setCarDOList(Set<CarDO> carDOList) {
        this.carDOList = carDOList;
    }

    /**
     * this setter created for findbyexample instance
     * @param dateCreated
     */
    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
