package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Pet;
import api.payload.User;
import io.restassured.response.Response;

public class PetTest {
	Faker faker;
	Pet petPayload;
	int petId;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		petPayload=new Pet();
		petPayload.setId(faker.idNumber().hashCode());
		petPayload.setName(faker.name().username());
		
		
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	//Testing create Pet
	@Test(priority=1)
	public void testCreatePet()
	{
		logger.info("**************Creating pet***********");
		Response response=PetEndPoints.createPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.contentType(), "application/json");
		
		/*
		 * responseBuilder.expectStatusLine("HTTP/1.1 200 OK");
		 * responseBuilder.expectContentType(ContentType.JSON);
		 * responseBuilder.expectResponseTime(Matchers.lessThan(2000L));
		 */
		logger.info("**************Pet created ***********");
		
	}
//Testing get Pet by PetId
	@Test(priority=2)
public void testGetPetById()
{
		logger.info("Reading pet information");
	Response response=PetEndPoints.getPet(this.petPayload.getId());
	response.then().log().all();
	Assert.assertEquals(response.statusCode(), 200);
	
	logger.info(" user information displyed");
}
	//Testing update pet by Pet Id
	@Test(priority=3)
	public void testUpdatePetById()
	{
		logger.info(" updating user information");
		petPayload.setName(faker.name().firstName());
		petPayload.setStatus(faker.name().lastName());
		
		Response response=PetEndPoints.updatePet(this.petPayload.getId(), petPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		//Checking data after update
		Response responseAfterUpdate=PetEndPoints.getPet(this.petPayload.getId());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info(" updated user information");
	}
	//Testing Delete pet by pet Id
	@Test(priority=4)
	public void testDeletePetById()
	{
		logger.info(" deleting pet ");
		Response response=PetEndPoints.getPet(this.petPayload.getId());
		response.then().log().ifError().assertThat().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Pet deleted ");
	}
}
