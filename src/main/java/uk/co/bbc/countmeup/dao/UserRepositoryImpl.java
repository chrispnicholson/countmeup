package uk.co.bbc.countmeup.dao;

import org.springframework.stereotype.Repository;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User createUser(String userName) {
        return null;
    }
}
