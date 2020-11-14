package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.CustomSearchDTO;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;
import com.freenow.service.driver.DriverService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.freenow.util.Consts.*;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find entity with id:")
    })
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Some constraints are violated ...")
    })
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find entity with id:")
    })
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find entity with id:")
    })
    public void updateLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @PatchMapping("/{driverId}/select_car")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find entity with id:"),
            @ApiResponse(code = 409, message = CAR_ALREADY_IN_USE_EXCEPTION)
    })
    public void selectCar(
            @Valid @PathVariable long driverId, @RequestParam long carId)
            throws EntityNotFoundException, CarAlreadyInUseException {
        driverService.selectCar(driverId, carId);
    }

    @PatchMapping("/{driverId}/deselect_car")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = CAR_NOT_AVAILABLE_EXCEPTION),
            @ApiResponse(code = 400, message = CAR_NOT_SELECTED_EXCEPTION)
    })
    public void deselectCar(
            @Valid @PathVariable long driverId, @RequestParam long carId)
            throws EntityNotFoundException, CarNotSelectedByDriverException {
        driverService.deselectCar(driverId, carId);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find entity with id:"),
            @ApiResponse(code = 404, message = COULD_NOT_FIND_ANY_DRIVER)
    })
    public List<DriverDTO> findDriverList(@Valid @RequestBody
                                                  CustomSearchDTO customSearchDTO)
            throws EntityNotFoundException, DriverNotFoundException {
        CarDO carDO = null;
        if (customSearchDTO.getCarDTO() != null) {
            carDO = CarMapper.makeCarDO(customSearchDTO.getCarDTO());
        }
        return DriverMapper.makeDriverDTOList(driverService.
                findByCustomSearchAndCar(customSearchDTO, carDO));

    }

}
