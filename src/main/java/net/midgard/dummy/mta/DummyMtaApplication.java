package net.midgard.dummy.mta;

import java.util.Arrays;

import net.midgard.dummy.mta.wisest.Wisest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

@SpringBootApplication
public class DummyMtaApplication {

    private static final String DEFAULT_ELASTICSEARCH_HOST = "elasticsearch:9200";
    private static final int DEFAULT_SMTP_PORT = 2500;

    public static void main(String[] args) {
        SpringApplication.run(DummyMtaApplication.class, args);
    }

    @Bean
    public Wiser wiser() {
        Wiser wiser = new Wisest();
        wiser.setPort(getSmtpPort());
        wiser.start();
        return wiser;
    }

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(getElasticsearchHost()).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return client;
    }

    private String getElasticsearchHost() {
        String host = System.getenv("DUMMY_MTA_ELASTICSEARCH_HOST");
        if (host == null) {
            host = DEFAULT_ELASTICSEARCH_HOST;
        }
        return host;
    }

    private int getSmtpPort() {
        int port;
        try {
            port = Integer.parseInt(System.getenv("DUMMY_MTA_PORT"));
        } catch (NullPointerException | NumberFormatException e) {
            port = DEFAULT_SMTP_PORT;
        }
        return port;
    }
        
}
