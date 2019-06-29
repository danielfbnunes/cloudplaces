Feature: Search Previous Properties

    John (lessee) would like to search for the previous properties that he searched for

    Scenario: John has already searched for properties before
        Given John is logged in
            And in the home page
            And he has searched for properties before
        When John searches for previous properties
        Then The properties that John has searched before should appear
    
    Scenario: John hasn't searched for properties before
        Given John is logged in
            And in the home page
            And he hasn't searched for properties before
        When John searches for previous properties
        Then No properties should appear