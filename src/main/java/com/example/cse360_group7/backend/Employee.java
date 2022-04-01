package application;


public class Employee extends Account{
	//constructor
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void giveCoupon(Customer customer, Coupon coupon) {
		customer.addCoupons(coupon);
	}
}
