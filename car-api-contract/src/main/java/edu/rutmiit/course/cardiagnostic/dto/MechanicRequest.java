package edu.rutmiit.course.cardiagnostic.dto;
import jakarta.validation.constraints.NotBlank;
public record MechanicRequest(@NotBlank String firstName, @NotBlank String lastName) {}
