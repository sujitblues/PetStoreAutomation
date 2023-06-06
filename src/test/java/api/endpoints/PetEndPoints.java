package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	//To do all curd operation

	public static Response createPet(Pet payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.create_pet);
		
		return response;
	}
	public static Response getPet(int petId)
	{
		Response response=given()
				.pathParam("petId", petId)
		.when()
			.get(Routes.get_pet);
		
		return response;
	}
	public static Response updatePet(int petId,Pet payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("petId", petId)
			.body(payload)
		.when()
			.put(Routes.update_pet);
		
		return response;
	}
	public static Response deletePet(int petId)
	{
		Response response=given()
				.pathParam("petId", petId)
		.when()
			.delete(Routes.delete_pet);
		
		return response;
	}
}
