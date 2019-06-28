Feature: Create an Ad

	Maria (lessor) wants to publish her property for rental

	Scenario: Fields are correctly filled
		Given Maria is logged in And clicks on the button create rental
		When Maria fills Name as "Casa 1" 
		    And Location as "Aveiro"
		    And Habitable Space as "30"
		    And Number of Rooms as "3"
		    And Price as "120"    
		    And presses publish
		Then The system should create a success message And publish the rental

	Scenario: Fields are not filled correctly
		Given I'm logged in And click on the button create rental
		When Maria fills Name as "Casa 1" 
		    And Location as "Aveiro"
		    And Habitable Space as "0"
		    And Number of Rooms as "3"
		    And Price as "120"    
		    And presses publish
		Then I should receive feedback alerting me for the incorrectly filled