Feature: Add, Search and Update Employee

Background: UserLogin to the application
	Given User launch Chrome browser
	When User opens url
	And User enters email as "Admin" and password as "admin123"
	And User clicks on Login button
	Then User can view the dashboard "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"
	When User click on PIM menu

@regression
Scenario: Add a new employee to company
	And User click on Add option
	Then User can view Add new employee page "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee"
	When User enter employee details
	| firstName | middleName | lastName | empId  | userName     | status   | password  |
    | John      | M          | Honai46  | 112296 | john.honai46 | enabled  | honai1234 |
	And User click on Save button
	Then User can see the success message "Success"

@regression
Scenario: Search an Employee by EmployeeId
	And User enter EmployeeId "112296"
	And User click on search button
	Then User see the employee details of "112296"
	
@regression
Scenario: Search an Employee by Invalid EmployeeId
	And User enter EmployeeId "asdfg"
	And User click on search button
	Then User do not see any employee records with message "No Records"
