package uk.co.bbc.countmeup;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import uk.co.bbc.countmeup.entity.User;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Chris on 30-Jul-17.
 */
public class StepDefs extends SpringIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<User> jsonUserTester;

    //private JacksonTester<Candidate> jsonCandidateTester;

    private int noOfVotes;
    
    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:"+ port + "/version");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertThat(latestResponse.getBody(), is(version));
    }

    @Given("^(\\d+) votes were received$")
    public void votes_were_received(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // set up database with 10000000 applicants
        //voteList = new ArrayList<Vote>(arg1);

        // register 10000000 applicants
        //executePost("http://localhost:"+ port + "/users");

        // register a load of users
        JacksonTester.initFields(this, objectMapper);
        noOfVotes = arg1;

        for (int i = 0; i < arg1; i++) {
            User user = new User();
            user.setUserName("user-" + i);

            String jsonBody = jsonUserTester.write(user).getJson();
            executePost("http://localhost:" + port + "/users", jsonBody);
        }
    }

    @Given("^votes were distributed against candidates as:$")
    public void votes_were_distributed_against_candidates_as(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        Map<String, Integer> candidatesAndVotes = arg1.asMap(String.class, Integer.class);
        Set<String> candidates = candidatesAndVotes.keySet();

        // create candidates - five POSTs, using first five users
        for (int i = 0; i < candidates.size(); i++) {
            User user = new User();
            user.setId(new Long(i+1));
            String jsonBody = jsonUserTester.write(user).getJson();
            executePost("http://localhost:" + port + "/users", jsonBody);
        }

        // candidates now created

        // create votes - cycle through users and spread out votes as need be

    }

    @Given("^no more than (\\d+) votes per user are allowed$")
    public void no_more_than_votes_per_user_are_allowed(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();

        // vote more than arg1 times with a specific user against a candidate - shouldn't count
    }

    @When("^CountMeUp is asked for the results$")
    public void countmeup_is_asked_for_the_results() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();

        // GET call for candidates
    }

    @Then("^it responds in under (\\d+) seconds$")
    public void it_responds_in_under_seconds(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();

        // optional, but can measure API call
    }

    @Then("^the final counts are:$")
    public void the_final_counts_are(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();

        // parse above API call for candidates
    }
}