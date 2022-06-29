package net.midgard.dummy.mta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repo;

    public List<Address> search(String searchTerm) {
        return repo.findWhereEmailAddressLike(searchTerm);
    }

    public Address upsert(String emailAddress) {
        Address address;
        List<Address> existing = new ArrayList<>();
        for (Address a : repo.findByEmailAddress(emailAddress)) {
            existing.add(a);
        }
        if (existing.isEmpty()) {
            address = new Address(emailAddress);
            repo.save(address);
        } else {
            address = existing.get(0);
        }
        return address;
    }
}
