Feature: Search By Price

    John (lessee) would like to restrict his search for a property by price

    Scenario: There are houses with the criteria
        Given John is logged in
            And in the home page
            And there are properties with the price "200" or lower
        When John searches by the price "200"
        Then The properties with the price "200" or lower should appear
    
    Scenario: There are no houses with the criteria
        Given John is logged in
            And in the home page
            And there are no properties with the price "100" or lower
        When John searches by the price "100"
        Then No properties should appear