Feature: Show list of properties
	When John (a lessee) logs in he can see all properties available in the plataform.

	Scenario: John is already logged in
		Given John as already logged in
		When He presses the home button
		Then He should see the list of properties

	Scenario: John is not logged in
		Given John is not logged in
		When He logs in
		Then He should see the list of properties