import java.util.Scanner;  // Import the Scanner class
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class main {

	public static void main(String[] args) throws IOException{
		//testing methods
        String username = "";
        String password = "";
        int choice = 0;
        Scanner scanner = new Scanner(System.in);    

        //Writing to text file
        FileWriter fw = new FileWriter("/home/cc-user/Restaurant/accountinfo.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        //Reading from text file
        Scanner findName = new Scanner(new FileReader("/home/cc-user/Restaurant/accountinfo.txt"));
        String currentLine;

        boolean flag = true; 
        
		Employee steve = new Employee("Steve", "asdf");
		Customer dan = new Customer("Dan", "abcd"); 
		
		System.out.println(steve.getUsername());
		System.out.println(steve.getPassword());
		System.out.println();
		
		System.out.println(dan.getUsername());
		System.out.println(dan.getPassword());
		System.out.println();
		
		Coupon cp1 = new Coupon();
		System.out.println(cp1.getCouponCode());
		System.out.println(cp1.getCouponDiscount());
		System.out.println();
		
		//Coupons are sequences of 5 letters and numbers
		cp1.setCouponCode("5a3Be");
		cp1.setCouponDiscount(0.35f);
		
		System.out.println(cp1.getCouponCode());
		System.out.println(cp1.getCouponDiscount());
		System.out.println();
		
		Coupon cp2 = new Coupon("a2j4l", 0.50f);
		
		System.out.println(cp2.getCouponCode());
		System.out.println(cp2.getCouponDiscount());
		System.out.println();
		
		MenuItem pizza = new MenuItem("Pizza", 2000, 15.42);
		System.out.println(pizza.getItemName());
		System.out.println(pizza.getCalories());
		System.out.println(pizza.getPrice());
		System.out.println();
		
		//god damn inflation
		pizza.setItemName("Pizzzzzzzzzzzzzza");
		pizza.setCalories(2043);
		pizza.setPrice(23.15);
		System.out.println(pizza.getItemName());
		System.out.println(pizza.getCalories());
		System.out.println(pizza.getPrice());
		System.out.println();
		
		//Testing employee giving out coupon, customer using coupon
		steve.giveCoupon(dan, cp2);
		steve.giveCoupon(dan, cp1);
		dan.getCoupons();
		System.out.println(dan.useCoupon(cp2, 12.30f));
		dan.getCoupons();
        System.out.println();
        
        //keep these at the bottom
        //Create account
        //write to filewriter: out.append(String);
        System.out.println("1. employee 2. customer");
        choice = scanner.nextInt();
        scanner.nextLine();
        //flag is true by default
        while (flag == true) 
        {
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            System.out.println("username: " +username);
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            System.out.println("password: " +password);
        
            findName = new Scanner(new FileReader("/home/cc-user/Restaurant/accountinfo.txt"));

            //loops throught the entire text file to see is username exists
            while(findName.hasNextLine()){
                currentLine = findName.nextLine();
                if(currentLine.equals(username)){
                    System.out.println("Error: Username already exists!");
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
            //TODO: Make this look better
            //TODO: Fix empty file write error
        }

        System.out.println("Account created!");

        if(choice == 1){
            out.append("Employee\n");
            out.append(username + "\n");
            out.append(password + "\n");
        }
        if(choice == 2){
    
            out.append("Customer\n");
            out.append(username + "\n");
            out.append(password + "\n");
            //coupons
            //creditcardinfo
            
        }
        //Login
        out.close();

	}

    // order of text file
    //1. Account type
    //2. Username
    //3. password only
    //4. coupon (customer only) 
    //5. creditcard (customer only) 
    //6. email (customer only)    

    // NOTE: AFTER APPEDNING PASSWORD ADD 3 EXTRA NEW LINES FOR CUSTOMER
     
      
    //1. Account type
    //2. Username
    //3. password only   

    //1. Account type
    //2. Username
    //3. password only   

    //cart functionality
    //order ETA caluclation logic
    




}

    
    
