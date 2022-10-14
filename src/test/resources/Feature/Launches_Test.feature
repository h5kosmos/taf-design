Feature: Launches information

  Scenario Template: Check launch information.
    Given User is on Home Page
    When User Navigate to Launches Page
    Then Launch with id: <launchId>  have correct information
      | <launchId> | <total> | <passed> | <failed> | <skipped> | <productBugs> | <autoBugs> | <systemIssues> | <toInvestigate> |
    Examples:
      | launchId | total | passed | failed | skipped | productBugs | autoBugs | systemIssues | toInvestigate |
      | 3938640  | 30    | 30     | 0      | 0       | 0           | 0        | 0            | 0             |
      | 3938635  | 25    | 20     | 5      | 0       | 4           | 1        | 0            | 2             |
      | 3938628  | 20    | 10     | 8      | 2       | 4           | 4        | 0            | 10            |
      | 3938626  | 15    | 5      | 9      | 1       | 1           | 5        | 4            | 8             |
      | 3938625  | 10    | 1      | 9      | 0       | 0           | 1        | 8            | 5             |
      | 3938623  | 30    | 30     | 0      | 0       | 0           | 0        | 0            | 0             |
      | 3938622  | 25    | 20     | 5      | 0       | 4           | 1        | 0            | 2             |
      | 3938621  | 20    | 10     | 8      | 2       | 4           | 4        | 0            | 10            |
      | 3938620  | 15    | 5      | 9      | 1       | 1           | 5        | 4            | 8             |
      | 3938619  | 10    | 1      | 9      | 0       | 0           | 1        | 8            | 5             |
