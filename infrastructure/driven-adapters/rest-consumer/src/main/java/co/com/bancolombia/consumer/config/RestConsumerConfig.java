package co.com.bancolombia.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConsumerConfig {

    @Value("${adapter.restconsumer.url}")
    private String url;

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(url)
                .requestFactory(new JdkClientHttpRequestFactory())
                .build();
    }
}
