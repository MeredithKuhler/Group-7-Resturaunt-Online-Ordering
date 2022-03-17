
public class Coupon {
	protected String couponCode;
	//percentage listed as decimal
	protected Double couponDiscount;
	
	//constructor
	public Coupon() {
		this.couponCode = "00000";
		this.couponDiscount = 0.10;
	}
	
	public Coupon(String couponCode, Double couponDiscount) {
		this.couponCode = couponCode;
		this.couponDiscount = couponDiscount;
	}
	
	//getters
	public String getCouponCode(){
		return couponCode;
	}
	public Double getCouponDiscount(){
		return couponDiscount;
	}
	
	//setters
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public void setCouponDiscount(Double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	
}
