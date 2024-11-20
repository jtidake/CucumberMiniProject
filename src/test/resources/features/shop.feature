Feature: Shop Jackets Search and Save Details

  @smoke
  Scenario: Store each Jacket Price, Title and Top Seller and attach to the report
    Given The user is on the landing page
    When The user clicks on the Men category in the Shop Page
    Then Verify user is navigated to Shop page successfully
    When The user clicks on Men category
    And The user clicks on Jackets link under Men category
    Then Verify that category page is displayed and confirm text Men Jacket products
    And User should store the Jacket Price, Title, and Top Seller message in a text file
    And User should attach the text file to the report
