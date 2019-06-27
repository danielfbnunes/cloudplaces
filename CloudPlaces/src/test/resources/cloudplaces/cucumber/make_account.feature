Feature: Register a User on the plataform

	John (a lessee or lessor) wants to use the plataform, but he doesn't have
	an account so he decides to register him self in the plataform.

	Scenario: John registers successfuly
		Given he is on the login page
		When he presses the sign up button
		   And fills the username with "user2"
		   And fills the password with "pw"
                   And fills the email with "email2@ua.pt"
                   And fills the cellphone with "919999999"
		Then he should see a success message
		    And be able to login with email "email2@ua.pt"
		    And password "pw"
                    And enter

	Scenario: John registers unssuccessfuly
		Given he is on the login page
		When he presses the sign up button
		   And fills the username with "user2"
		   And fills the password with "pw1"
                   And fills the email with "email2@ua.pt"
                   And fills the cellphone with "919999999"
		Then he should see an unsuccessful message
		   And can't be able to login using email "email2@ua.pt"
   		   And using password "pw1"
                   And log in