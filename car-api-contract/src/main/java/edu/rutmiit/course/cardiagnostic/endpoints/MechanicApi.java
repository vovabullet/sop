package edu.rutmiit.course.cardiagnostic.endpoints;
import edu.rutmiit.course.cardiagnostic.dto.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/mechanics")
public interface MechanicApi {
    @GetMapping CollectionModel<EntityModel<MechanicResponse>> getAllMechanics();
    @GetMapping("/{id}") EntityModel<MechanicResponse> getMechanicById(@PathVariable Long id);
    @PostMapping EntityModel<MechanicResponse> createMechanic(@RequestBody MechanicRequest request);
    @PutMapping("/{id}") EntityModel<MechanicResponse> updateMechanic(@PathVariable Long id, @RequestBody MechanicRequest request);
    @DeleteMapping("/{id}") void deleteMechanic(@PathVariable Long id);
}
