package edu.rutmiit.course.cardiagnostic.events;
import java.io.Serializable;
public record CarCreatedEvent(Long carId, String brand, String model, String licensePlate, String mechanicFullName) implements Serializable {}
