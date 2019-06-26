Feature: Edit a review

    John rented a property And he made a review about the experience. Now, he would like to edit this review because he misspelled a word.

    Scenario: Fields are correctly filled
        Given John is logged in
            And finds the property that he rented
            And finds his review
            And clicks on the button edit review
        When John fills Comment with "Nice property"
            And presses publish
        Then The system should create a success message And republish the review
    
    Scenario: There are empty fields 
        Given John is logged in
            And finds the property that he rented
            And finds his review
            And clicks on the button edit review
        When John deletes the text on Comment field
            And presses publish
        Then John should receive feedback alerting him for the empty fields