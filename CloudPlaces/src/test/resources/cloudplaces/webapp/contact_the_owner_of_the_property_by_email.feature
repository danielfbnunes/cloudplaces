Feature: Contact the owner of the property by email

    John wants to contact the owner of a property that he's interested in

    Scenario: John is in the property page And wants to contact the owner
        Given John is logged in
            And in the property page
        When John clicks on owner's info button
        Then Owner's email is shown as well as more information about him