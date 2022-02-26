package net.midgard.dummy.mta;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

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
public class GetEmailCountController {

    private final Logger log = LoggerFactory.getLogger(GetEmailCountController.class);

    @Inject
    private Wiser wiser;

    @GetMapping("/email/count")
    public int getEmailCount() {
        log.trace("Entered method getEmailCount");
        int retVal = this.wiser.getMessages().size();
        log.trace("About to leave method getEmailCount");
        return retVal;
    }

}
