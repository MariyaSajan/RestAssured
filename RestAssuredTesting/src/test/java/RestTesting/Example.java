package RestTesting;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class Example {
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	public static String id;
	@Test(priority = 0)
	public void signup()
	{
		RestAssured.baseURI = baseurl;
		String 	requestbody = "{\n"
				+ "	\"email\": \"mariam11@gmail.com\",\n"
				+ "	\"password\": \"welcome123\"\n"
				+ "}";
		Response response = given()
				.header("Content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).contentType(ContentType.JSON)
				.extract().response();	
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		message = js.get("message");
		System.out.println(message);
		
		
	}
	@Test(priority = 1, dependsOnMethods = "signup")
	public void login()
	{
		RestAssured.baseURI = baseurl;
		String 	requestbody = "{\n"
				+ "	\"email\": \"mariam11@gmail.com\",\n"
				+ "	\"password\": \"welcome123\"\n"
				+ "}";
		Response response = given()
				.header("Content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();	
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		
		//nw i have to fetch the id
		//message = js.get("message");
		//System.out.println(message);
		accessToken=js.get("accessToken");
		System.out.println(accessToken);
		
		}
	  
	
	@Test (priority=2 , dependsOnMethods = "login")
	public void getusers()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().
		//queryParam("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjMwYzI4ZTZhYjVjNjAwMTcyZmQxYjYiLCJpYXQiOjE2NDc0MDA4NTcsImV4cCI6MTY0NzQ4NzI1N30.ot3_lNRGpb-mvs1wnFWhpzKWOvQyr4lttgR2dwCda2Y")
		 header("Content-Type","application/json")
		.header("Authorization", "bearer "+ accessToken)
		.when()
		.get("/user")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
		JsonPath js = new JsonPath(jsonresponse);
		id=js.get("users[100]._id");
	}
	@Test (priority=3 , dependsOnMethods = "login")
	public void deleteuser()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().
		//queryParam("Authorization","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjMwYzI4ZTZhYjVjNjAwMTcyZmQxYjYiLCJpYXQiOjE2NDc0MDA4NTcsImV4cCI6MTY0NzQ4NzI1N30.ot3_lNRGpb-mvs1wnFWhpzKWOvQyr4lttgR2dwCda2Y")
		 header("Content-Type","application/json")
		.header("Authorization", "bearer "+ accessToken)
		.when()
		.delete("/user/" + id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}
}









