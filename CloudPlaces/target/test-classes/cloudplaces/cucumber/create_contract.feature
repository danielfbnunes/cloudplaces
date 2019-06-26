Feature: Create a new contract

	Maria (a lessor) wants to automatically generate a contract for her property

	Scenario: Maria fills everything correctly
		Given Maria is logged in
		    And on her property page
		When She presses the generate contract button
		    And fills name with "João"
		    And start date with "30/06/2019"
		    And end date with "31/07/2019"
		Then the system generates a contract 
		    And makes it available for download

	Scenario: Maria fills one field incorrectly
		Given Maria is logged in
		    And on her property page
		When She presses the generate contract button
		    And fills name with "João"
		    And start date with "20/06/2019"
		    And end date with "31/07/2019"
		Then the system generates an error message
		    And doesn't allow the download of the contract