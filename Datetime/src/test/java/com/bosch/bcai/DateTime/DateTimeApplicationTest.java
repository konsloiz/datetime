package com.bosch.bcai.DateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class DateTimeApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnISODateTimeIsOk() throws Exception {
        this.mockMvc.perform(get("/now")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnSimpleDateTimeIsOk() throws Exception {
        this.mockMvc.perform(get("/now/simple")).andDo(print()).andExpect(status().isOk());
    }
}