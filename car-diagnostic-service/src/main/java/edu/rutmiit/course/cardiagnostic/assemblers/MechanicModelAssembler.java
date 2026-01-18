package edu.rutmiit.course.cardiagnostic.assemblers;

import edu.rutmiit.course.cardiagnostic.controllers.MechanicController;
import edu.rutmiit.course.cardiagnostic.controllers.CarController;
import edu.rutmiit.course.cardiagnostic.dto.MechanicResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MechanicModelAssembler implements RepresentationModelAssembler<MechanicResponse, EntityModel<MechanicResponse>> {
    @Override public EntityModel<MechanicResponse> toModel(MechanicResponse mechanic) {
        return EntityModel.of(mechanic,
                linkTo(methodOn(MechanicController.class).getMechanicById(mechanic.getId())).withSelfRel(),
                linkTo(methodOn(MechanicController.class).getAllMechanics()).withRel("collection"),
                linkTo(methodOn(CarController.class).getAllCars(mechanic.getId(), 0, 10)).withRel("cars"));
    }
    @Override public CollectionModel<EntityModel<MechanicResponse>> toCollectionModel(Iterable<? extends MechanicResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities).add(linkTo(methodOn(MechanicController.class).getAllMechanics()).withSelfRel());
    }
}
