package net.midgard.dummy.mta;

import net.midgard.dummy.mta.wisest.Wisest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.subethamail.wiser.Wiser;

@SpringBootApplication
public class DummyMtaApplication {

    @Value("${dummy.mta.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(DummyMtaApplication.class, args);
    }

    @Bean
    public Wiser wiser() {
        Wiser wiser = new Wisest();
        wiser.setPort(port);
        wiser.start();
        return wiser;
    }
        
}
