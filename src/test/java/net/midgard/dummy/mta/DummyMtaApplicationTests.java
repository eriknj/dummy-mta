package net.midgard.dummy.mta;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.midgard.dummy.mta.wisest.Wisest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class DummyMtaApplicationTests {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    Wisest wisest;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup() throws Exception {
        messageRepo.deleteAll();
        wisest.deliver(TestConstants.ME_AT_MIDGARD, TestConstants.YOU_AT_MIDGARD, TestConstants.getEmailStream());
    }

    @Test
    void getEmailCount_One_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/count").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(content().string(equalTo("1")));
    }

    @Test
    void getEmail_Found_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/0").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void getEmailNegativeIndex_Found_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/-1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void getEmail_NotFound_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/2").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void getEmailNegativeIndex_NotFound_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/-2").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void searchEmail_Found_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addresses?searchTerm=me@midgard.net").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void searchEmail_NotFound_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addresses?searchTerm=nope").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void searchMessagesByRecipient_Found_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addresses/2/messages").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void searchMessagesByRecipient_Found_NoMessages_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/1/messages").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void searchMessagesByRecipient_NotFound_404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/address/3/messages").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

}
