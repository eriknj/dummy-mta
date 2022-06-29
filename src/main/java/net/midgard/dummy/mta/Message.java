package net.midgard.dummy.mta;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private long id;
    private String body;
    @ManyToOne
    private Address from;
    @ManyToOne
    private Address recipient;
    private Instant sent;
    private String subject;

    public Message() {}

    public Message(String subject, String body, Address from, Address recipient) {
        this.body = body;
        this.from = from;
        this.recipient = recipient;
        this.sent = Instant.now();
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public Address getFrom() {
        return this.from;
    }

    public long getId() {
        return this.id;
    }

    public Address getRecipient() {
        return this.recipient;
    }

    public Instant getSent() {
        return this.sent;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRecipient(Address recipient) {
        this.recipient = recipient;
    }

    public void setSent(Instant sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "from: " + from.getEmailAddress() + ", to: " + recipient.getEmailAddress() + ", at: " + sent + " subject: " + subject + " body: " + body;
    }
}
