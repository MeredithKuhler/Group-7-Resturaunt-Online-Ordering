package com.example.cse360_group7;

//import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.AttributedCharacterIterator;

//Imports for reading/writing
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaFX extends Application
{
//======================== JavaFX Method ========================//
@Override
public void start(Stage stage) throws Exception
{
	Scanner scan = new Scanner(System.in);




	//==============================================================================================================
	// Section: Global Pages & Styling
	// Description: Pages used by all users and elements/styling that will be needed on all 3 portals
	//==============================================================================================================

	int PADDING_VAL = 10;


	//----- Global Colors -----//
	Color GRAY = Color.rgb(135,135,135); //#878787
	Color RED = Color.rgb(239,1,1); //#EF0101
	Color GREEN = Color.rgb(0,192,72); //#00C048


	//----- Global Fonts -----//
	String GLOBALFONT_FAMILY = "monospace";

	double TITLEFONT_SIZE = 45;
	Font TITLE_FONT = new Font(GLOBALFONT_FAMILY, TITLEFONT_SIZE);

	double SUB1FONT_SIZE = 35;
	Font SUB1_FONT = new Font(GLOBALFONT_FAMILY, SUB1FONT_SIZE);

	double BODYFONT_SIZE = 25;
	Font BODY_FONT = new Font(GLOBALFONT_FAMILY, BODYFONT_SIZE);


	//----- Global Box Styles -----//
	String OUTER_BOX_CSS = "-fx-background-color: #878787;" +
				"-fx-alignment: bottom-center;" +
				"-fx-alignment: top-center";
	// nav menu on all pages
	String NAV_BOX_CSS = "-fx-background-color: #FFFFFF;" +
				"-fx-alignment: top-right;" +
				"-fx-padding: 10 100 5 30;" +
				"-fx-border-color: #EF0101;" +
				"-fx-border-width: 0 0 10 0";
	String NAV_BUTTON_CSS = "-fx-background-color: #FFFFFF;" +
				"-fx-padding: 0 20 0 20;" +
				"-fx-border-color: #000000;" +
				"-fx-border-width: 0 2 0 0";
	String NAV_LOGO_HBOX_CSS = "-fx-padding: 0 500 0 0";
	// food menu on order now pages (customer & employee versions)
	String MENU_BUTTON_CSS = "";


	//----- Global Images -----//
	Image logo = new Image(".\\logo.png"); //width = 75, height = 196
	
	//----- Global Vars -----//
	Customer currentCustomer = new Customer("guest", "guest");
	Employee admin = new Employee("admin", "admin");
	
 //============================= Log In =============================//

	//----- Main Header Menu -----//
	ImageView LI_logo = new ImageView(logo);
	LI_logo.setFitHeight(75);
	LI_logo.setFitWidth(196);

	HBox LI_logoBox = new HBox();
	LI_logoBox.getChildren().addAll(LI_logo);
	LI_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button LI_orderNow = new Button("ORDER NOW");
	LI_orderNow.setFont(TITLE_FONT);
	LI_orderNow.setStyle(NAV_BUTTON_CSS);

	Button LI_cart = new Button("CART");
	LI_cart.setFont(TITLE_FONT);
	LI_cart.setStyle(NAV_BUTTON_CSS);

	Button LI_login = new Button("LOGIN");
	LI_login.setFont(TITLE_FONT);
	LI_login.setStyle(NAV_BUTTON_CSS);
	LI_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox LI_menu = new HBox();
	LI_menu.getChildren().addAll(LI_logoBox, LI_orderNow, LI_cart, LI_login);
	LI_menu.setStyle(NAV_BOX_CSS);



	//----- User Entry Box -----//
	String li_entryBox_css = "-fx-background-color: #FFFFFF;" +
			"-fx-background-insets: 20 500 20 500;" +
			"-fx-padding: 50 500 190 510";
	String li_entryLab_css = "-fx-alignment: top-left;";
	String li_entryField_css = "fx-padding: 20;" +
			"-fx-min-height: 50;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";
	String li_accButton_css = "-fx-background-color: #FFFFFF;" +
			"-fx-padding: 0 200 0 0;" +
			"-fx-underline: true";
	String li_signInButton = "-fx-background-color: #FFFFFF;" +
			"-fx-underline: true;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";

	// title
	Label LI_titleLab = new Label("LOGIN");
	LI_titleLab.setFont(TITLE_FONT);
	VBox LI_title = new VBox();
	LI_title.getChildren().addAll(LI_titleLab);
	LI_title.setAlignment(Pos.TOP_CENTER);

	// user and pass entry
	Label LI_user = new Label("Username");
	LI_user.setFont(SUB1_FONT);
	LI_user.setStyle(li_entryLab_css);

	TextField LI_userField = new TextField();
	LI_userField.setFont(BODY_FONT);
	LI_userField.setStyle(li_entryField_css);

	Label LI_pass = new Label("Password");
	LI_pass.setFont(SUB1_FONT);
	LI_pass.setStyle(li_entryLab_css);

	TextField LI_passField = new TextField();
	LI_passField.setFont(BODY_FONT);
	LI_passField.setStyle(li_entryField_css);
	
	Label LI_error = new Label("");
	LI_error.setTextFill(RED);

	// create account and sign in buttons
	Button LI_createAcc = new Button("Create Account");
	LI_createAcc.setStyle(li_accButton_css);
	LI_createAcc.setFont(BODY_FONT);

	Button LI_signIn = new Button("Sign In");
	LI_signIn.setStyle(li_signInButton);
	LI_signIn.setFont(BODY_FONT);

	HBox LI_accButtons = new HBox();
	LI_accButtons.getChildren().addAll(LI_createAcc, LI_signIn);
	LI_accButtons.setStyle("-fx-padding: 75 0 0 0");

	//left aligned section VBox
	VBox LI_userEntry = new VBox();
	LI_userEntry.getChildren().addAll(LI_user, LI_userField, LI_pass, LI_passField, LI_error, LI_accButtons);
	LI_userEntry.setStyle("-fx-padding: 50");

	//Whole Entry Box VBox
	VBox LI_entry = new VBox();
	LI_entry.setStyle(li_entryBox_css);
	LI_entry.getChildren().addAll(LI_title, LI_userEntry);
	LI_entry.setAlignment(Pos.TOP_CENTER);


	//===== Creating the scene =====//
	VBox LI_outerBox = new VBox();
	LI_outerBox.setStyle(OUTER_BOX_CSS);
	LI_outerBox.getChildren().addAll(LI_menu, LI_entry);

	Scene SignInScene = new Scene(LI_outerBox, 1600, 850);

 //============================= Sign Up =============================//


	//----- Main Header Menu -----//
	ImageView SU_logo = new ImageView(logo);
	SU_logo.setFitHeight(75);
	SU_logo.setFitWidth(196);

	HBox SU_logoBox = new HBox();
	SU_logoBox.getChildren().addAll(SU_logo);
	SU_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button SU_orderNow = new Button("ORDER NOW");
	SU_orderNow.setFont(TITLE_FONT);
	SU_orderNow.setStyle(NAV_BUTTON_CSS);

	Button SU_cart = new Button("CART");
	SU_cart.setFont(TITLE_FONT);
	SU_cart.setStyle(NAV_BUTTON_CSS);

	Button SU_login = new Button("LOGIN");
	SU_login.setFont(TITLE_FONT);
	SU_login.setStyle(NAV_BUTTON_CSS);
	SU_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox SU_menu = new HBox();
	SU_menu.getChildren().addAll(SU_logoBox, SU_orderNow, SU_cart, SU_login);
	SU_menu.setStyle(NAV_BOX_CSS);



	//----- User Entry Box -----//
	String su_entryBox_css = "-fx-background-color: #FFFFFF;" +
			"-fx-background-insets: 20 500 20 500;" +
			"-fx-padding: 50 500 190 510";
	String su_entryLab_css = "-fx-alignment: top-left;";
	String su_entryField_css = "fx-padding: 20;" +
			"-fx-min-height: 50;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";
	String su_accButton_css = "-fx-background-color: #FFFFFF;" +
			"-fx-padding: 0 200 0 0;" +
			"-fx-underline: true";
	String su_signInButton = "-fx-background-color: #FFFFFF;" +
			"-fx-underline: true;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";

	// title
	Label SU_titleLab = new Label("SIGN UP");
	SU_titleLab.setFont(TITLE_FONT);
	VBox SU_title = new VBox();
	SU_title.getChildren().addAll(SU_titleLab);
	SU_title.setAlignment(Pos.TOP_CENTER);

	// user and pass entry
	Label SU_user = new Label("Username");
	SU_user.setFont(SUB1_FONT);
	SU_user.setStyle(li_entryLab_css);

	TextField SU_userField = new TextField();
	SU_userField.setFont(BODY_FONT);
	SU_userField.setStyle(su_entryField_css);

	Label SU_pass = new Label("Password");
	SU_pass.setFont(SUB1_FONT);
	SU_pass.setStyle(su_entryLab_css);

	TextField SU_passField = new TextField();
	SU_passField.setFont(BODY_FONT);
	SU_passField.setStyle(su_entryField_css);

	// create account and sign in buttons
	Button SU_createAcc = new Button("Sign In");
	SU_createAcc.setStyle(su_accButton_css);
	SU_createAcc.setFont(BODY_FONT);

	Button SU_signIn = new Button("Create Account");
	SU_signIn.setStyle(su_signInButton);
	SU_signIn.setFont(BODY_FONT);

	HBox SU_accButtons = new HBox();
	SU_accButtons.getChildren().addAll(SU_createAcc, SU_signIn);
	SU_accButtons.setStyle("-fx-padding: 75 0 0 0");

	//left aligned section VBox
	VBox SU_userEntry = new VBox();
	SU_userEntry.getChildren().addAll(SU_user, SU_userField, SU_pass, SU_passField, SU_accButtons);
	SU_userEntry.setStyle("-fx-padding: 50");

	//Whole Entry Box VBox
	VBox SU_entry = new VBox();
	SU_entry.setStyle(li_entryBox_css);
	SU_entry.getChildren().addAll(SU_title, SU_userEntry);
	SU_entry.setAlignment(Pos.TOP_CENTER);


	//===== Creating the scene =====//
	VBox SU_outerBox = new VBox();
	SU_outerBox.setStyle(OUTER_BOX_CSS);
	SU_outerBox.getChildren().addAll(SU_menu, SU_entry);

	Scene SignUpScene = new Scene(SU_outerBox, 1600, 850);





	//==============================================================================================================
	// Section: Customer Pages
	// Description: Pages that are specific to the customer view
	//==============================================================================================================

//============================= Account Info =============================//

	//----- Main Header Menu -----//
	ImageView C_AI_logo = new ImageView(logo);
	C_AI_logo.setFitHeight(75);
	C_AI_logo.setFitWidth(196);

	HBox C_AI_logoBox = new HBox();
	C_AI_logoBox.getChildren().addAll(C_AI_logo);
	C_AI_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button C_AI_orderNow = new Button("ORDER NOW");
	C_AI_orderNow.setFont(TITLE_FONT);
	C_AI_orderNow.setStyle(NAV_BUTTON_CSS);

	Button C_AI_cart = new Button("CART");
	C_AI_cart.setFont(TITLE_FONT);
	C_AI_cart.setStyle(NAV_BUTTON_CSS);

	Button C_AI_account = new Button("ACCOUNT");
	C_AI_account.setFont(TITLE_FONT);
	C_AI_account.setStyle(NAV_BUTTON_CSS);
	C_AI_account.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_AI_menu = new HBox();
	C_AI_menu.getChildren().addAll(C_AI_logoBox, C_AI_orderNow, C_AI_cart, C_AI_account);
	C_AI_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//

	Label C_AILabel = new Label("ACCOUNT INFO");
	C_AILabel.setFont(TITLE_FONT);

	Label C_AI_userLabel = new Label ("Username");
	C_AI_userLabel.setFont(SUB1_FONT);
	TextField C_AI_user = new TextField();
	C_AI_user.setFont(BODY_FONT);

	Label C_AI_visitsLabel = new Label("# of Visits");
	C_AI_visitsLabel.setFont(SUB1_FONT);
	TextField C_AI_visits = new TextField();
	C_AI_visits.setFont(BODY_FONT);

	Button C_AI_payment = new Button("Edit Payment Info");
	C_AI_payment.setFont(BODY_FONT);
	Button C_AI_orderStatus = new Button("Check Order Status");
	C_AI_orderStatus.setFont(BODY_FONT);

	Button C_AI_Logout = new Button("LOGOUT");
	C_AI_Logout.setFont(SUB1_FONT);

	HBox C_AI_buttonBox = new HBox();
	C_AI_buttonBox.getChildren().addAll(C_AI_payment, C_AI_orderStatus);
	VBox C_AI_userInfo = new VBox();
	C_AI_userInfo.getChildren().addAll(C_AI_userLabel, C_AI_user, C_AI_visitsLabel, C_AI_visits);


	//===== Creating the scene =====//
	VBox C_AI_outerBox = new VBox();
	C_AI_outerBox.setStyle(OUTER_BOX_CSS);
	C_AI_outerBox.getChildren().addAll(C_AI_menu, C_AI_userInfo, C_AI_buttonBox, C_AI_Logout);

	Scene CAccountInfoScene = new Scene(C_AI_outerBox, 1600, 850);




//============================= Cart =============================//

	//----- Main Header Menu -----//
	ImageView C_CA_logo = new ImageView(logo);
	C_CA_logo.setFitHeight(75);
	C_CA_logo.setFitWidth(196);

	HBox C_CA_logoBox = new HBox();
	C_CA_logoBox.getChildren().addAll(C_CA_logo);
	C_CA_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button C_CA_orderNow = new Button("ORDER NOW");
	C_CA_orderNow.setFont(TITLE_FONT);
	C_CA_orderNow.setStyle(NAV_BUTTON_CSS);

	Button C_CA_cart = new Button("CART");
	C_CA_cart.setFont(TITLE_FONT);
	C_CA_cart.setStyle(NAV_BUTTON_CSS);

	Button C_CA_login = new Button("LOGIN");
	C_CA_login.setFont(TITLE_FONT);
	C_CA_login.setStyle(NAV_BUTTON_CSS);
	C_CA_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_CA_menu = new HBox();
	C_CA_menu.getChildren().addAll(C_CA_logoBox, C_CA_orderNow, C_CA_cart, C_CA_login);
	C_CA_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//

	Label C_CALabel = new Label("CART");
	C_CALabel.setFont(TITLE_FONT);

	VBox C_CA_cartBox = new VBox();

	//----- empty cart -----//
	Label C_CA_noCart = new Label("!");
	C_CA_noCart.setFont(TITLE_FONT);

	Label C_CA_noItems = new Label("No items are in your cart!");
	C_CA_noItems.setFont(BODY_FONT);

	Button C_CA_newOrder = new Button("Order Now");
	C_CA_newOrder.setFont(BODY_FONT);

	C_CA_cartBox.getChildren().addAll(C_CA_noCart, C_CA_noItems, C_CA_newOrder);

	//----- cart with items -----//


	//===== Creating the scene =====//
	VBox C_CA_outerBox = new VBox();
	C_CA_outerBox.setStyle(OUTER_BOX_CSS);
	C_CA_outerBox.getChildren().addAll(C_CA_menu, C_CALabel, C_CA_cartBox);

	Scene CCartScene = new Scene(C_CA_outerBox, 1600, 850);




//============================= Customer Menu =============================//

	//----- Main Header Menu -----//
	ImageView C_CM_logo = new ImageView(logo);
	C_CM_logo.setFitHeight(75);
	C_CM_logo.setFitWidth(196);

	HBox C_CM_logoBox = new HBox();
	C_CM_logoBox.getChildren().addAll(C_CM_logo);
	C_CM_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button C_CM_orderNow = new Button("ORDER NOW");
	C_CM_orderNow.setFont(TITLE_FONT);
	C_CM_orderNow.setStyle(NAV_BUTTON_CSS);

	Button C_CM_cart = new Button("CART");
	C_CM_cart.setFont(TITLE_FONT);
	C_CM_cart.setStyle(NAV_BUTTON_CSS);

	Button C_CM_login = new Button("LOGIN");
	C_CM_login.setFont(TITLE_FONT);
	C_CM_login.setStyle(NAV_BUTTON_CSS);
	C_CM_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_CM_menu = new HBox();
	C_CM_menu.getChildren().addAll(C_CM_logoBox, C_CM_orderNow, C_CM_cart, C_CM_login);
	C_CM_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//




//============================= Customer Payment Info =============================//

	//----- Main Header Menu -----//
	ImageView C_PI_logo = new ImageView(logo);
	C_PI_logo.setFitHeight(75);
	C_PI_logo.setFitWidth(196);

	HBox C_PI_logoBox = new HBox();
	C_PI_logoBox.getChildren().addAll(C_PI_logo);
	C_PI_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button C_PI_orderNow = new Button("ORDER NOW");
	C_PI_orderNow.setFont(TITLE_FONT);
	C_PI_orderNow.setStyle(NAV_BUTTON_CSS);

	Button C_PI_cart = new Button("CART");
	C_PI_cart.setFont(TITLE_FONT);
	C_PI_cart.setStyle(NAV_BUTTON_CSS);

	Button C_PI_login = new Button("LOGIN");
	C_PI_login.setFont(TITLE_FONT);
	C_PI_login.setStyle(NAV_BUTTON_CSS);
	C_PI_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_PI_menu = new HBox();
	C_PI_menu.getChildren().addAll(C_CM_logoBox, C_PI_orderNow, C_PI_cart, C_PI_login);
	C_PI_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//






	//==============================================================================================================
	// Section: Employee Pages
	// Description: Pages that are specific to the employee view
	//==============================================================================================================


//============================= Employee Account =============================//

	//----- Main Header Menu -----//
	ImageView E_AI_logo = new ImageView(logo);
	E_AI_logo.setFitHeight(75);
	E_AI_logo.setFitWidth(196);

	HBox E_AI_logoBox = new HBox();
	C_CM_logoBox.getChildren().addAll(E_AI_logo);
	C_CM_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button E_AI_orderNow = new Button("ORDER NOW");
	E_AI_orderNow.setFont(TITLE_FONT);
	E_AI_orderNow.setStyle(NAV_BUTTON_CSS);

	Button E_AI_cart = new Button("CART");
	E_AI_cart.setFont(TITLE_FONT);
	E_AI_cart.setStyle(NAV_BUTTON_CSS);

	Button E_AI_account = new Button("ACCOUNT");
	E_AI_account.setFont(TITLE_FONT);
	E_AI_account.setStyle(NAV_BUTTON_CSS);
	E_AI_account.setTextFill(RED);

	//Main Header Menu HBox
	HBox E_AI_menu = new HBox();
	E_AI_menu.getChildren().addAll(E_AI_logoBox, E_AI_orderNow, E_AI_cart, E_AI_account);
	E_AI_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//
	Label E_AILabel = new Label("ACCOUNT AND COUPONS");
	E_AILabel.setFont(TITLE_FONT);

	VBox E_AI_mainBox = new VBox();

	ArrayList<Customer> customers = new ArrayList<Customer>();
	customers.add(new Customer("Steve", "Steve"));
	customers.add(new Customer("James", "James"));

	Label E_AI_userLabel = new Label("Username");
	E_AI_userLabel.setFont(SUB1_FONT);
	ComboBox E_AI_userBox = new ComboBox();

	ObservableList<String> customerList = E_AI_userBox.getItems();
	for(int i = 0; i < customers.size(); i++) {
		customerList.add(customers.get(i).getUsername());
	}
	
	VBox E_AI_coupon = new VBox();
	
	ArrayList<Coupon> coupons = new ArrayList<Coupon>();
	coupons.add(new Coupon("a43wa", .15f));
	coupons.add(new Coupon("4b54c", .5f));
	
	Label E_AI_couponDist = new Label("Distribute Coupon");
	E_AI_couponDist.setFont(SUB1_FONT);
	ComboBox E_AI_couponField = new ComboBox();
	
	ObservableList<String> couponList = E_AI_couponField.getItems();
	for(int i = 0; i < coupons.size(); i++) {
		couponList.add(coupons.get(i).getCouponCode() + "\t" + String.format("%.2f",coupons.get(i).getCouponDiscount()*100) + "% off");
	}
	
	Label E_AI_codeLabel = new Label("Coupon Code");
	E_AI_codeLabel.setFont(SUB1_FONT);
	TextField E_AI_code = new TextField();
	E_AI_code.setFont(BODY_FONT);

	Label E_AI_discLabel = new Label("Coupon Code");
	E_AI_discLabel.setFont(SUB1_FONT);
	TextField E_AI_disc = new TextField();
	E_AI_disc.setFont(BODY_FONT);

	Button E_AI_couponButton = new Button("Distribute");
	E_AI_couponButton.setFont(BODY_FONT);

	E_AI_coupon.getChildren().addAll(E_AI_couponDist, E_AI_couponField, E_AI_codeLabel, E_AI_code, E_AI_discLabel, E_AI_disc, E_AI_couponButton);

	Button E_AI_logout = new Button("LOGOUT");
	E_AI_logout.setFont(SUB1_FONT);

	E_AI_mainBox.getChildren().addAll(E_AI_userLabel, E_AI_userBox, E_AI_coupon, E_AI_logout);

	//===== Creating the scene =====//
	VBox E_AI_outerBox = new VBox();
	E_AI_outerBox.setStyle(OUTER_BOX_CSS);
	E_AI_outerBox.getChildren().addAll(E_AI_menu, E_AILabel, E_AI_mainBox);

	Scene EAccountScene = new Scene(E_AI_outerBox, 1600, 850);




//============================= Employee Menu =============================//

	//----- Main Header Menu -----//
	ImageView E_EM_logo = new ImageView(logo);
	E_EM_logo.setFitHeight(75);
	E_EM_logo.setFitWidth(196);

	HBox E_EM_logoBox = new HBox();
	E_EM_logoBox.getChildren().addAll(E_EM_logo);
	E_EM_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button E_EM_orderNow = new Button("ORDER NOW");
	E_EM_orderNow.setFont(TITLE_FONT);
	E_EM_orderNow.setStyle(NAV_BUTTON_CSS);

	Button E_EM_cart = new Button("CART");
	E_EM_cart.setFont(TITLE_FONT);
	E_EM_cart.setStyle(NAV_BUTTON_CSS);

	Button E_EM_login = new Button("LOGIN");
	E_EM_login.setFont(TITLE_FONT);
	E_EM_login.setStyle(NAV_BUTTON_CSS);
	E_EM_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox E_EM_menu = new HBox();
	E_EM_menu.getChildren().addAll(E_EM_logoBox, E_EM_orderNow, E_EM_cart, E_EM_login);
	E_EM_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//





	//==============================================================================================================
	// Section: GUI Management
	// Description: Sets page displays (opens and closes based on event handlers)
	//==============================================================================================================
	
	//event handlers
	//login event
	/*Future error handling:
	 * Spaces in name
	 * Char limit
	 * Illegal chars
	 * Make the file reader look nicer
	 * Might not be able to make multiple accounts at once
	*/
	//If things are broken make sure you have the accountinfo.txt file. Also make sure the directory is set up correctly.
		
	LI_createAcc.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		//Reading from text file
		String currentline = "";
		Scanner findName = new Scanner(new FileReader(".\\src\\accountinfo.txt"));
		Boolean usernameExists = true;
		
        //Writing to text file
        FileWriter fw = new FileWriter(".\\src\\accountinfo.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        @Override
        public void handle(javafx.scene.input.MouseEvent e) {
            if(LI_userField.getText() == "" || LI_passField.getText() == "") {
                System.out.println("Error: Missing username/password");
                LI_error.setText("Error: Missing username/password");
            }
            else {
            	while(findName.hasNextLine()){
                    currentline = findName.nextLine();
                    if(currentline.equals("username: " + LI_userField.getText())){
                        System.out.println("Error: Username already exists!");
                        usernameExists = true;
                        break;
                    }
                    else{
                        usernameExists = false;
                    }
                }
            	if(usernameExists) {
            		LI_error.setText("Username already exists!");
            	}
            	else {
            		System.out.println("Pass");
            		LI_error.setText("");
            		out.append("username: " + LI_userField.getText() +"\n");
            		out.append("password: " + LI_passField.getText() + "\n");
            		out.append("accounttype: customer\n");
            		currentCustomer.setUsername(LI_userField.getText());
            		currentCustomer.setPassword(LI_passField.getText());
            		C_AI_user.setText(currentCustomer.getUsername());
            		LI_userField.setText("");
            		LI_passField.setText("");
            		out.close();
            		stage.setScene(CAccountInfoScene);
            	}
            }
            try {
				findName = new Scanner(new FileReader(".\\src\\accountinfo.txt"));
			} catch (FileNotFoundException e1) {
				System.out.println("Unexpected error when reading file. CreateAccount");
			}
            currentline = "";
        }
	}));
	
	//Login
	LI_signIn.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String currentline = "";
		Scanner findName = new Scanner(new FileReader(".\\src\\accountinfo.txt"));
		Boolean userExists = true;
		public void handle(javafx.scene.input.MouseEvent e) {
			try {
				findName = new Scanner(new FileReader(".\\src\\accountinfo.txt"));
			} catch (FileNotFoundException e1) {
				System.out.println("Unexpected error when reading file. Login");
			}
            if(LI_userField.getText() == "" || LI_passField.getText() == "") {
                System.out.println("Error: Missing username/password");
                LI_error.setText("Error: Missing username/password");
            }
            else {
            	while(findName.hasNextLine()){
                    currentline = findName.nextLine();
                    if(currentline.equals("username: " + LI_userField.getText()) && findName.nextLine().equals("password: " + LI_passField.getText())){
                    	userExists = true;
                    	break;
                    }
                    else{
                        userExists = false;
                    }
                }
            	if(userExists) {
            		System.out.println("Pass");
            		if(findName.nextLine().equals("accounttype: customer")) {
                		currentCustomer.setUsername(LI_userField.getText());
                		currentCustomer.setPassword(LI_passField.getText());
                		LI_userField.setText("");
                		LI_passField.setText("");
                		C_AI_user.setText(currentCustomer.getUsername());
            			stage.setScene(CAccountInfoScene);
            		}
            		else
            			stage.setScene(EAccountScene);
            		LI_error.setText("");
            	}
            	else {
            		LI_error.setText("Error: incorrect username/password");
            	}
            }
            currentline = "";
		}
	}));
	
	//logout
	C_AI_Logout.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(SignInScene);
			currentCustomer.setUsername("Guest");
			currentCustomer.setPassword("Guest");
		}
	}));
	
	E_AI_logout.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(SignInScene);
			currentCustomer.setUsername("Guest");
			currentCustomer.setPassword("Guest");
    		LI_userField.setText("");
    		LI_passField.setText("");
		}
	}));
	//coupon distribution
	
	stage.show();
	stage.setScene(SignInScene);
	//stage.setScene(SignUpScene);

	//stage.setScene(CAccountInfoScene);
	//stage.setScene(CCartScene);
	//stage.setScene(CMenu); -
	//stage.setScene(CPaymentInfo); -

	//stage.setScene(EMenu); -
	//stage.setScene(EAccountScene);

}

	public static void main(String[] args) {
		launch();
	}
}
