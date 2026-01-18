package edu.rutmiit.course.cardiagnostic.service;

import edu.rutmiit.course.cardiagnostic.dto.MechanicRequest;
import edu.rutmiit.course.cardiagnostic.dto.MechanicResponse;
import edu.rutmiit.course.cardiagnostic.exception.ResourceNotFoundException;
import edu.rutmiit.course.cardiagnostic.storage.InMemoryStorage;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MechanicService {
    private final InMemoryStorage storage;
    public MechanicService(InMemoryStorage storage) { this.storage = storage; }
    public List<MechanicResponse> getAllMechanics() { return new ArrayList<>(storage.mechanics.values()); }
    public MechanicResponse getMechanicById(Long id) {
        MechanicResponse mechanic = storage.mechanics.get(id);
        if (mechanic == null) throw new ResourceNotFoundException("Механик не найден");
        return mechanic;
    }
    public MechanicResponse createMechanic(MechanicRequest request) {
        Long id = storage.mechanicSequence.incrementAndGet();
        MechanicResponse mechanic = new MechanicResponse(id, request.firstName(), request.lastName());
        storage.mechanics.put(id, mechanic);
        return mechanic;
    }
    public MechanicResponse updateMechanic(Long id, MechanicRequest request) {
        if (!storage.mechanics.containsKey(id)) throw new ResourceNotFoundException("Механик не найден");
        MechanicResponse updated = new MechanicResponse(id, request.firstName(), request.lastName());
        storage.mechanics.put(id, updated);
        return updated;
    }
    public void deleteMechanic(Long id) { storage.mechanics.remove(id); }
}
