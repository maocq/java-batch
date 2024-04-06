package co.com.bancolombia.consumer;

import co.com.bancolombia.model.info.gateways.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class RestConsumer implements InfoService {

    private final RestClient restClient;

    @Override
    public String get() {
        return restClient
                .get()
                .uri("/")
                .retrieve()
                .body(String.class);
    }
}
