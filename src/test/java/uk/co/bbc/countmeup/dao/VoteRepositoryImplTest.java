package uk.co.bbc.countmeup.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.countmeup.entity.Candidate;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.entity.Vote;

/**
 * Created by Chris on 03-Aug-17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void voteCreated() {
        User obama = new User();
        obama.setUserName("barack.obama@whitehouse.gov");
        obama.setName("Barack Obama");

        entityManager.persist(obama);

        Candidate candidate = new Candidate();
        candidate.setUser(obama);

        entityManager.persist(candidate);

        User commonVoter = new User();
        commonVoter.setUserName("common.voter@hotmail.com");
        commonVoter.setName("Common Voter");

        entityManager.persist(commonVoter);

        Vote vote = new Vote(commonVoter, candidate);

        entityManager.persist(vote);

        Assert.assertEquals(1, voteRepository.count());
    }
}
