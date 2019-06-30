
Feature: Show property details
	When John (a lessee) finds a property of his liking he should be able to see its details.
	
	Scenario: John finds one property from the list of properties
		Given John is logged in 
		    And in the homepage
		When he clicks on the property
		Then he should be redirected to the property details