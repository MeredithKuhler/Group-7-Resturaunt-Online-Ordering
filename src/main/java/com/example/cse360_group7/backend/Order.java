package application;

public class Order{

  //class attributes
  private String customerName;
  private MenuItem[] orderItems;
  private Float waitTime;

  //class constructor
  public Order(String customerName, MenuItem[] orderItems, Float waitTime){

    this.customerName = customerName;
    this.orderItems = orderItems;
    this.waitTime = waitTime;
    
  }
}
