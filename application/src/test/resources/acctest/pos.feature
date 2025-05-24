Feature: Points of Sale Management
  This feature allows users to create and modify points of sale (POS).

  Scenario: Insert and retrieve two POS
    Given an empty POS list
    When I insert POS with the following elements
      | name                   | description             | type             | campus | street          | houseNumber | postalCode | city     |
      | Lidl (Nürnberger Str.) | Vending machine at Lidl | VENDING_MACHINE  | ZAPF   | Nürnberger Str. | 3a          | 95448      | Bayreuth |
      | New Cafe               | Fancy new cafe          | CAFE             | MAIN   | Teststraße      | 99          | 12345      | New City |
    Then the POS list should contain the same elements in the same order

  Scenario: Update one of three existing POS
    Given an empty POS list
    When I insert POS with the following elements
      | name           | description             | type             | campus | street       | houseNumber | postalCode | city    |
      | Alpha Shop     | First description       | CAFE             | MAIN   | Alpha Str.   | 1           | 11111      | Town A  |
      | Beta Vendo     | Second description      | VENDING_MACHINE  | ZAPF   | Beta Str.    | 2           | 22222      | Town B  |
      | Gamma Corner   | Third description       | CAFE             | MAIN   | Gamma Str.   | 3           | 33333      | Town C  |
    And I update the description of the POS named "Beta Vendo" to "Updated second description"
    Then the POS with name "Beta Vendo" should have description "Updated second description"
