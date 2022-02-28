package net.midgard.dummy.mta;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

@RestController
public class GetEmailController {

    private final Logger log = LoggerFactory.getLogger(GetEmailController.class);

    @Inject
    private Wiser wiser;

    @GetMapping("/email/{index}")
    public MailRecord getEmail(@PathVariable int index)
            throws ResponseStatusException, MessagingException, ParseException {
        log.trace("Entered method getEmail");
        log.info(String.format("Retrieving email w/ index %d", index));
        if (index >= this.wiser.getMessages().size()) {
            String errMsg = String.format("Index %d out of bounds", index);
            log.warn(errMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg);
        }
        WiserMessage wm = this.wiser.getMessages().get(index);
        MailRecord retVal = new MailRecord(wm);
        log.trace("About to leave method getEmail");
        return retVal;
    }
}
