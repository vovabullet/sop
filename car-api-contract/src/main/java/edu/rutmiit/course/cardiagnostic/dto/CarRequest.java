package edu.rutmiit.course.cardiagnostic.dto;
import jakarta.validation.constraints.*;
public record CarRequest(@NotBlank String brand, @NotBlank String model, int year, @Size(min=17, max=17) String vin, @NotBlank String licensePlate, @NotNull Long mechanicId) {}
