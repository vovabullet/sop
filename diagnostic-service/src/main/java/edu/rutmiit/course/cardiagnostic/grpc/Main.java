package edu.rutmiit.course.cardiagnostic.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new DiagnosticServiceImpl())
                .build();

        server.start();
        System.out.println("Diagnostic Service started on port 9090");
        server.awaitTermination();
    }
}
