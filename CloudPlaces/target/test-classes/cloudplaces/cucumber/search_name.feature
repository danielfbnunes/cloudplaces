Feature: Search By Name
	
	John (a lesse) would like to restrict his search for a property by the name

	Scenario: There are houses with the criteria
		Given John is logged in 
		    And in the homepage 
		    And there are properties with the name "Casa 1"
		When he searches by the name "Casa 1"
		Then the properties with the name "Casa 1" should appear

	Scenario: There are no houses with the criteria
		Given John is logged in
		    And in the homepage
		    And there are no properties with the name "Casa 1"
		When he searches by the name "Casa 1"
		Then no properties should appear.