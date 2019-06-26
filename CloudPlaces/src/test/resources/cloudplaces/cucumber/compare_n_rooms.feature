Feature: Compare by Nº of Rooms

    John is undecided between two houses, so he wants to compare them by number of rooms
    
    Scenario: John compares by nº of rooms
        Given John is logged in
            And clicks on button add to comparator in 'House 1' 
            And clicks on button add to comparator in 'House 2'
            And clicks on Compare
        When John checks Rooms option
        Then The system should compare the nº of rooms in 'House 1' with 'House 2'