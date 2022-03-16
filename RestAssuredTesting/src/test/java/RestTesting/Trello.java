package RestTesting;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class Trello {
	
	//Base URL is common to all the actions
	public static String baseurl = "https://api.trello.com";
	
	public static String id;
	
	@Test(priority=0 )
	public void createBoard()
	{
		 
		//fetching the base url to add other things to that base url
		//The first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//There are three keywords in RestAssured
		//given:pre-conditions like parameters,header,authorization
		//when: conditions that i am testing like resources
		//then: post-conditions, something like checking the response
		
		Response response = given().queryParam("name", "Mariya's Moolya Board")
		.queryParam("key", "29bfc488a85fc5d258996154992a765c")
		.queryParam("token", "40394db83a5a9f25a5c256fa590b22124c07cad8139f6c3eaeb9fcfa941a7aa7")
		.header("Content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		
		JsonPath js = new JsonPath(jsonresponse);
		
		id = js.get("id");
		System.out.println(id);
		
	}
		
		
	@Test(priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given()
		.queryParam("key", "29bfc488a85fc5d258996154992a765c")
		.queryParam("token", "40394db83a5a9f25a5c256fa590b22124c07cad8139f6c3eaeb9fcfa941a7aa7")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		//JsonPath js = new JsonPath(jsonresponse);
		System.out.println(jsonresponse);
		
		
		
	}
	
	@Test(priority = 2)
	public void deleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		 Response response =  given()
		.queryParam("key", "29bfc488a85fc5d258996154992a765c")
		.queryParam("token", "40394db83a5a9f25a5c256fa590b22124c07cad8139f6c3eaeb9fcfa941a7aa7")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		
		//JsonPath js = new JsonPath(jsonresponse);
		System.out.println(jsonresponse);
		
		
		
	}

	
	

}
