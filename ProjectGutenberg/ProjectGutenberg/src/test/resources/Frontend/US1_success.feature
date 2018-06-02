Feature: Get the authors and books that mentions a city
  Return the authors and books

  Scenario: Correct city name US1, AC3
    Given A city name is "London"
    When I type in the city name
    Then I should get 2 results