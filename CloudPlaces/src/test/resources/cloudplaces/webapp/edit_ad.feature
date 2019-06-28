Feature: Edit Ad
	A Maria (lessor) needs to be able to update the fields on their porperties.

	Scenario: Correctly edit the property
		Given Maria is logged in And selected the property to edit
		When Maria fills Name as "Casa 1" 
		    And Location as "Aveiro"
		    And Habitable Space as "30"
		    And Number of Rooms as "3"
		    And Price as "120"
		    And submits the change
		Then Maria should receive a success message.


	Scenario: Incorrectly edit the property
		Given Maria is logged in And selected the property to edit
		When Maria  When Maria fills Name as "Casa 1" 
		    And Location as "Aveiro"
		    And Habitable Space as "30"
		    And Number of Rooms as "0"
		    And Price as "120"
		    And submits the change
		Then Maria should receive an unsuccessfull message.