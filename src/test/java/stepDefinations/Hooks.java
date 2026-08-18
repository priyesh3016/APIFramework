package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	//execute  this  code  only when  place id is  null '
	//write  the  code  give  you place  id  
	
			
	@Before("@DeletePlace")
	
	public void beforeScenario() throws IOException
	{
		
		stepDefination m = new stepDefination();
		if(m.place_id == null)
		{
		m.add_place_payload_with("ONE", "TWO", "Three");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("ONE", "getPlaceApi");
		}
	}
	
}
