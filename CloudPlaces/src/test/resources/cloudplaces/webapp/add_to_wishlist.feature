Feature: Add a property to the wishlist

	John (a lesse) want to add a property to his wishlist.

	Scenario: John adds a property to the wishlist from the list of properties
		Given John is logged in
		    And on the homepage
		When he presses the heart button
		Then the corresponding property is added to his wishlist

	Scenario: John adds a property to the wishlist from the property details page
		Given John is logged in
		    And on the corresponding property page
		When he presses the heart button
		Then the corresponding property is added to his wishlist 