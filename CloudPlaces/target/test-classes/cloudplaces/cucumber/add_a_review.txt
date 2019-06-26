Feature: Add a review

    John rented a property And he would like to give feedback about the property

    Scenario: Fields are correctly filled
        Given John is logged in 
    	    And clicks on the button add review
        When John fills Rating with "4"
            And Comment with "Nice property"
            And presses publish
        Then The system should create a success message And publish the review
    
    Scenario: There are empty fields 
        Given John is logged in 
	    And clicks on the button add review
        When John fills Rating with "4"
            And presses publish
        Then John should receive feedback alerting him for the empty fields