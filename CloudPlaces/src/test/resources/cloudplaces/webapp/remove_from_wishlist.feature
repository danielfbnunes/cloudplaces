Feature: Remove a property from the wishlist

	John wants to remove a property that is no longer usefull from the wishlist

	Scenario: John is on the wishlist page
		Given John is logged in
		    And on the wishlist page
		When he presses the heart button of a house
		Then the system removes that property from his wishlist

	Scenario: John is on the property page
		Given John is logged in
		   And on the property page
    		   And the property is on his wishlist
		When he presses the heart button
		Then the system removes that property from his wishlist
