package edu.rutmiit.course.cardiagnostic.controllers;

import edu.rutmiit.course.cardiagnostic.config.RabbitMQConfig;
import edu.rutmiit.course.cardiagnostic.events.CarDiagnosedEvent;
import edu.rutmiit.course.cardiagnostic.grpc.DiagnosticServiceGrpc;
import edu.rutmiit.course.cardiagnostic.grpc.CarConditionRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticController {
    @GrpcClient("diagnostic-service")
    private DiagnosticServiceGrpc.DiagnosticServiceBlockingStub diagnosticStub;
    private final RabbitTemplate rabbitTemplate;
    public DiagnosticController(RabbitTemplate rabbitTemplate) { this.rabbitTemplate = rabbitTemplate; }

    @PostMapping("/api/cars/{id}/diagnose")
    public String diagnoseCar(@PathVariable Long id) {
        var request = CarConditionRequest.newBuilder().setCarId(id).setCategory("General").build();
        var response = diagnosticStub.calculateCarCondition(request);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", new CarDiagnosedEvent(response.getCarId(), response.getConditionScore(), response.getVerdict()));
        return "Score: " + response.getConditionScore();
    }
}
