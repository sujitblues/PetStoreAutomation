package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userpayload;
	public Logger logger;
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
		logger=LogManager.getLogger(this.getClass());
	}
	
	//Testing create user
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("**************Creating user***********");
		Response response=UserEndPoints2.createUser(userpayload);
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
	Response response=UserEndPoints2.readUser(this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.statusCode(), 200);
}
	//Testing update user by Name
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints2.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response=UserEndPoints2.deleteUser(this.userpayload.getUsername());
		response.then().log().ifError().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
