package org.lognet.springboot.grpc.demo;


import io.grpc.examples.GreeterGrpc;
import io.grpc.examples.GreeterOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alexf on 28-Jan-16.
 */


@SpringBootApplication
public class GreeterApp {

    @GRpcService(grpcServiceOuterClass = GreeterGrpc.class)
    public static class GreeterService implements GreeterGrpc.Greeter{
        @Override
        public void sayHello(GreeterOuterClass.HelloRequest request, StreamObserver<GreeterOuterClass.HelloReply> responseObserver) {
            final GreeterOuterClass.HelloReply.Builder replyBuilder = GreeterOuterClass.HelloReply.newBuilder().setMessage("Hello " + request.getName());
            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(GreeterApp.class,args);
    }
}

