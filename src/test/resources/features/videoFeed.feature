Feature: Validate Video Feeds on the News & Features page

  @smoke
  Scenario: Count video feeds and filter those greater than or equal to three days
    Given I navigate to the Warriors website
    When I hover over the menu item and click on News & Features    
    Then I should be able to count all video feeds on the page
    And I should count video feeds that are greater than or equal to three days old