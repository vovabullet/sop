package edu.rutmiit.course.cardiagnostic.dto;
import jakarta.validation.constraints.*;
public record UpdateCarRequest(@NotBlank String brand, @NotBlank String model, int year, @Size(min=17, max=17) String vin, @NotBlank String licensePlate) {}
