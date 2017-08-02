package uk.co.bbc.countmeup.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.dao.UserRepository;
import uk.co.bbc.countmeup.entity.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by Chris on 31-Jul-17.
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User newUser;
    private User createdUser;

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Before
    public void setUp() {
        newUser = new User();
        newUser.setUserName("dougie.jones@lasvegas.co.uk");

        createdUser = new User();
        createdUser.setUserName("dougie.jones@lasvegas.co.uk");
        createdUser.setId(1234l);

    }

    @Test
    public void registerUser() throws Exception {
        Mockito.when(userRepository.createUser(newUser.getUserName())).thenReturn(createdUser).thenThrow(new Exception());
        // creates a newUser with an email address
        // cannot create another newUser associated with same email address
        User registeredUser = null;
        try {
            registeredUser = userService.registerUser(newUser.getUserName());
        } catch (Exception e) {
            // unique email/username should be registered with a new user
            fail();
        }
        // further iteration of this will be an access token will be produced
        assertNotNull(registeredUser);
        assertNotNull(registeredUser.getId()); // if this passes, new user created

        try {
            registeredUser = userService.registerUser(newUser.getUserName());
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void getUsers() {

    }
}
