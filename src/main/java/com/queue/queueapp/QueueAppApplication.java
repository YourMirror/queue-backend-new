package com.queue.queueapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

@SpringBootApplication
public class QueueAppApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        // Set environment variables
        System.setProperty("DB_HOST", Objects.requireNonNull(dotenv.get("DB_HOST")));
        System.setProperty("DB_PORT", Objects.requireNonNull(dotenv.get("DB_PORT")));
        System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        System.setProperty("TWILIO_ACCOUNT_SID", Objects.requireNonNull(dotenv.get("TWILIO_ACCOUNT_SID")));
        System.setProperty("TWILIO_AUTH_TOKEN", Objects.requireNonNull(dotenv.get("TWILIO_AUTH_TOKEN")));
        System.setProperty("TWILIO_SERVICE_SID", Objects.requireNonNull(dotenv.get("TWILIO_SERVICE_SID")));

        SpringApplication.run(QueueAppApplication.class, args);

    }

}
