Feature: Amazon UI Test 

Background: 
	Given The web application is open
	 
@Registration	
Scenario: Creare account 

	Given I am on the registration page of the amazon application 
	When I enter user name
	And I enter mobile number
	And I enter password
	And I click on continue
	
@AddToCart	
Scenario: Add items to cart 

	Given I click on sign in on home page
	When I enter mobile number on sign in page 
	And I click on continue
	And I enter password
	And I click on sign in 
	And I search for required product
	And I click on required product
	And I add required product to cart
