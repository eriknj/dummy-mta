package net.midgard.dummy.mta;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository repo;

    public Message createMessage(Address from, Address to, MimeMessage mime) throws IOException, MessagingException {
        Object content = mime.getContent();
        String body;
        if (content instanceof String) {
            body = (String) content;
        } else  {
            body = "Message text unavailable.";
            log.warn(body);
        }
        Message m = new Message(mime.getSubject(), body.trim(), from, to);
        repo.save(m);
        return m;
    }

    public long count() {
        return repo.count();
    }

    public Message getMessageAtIndex(int index) {
        Sort sort = Sort.by(Sort.Direction.ASC, "sent");
        Pageable pageable = PageRequest.of(index, 1, sort);
        Page<Message> page = repo.findAll(pageable);
        if (!page.hasContent()) {
            throw new NoResultException(String.format("No message at index %d", index));
        }
        return page.getContent().get(0);
    }

    public List<Message> getMessagesByRecipient(long addressId) {
        return repo.findByRecipientId(addressId);
    }
}
