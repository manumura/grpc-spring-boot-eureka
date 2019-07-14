package com.manolo.grpcspringbooteurekaclient;

import com.manolo.grpc.HelloRequest;
import com.manolo.grpc.HelloResponse;
import com.manolo.grpc.HelloServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceConsumer {

    private final Logger logger = LoggerFactory.getLogger(HelloServiceConsumer.class);

    @Autowired
    private EurekaClient eurekaClient;

    public String sayHello(String firstName, String lastName) {
        final InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("hello-service", false);
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                .usePlaintext()
                .build();

        final HelloRequest request = HelloRequest.newBuilder()
                .setFistName(firstName)
                .setLastName(lastName)
                .build();

        final HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        final HelloResponse response = stub.hello(request);
        logger.info("response: {}", response);

        return response.getGreeting();
    }
}
