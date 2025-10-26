//package uz.camunda.task.config;
//
//import io.camunda.zeebe.client.ZeebeClient;
//import io.camunda.zeebe.client.ZeebeClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class ZeebeConfiguration {
//
//    @Bean
//    @Primary
//    public ZeebeClientBuilder zeebeClientBuilder() {
//
//        return ZeebeClient.newClientBuilder()
//                .gatewayAddress("127.0.0.1:26500") // local gateway
//                .usePlaintext(); // disable TLS for local
//    }
//
//    @Bean
//    @Primary
//    public ZeebeClient zeebeClient(ZeebeClientBuilder builder) {
//        return builder.build();
//    }
//}
