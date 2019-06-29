Feature: Remove an Ad
	John (a lessor) wants to remove one of his properties from the listing

	Scenario: The property is removed successfully
		Given John is logged in 
		    And has selected the property
		When he presses the remove button
		Then he should see a positive feedback message 
		    And the property should be removed from the list of properties.