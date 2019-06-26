Feature: Compare by location

    John is undecided between two houses, so he wants to compare them by location
    
    Scenario: John compares by location
        Given John is logged in
            And clicks on button add to comparator in 'House 1' 
            And clicks on button add to comparator in 'House 2'
            And clicks on Compare
        When John checks Location option
        Then The system should compare the location of 'House 1' with 'House 2'