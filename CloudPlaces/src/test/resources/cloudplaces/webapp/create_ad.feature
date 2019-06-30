Feature: Create an Ad

	John (lessor) wants to publish her property for rental

	Scenario: Fields are correctly filled
		Given John is logged in 
                    And clicks on the button create rental
		When He fills Name as "Casa 1" 
		    And Location as "rua velha, Aveiro"
		    And Price as "120"
                    And Number of Rooms as "3"
                    And Habitable Space as "30"
                    And Number of Rooms as "4"
                    And Number of Bathrooms as "2"
		    And Garage as "4"
                    And description as "Lovely Place"
                    And features as "garden_test1_test2"
                    And availability as "1"
		    And presses publish
		Then The system should create a success message 
                    And publish the rental with the name "Casa 1"