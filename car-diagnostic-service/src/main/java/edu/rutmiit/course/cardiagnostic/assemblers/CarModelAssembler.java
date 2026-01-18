package edu.rutmiit.course.cardiagnostic.assemblers;

import edu.rutmiit.course.cardiagnostic.controllers.MechanicController;
import edu.rutmiit.course.cardiagnostic.controllers.CarController;
import edu.rutmiit.course.cardiagnostic.dto.CarResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<CarResponse, EntityModel<CarResponse>> {
    @Override public EntityModel<CarResponse> toModel(CarResponse car) {
        return EntityModel.of(car,
                linkTo(methodOn(CarController.class).getCarById(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).getAllCars(null, 0, 10)).withRel("collection"),
                linkTo(methodOn(MechanicController.class).getMechanicById(car.getMechanic().getId())).withRel("mechanic"));
    }
}
