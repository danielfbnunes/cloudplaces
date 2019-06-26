Feature: Compare by habitable space

    John is undecided between two houses, so he wants to compare them by habitable space
    
    Scenario: John compares by habitable space
        Given John is logged in
            And clicks on button add to comparator in 'House 1' 
            And clicks on button add to comparator in 'House 2'
            And clicks on Compare
        When John checks Habitable Space option
        Then The system should compare the habitable space in 'House 1' with 'House 2'