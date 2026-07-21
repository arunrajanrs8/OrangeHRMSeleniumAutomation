Feature: Login to OrangeHRM

@regression
Scenario: Login with valid credentials
	Given User launch Chrome browser
	When User opens url
	And User enters email as "Admin" and password as "admin123"
	And User clicks on Login button
	Then User should be in dashboard "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"
	When User click on logout link
	Then User should be in login "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
	And User close the browser

@regression
Scenario Outline: Login with invalid credentials
	Given User launch Chrome browser
	When User opens url
	And User enters email as "<email>" and password as "<password>"
	And User clicks on Login button
	Then User should not be in dashboard "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"
	Examples:
	|email|password|
	|Admin|admin@123|