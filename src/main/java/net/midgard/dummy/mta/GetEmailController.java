package net.midgard.dummy.mta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GetEmailController {

    private final Logger log = LoggerFactory.getLogger(GetEmailController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/email/{index}")
    public Message getMessage(@PathVariable int index)
            throws ResponseStatusException {
        long count = messageService.count();
        if (index < 0) {
            index += count;
        }
        if (index < 0 || index >= count) {
            String errMsg = String.format("Index %d out of bounds", index);
            log.warn(errMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg);
        }
        return messageService.getMessageAtIndex(index);
    }
}
