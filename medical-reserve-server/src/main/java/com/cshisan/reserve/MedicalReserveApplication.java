package com.cshisan.reserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author CShisan
 * @date 2022-2-19 0:05
 */
@EnableScheduling
@SpringBootApplication
public class MedicalReserveApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalReserveApplication.class, args);
    }

}
