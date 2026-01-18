package edu.rutmiit.course.cardiagnostic.events;
import java.io.Serializable;
public record CarDiagnosedEvent(Long carId, Integer score, String verdict) implements Serializable {}
