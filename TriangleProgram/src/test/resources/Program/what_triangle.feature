Feature: Get the type of Triangle 
  Return the type of the triangle 

  Scenario: This is Scalene
    Given The sides are 6,8,12
    When I ask what type of triangle
    Then I should get "Scalene triangle"

  Scenario: This is Isosceles
    Given The sides are 10,5,10
    When I ask what type of triangle
    Then I should get "Isosceles triangle"

  Scenario: This is Equilateral
    Given The sides are 10,10,10
    When I ask what type of triangle
    Then I should get "Equilateral triangle"