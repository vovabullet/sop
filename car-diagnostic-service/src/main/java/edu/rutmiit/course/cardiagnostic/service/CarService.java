package edu.rutmiit.course.cardiagnostic.service;

import edu.rutmiit.course.cardiagnostic.dto.*;
import edu.rutmiit.course.cardiagnostic.events.CarCreatedEvent;
import edu.rutmiit.course.cardiagnostic.exception.ResourceNotFoundException;
import edu.rutmiit.course.cardiagnostic.exception.VinAlreadyExistsException;
import edu.rutmiit.course.cardiagnostic.storage.InMemoryStorage;
import edu.rutmiit.course.cardiagnostic.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final InMemoryStorage storage;
    private final MechanicService mechanicService;
    private final RabbitTemplate rabbitTemplate;

    public CarService(InMemoryStorage storage, MechanicService mechanicService, RabbitTemplate rabbitTemplate) {
        this.storage = storage;
        this.mechanicService = mechanicService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PagedResponse<CarResponse> getAllCars(Long mechanicId, int page, int size) {
        List<CarResponse> filtered = storage.cars.values().stream()
                .filter(c -> mechanicId == null || c.getMechanic().getId().equals(mechanicId))
                .collect(Collectors.toList());
        int totalElements = filtered.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalElements);
        List<CarResponse> content = (fromIndex < totalElements) ? filtered.subList(fromIndex, toIndex) : new ArrayList<>();
        return new PagedResponse<>(content, page, size, totalElements, totalPages, page >= totalPages - 1);
    }

    public CarResponse getCarById(Long id) {
        CarResponse car = storage.cars.get(id);
        if (car == null) throw new ResourceNotFoundException("Автомобиль не найден");
        return car;
    }

    public CarResponse createCar(CarRequest request) {
        if (storage.cars.values().stream().anyMatch(c -> c.getVin().equals(request.vin()))) throw new VinAlreadyExistsException("VIN существует");
        MechanicResponse mechanic = mechanicService.getMechanicById(request.mechanicId());
        Long id = storage.carSequence.incrementAndGet();
        CarResponse car = new CarResponse(id, request.brand(), request.model(), request.year(), request.vin(), request.licensePlate(), mechanic, LocalDateTime.now());
        storage.cars.put(id, car);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "car.created", new CarCreatedEvent(car.getId(), car.getBrand(), car.getModel(), car.getLicensePlate(), mechanic.getFirstName() + " " + mechanic.getLastName()));
        return car;
    }

    public CarResponse updateCar(Long id, UpdateCarRequest request) {
        CarResponse existing = getCarById(id);
        CarResponse updated = new CarResponse(id, request.brand(), request.model(), request.year(), request.vin(), request.licensePlate(), existing.getMechanic(), existing.getCreatedAt());
        storage.cars.put(id, updated);
        return updated;
    }

    public void deleteCar(Long id) { storage.cars.remove(id); }
}
