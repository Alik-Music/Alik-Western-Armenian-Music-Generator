package com.balians.musicgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
public class MusicgenBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicgenBackendApplication.class, args);
    }
}
