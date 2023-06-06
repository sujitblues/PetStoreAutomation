package api.payload;

public class PetCategory {
	private int petId;
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetCategoryName() {
		return petCategoryName;
	}
	public void setPetCategoryName(String petCategoryName) {
		this.petCategoryName = petCategoryName;
	}
	private String petCategoryName;
	

}
