package uk.co.bbc.countmeup.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 03-Aug-17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUser() {
        User user = new User();
        //user.setId(1234l);
        user.setName("Dougie Jones");
        user.setUserName("dougie.jones@lasvegas.com");

        this.entityManager.persist(user);
        User foundUser = this.userRepository.findByUserName("dougie.jones@lasvegas.com");
        Assert.assertEquals("dougie.jones@lasvegas.com", foundUser.getUserName());
    }
}
