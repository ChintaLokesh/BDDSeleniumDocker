Feature: To Find the Patient Details
Scenario Outline: Find the Patient Details 
Given User Login To APP with "<username>" username and "<password>" password 
When User Gives the "<patientName>"Patient Name
Then User gets the patient Summary Details of the procedure test for the patient "<patientName>" 
Examples:
  |username|password|patientName|
  |admin|Admin123|John Smith|