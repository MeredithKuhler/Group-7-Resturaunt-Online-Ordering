package application;

public class MenuItem {
	private String itemName;
	private String calories;
	private Double price;
	private int prepTime;
	private String image;
	
	//constructors - no default
	public MenuItem(String itemName, String calories, Double price, int prepTime, String image) {
		this.itemName = itemName;
		this.calories = calories;
		this.price = price;
		this.prepTime = prepTime;
		this.image = image;
	}
	
	//getters
	public String getItemName() {
		return itemName;
	}
	public String getCalories() {
		return calories;
	}
	public Double getPrice() {
		return price;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public String getImage() {
		return image;
	}
	//setters - editing menu items on employee side(no customer interaction)
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}