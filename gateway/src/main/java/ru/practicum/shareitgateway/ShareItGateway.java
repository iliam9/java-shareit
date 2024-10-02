package ru.practicum.shareitgateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ShareItGateway {

    public static void main(String[] args) {
        SpringApplication.run(ShareItGateway.class, args);

        log.info("Приложение Gateway запущено");
    }
}