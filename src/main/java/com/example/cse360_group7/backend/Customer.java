public class Customer extends Account{

  //private attributes
  private MenuItem menuItems[];
  private CreditCard creditCard;
  private int cartPrice;
  private Coupon coupons[];
  private int phoneNumber;

  //getters

  public MenuItem addMenuItem()
  {
    MenuItem menuItem;

  return menuItem;
  }
  
  public void removeMenuItem(MenuItem menuItem){

      int number;
    
    }
  
  public Float useCoupon(Coupon coupon, Float price){


    return price;
    
  }

  public MenuItem[] getCart(){

    return menuItems;
  }

  public CreditCard getCreditCard(){

    return creditCard;
  }

  public Coupon[] getCoupons(){

    return coupons;
  }

  public int getPhoneNumber(){

    return phoneNumber;
  }
}