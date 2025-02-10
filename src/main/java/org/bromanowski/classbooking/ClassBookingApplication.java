package org.bromanowski.classbooking;

import org.bromanowski.classbooking.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ClassBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassBookingApplication.class, args);
    }

}
