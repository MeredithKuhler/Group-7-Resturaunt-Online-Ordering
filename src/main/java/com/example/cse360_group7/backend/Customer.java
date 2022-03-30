import java.util.ArrayList;

public class Customer extends Account{

  //private attributes
  private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
  private CreditCard creditCard;
  private Float cartPrice;
  private ArrayList<Coupon> coupons = new ArrayList<Coupon>();
  private String email;
  
  //constructor
  public Customer(String username, String password) {
	  this.username = username;
	  this.password = password;
  }
  
  //getters
  
  public MenuItem addMenuItem()
  {
    MenuItem menuItem = new MenuItem("test", 100, 1203.00);

    return menuItem;
  }
  
  public void removeMenuItem(MenuItem menuItem){

      int number;
    
    }
  
  public Float useCoupon(Coupon coupon, Float price){

	coupons.remove(coupon);
    return price*coupon.getCouponDiscount();
    
  }

//  public MenuItem[] getCart(){

//  }

  public CreditCard getCreditCard(){

    return creditCard;
  }
  
  public void addCoupons(Coupon coupon) {
	  coupons.add(coupon);
  }
  
  public void getCoupons() {
	  System.out.println(coupons);
  }
  public String getPhoneNumber(){

    return phoneNumber;
  }
}
