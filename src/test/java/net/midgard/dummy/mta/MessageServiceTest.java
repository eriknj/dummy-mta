package net.midgard.dummy.mta;

import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest(classes = MessageService.class)
class MessageServiceTest {

    @MockBean
    MessageRepository repo;

    @Autowired
    MessageService target;

    @Test
    void testCreateMessage() throws Exception {
        Address from = new Address(TestConstants.ME_AT_MIDGARD);
        Address to = new Address(TestConstants.YOU_AT_MIDGARD);
        MimeMessage mime = Mockito.mock(MimeMessage.class);

        Message message = target.createMessage(from, to, mime);
        assertEquals(to, message.getRecipient());
    }
    
    @Test
    void testGetMessageAtIndex() throws Exception {
        Page<Message> page = Mockito.mock(Page.class);
        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        assertThrows(NoResultException.class, () -> {
            target.getMessageAtIndex(1);
        });
    }
}
