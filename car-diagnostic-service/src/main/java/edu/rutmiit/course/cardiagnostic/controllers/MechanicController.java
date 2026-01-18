package edu.rutmiit.course.cardiagnostic.controllers;

import edu.rutmiit.course.cardiagnostic.dto.MechanicRequest;
import edu.rutmiit.course.cardiagnostic.dto.MechanicResponse;
import edu.rutmiit.course.cardiagnostic.endpoints.MechanicApi;
import edu.rutmiit.course.cardiagnostic.service.MechanicService;
import edu.rutmiit.course.cardiagnostic.assemblers.MechanicModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MechanicController implements MechanicApi {
    private final MechanicService mechanicService;
    private final MechanicModelAssembler assembler;

    public MechanicController(MechanicService mechanicService, MechanicModelAssembler assembler) {
        this.mechanicService = mechanicService;
        this.assembler = assembler;
    }

    @Override
    public CollectionModel<EntityModel<MechanicResponse>> getAllMechanics() {
        return assembler.toCollectionModel(mechanicService.getAllMechanics());
    }

    @Override
    public EntityModel<MechanicResponse> getMechanicById(Long id) {
        return assembler.toModel(mechanicService.getMechanicById(id));
    }

    @Override
    public EntityModel<MechanicResponse> createMechanic(MechanicRequest request) {
        return assembler.toModel(mechanicService.createMechanic(request));
    }

    @Override
    public EntityModel<MechanicResponse> updateMechanic(Long id, MechanicRequest request) {
        return assembler.toModel(mechanicService.updateMechanic(id, request));
    }

    @Override
    public void deleteMechanic(Long id) { mechanicService.deleteMechanic(id); }
}
