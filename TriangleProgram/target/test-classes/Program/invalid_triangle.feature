Feature: Handle Invalid Side lengths of a Triangle
  Return the type of the triangle as Not a triangle

  Scenario: Invalid length of zero
    Given The sides are 10,0,12
    When I ask what type of triangle
    Then I should get "Not a triangle"

  Scenario: Invalid lengths of a triangle
    Given The sides are 1,2,3
    When I ask what type of triangle
    Then I should get "Not a triangle"