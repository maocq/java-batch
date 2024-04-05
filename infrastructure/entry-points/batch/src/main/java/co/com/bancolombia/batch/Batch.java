package co.com.bancolombia.batch;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Log
@Configuration
public class Batch {

    @Bean
    public int startBatch() {
        log.info("Starting batch");
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()){
        //try (var executor = Executors.newFixedThreadPool(200)){
            IntStream.range(0, 1_000_000)
                    .forEach(i -> {
                        executor.submit(() -> {
                            sleep(10);
                            return "Ok";
                        });
                    });
        }
        log.info("\n\n\nEnd!!!\n\n\n");

        return 0;
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
