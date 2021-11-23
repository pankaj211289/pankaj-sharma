Feature: Validates endpoints for products : Find, create, update and remove products

  Scenario: Validate fetching all products end point
  	Given I check the health information of system
    When I create a product with following details
      |name         | TestProduct           |
      |type         | HardGood              |
      |upc          | 0987654321            |
      |description  | Test Product; AA Size |
      |model        | MN12345               |
    Then I can validate that product "TestProduct" is created
    And I update product "TestProduct" with following details and validate it
      |name         | NewTestProduct        |
      |type         | Good                  |
    And I validate by setting limit for number of products as 1
    And I can delete product "TestProduct"
    And I validate product "TestProduct" does not exist

  Scenario: Validate negative responses
    Given I check the health information of system
    And I create a product with insufficient details
      |name         | TestProduct           |
      |type         | HardGood              |
    And I try to fetch invalid product with ID "999968123"
    And I try to delete invalid product with ID "999968123"