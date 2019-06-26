Feature: Search By Date
	
	John (a lesse) would like to restrict his search for a property by the date of publish

	Scenario: There are houses with the criteria
		Given John is logged in 
		    And in the homepage 
		    And there are properties with the date "12/05/2019"
		When he searches by the date "12/05/2019"
		Then the properties with the date "12/05/2019" should appear

	Scenario: There are no houses with the criteria
		Given John is logged in
		    And in the homepage
		    And there are no properties with the date "12/05/2019"
		When he searches by the date "12/05/2019"
		Then no properties should appear.