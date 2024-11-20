Feature: Extract and report footer hyperlinks
  @smoke
  Scenario: Extract all footer hyperlinks and check for duplicates
    Given I am on the DP2 home page
    When I scroll down to the footer section    
    And I collect all the hyperlinks in the footer
    Then I should save the hyperlinks into a CSV file
    And I should check if any duplicate hyperlinks are present
    Then I should report the duplicates if found