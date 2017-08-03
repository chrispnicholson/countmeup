package uk.co.bbc.countmeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.dao.UserRepository;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String userName) throws Exception {
        User newUser = new User();
        newUser.setUserName(userName);
        User registeredUser = userRepository.save(newUser);

        if (registeredUser == null) {
            throw new Exception();
        }

        return registeredUser;
    }

    @Override
    public User getUser(long l) {
        return null;
    }
}
