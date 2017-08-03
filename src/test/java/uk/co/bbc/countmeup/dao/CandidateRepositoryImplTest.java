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

/**
 * Created by Chris on 03-Aug-17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CandidateRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void findCandidate() throws Exception {
        User user = new User();
        user.setUserName("david.lynch@davidlynchfoundation.org");
        user.setName("David Lynch");
        Candidate candidate = new Candidate();
        candidate.setUser(user);

        this.entityManager.persist(candidate);

        Candidate foundCandidate = this.candidateRepository.findByUser(user);
        Assert.assertEquals("david.lynch@davidlynchfoundation.org", foundCandidate.getUser().getUserName());
        Assert.assertEquals("David Lynch", foundCandidate.getUser().getName());

    }
}
