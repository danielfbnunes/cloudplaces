Feature: Search By Location
	
	John (a lesse) would like to restrict his search for a property by the location

	Scenario: There are houses with the criteria
		Given John is logged in 
		    And in the homepage 
		    And there are properties with the location "Aveiro"
		When he searches by the location "Aveiro"
		Then the properties with the location "Aveiro should appear

	Scenario: There are no houses with the criteria
		Given John is logged in
		    And in the homepage
		    And there are no properties with the location "Aveiro"
		When he searches by the location "Aveiro"
		Then no properties should appear.