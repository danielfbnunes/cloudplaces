
Feature: Show property details
	When John (a lessee) finds a property of his liking he should be able to see its details.
	
	Scenario: John finds one property from the list of properties
		Given John is logged in and on the list of properties
		When John clicks on the property
		Then John should see a popup with the property details