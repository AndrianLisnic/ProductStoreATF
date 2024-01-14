Feature: Log in and purchase a product

  Background:
    Given user is on Product Store Landing page

  Scenario: Log in as existing user and log out
    When user clicks on LogIn link
    Then Log In modal window is displayed
    And user enters chisinau as username and chisinau as password
    When user clicks on Sign Up button
    Then user is successfully logged in
    And user clicks on LogOut link

  Scenario: Log in as existing user and purchase a product
    When user clicks on LogIn link
    Then Log In modal window is displayed
    And user enters chisinau as username and chisinau as password
    When user clicks on Sign Up button
    Then user is successfully logged in
    And user selects a random product from the Landing page
    And user adds the selected product to the cart
    And user closes the confirmation pop-up message
    When user opens the shopping cart
    Then validate the product name and price are correctly displayed
    And user clicks on Place Order button from the Place Order modal
    And user fills in the order details in the modal window with following details
      | name    | country | city     | creditCard       | month | year |
      | Andrian | Moldova | Chisinau | 2242458911226658 | 12    | 2023 |
    When user clicks on Purchase button
    Then validate the purchase details are correctly displayed
    And user clicks the OK button from the confirmation pop-up