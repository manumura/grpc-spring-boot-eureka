package com.manolo.grpcspringbooteurekaserver;

import com.manolo.grpc.HelloRequest;
import com.manolo.grpc.HelloResponse;
import com.manolo.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class HelloGrpcServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    private final Logger logger = LoggerFactory.getLogger(HelloGrpcServiceImpl.class);

    // https://github.com/LogNet/grpc-spring-boot-starter
    // https://github.com/yidongnan/grpc-spring-boot-starter
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = new StringBuffer()
                .append("Hello, ")
                .append(request.getFistName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();
        logger.info("response: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
