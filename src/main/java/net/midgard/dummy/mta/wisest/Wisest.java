package net.midgard.dummy.mta.wisest;

import java.io.InputStream;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import net.midgard.dummy.mta.Address;
import net.midgard.dummy.mta.AddressService;
import net.midgard.dummy.mta.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

public class Wisest extends Wiser {

    @Autowired
    private AddressService addressService;

    @Autowired
    private MessageService messageService;

    @Override
    public void deliver(String from, String recipient, InputStream data) throws IOException {
        Address fromAddress = addressService.upsert(from);
        Address recipientAddress = addressService.upsert(recipient);
        try {
            MimeMessage mime = new MimeMessage(getSession(), data);
            messageService.createMessage(fromAddress, recipientAddress, mime);
        } catch (MessagingException e) {
            throw new IOException(e);
        }
    }

}
