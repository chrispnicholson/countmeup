package uk.co.bbc.countmeup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Chris on 01-Aug-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User unregisteredUser;
    private User registeredUser;

    private JacksonTester<User> jsonTester;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, objectMapper);
        unregisteredUser = new User();
        unregisteredUser.setUserName("dougie.jones@lasvegas.co.uk");
        unregisteredUser.setId(null);
        unregisteredUser.setName("Dougie Jones");

        registeredUser = new User();
        registeredUser.setUserName("dougie.jones@lasvegas.co.uk");
        registeredUser.setId(new Long(1234));
        registeredUser.setName("Dougie Jones");
    }

    @Test
    public void registerValidUser() throws Exception {

        String userJson = jsonTester.write(unregisteredUser).getJson();
        Mockito.when(userService.registerUser(unregisteredUser.getUserName())).thenReturn(registeredUser).thenThrow(new Exception());
        // has to register unregisteredUser with valid email address
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.id", is(1234)));

        // call again
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isForbidden());
    }

    @Test
    public void registerInvalidEmailForUser() {
        // if no valid email address, return error
    }

    @Test
    public void registerSameEmailTwice() {
        // if attempt to register unregisteredUser with valid email address again, return error
    }
}
