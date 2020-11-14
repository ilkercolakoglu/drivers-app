package com.freenow.controller.mapper;


import com.freenow.datatransferobject.ManufacturerDTO;
import com.freenow.domainobject.ManufacturerDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @developer -- ilkercolakoglu
 */

public class ManufacturerMapper
{
    public static ManufacturerDO makeManufacturerDO(ManufacturerDTO manufacturerDTO)
    {
        return new ManufacturerDO(manufacturerDTO.getBrandName(),manufacturerDTO.getWarrantyPeriod());
    }


    public static ManufacturerDTO makeManufacturerDTO(ManufacturerDO manufacturerDO)
    {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder = ManufacturerDTO.newBuilder()
                .setId(manufacturerDO.getId())
                .setBrandName(manufacturerDO.getBrandName());

        if (manufacturerDO.getWarrantyPeriod() != null)
        {
            manufacturerDTOBuilder.setWarrantyPeriod(manufacturerDO.getWarrantyPeriod());
        }

        return manufacturerDTOBuilder.createManufacturerDTO();
    }


    public static List<ManufacturerDTO> makeManufacturerDTOList(Collection<ManufacturerDO> manufacturers)
    {
        return manufacturers.stream()
                .map(ManufacturerMapper::makeManufacturerDTO)
                .collect(Collectors.toList());
    }
}

