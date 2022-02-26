package net.midgard.dummy.mta;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private MockMvc mvc;

    @Test
    void getEmailCount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/count").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(content().string(equalTo("0")));
    }

    @Test
    void getEmail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void searchEmail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/email?searchTerm=term").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

}
