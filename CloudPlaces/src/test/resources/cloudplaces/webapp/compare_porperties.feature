Feature: Compare Houses

    John is undecided between two houses, so he wants to compare them
    
    Scenario: John compares two houses
        Given John is logged in
            And clicks on button add to comparator in House 1 
            And clicks on button add to comparator in House 2
        When he clicks on Compare
        Then a popup should appear with the comparison of the 2 houses

    Scenario: John compares three houses
        Given John is logged in
            And clicks on button add to comparator in House 1
            And clicks on button add to comparator in House 2
	    And clicks on button add to comparator in House 3
        When he clicks on Compare
        Then a popup should appear with the comparison of the 3 houses