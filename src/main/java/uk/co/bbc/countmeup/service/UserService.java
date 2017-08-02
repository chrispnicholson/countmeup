package uk.co.bbc.countmeup.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */

@Service
public interface UserService {
    public User registerUser(String userName) throws Exception;
}
