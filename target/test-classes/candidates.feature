Feature: counting votes
  As a BBC television presenter
  I want to see the counts for candidates within a given time frame
  So that I can announce the winner of the competitions

  Scenario: partition choices sent to the queue by candidate percentage
    Given 10000000 votes were received
    And votes were distributed against candidates as:
          | candidate          | percentage |
          | candidate-1        |      5     |
          | candidate-2        |      10    |
          | candidate-3        |      20    |
          | candidate-4        |      25    |
          | candidate-5        |      40    |
    And no more than 3 votes per user are allowed
    When CountMeUp is asked for the results
    Then it responds in under 1 seconds
    And the final counts are:
          | candidate          |    count     |
          | candidate-1        |    500000    |
          | candidate-2        |    1000000   |
          | candidate-3        |    2000000   |
          | candidate-4        |    2000000   |
          | candidate-5        |    3000000   |