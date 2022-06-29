package net.midgard.dummy.mta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides REST endpoint GET /addresses/:addressId/messages
 *
 * This method returns all messages addressed to a given recipient identified by <code>addressId</code>
 *
 */
@RestController
public class GetAddressesAddressIdMessagesController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/addresses/{addressId}/messages")
    public List<Message> searchEmails(@PathVariable long addressId) {
        return messageService.getMessagesByRecipient(addressId);
    }
}
