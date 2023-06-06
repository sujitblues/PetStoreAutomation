package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userpayload;
	public static Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger("PetStoreAutomation");
	}
	
	//Testing create user
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("**************Creating user***********");
		Response response=UserEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.contentType(), "application/json");
		
		/*
		 * responseBuilder.expectStatusLine("HTTP/1.1 200 OK");
		 * responseBuilder.expectContentType(ContentType.JSON);
		 * responseBuilder.expectResponseTime(Matchers.lessThan(2000L));
		 */
		logger.info("**************User created ***********");
		
	}
//Testing get user by Name
	@Test(priority=2)
public void testGetUserByNmae()
{
		logger.info("Reading user information");
	Response response=UserEndPoints.readUser(this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.statusCode(), 200);
	
	logger.info(" user information displyed");
}
	//Testing update user by Name
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info(" updating user information");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info(" updated user information");
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info(" deleting user ");
		Response response=UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().ifError().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" user deleted ");
	}
}
