package net.midgard.dummy.mta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetEmailCountController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/email/count")
    public long getEmailCount() {
        return messageService.count();
    }

}
