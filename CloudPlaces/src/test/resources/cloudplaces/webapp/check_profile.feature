Feature: John needs to be logged inn

    John wants to check is profile in order to see his info
    
    Scenario: John checks his profile
        Given John is logged in
        When he presses the profile button
        Then he should be redirected to his profile