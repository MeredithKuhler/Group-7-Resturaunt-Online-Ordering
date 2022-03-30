
public class Coupon {
	private String couponCode;
	//percentage listed as decimal
	private Float couponDiscount;
	
	//constructor
	public Coupon() {
		this.couponCode = "00000";
		this.couponDiscount = 0.10f;
	}
	
	public Coupon(String couponCode, Float couponDiscount) {
		this.couponCode = couponCode;
		this.couponDiscount = couponDiscount;
	}
	
	//getters
	public String getCouponCode(){
		return couponCode;
	}
	public Float getCouponDiscount(){
		return couponDiscount;
	}
	
	//setters
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public void setCouponDiscount(Float couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	
	public String toString() {
		return("Coupon code: " + this.getCouponCode() +
				" Coupon Discount: " + this.getCouponDiscount());
	}
	
}
