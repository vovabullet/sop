package edu.rutmiit.course.cardiagnostic.grpc;

import io.grpc.stub.StreamObserver;
import java.util.Random;

public class DiagnosticServiceImpl extends DiagnosticServiceGrpc.DiagnosticServiceImplBase {
    @Override
    public void calculateCarCondition(CarConditionRequest request, StreamObserver<CarConditionResponse> responseObserver) {
        int score = new Random().nextInt(101);
        String verdict = score > 80 ? "Excellent" : score > 50 ? "Good" : score > 20 ? "Fair" : "Poor";

        CarConditionResponse response = CarConditionResponse.newBuilder()
                .setCarId(request.getCarId())
                .setConditionScore(score)
                .setVerdict(verdict)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
