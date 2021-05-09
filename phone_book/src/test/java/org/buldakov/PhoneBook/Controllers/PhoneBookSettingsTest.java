package org.buldakov.PhoneBook.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.buldakov.PhoneBook.DAO.PhoneBookDAO;
import org.buldakov.PhoneBook.models.PhoneBook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PhoneBookSettings.class)
@ContextConfiguration(classes = org.buldakov.PhoneBook.config.SpringConfig.class)
public class PhoneBookSettingsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PhoneBookDAO phoneBookDAO;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void sendList() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                .get("/PhoneBooks/getAll?user_id=4")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void sendNote() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/PhoneBooks/getOne?book_id=6")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveNote() throws Exception{
        PhoneBook phoneBook = new PhoneBook(1, "Vasiliy Ivanov", 4,"89333456279");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/PhoneBooks/Save")
                .content(objectMapper.writeValueAsString(phoneBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateNote() throws Exception{
        PhoneBook phoneBook = phoneBookDAO.getLast();
        phoneBook = new PhoneBook(phoneBook.getId(), "Vasiliy Ivanovashenko", 4,"89333456281");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/PhoneBooks/Update")
                .content(objectMapper.writeValueAsString(phoneBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteNote() throws Exception {
        PhoneBook phoneBook = phoneBookDAO.getLast();
        mockMvc.perform( MockMvcRequestBuilders
                .post("/PhoneBooks/Delete")
                .content(objectMapper.writeValueAsString(phoneBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void sendNotebyPhone()throws Exception {
        PhoneBook phoneBook = new PhoneBook(6, "Nikita Buldakov", 4,"89533553049");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/PhoneBooks/getbyPhone")
                .content(objectMapper.writeValueAsString(phoneBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

}