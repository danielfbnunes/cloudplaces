Feature: Search By Habitable Space
	
	John (a lesse) would like to restrict his search for a property by the habitable space

	Scenario: There are houses with the habitable space John wants
		Given John is logged in 
		    And in the homepage 
		    And there are properties with the habitable space "30"
		When he searches by the habitable space "30"
		Then the properties with habitable space "30" should appear

	Scenario: No houses found
		Given John logged in
		    And in the homepage
		    And there are no properties with the habitable space "30"
		When John searches by the habitable space "30"
		Then no properties should appear.