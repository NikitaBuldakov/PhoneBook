package org.buldakov.PhoneBook.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.buldakov.PhoneBook.models.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneBookSettings.class)
@ContextConfiguration(classes = org.buldakov.PhoneBook.config.SpringConfig.class)
public class UserSettingsTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void sendList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/Users/getAll")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void sendUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/Users/getOne?user_id=4")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("4"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveUser() throws Exception{
        Users users = new Users(0, "Andrey Nikitin","89992178195");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/Users/Save")
                .content(objectMapper.writeValueAsString(users))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUser() throws Exception{
        Users users = new Users(6, "Andrey Nikitin Loh","89992178195");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/Users/Update")
                .content(objectMapper.writeValueAsString(users))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUser() throws Exception{
        Users users = new Users(5, "Andrey Nikitin","89992178195");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/Users/Delete")
                .content(objectMapper.writeValueAsString(users))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserbyName() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                .get("/Users/getbyName?user_name=N")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }
}