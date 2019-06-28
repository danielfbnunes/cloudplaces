Feature: Choose best property

	John (a lesse) after comparing various properties might want to chose one to analyse.

	Scenario: John wants to chose a property
		Given John already compared the properties
		When John presses on a property
		Then John should see the property details

	Scenario: John doesn't want to chose a property
		Given John already compared the properties
		When John presses close
		Then the comparator is removed.