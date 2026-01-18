package edu.rutmiit.course.cardiagnostic.endpoints;
import edu.rutmiit.course.cardiagnostic.dto.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/cars")
public interface CarApi {
    @GetMapping("/{id}") EntityModel<CarResponse> getCarById(@PathVariable Long id);
    @GetMapping PagedModel<EntityModel<CarResponse>> getAllCars(@RequestParam(required=false) Long mechanicId, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size);
    @PostMapping ResponseEntity<EntityModel<CarResponse>> createCar(@RequestBody CarRequest request);
    @PutMapping("/{id}") EntityModel<CarResponse> updateCar(@PathVariable Long id, @RequestBody UpdateCarRequest request);
    @DeleteMapping("/{id}") void deleteCar(@PathVariable Long id);
}
