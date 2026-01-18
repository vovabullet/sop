package edu.rutmiit.course.cardiagnostic.dto;
import java.time.LocalDateTime;
import org.springframework.hateoas.RepresentationModel;
public class CarResponse extends RepresentationModel<CarResponse> {
    private final Long id; private final String brand; private final String model; private final int year; private final String vin; private final String licensePlate; private final MechanicResponse mechanic; private final LocalDateTime createdAt;
    public CarResponse(Long id, String brand, String model, int year, String vin, String licensePlate, MechanicResponse mechanic, LocalDateTime createdAt) {
        this.id = id; this.brand = brand; this.model = model; this.year = year; this.vin = vin; this.licensePlate = licensePlate; this.mechanic = mechanic; this.createdAt = createdAt;
    }
    public Long getId() { return id; } public String getBrand() { return brand; } public String getModel() { return model; } public int getYear() { return year; } public String getVin() { return vin; } public String getLicensePlate() { return licensePlate; } public MechanicResponse getMechanic() { return mechanic; } public LocalDateTime getCreatedAt() { return createdAt; }
}
