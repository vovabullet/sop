package edu.rutmiit.course.cardiagnostic.graphql;
import edu.rutmiit.course.cardiagnostic.dto.*;
import edu.rutmiit.course.cardiagnostic.service.CarService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
@Controller
public class CarDataFetcher {
    private final CarService carService;
    public CarDataFetcher(CarService carService) { this.carService = carService; }
    @QueryMapping public PagedResponse<CarResponse> cars(@Argument int page, @Argument int size) { return carService.getAllCars(null, page, size); }
}
