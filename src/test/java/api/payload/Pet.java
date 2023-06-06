package api.payload;

import java.util.List;

public class Pet {
	private int id;
	private String name;
	private List<String> photoUrls;
	private String tags[];
	private String status;
	PetCategory petCat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*
	{
		  "id": 0,
		  "category": {
		    "id": 0,
		    "name": "string"
		  },
		  "name": "doggie",
		  "photoUrls": [
		    "string"
		  ],
		  "tags": [
		    {
		      "id": 0,
		      "name": "string"
		    }
		  ],
		  "status": "available"
		}
		*/

}
