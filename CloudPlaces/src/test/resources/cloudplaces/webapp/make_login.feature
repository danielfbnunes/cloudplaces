Feature: Make Login

	John (a lessee or lessor) wants to use the plataform, 
	to do that he needs to login

	Scenario: John logs in successfully
		Given John as an account
		    And he is on the login page
		When he fills the email with "daniel@ua.pt"
   		    And the password with "password"
		Then he should be redirected to his homepage

	Scenario: John incorrectly fills one field
		Given John as an account
		    And he is on the login page
		When he fills the email with "daniel@ua.pt"
		    And the password with "pw1"
		Then he should see a negative feedback message informing about the failure of the login
