package net.midgard.dummy.mta;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = AddressService.class)
class AddressServiceTest {

    @MockBean
    AddressRepository repo;

    @Autowired
    AddressService target;

    @Test
    void testSearch() throws Exception {
        List<Address> results = target.search(TestConstants.ME_AT_MIDGARD);
        assertTrue(results.isEmpty());
    }
    
    @Test
    void testUpsert() throws Exception {
        target.upsert(TestConstants.ME_AT_MIDGARD);
        Address a = target.upsert(TestConstants.ME_AT_MIDGARD);
        assertEquals(TestConstants.ME_AT_MIDGARD, a.getEmailAddress());
    }
}
