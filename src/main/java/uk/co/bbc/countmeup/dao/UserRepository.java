package uk.co.bbc.countmeup.dao;

import org.springframework.data.repository.CrudRepository;
import uk.co.bbc.countmeup.entity.User;

/**
 * Created by Chris on 31-Jul-17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUserName(String s);
    //public User createUser(String userName) throws Exception;
}
