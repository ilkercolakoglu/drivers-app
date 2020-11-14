package com.freenow.controller.mapper;


import com.freenow.datatransferobject.CarDTO;
import com.freenow.datatransferobject.ManufacturerDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.ManufacturerDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @developer -- ilkercolakoglu
 */

public class CarMapper {
    public static CarDO makeCarDO(CarDTO carDTO) {
        ManufacturerDO manufacturerDO = null;
        if(carDTO.getManufacturerDTO()!=null) {
            manufacturerDO = ManufacturerMapper.makeManufacturerDO(carDTO.getManufacturerDTO());
        }

        return new CarDO(carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.getConvertible(),
                carDTO.getRating(), carDTO.getEngineType(), carDTO.getColor(), carDTO.getGearType(),
                carDTO.getModelYear(), carDTO.getHp(),manufacturerDO);
    }


    public static CarDTO makeCarDTO(CarDO carDO) {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(carDO.getId())
                .setLicensePlate(carDO.getLicensePlate());


        if (carDO.getSeatCount() != null) {
            carDTOBuilder.setSeatCount(carDO.getSeatCount());
        }
        if (carDO.getEngineType() != null) {
            carDTOBuilder.setEngineType(carDO.getEngineType());
        }
        if (carDO.getConvertible() != null) {
            carDTOBuilder.setConvertible(carDO.getConvertible());
        }
        if (carDO.getRating() != null) {
            carDTOBuilder.setRating(carDO.getRating());
        }
        if (carDO.getColor() != null) {
            carDTOBuilder.setColor(carDO.getColor());
        }
        if (carDO.getGearType() != null) {
            carDTOBuilder.setGearType(carDO.getGearType());
        }
        if (carDO.getModelYear() != null) {
            carDTOBuilder.setModelYear(carDO.getModelYear());
        }
        if (carDO.getHp() != null) {
            carDTOBuilder.setHp(carDO.getHp());
        }
        if (carDO.getManufacturerDO() != null) {
            ManufacturerDTO manufacturerDTO = ManufacturerMapper.makeManufacturerDTO(carDO.getManufacturerDO());
            carDTOBuilder.setManufacturerDTO(manufacturerDTO);
        }

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
        return cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}

