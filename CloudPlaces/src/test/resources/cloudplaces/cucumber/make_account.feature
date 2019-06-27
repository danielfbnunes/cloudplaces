Feature: Register a User on the plataform

	John (a lessee or lessor) wants to use the plataform, but he doesn't have
	an account so he decides to register him self in the plataform.

	Scenario: John registers successfuly
		Given John is on the login page
		When he presses the sign up button
		   And fills the username with "user1"
		   And fills the password with "pw"  
		Then he should see a success message
		    And be able to login with username "user1"
		    And password "pw"

	Scenario: John registers unssuccessfuly
		Given John is on the login page
		When he presses the sign up button
		   And fills the username with "user2"
		   And fills the password with "pw"
		Then he should see an unsuccessful message
		   And can't be able to login using username "user2"
   		   And password "pw"