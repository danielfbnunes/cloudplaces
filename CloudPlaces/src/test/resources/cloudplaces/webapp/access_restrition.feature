Feature: John needs to be logged inn

    John when logout, can't access the system.
    
    Scenario: John tries to use the system while logged out
        Given John is logged in
        When he logs out
        Then can't get a property
            And can't get his profile
            And can't  get all properties
            And can't get is properties