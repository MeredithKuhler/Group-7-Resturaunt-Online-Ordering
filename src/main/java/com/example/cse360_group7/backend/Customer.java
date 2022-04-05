package application;

import java.util.ArrayList;

public class Customer extends Account{

  //private attributes
  private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
  private CreditCard creditCard;
  private Double cartPrice;
  private ArrayList<Coupon> coupons = new ArrayList<Coupon>();
  private String email;
  private Boolean orderPlaced;
  private int orderETA;
  
  //constructor
  public Customer(String username, String password) {
	  this.username = username;
	  this.password = password;
	  this.orderPlaced = false;
  }
  
  //setter
  public void setUsername(String username) {
	  this.username = username;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public void setOrderPlaced(Boolean bool) {
	  orderPlaced = bool;
  }
  
  public void setOrderETA() {

      this.orderETA = 0;
      for (int i =0; i<cart.size(); i++)//iterate through cart
          this.orderETA += cart.get(i).getPrepTime(); //get prep time of each item in the cart and add it.

  }
  
  public int getOrderETA() {
	  
	  return this.orderETA;
  }
  
  public Boolean getOrderPlaced() {
	  return orderPlaced;
  }
  
  public Double getCartPrice() {
	  this.cartPrice = 0.00; // set cart price intially to zero
	  
	  for (int i =0; i<cart.size(); i++)//iterate through cart
		  this.cartPrice += cart.get(i).getPrice(); //get price of each item in the cart and add it.
	  
	  return cartPrice;
  }
  
  public void setEmail(String email) {
	  
	  this.email = email;
  }
  
  public void addMenuItem(MenuItem menuItem) // when user selects a menu item then call this method 
  {											//  via the customer object and pass-in the menu item
	  cart.add(menuItem);
  }
  
  public void removeMenuItem(String menuItem){

	  for (int i =0; i<cart.size(); i++)//iterate through cart
		  if (cart.get(i).getItemName() == menuItem) // if the cart item matches the item to be removed
		  {
			  cart.remove(i); 		   // then remove the item from the cart		
			  break;
		  }	  	  
    }
  
  public double useCoupon(Double coupon, Double price){

    return price - price*coupon;
    
  }
  
  public int getAmountOfItem (MenuItem menuItem) {
	  
	  int count  = 0;
	  
	  for (int i =0; i<cart.size(); i++)//iterate through cart
		  if (cart.get(i) == menuItem) // if the cart item matches the item to be removed
			  count++;		   		  // updating count		
	  
	  return count;
    }  
  
  public CreditCard getCreditCard(){

    return creditCard;
  }
  
  public void addCoupons(Coupon coupon) {
	  coupons.add(coupon);
  }
  
  public void getCoupons() {
	  System.out.println(coupons);
  }
  public String getEmail(){

    return email;
  }
  
  public ArrayList<MenuItem> getCart(){
	  
	  return cart;
  }
}