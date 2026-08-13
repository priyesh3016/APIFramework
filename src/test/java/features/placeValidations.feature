Feature: Validating Place API's

Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
  Given Add Place Payload with "<name>" "<language>" "<address>"
  When user calls "AddPlaceAPI" with "Post" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<name>" using "getPlaceApi"
  
  
 Examples:
 
 |name		|language		|address							| 
 |AAHouse	|English 		|World  Cross  Center |
#|BBHouse |Marathi		|Ekta Nagar 					|


Scenario: Verify if  delete place functionality is working 

	Given DeletePlace Payload 
	When user calls "deletePlaceApi" with "POST" http request 
	Then the API call got success with status code 200
	And "status" in response body is "OK"