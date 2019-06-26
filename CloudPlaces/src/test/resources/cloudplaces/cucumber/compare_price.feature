Feature: Compare by price

    John is undecided between two houses, so he wants to compare them by price
    
    Scenario: John compares by price
        Given John is logged in
            And clicks on button add to comparator in 'House 1' 
            And clicks on button add to comparator in 'House 2'
            And clicks on Compare
        When John checks Price option
        Then The system should compare the price of 'House 1' with 'House 2'