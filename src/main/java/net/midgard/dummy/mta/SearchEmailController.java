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
public class SearchEmailController {

    private final Logger log = LoggerFactory.getLogger(SearchEmailController.class);

    @Inject
    private Wiser wiser;

    @GetMapping("/email")
    public Set<MailRecord> searchEmails(@RequestParam String searchTerm)
            throws MessagingException, ParseException {
        log.trace("Entered method searchEmails");
        log.info(String.format("Searching for %s", searchTerm));
        Set<MailRecord> hits = new HashSet<>();
        for (WiserMessage msg : wiser.getMessages()) {
            String msgStr = msg.toString();
            if (msgStr.contains(searchTerm)) {
                hits.add(new MailRecord(msg));
                log.info(String.format("Search hit on %s", msgStr));
            }
        }
        if (hits.isEmpty()) {
            String errMsg = String.format("Search term %s produced no hits", searchTerm);
            log.warn(errMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg);
        }
        log.trace("About to leave method searchEmails");
        return hits;
    }

}
