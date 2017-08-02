package uk.co.bbc.countmeup.dao;

import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface UserRepository {
    public User createUser(String userName) throws Exception;
}
