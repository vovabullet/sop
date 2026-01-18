package edu.rutmiit.course.cardiagnostic.listeners;
import edu.rutmiit.course.cardiagnostic.events.CarCreatedEvent;
import edu.rutmiit.course.cardiagnostic.events.CarDiagnosedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class CarEventListener {
    private static final Logger log = LoggerFactory.getLogger(CarEventListener.class);
    @RabbitListener(queues = "car-audit-queue")
    public void handleCarCreated(CarCreatedEvent event) { log.info("Audit car created: {}", event); }
    @RabbitListener(queues = "car-rating-audit-queue")
    public void handleCarDiagnosed(CarDiagnosedEvent event) { log.info("Audit car diagnosed: {}", event); }
}
