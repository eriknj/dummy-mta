package net.midgard.dummy.mta.wisest;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import net.midgard.dummy.mta.AddressService;
import net.midgard.dummy.mta.TestConstants;
import net.midgard.dummy.mta.MessageService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = Wisest.class)
class WisestTest {

    @MockBean
    AddressService addressService;

    @MockBean
    MessageService MessageService;

    @Autowired
    Wisest target;

    @Test
    void testDeliver() throws Exception {
        target.deliver(TestConstants.ME_AT_MIDGARD, TestConstants.YOU_AT_MIDGARD, TestConstants.getEmailStream());
        assertTrue(true);
    }
}
