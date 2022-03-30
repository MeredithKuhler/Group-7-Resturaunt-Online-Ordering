
public class MenuItem {
	private String itemName;
	private int calories;
	private Double price;
	//TODO: Something image related?
	
	//constructors - no default
	public MenuItem(String itemName, int calories, Double price) {
		this.itemName = itemName;
		this.calories = calories;
		this.price = price;
	}
	
	//getters
	public String getItemName() {
		return itemName;
	}
	public int getCalories() {
		return calories;
	}
	public Double getPrice() {
		return price;
	}
	
	//setters - editing menu items on employee side(no customer interaction)
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
