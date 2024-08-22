package models;

public class CategoryModel {
	private int categoryID;
	private String categoryName;

	public CategoryModel() {
	}

	public CategoryModel(String categoryName) {
		this.categoryName = categoryName;
	}

	public CategoryModel(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return categoryName.toString();
	}

}
