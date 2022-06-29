package net.midgard.dummy.mta;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SearchEmailController {

    private final Logger log = LoggerFactory.getLogger(SearchEmailController.class);

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public List<Address> searchEmails(@RequestParam String searchTerm) {
        List<Address> hits = addressService.search(searchTerm);
        if (hits.isEmpty()) {
            String errMsg = String.format("Search term %s produced no hits", searchTerm);
            log.warn(errMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMsg);
        }
        return hits;
    }

}
