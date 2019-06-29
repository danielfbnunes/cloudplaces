Feature: Compare by distance

    John is undecided between two houses, so he wants to compare them by distance
    
    Scenario: John compares by distance
        Given John is logged in
            And clicks on button add to comparator in 'House 1' 
            And clicks on button add to comparator in 'House 2'
            And clicks on Compare
        When John checks Distance option
        Then The system should compare the distance between a certain point from 'House 1' And from 'House 2'