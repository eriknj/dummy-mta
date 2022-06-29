package net.midgard.dummy.mta;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public List<Address> findByEmailAddress(String emailAddress);

    @Query("SELECT a FROM Address a WHERE a.emailAddress LIKE CONCAT('%',:term,'%')")
    public List<Address> findWhereEmailAddressLike(String term);
}
