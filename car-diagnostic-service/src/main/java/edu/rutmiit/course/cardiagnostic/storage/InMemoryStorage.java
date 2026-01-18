package edu.rutmiit.course.cardiagnostic.storage;

import edu.rutmiit.course.cardiagnostic.dto.MechanicResponse;
import edu.rutmiit.course.cardiagnostic.dto.CarResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryStorage {
    public final Map<Long, MechanicResponse> mechanics = new ConcurrentHashMap<>();
    public final Map<Long, CarResponse> cars = new ConcurrentHashMap<>();

    public final AtomicLong mechanicSequence = new AtomicLong(0);
    public final AtomicLong carSequence = new AtomicLong(0);

    @PostConstruct
    public void init() {
        MechanicResponse mechanic1 = new MechanicResponse(mechanicSequence.incrementAndGet(), "Иван", "Иванов");
        MechanicResponse mechanic2 = new MechanicResponse(mechanicSequence.incrementAndGet(), "Петр", "Петров");
        mechanics.put(mechanic1.getId(), mechanic1);
        mechanics.put(mechanic2.getId(), mechanic2);

        long carId1 = carSequence.incrementAndGet();
        cars.put(carId1, new CarResponse(carId1, "Toyota", "Camry", 2020, "VIN12345678901234", "А123ВВ77", mechanic1, LocalDateTime.now()));

        long carId2 = carSequence.incrementAndGet();
        cars.put(carId2, new CarResponse(carId2, "Lada", "Vesta", 2022, "VIN98765432109876", "Б456ГГ99", mechanic2, LocalDateTime.now()));
    }
}
