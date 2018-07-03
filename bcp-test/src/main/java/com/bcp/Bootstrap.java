package com.bcp;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bcp.repository.TestRepository;

@SpringBootApplication
public class Bootstrap implements CommandLineRunner {
    private TestRepository testRepository;

    public Bootstrap(TestRepository testBcpStringRepository) {
        this.testRepository = testBcpStringRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = IntStream.range(0, 20)
                .mapToObj(index -> CompletableFuture.runAsync(() -> testRepository.insert(), executorService))
                .collect(Collectors.toList());
        futures.stream().forEach(CompletableFuture::join);
    }
}
