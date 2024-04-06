package co.com.bancolombia.batch;

import co.com.bancolombia.model.balance.gateways.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Log
@Configuration
@RequiredArgsConstructor
public class Batch {

    private final BalanceRepository balanceRepository;

    @Bean
    public int startBatch(@Value("${batch.operation}") String batchOperation) {
        log.info("Starting batch");

        switch (batchOperation) {
            case "demo":
                demo();
                break;
            case "dynamodb":
                dynamodb();
                break;
            default:
                log.warning("Invalid operation");
        }

        log.info("\n\n\nEnd!!!\n\n\n");
        return 0;
    }

    void demo() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()){
        //try (var executor = Executors.newFixedThreadPool(200)){
            IntStream.range(0, 1_000_000)
                    .forEach(i -> {
                        executor.submit(() -> {
                            sleep(10);
                        });
                    });
        }
    }

    void dynamodb() {
        try (var executor = Executors.newFixedThreadPool(200)){
            IntStream.range(0, 100_000)
                    .forEach(i -> {
                        executor.submit(() -> {
                            balanceRepository.put(Integer.toString(i));
                        });
                    });
        }
    }


    void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
