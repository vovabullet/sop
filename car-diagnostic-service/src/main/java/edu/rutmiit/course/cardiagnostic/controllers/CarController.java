package edu.rutmiit.course.cardiagnostic.controllers;

import edu.rutmiit.course.cardiagnostic.dto.*;
import edu.rutmiit.course.cardiagnostic.endpoints.CarApi;
import edu.rutmiit.course.cardiagnostic.service.CarService;
import edu.rutmiit.course.cardiagnostic.assemblers.CarModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class CarController implements CarApi {
    private final CarService carService;
    private final CarModelAssembler assembler;

    public CarController(CarService carService, CarModelAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    @Override
    public EntityModel<CarResponse> getCarById(Long id) { return assembler.toModel(carService.getCarById(id)); }

    @Override
    public PagedModel<EntityModel<CarResponse>> getAllCars(Long mechanicId, int page, int size) {
        PagedResponse<CarResponse> pagedResponse = carService.getAllCars(mechanicId, page, size);
        return PagedModel.of(pagedResponse.content().stream().map(assembler::toModel).collect(Collectors.toList()),
                new PagedModel.PageMetadata(pagedResponse.pageSize(), pagedResponse.pageNumber(), pagedResponse.totalElements()));
    }

    @Override
    public ResponseEntity<EntityModel<CarResponse>> createCar(CarRequest request) {
        return new ResponseEntity<>(assembler.toModel(carService.createCar(request)), HttpStatus.CREATED);
    }

    @Override
    public EntityModel<CarResponse> updateCar(Long id, UpdateCarRequest request) {
        return assembler.toModel(carService.updateCar(id, request));
    }

    @Override
    public void deleteCar(Long id) { carService.deleteCar(id); }
}
