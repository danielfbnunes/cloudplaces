Feature: Search By Number of Rooms
	
	John (a lesse) would like to restrict his search for a property by the number of rooms

	Scenario: There are houses with the criteria
		Given John is logged in 
		    And in the homepage 
		    And there are properties with the number of rooms "3"
		When he searches by the number of rooms "3"
		Then the properties with the number of rooms "3" should appear

	Scenario: There are no houses with the criteria
		Given John is logged in
		    And in the homepage
		    And there are no properties with the number of rooms "3"
		When he searches by the number of rooms "3"
		Then no properties should appear.