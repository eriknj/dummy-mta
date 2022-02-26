package net.midgard.dummy.mta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

@SpringBootApplication
public class DummyMtaApplication {

    private static final int DEFAULT_PORT = 2500;

    public static void main(String[] args) {
        SpringApplication.run(DummyMtaApplication.class, args);
    }

    @Bean
    public Wiser wiser() {
        Wiser wiser = new Wiser();
        int port;
        try {
            port = Integer.parseInt(System.getenv("DUMMY_MTA_PORT"));
        } catch (NullPointerException | NumberFormatException e) {
            port = DEFAULT_PORT;
        }
        wiser.setPort(port);
        wiser.start();
        return wiser;
    }
}
