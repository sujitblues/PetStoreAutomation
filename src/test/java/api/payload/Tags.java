package api.payload;

public class Tags {
	/* "tags": [
		    {
		      "id": 0,
		      "name": "string"
		    }
		  ]
		  */
	private int tagId;
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	private String tagName;
	

}
