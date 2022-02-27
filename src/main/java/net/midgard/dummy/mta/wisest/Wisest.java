package net.midgard.dummy.mta.wisest;

import java.io.InputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.mail.MessagingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

public class Wisest extends Wiser {

    private final Logger log = LoggerFactory.getLogger(Wisest.class);

    @Inject
    private RestHighLevelClient client;

    @Override
    public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
        log.trace("Entered method deliver");
        super.deliver(from, recipient, data);
        int messageIndex = this.messages.size() - 1;
        WiserMessage newMessage = this.messages.get(messageIndex);
        try {
            String json = new ObjectMapper().writeValueAsString(newMessage.getMimeMessage());
            IndexRequest request = new IndexRequest("dummy-mta");
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            log.info(String.format("Indexed: %s", String.valueOf(Result.CREATED.equals(response.getResult()))));

        } catch (MessagingException e) {
            IOException ioe = new IOException("Could not produce MimeMessage from WiserMessage");
            ioe.initCause(e);
            throw ioe;
        }
        log.trace("About to leave method deliver");
    }

}
