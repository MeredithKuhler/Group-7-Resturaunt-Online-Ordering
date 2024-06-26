package application;

//import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.text.AttributedCharacterIterator;

//Imports for reading/writing

import java.util.ArrayList;
import java.util.Scanner;

//All instances of reading and writing text files were created with the assistance of this stackoverflow answer:
//https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java

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


	//----- Global Styles -----//
	String OUTER_BOX_CSS = "-fx-background-color: #878787;" +
				"-fx-alignment: bottom-center;" +
				"-fx-alignment: top-center;";
	// nav menu on all pages
	String NAV_BOX_CSS = "-fx-background-color: #FFFFFF;" +
				"-fx-alignment: top-right;" +
				"-fx-padding: 10 100 5 30;" +
				"-fx-border-color: #EF0101;" +
				"-fx-border-width: 0 0 10 0;";
	String NAV_BUTTON_CSS = "-fx-background-color: #FFFFFF;" +
				"-fx-padding: 0 20 0 20;" +
				"-fx-border-color: #000000;" +
				"-fx-border-width: 0 2 0 0;";
	String NAV_LOGO_HBOX_CSS = "-fx-padding: 0 500 0 0";
	String WHITEBOX_CSS = "-fx-background-color: #FFFFFF;" +
			"-fx-background-insets: 20 500 20 500;" +
			"-fx-padding: 50 510 190 510;";
	String BOXED_BUTTON_CSS = "-fx-background-color: #FFFFFF;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2;";
	String UNDERLINE_BUTTON_CSS = "-fx-background-color: #FFFFFF;" +
			"-fx-padding: 0 200 0 0;" +
			"-fx-underline: true;";
	String TITLE_CSS = "-fx-padding: 0 0 50 0;" +
			"-fx-text-alignment: CENTER;" +
			"-fx-alignment: top-center;";


	//----- Global Images -----//
	Image logo = new Image(".\\logo.png"); //width = 75, height = 196
	
	//----- Global Vars -----//
	final String CCINFOFILE = ".\\src\\ccinfo.txt";
	final String TEXTFILE = ".\\src\\accountinfo.txt";
	final String COUPONFILE = ".\\src\\coupons.txt";
	Customer currentCustomer = new Customer("guest", "guest");
	Employee admin = new Employee("admin", "admin");
	ArrayList<MenuItem> displayedMenu = new ArrayList<MenuItem>();
	int totalETA = 0;
	
	//preset coupons
	ArrayList<Coupon> couponList = new ArrayList<Coupon>();
	couponList.add(new Coupon("2abt3", 0.15f));
	couponList.add(new Coupon("03bsd", 0.5f));
	couponList.add(new Coupon("25hac", 0.25f));
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
	String li_entryLab_css = "-fx-alignment: top-left;";
	String li_entryField_css = "fx-padding: 20;" +
			"-fx-min-height: 50;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";

	// title
	Label LI_titleLab = new Label("LOGIN");
	LI_titleLab.setFont(TITLE_FONT);
	VBox LI_title = new VBox();
	LI_title.getChildren().addAll(LI_titleLab);
	LI_title.setStyle(TITLE_CSS);

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
	LI_createAcc.setStyle(UNDERLINE_BUTTON_CSS);
	LI_createAcc.setFont(BODY_FONT);

	Button LI_signIn = new Button("Sign In");
	LI_signIn.setStyle(BOXED_BUTTON_CSS);
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
	LI_entry.setStyle(WHITEBOX_CSS);
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
	String su_entryLab_css = "-fx-alignment: top-left;";
	String su_entryField_css = "fx-padding: 20;" +
			"-fx-min-height: 50;" +
			"-fx-border-color: #878787;" +
			"-fx-border-width: 2";

	// title
	Label SU_titleLab = new Label("SIGN UP");
	SU_titleLab.setFont(TITLE_FONT);
	VBox SU_title = new VBox();
	SU_title.getChildren().addAll(SU_titleLab);
	SU_title.setStyle(TITLE_CSS);

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
	SU_createAcc.setStyle(UNDERLINE_BUTTON_CSS);
	SU_createAcc.setFont(BODY_FONT);

	Button SU_signIn = new Button("Create Account");
	SU_signIn.setStyle(BOXED_BUTTON_CSS);
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
	SU_entry.setStyle(WHITEBOX_CSS);
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

	Button C_AI_login = new Button("LOGIN");
	C_AI_login.setFont(TITLE_FONT);
	C_AI_login.setStyle(NAV_BUTTON_CSS);
	C_AI_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_AI_menu = new HBox();
	C_AI_menu.getChildren().addAll(C_AI_logoBox, C_AI_orderNow, C_AI_cart, C_AI_login);
	C_AI_menu.setStyle(NAV_BOX_CSS);

	//===== CSS =====//
	String C_AI_buttonBox_css = "-fx-alignment: top-center;" +
			"-fx-padding: 40 30 30 30";

	//===== Main Page Box =====//

	//title
	Label C_AILabel = new Label("ACCOUNT INFO");
	C_AILabel.setFont(TITLE_FONT);
	VBox C_AI_title = new VBox(C_AILabel);
	C_AI_title.setStyle(TITLE_CSS);

	// user info
	Label C_AI_userLabel = new Label ("Username");
	C_AI_userLabel.setFont(SUB1_FONT);
	TextField C_AI_user = new TextField();
	C_AI_user.setFont(BODY_FONT);

	Label C_AI_visitsLabel = new Label("# of Visits");
	C_AI_visitsLabel.setFont(SUB1_FONT);
	TextField C_AI_visits = new TextField();
	C_AI_visits.setFont(BODY_FONT);

	VBox C_AI_userInfo = new VBox();
	C_AI_userInfo.getChildren().addAll(C_AI_userLabel, C_AI_user, C_AI_visitsLabel, C_AI_visits);
	C_AI_userInfo.setStyle("-fx-padding: 0 40 0 40");

	// buttons
	Button C_AI_payment = new Button("Edit Payment Info");
	C_AI_payment.setFont(BODY_FONT);
	C_AI_payment.setStyle(BOXED_BUTTON_CSS);
	Button C_AI_orderStatus = new Button("Check Order Status");
	C_AI_orderStatus.setFont(BODY_FONT);
	C_AI_orderStatus.setStyle(BOXED_BUTTON_CSS);

	Button C_AI_Logout = new Button("LOG OUT");
	C_AI_Logout.setFont(SUB1_FONT);
	C_AI_Logout.setStyle(UNDERLINE_BUTTON_CSS + "-fx-padding: 20 0 0 40;");

	HBox C_AI_buttonBox = new HBox();
	C_AI_buttonBox.getChildren().addAll(C_AI_payment, C_AI_orderStatus);
	C_AI_buttonBox.setStyle(C_AI_buttonBox_css);
	C_AI_buttonBox.setSpacing(30);

	// outer white box
	VBox C_AI_mainBox = new VBox();
	C_AI_mainBox.setStyle(WHITEBOX_CSS);
	C_AI_mainBox.getChildren().addAll(C_AI_title, C_AI_userInfo, C_AI_buttonBox, C_AI_Logout);


	//===== Creating the scene =====//
	VBox C_AI_outerBox = new VBox();
	C_AI_outerBox.setStyle(OUTER_BOX_CSS);
	C_AI_outerBox.getChildren().addAll(C_AI_menu, C_AI_mainBox);

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
	C_CA_cart.setTextFill(RED);

	Button C_CA_login = new Button("LOGIN");
	C_CA_login.setFont(TITLE_FONT);
	C_CA_login.setStyle(NAV_BUTTON_CSS);

	//Main Header Menu HBox
	HBox C_CA_menu = new HBox();
	C_CA_menu.getChildren().addAll(C_CA_logoBox, C_CA_orderNow, C_CA_cart, C_CA_login);
	C_CA_menu.setStyle(NAV_BOX_CSS);

	// css
	String cart_box_css = "-fx-border-color: #EF0101;" +
			"-fx-border-width: 2;" +
			"-fx-padding: 20 20 20 20;";

	//===== Main Page Box =====//

	// title
	Label C_CALabel = new Label("CART");
	C_CALabel.setFont(TITLE_FONT);
	VBox C_CA_title = new VBox(C_CALabel);
	C_CA_title.setStyle(TITLE_CSS);

	VBox C_CA_cartBox = new VBox();

	//----- cart -----//

	Label C_CA_itemStatus = new Label("!\nNo items are in your cart!");
	C_CA_itemStatus.setFont(BODY_FONT);

	Label C_CA_yesItems = new Label("");
	C_CA_yesItems.setFont(BODY_FONT);
	
	ComboBox C_CA_removeItemBox = new ComboBox();
	Button C_CA_removeItemButton = new Button("Remove Item");
	
	C_CA_removeItemButton.setFont(BODY_FONT);
	
	Button C_CA_newOrder = new Button("Order Now");
	C_CA_newOrder.setFont(BODY_FONT);
	C_CA_newOrder.setStyle(BOXED_BUTTON_CSS);

	C_CA_cartBox.setStyle(cart_box_css);
	C_CA_cartBox.setAlignment(Pos.TOP_CENTER);

	//full cart
	VBox C_CA_cartItems = new VBox();
	ScrollPane C_CA_fullCart = new ScrollPane(C_CA_cartItems);
	C_CA_cartItems.setSpacing(20);
	//empty cart
	VBox C_CA_emptyCart = new VBox();
	C_CA_emptyCart.getChildren().addAll(C_CA_itemStatus, C_CA_yesItems, C_CA_removeItemBox, C_CA_removeItemButton, C_CA_newOrder);
	



	//===== Creating the scene =====//
	C_CA_cartBox.getChildren().addAll(C_CA_emptyCart);
	// remove all and add - empty cart: C_CA_emptyCart. full cart: C_CA_fullCart

	VBox C_CA_mainBox = new VBox();
	C_CA_mainBox.setStyle(WHITEBOX_CSS);
	C_CA_mainBox.getChildren().addAll(C_CA_title, C_CA_cartBox);

	VBox C_CA_outerBox = new VBox();
	C_CA_outerBox.setStyle(OUTER_BOX_CSS);
	C_CA_outerBox.getChildren().addAll(C_CA_menu, C_CA_mainBox);

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
	C_CM_orderNow.setTextFill(RED);

	Button C_CM_cart = new Button("CART");
	C_CM_cart.setFont(TITLE_FONT);
	C_CM_cart.setStyle(NAV_BUTTON_CSS);

	Button C_CM_login = new Button("LOGIN");
	C_CM_login.setFont(TITLE_FONT);
	C_CM_login.setStyle(NAV_BUTTON_CSS);

	//Main Header Menu HBox
	HBox C_CM_menu = new HBox();
	C_CM_menu.getChildren().addAll(C_CM_logoBox, C_CM_orderNow, C_CM_cart, C_CM_login);
	C_CM_menu.setStyle(NAV_BOX_CSS);

	// css
	String C_CM_subMenu_css = "-fx-background-color: #FFFFFF;" +
			"-fx-padding: 10 10 10 10;";

	//===== Sub Menu Box =====//
	Button C_CM_saladButton = new Button("SALADS");
	C_CM_saladButton.setFont(SUB1_FONT);
	C_CM_saladButton.setStyle(BOXED_BUTTON_CSS);

	Button C_CM_pastaButton = new Button("PASTA");
	C_CM_pastaButton.setFont(SUB1_FONT);
	C_CM_pastaButton.setStyle(BOXED_BUTTON_CSS);

	Button C_CM_paniniButton = new Button("PANINI");
	C_CM_paniniButton.setFont(SUB1_FONT);
	C_CM_paniniButton.setStyle(BOXED_BUTTON_CSS);

	Button C_CM_pizzaButton = new Button("PIZZA");
	C_CM_pizzaButton.setFont(SUB1_FONT);
	C_CM_pizzaButton.setStyle(BOXED_BUTTON_CSS);

	TextField C_CM_searchField = new TextField();
	C_CM_searchField.setPromptText("SEARCH");
	C_CM_searchField.setFont(SUB1_FONT);
	C_CM_searchField.setStyle(BOXED_BUTTON_CSS);

	HBox C_CM_subMenu = new HBox();
	C_CM_subMenu.getChildren().addAll(C_CM_saladButton, C_CM_pastaButton, C_CM_paniniButton, C_CM_pizzaButton, C_CM_searchField);
	C_CM_subMenu.setStyle(C_CM_subMenu_css);
	C_CM_subMenu.setAlignment(Pos.TOP_RIGHT);
	C_CM_subMenu.setSpacing(20);


	//===== Main Body Box =====//
	VBox C_CM_mainBodyBox = new VBox();

	Label C_CM_saladLabel = new Label("SALAD");
	C_CM_saladLabel.setFont(TITLE_FONT);
	GridPane C_CM_salads = new GridPane();

	Label C_CM_pastaLabel = new Label("PASTA");
	C_CM_pastaLabel.setFont(TITLE_FONT);
	GridPane C_CM_pastas = new GridPane();

	Label C_CM_paniniLabel = new Label("PANINI");
	C_CM_paniniLabel.setFont(TITLE_FONT);
	GridPane C_CM_paninis = new GridPane();

	Label C_CM_pizzaLabel = new Label("PIZZA");
	C_CM_pizzaLabel.setFont(TITLE_FONT);
	GridPane C_CM_pizzas = new GridPane();

	C_CM_mainBodyBox.getChildren().addAll(C_CM_saladLabel, C_CM_salads, C_CM_pastaLabel, C_CM_pastas, C_CM_paniniLabel, C_CM_paninis, C_CM_pizzaLabel, C_CM_pizzas);
	ScrollPane C_CM_mainBody = new ScrollPane();
	C_CM_mainBody.setContent(C_CM_mainBodyBox);
	C_CM_mainBody.setStyle(WHITEBOX_CSS);
	
	ComboBox C_CM_orderSelection = new ComboBox();
	Button C_CM_addToCart = new Button();
	
	C_CM_addToCart.setText("Add to cart");
	//===== Creating the scene =====//
	VBox C_CM_outerBox = new VBox();
	C_CM_outerBox.setStyle(OUTER_BOX_CSS);
	C_CM_outerBox.getChildren().addAll(C_CM_menu, C_CM_subMenu, C_CM_mainBody, C_CM_orderSelection, C_CM_addToCart);

	Scene CMenuScene = new Scene(C_CM_outerBox, 1600, 850);



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

	Button C_PI_login = new Button("ACCOUNT");
	C_PI_login.setFont(TITLE_FONT);
	C_PI_login.setStyle(NAV_BUTTON_CSS);
	C_PI_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_PI_menu = new HBox();
	C_PI_menu.getChildren().addAll(C_PI_logoBox, C_PI_orderNow, C_PI_cart, C_PI_login);
	C_PI_menu.setStyle(NAV_BOX_CSS);

	// css
	String C_PI_cardinfobox_css = new String("-fx-background-color: #EBEBEB");


	//===== Main Page Box =====//

	// title
	Label C_PIlabel = new Label("Payment Information");
	C_PIlabel.setFont(TITLE_FONT);
	VBox C_PI_title = new VBox(C_PIlabel);
	C_PI_title.setStyle(TITLE_CSS);

	// card number
	TextField C_PI_cardNumber = new TextField();
	C_PI_cardNumber.setFont(BODY_FONT);
	C_PI_cardNumber.setStyle(C_PI_cardinfobox_css);

	Label C_PI_numberLabel = new Label("Card Number");
	C_PI_numberLabel.setFont(BODY_FONT);

	VBox C_PI_numBox = new VBox();
	C_PI_numBox.getChildren().addAll(C_PI_cardNumber, C_PI_numberLabel);
	C_PI_numBox.setStyle("-fx-padding: 0 0 30 0");


	// card name
	TextField C_PI_cardName = new TextField();
	C_PI_cardName.setFont(BODY_FONT);
	C_PI_cardName.setStyle(C_PI_cardinfobox_css);

	Label C_PI_nameLabel = new Label("Name");
	C_PI_nameLabel.setFont(BODY_FONT);

	VBox C_PI_nameBox = new VBox();
	C_PI_nameBox.getChildren().addAll(C_PI_cardName, C_PI_nameLabel);

	// security code
	TextField C_PI_secCode = new TextField();
	C_PI_secCode.setFont(BODY_FONT);
	C_PI_secCode.setStyle(C_PI_cardinfobox_css);

	Label C_PI_secLabel = new Label("Security Code");
	C_PI_secLabel.setFont(BODY_FONT);

	VBox C_PI_secBox = new VBox();
	C_PI_secBox.getChildren().addAll(C_PI_secCode, C_PI_secLabel);

	// expiration
	TextField C_PI_expiration = new TextField();
	C_PI_expiration.setFont(BODY_FONT);
	C_PI_expiration.setStyle(C_PI_cardinfobox_css);

	Label C_PI_expirLabel = new Label("Expiration");
	C_PI_expirLabel.setFont(BODY_FONT);
	
	VBox C_PI_expBox = new VBox();
	C_PI_expBox.getChildren().addAll(C_PI_expiration, C_PI_expirLabel);
	
	// horizontal styling
	HBox C_PI_cardBox = new HBox();
	C_PI_cardBox.getChildren().addAll(C_PI_nameBox, C_PI_secBox, C_PI_expBox);
	C_PI_cardBox.setSpacing(20);
	
	Label C_PI_couponLabel = new Label("Select Coupon: ");
	ComboBox C_PI_couponBox = new ComboBox();
	Button C_PI_applyCoupon = new Button("Apply Coupon");
	Label C_PI_totalPrice = new Label("Total price: " + currentCustomer.getCartPrice());
	// white box
	VBox C_PI_mainBox = new VBox();
	C_PI_mainBox.getChildren().addAll(C_PI_title, C_PI_numBox, C_PI_cardBox, C_PI_couponLabel, C_PI_couponBox, C_PI_applyCoupon, C_PI_totalPrice);
	C_PI_mainBox.setStyle(WHITEBOX_CSS);
	
	//order now button
	Button C_PI_confirmOrder = new Button("Confirm Order");
	
	//===== Creating the scene =====//
	VBox C_PI_outerBox = new VBox();
	C_PI_outerBox.setStyle(OUTER_BOX_CSS);
	C_PI_outerBox.getChildren().addAll(C_PI_menu, C_PI_mainBox, C_PI_confirmOrder);

	Scene CCardInfoScene = new Scene(C_PI_outerBox, 1600, 850);





//============================= Customer Order Status =============================//

	//----- Main Header Menu -----//
	ImageView C_OS_logo = new ImageView(logo);
	C_OS_logo.setFitHeight(75);
	C_OS_logo.setFitWidth(196);

	HBox C_OS_logoBox = new HBox();
	C_OS_logoBox.getChildren().addAll(C_OS_logo);
	C_OS_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button C_OS_orderNow = new Button("ORDER NOW");
	C_OS_orderNow.setFont(TITLE_FONT);
	C_OS_orderNow.setStyle(NAV_BUTTON_CSS);

	Button C_OS_cart = new Button("CART");
	C_OS_cart.setFont(TITLE_FONT);
	C_OS_cart.setStyle(NAV_BUTTON_CSS);

	Button C_OS_login = new Button("ACCOUNT");
	C_OS_login.setFont(TITLE_FONT);
	C_OS_login.setStyle(NAV_BUTTON_CSS);
	C_OS_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox C_OS_menu = new HBox();
	C_OS_menu.getChildren().addAll(C_OS_logoBox, C_OS_orderNow, C_OS_cart, C_OS_login);
	C_OS_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//

	// title
	Label C_OSLabel = new Label("Order Status");
	C_OSLabel.setFont(TITLE_FONT);
	VBox C_OS_title = new VBox(C_OSLabel);
	C_OS_title.setStyle(TITLE_CSS);
	
	Label C_OSOrder1 = new Label("Customer 1\t 20 minutes");
	Label C_OSOrder2 = new Label("Customer 2\t 50 minutes");
	Label C_OSOrder3 = new Label("");
	// no orders
	Label C_OS_noOrderLabel = new Label("You have no orders under your account!");
	C_OS_noOrderLabel.setFont(SUB1_FONT);
	
	VBox C_OS_noOrders = new VBox(C_OS_noOrderLabel);

	// orders
	VBox C_OS_orderList = new VBox();

	ScrollPane C_OS_orders = new ScrollPane(C_OS_orderList);
	
	// white box
	VBox C_OS_mainBox = new VBox();
	C_OS_mainBox.getChildren().addAll(C_OS_title, C_OSOrder1, C_OSOrder2, C_OSOrder3);
	C_OS_mainBox.setStyle(WHITEBOX_CSS);

	//===== Creating the scene =====//
	VBox C_OS_outerBox = new VBox();
	C_OS_outerBox.setStyle(OUTER_BOX_CSS);
	C_OS_outerBox.getChildren().addAll(C_OS_menu, C_OS_mainBox);
	
	Scene COrderStatus = new Scene(C_OS_outerBox, 1600, 850);
	


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
	E_AI_logoBox.getChildren().addAll(E_AI_logo);
	E_AI_logoBox.setStyle(NAV_LOGO_HBOX_CSS);

	Button E_AI_orderNow = new Button("ORDER NOW");
	E_AI_orderNow.setFont(TITLE_FONT);
	E_AI_orderNow.setStyle(NAV_BUTTON_CSS);

	Button E_AI_cart = new Button("CART");
	E_AI_cart.setFont(TITLE_FONT);
	E_AI_cart.setStyle(NAV_BUTTON_CSS);

	Button E_AI_login = new Button("ACCOUNT");
	E_AI_login.setFont(TITLE_FONT);
	E_AI_login.setStyle(NAV_BUTTON_CSS);
	E_AI_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox E_AI_menu = new HBox();
	E_AI_menu.getChildren().addAll(E_AI_logoBox, E_AI_orderNow, E_AI_cart, E_AI_login);
	E_AI_menu.setStyle(NAV_BOX_CSS);


	//===== Main Page Box =====//

	// title
	Label E_AILabel = new Label("ACCOUNT AND COUPONS");
	E_AILabel.setFont(TITLE_FONT);

	VBox E_AI_title = new VBox();
	E_AI_title.getChildren().addAll(E_AILabel);
	E_AI_title.setStyle(TITLE_CSS);

	// employee user
	Label E_AI_userLabel = new Label("Username");
	E_AI_userLabel.setFont(SUB1_FONT);
	Label E_AI_username = new Label("Username goes here");
	E_AI_username.setFont(BODY_FONT);

	VBox E_AI_userBox = new VBox();
	E_AI_userBox.getChildren().addAll(E_AI_userLabel, E_AI_username);
	E_AI_userBox.setAlignment(Pos.TOP_CENTER);
	E_AI_userBox.setStyle("-fx-padding: 0 0 40 0");
	
	// coupon user
	Label E_AI_couponDist = new Label("Distribute Coupon");
	E_AI_couponDist.setFont(SUB1_FONT);
	ComboBox E_AI_couponField = new ComboBox(FXCollections.observableArrayList(couponList));
	
	//appends all existing users to combobox
		Scanner findName = new Scanner(new FileReader(TEXTFILE));
		String currentLine = "";
		String couponUsername = "";
		ArrayList<String> couponUsernameList = new ArrayList<String>();
		while(findName.hasNextLine()) {
			currentLine = findName.nextLine();
			if(currentLine.contains("username: ")) {
				couponUsername = currentLine;
				findName.nextLine();
				if(findName.nextLine().contains("customer")) {
					couponUsernameList.add(couponUsername.substring(couponUsername.lastIndexOf(" ") + 1));
				}
			}
		}
		
	ComboBox E_AI_userField = new ComboBox(FXCollections.observableArrayList(couponUsernameList));
	
	VBox E_AI_cUserBox = new VBox();
	E_AI_cUserBox.getChildren().addAll(E_AI_couponDist, E_AI_couponField, E_AI_userField);

	// coupon code
	Label E_AI_codeLabel = new Label("Coupon Code");
	E_AI_codeLabel.setFont(BODY_FONT);
	TextField E_AI_code = new TextField();
	E_AI_code.setFont(BODY_FONT);

	HBox E_AI_codeBox = new HBox();
	E_AI_codeBox.getChildren().addAll(E_AI_codeLabel, E_AI_code);
	E_AI_codeBox.setSpacing(10);

	// discount amount
	Label E_AI_discLabel = new Label("Discount Amount");
	E_AI_discLabel.setFont(BODY_FONT);
	TextField E_AI_disc = new TextField();
	E_AI_disc.setFont(BODY_FONT);

	HBox E_AI_discBox = new HBox();
	E_AI_discBox.getChildren().addAll(E_AI_discLabel, E_AI_disc);
	E_AI_discBox.setSpacing(10);

	// distribute button
	Button E_AI_couponButton = new Button("Distribute");
	E_AI_couponButton.setFont(BODY_FONT);
	E_AI_couponButton.setStyle(BOXED_BUTTON_CSS);

	//----- coupon info box -----//
	VBox E_AI_coupon = new VBox();
	E_AI_coupon.getChildren().addAll(E_AI_cUserBox, E_AI_codeBox, E_AI_discBox, E_AI_couponButton);
	E_AI_coupon.setSpacing(10);
	E_AI_coupon.setStyle(BOXED_BUTTON_CSS + "-fx-padding: 10 10 10 10");

	//----- outer elements -----//
	Button E_AI_logout = new Button("LOGOUT");
	E_AI_logout.setFont(SUB1_FONT);
	E_AI_logout.setStyle(UNDERLINE_BUTTON_CSS + "-fx-padding: 40 0 0 0");

	// outer box
	VBox E_AI_mainBox = new VBox();
	E_AI_mainBox.getChildren().addAll(E_AI_title, E_AI_userBox, E_AI_coupon, E_AI_logout);
	E_AI_mainBox.setStyle(WHITEBOX_CSS);

	//===== Creating the scene =====//
	VBox E_AI_outerBox = new VBox();
	E_AI_outerBox.setStyle(OUTER_BOX_CSS);
	E_AI_outerBox.getChildren().addAll(E_AI_menu, E_AI_mainBox);

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

	Button E_EM_login = new Button("ACCOUNT");
	E_EM_login.setFont(TITLE_FONT);
	E_EM_login.setStyle(NAV_BUTTON_CSS);
	E_EM_login.setTextFill(RED);

	//Main Header Menu HBox
	HBox E_EM_menu = new HBox();
	E_EM_menu.getChildren().addAll(E_EM_logoBox, E_EM_orderNow, E_EM_cart, E_EM_login);
	E_EM_menu.setStyle(NAV_BOX_CSS);


	String E_EM_subMenu_css = "-fx-background-color: #FFFFFF;" +
			"-fx-padding: 10 10 10 10;";

	//===== Sub Menu Box =====//
	Button E_EM_saladButton = new Button("SALADS");
	E_EM_saladButton.setFont(SUB1_FONT);
	E_EM_saladButton.setStyle(BOXED_BUTTON_CSS);

	Button E_EM_pastaButton = new Button("PASTA");
	E_EM_pastaButton.setFont(SUB1_FONT);
	E_EM_pastaButton.setStyle(BOXED_BUTTON_CSS);

	Button E_EM_paniniButton = new Button("PANINI");
	E_EM_paniniButton.setFont(SUB1_FONT);
	E_EM_paniniButton.setStyle(BOXED_BUTTON_CSS);

	Button E_EM_pizzaButton = new Button("PIZZA");
	E_EM_pizzaButton.setFont(SUB1_FONT);
	E_EM_pizzaButton.setStyle(BOXED_BUTTON_CSS);

	Button E_EM_addItem = new Button("ADD ITEM");
	E_EM_addItem.setFont(SUB1_FONT);
	E_EM_addItem.setStyle(BOXED_BUTTON_CSS);
	E_EM_addItem.setTextFill(RED);

	HBox E_EM_subMenu = new HBox();
	E_EM_subMenu.getChildren().addAll(E_EM_saladButton, E_EM_pastaButton, E_EM_paniniButton, E_EM_pizzaButton, E_EM_addItem);
	E_EM_subMenu.setStyle(E_EM_subMenu_css);
	E_EM_subMenu.setAlignment(Pos.TOP_RIGHT);
	E_EM_subMenu.setSpacing(20);


	//===== Main Body Box =====//


	Label E_EM_saladLabel = new Label("SALAD");
	E_EM_saladLabel.setFont(TITLE_FONT);
	GridPane E_EM_salads = new GridPane();

	Label E_EM_pastaLabel = new Label("PASTA");
	E_EM_pastaLabel.setFont(TITLE_FONT);
	GridPane E_EM_pastas = new GridPane();

	Label E_EM_paniniLabel = new Label("PANINI");
	E_EM_paniniLabel.setFont(TITLE_FONT);
	GridPane E_EM_paninis = new GridPane();

	Label E_EM_pizzaLabel = new Label("PIZZA");
	E_EM_pizzaLabel.setFont(TITLE_FONT);
	GridPane E_EM_pizzas = new GridPane();

	VBox E_EM_mainBodyBox = new VBox();
	E_EM_mainBodyBox.getChildren().addAll(E_EM_saladLabel, E_EM_salads, E_EM_pastaLabel, E_EM_pastas, E_EM_paniniLabel, E_EM_paninis, E_EM_pizzaLabel, E_EM_pizzas);

	ScrollPane E_EM_mainBody = new ScrollPane();
	E_EM_mainBody.setContent(E_EM_mainBodyBox);
	E_EM_mainBody.setStyle(WHITEBOX_CSS);


	//===== Creating the scene =====//
	VBox E_EM_outerBox = new VBox();
	E_EM_outerBox.setStyle(OUTER_BOX_CSS);
	E_EM_outerBox.getChildren().addAll(E_EM_menu, E_EM_subMenu, E_EM_mainBody);

	Scene EMenuScene = new Scene(E_EM_outerBox, 1600, 850);




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
		Scanner findName = new Scanner(new FileReader(TEXTFILE));
		Boolean usernameExists = true;
 
        //Writing to text file
        FileWriter fw = new FileWriter(TEXTFILE, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        @Override
        public void handle(javafx.scene.input.MouseEvent e) {
        	//code block allows multiple accounts to be created in same instance
	        try {
				fw = new FileWriter(TEXTFILE, true);
		        bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
			} catch (IOException e1) {
				System.out.println("Unexpected error when writing to file. createacc");
			}
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
            		//insert credit card stuff
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
				findName = new Scanner(new FileReader(TEXTFILE));
			} catch (FileNotFoundException e1) {
				System.out.println("Unexpected error when reading file. CreateAccount");
			}
            currentline = "";
        }
	}));
 
	//Login
	LI_signIn.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String currentline = "";
		Scanner findName = new Scanner(new FileReader(TEXTFILE));
		Scanner findCoupon = new Scanner(new FileReader(COUPONFILE));
		Boolean userExists = true;
		public void handle(javafx.scene.input.MouseEvent e) {
			try {
				findName = new Scanner(new FileReader(TEXTFILE));
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
            		if(findName.nextLine().contains("accounttype: customer")) {
                		currentCustomer.setUsername(LI_userField.getText());
                		currentCustomer.setPassword(LI_passField.getText());
                		LI_userField.setText("");
                		LI_passField.setText("");
                		C_AI_user.setText(currentCustomer.getUsername());
                		//Checks if user has any coupons upon login
                		ArrayList<String> couponCodeList = new ArrayList<String>();
                		System.out.println(currentCustomer.getUsername());
 
            			//appends all coupons for user

            			String currentLine = "";
            			while(findCoupon.hasNextLine()) {
            				currentLine = findCoupon.nextLine();
            				if(currentLine.contains("Username: " + currentCustomer.getUsername())) {
            					couponCodeList.add(findCoupon.nextLine() + " " + findCoupon.nextLine());
            				}
 
            			}
            			C_PI_couponBox.setItems(FXCollections.observableArrayList(couponCodeList));
            			C_CM_login.setText("ACCOUNT");
            			C_AI_login.setText("ACCOUNT");
            			C_CM_login.setText("ACCOUNT");
            			C_PI_login.setText("ACCOUNT");
            			C_CA_login.setText("ACCOUNT");
            			stage.setScene(CAccountInfoScene);
            		}
            		else {
            			E_EM_login.setText("ACCOUNT");
            			E_AI_login.setText("ACCOUNT");
            			stage.setScene(EAccountScene);
            		}
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
			currentCustomer.setUsername("guest");
			currentCustomer.setPassword("guest");
			C_CM_login.setText("LOGIN");
			C_AI_login.setText("LOGIN");
			C_CM_login.setText("LOGIN");
			C_PI_login.setText("LOGIN");
			C_CA_login.setText("LOGIN");
		}
	}));
 
	E_AI_logout.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(SignInScene);
			currentCustomer.setUsername("guest");
			currentCustomer.setPassword("guest");
    		LI_userField.setText("");
    		LI_passField.setText("");
			C_CM_login.setText("LOGIN");
			C_AI_login.setText("LOGIN");
			C_CM_login.setText("LOGIN");
			C_PI_login.setText("LOGIN");
			C_CA_login.setText("LOGIN");
		}
	}));
 
	//coupon distribution
	//cart
	C_AI_cart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CCartScene);
		}
	}));
	
	
	C_AI_cart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String cartItems = "";
		ArrayList<String> checkDuplicate = new ArrayList<String>();

		public void handle(javafx.scene.input.MouseEvent e) {
			cartItems = "";
			stage.setScene(CCartScene);
			if (currentCustomer.getCart().size() == 0)
			{
				C_CA_itemStatus.setText("!\nNo items are in your cart!");
		 
			}
			else
			{
				for (int i = 0; i < currentCustomer.getCart().size(); i++ ) // looping through the cart
				{
					if (checkDuplicate.contains(currentCustomer.getCart().get(i).getItemName()) == false) {
						cartItems += currentCustomer.getCart().get(i).getItemName() + "\t" + currentCustomer.getCart().get(i).getPrice()
								 + "\t" + currentCustomer.getAmountOfItem(currentCustomer.getCart().get(i)) + "\n";
						checkDuplicate.add(currentCustomer.getCart().get(i).getItemName());
					}
				}
					C_CA_itemStatus.setText(cartItems);
					C_CA_removeItemBox.setItems(FXCollections.observableArrayList(checkDuplicate));
			}
		}
	}));
 
	C_AI_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CMenuScene);
		}
	}));
 
	C_AI_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(currentCustomer.getUsername() == "guest") {
				stage.setScene(SignInScene);
			}
			else {
				stage.setScene(CAccountInfoScene);
			}
		}
	}));
	
	//cart button in cart scene -- functionally useless
	C_CA_cart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CCartScene);
		}
	}));
 
	C_CA_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CMenuScene);
		}
	}));
 
	C_CA_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(currentCustomer.getUsername() == "guest") {
				stage.setScene(SignInScene);
			}
			else {
				stage.setScene(CAccountInfoScene);
			}
		}
	}));
 
	C_CM_cart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String cartItems = "";
		ArrayList<String> checkDuplicate = new ArrayList<String>();
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CCartScene);
			if (currentCustomer.getCart().size() == 0)
			{
				C_CA_itemStatus.setText("!\nNo items are in your cart!");
		 
			}
			else
			{
				for (int i = 0; i < currentCustomer.getCart().size(); i++ ) // looping through the cart
				{
					if (checkDuplicate.contains(currentCustomer.getCart().get(i).getItemName()) == false) {
						cartItems += currentCustomer.getCart().get(i).getItemName() + "\t" + currentCustomer.getCart().get(i).getPrice()
								 + "\t" + currentCustomer.getAmountOfItem(currentCustomer.getCart().get(i)) + "\n";
						checkDuplicate.add(currentCustomer.getCart().get(i).getItemName());
					}
				}
					C_CA_itemStatus.setText(cartItems);
					C_CA_removeItemBox.setItems(FXCollections.observableArrayList(checkDuplicate));
			}
		}
	}));
 
	C_CM_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CMenuScene);
		}
	}));
 
	C_CM_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(currentCustomer.getUsername() == "guest") {
				stage.setScene(SignInScene);
			}
			else {
				stage.setScene(CAccountInfoScene);
			}
		}
	}));
 
	LI_cart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String cartItems = "";
		ArrayList<String> checkDuplicate = new ArrayList<String>();
		public void handle(javafx.scene.input.MouseEvent e) {
			cartItems = "";
			stage.setScene(CCartScene);
			if (currentCustomer.getCart().size() == 0)
			{
				C_CA_itemStatus.setText("!\nNo items are in your cart!");
		 
			}
			else
			{
				for (int i = 0; i < currentCustomer.getCart().size(); i++ ) // looping through the cart
				{
					if (checkDuplicate.contains(currentCustomer.getCart().get(i).getItemName()) == false) {
						cartItems += currentCustomer.getCart().get(i).getItemName() + "\t" + currentCustomer.getCart().get(i).getPrice()
								 + "\t" + currentCustomer.getAmountOfItem(currentCustomer.getCart().get(i)) + "\n";
						checkDuplicate.add(currentCustomer.getCart().get(i).getItemName());
					}
				}
					C_CA_itemStatus.setText(cartItems);
					C_CA_removeItemBox.setItems(FXCollections.observableArrayList(checkDuplicate));
			}
		}
	}));
 
	LI_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(CMenuScene);
		}
	}));
 
	LI_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(currentCustomer.getUsername() == "guest") {
				stage.setScene(SignInScene);
			}
			else {
				stage.setScene(CAccountInfoScene);
			}
		}
	}));
 
	C_CM_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(currentCustomer.getUsername() == "guest") {
				stage.setScene(SignInScene);
			}
			else {
				stage.setScene(CAccountInfoScene);
			}
		}
	}));
 
	E_AI_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(EMenuScene);
		}
	}));
 
	E_AI_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(EAccountScene);
		}
	}));
 
	E_EM_orderNow.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(EMenuScene);
		}
	}));
 
	E_EM_login.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			stage.setScene(EAccountScene);
		}
	}));
 
	//Preset menu items
	
	MenuItem pizza1 = new MenuItem("Pizza1", "2000", 10.00, 10, "https://i.imgur.com/kUT27sU.png");
	MenuItem pizza2 = new MenuItem("Pizza2", "2000", 10.00, 10, "https://i.imgur.com/kzvqJBn.png");
	MenuItem pasta1 = new MenuItem("Pasta1", "1000", 10.00, 20, "https://i.imgur.com/r0L2FBd.png");
	MenuItem pasta2 = new MenuItem("Pasta2", "1000", 10.00, 20, "https://i.imgur.com/GccCxBo.png");
	MenuItem salad1 = new MenuItem("Salad1", "500", 15.00, 12, "https://i.imgur.com/NE1wNlB.png");
	MenuItem salad2 = new MenuItem("Salad2", "500", 15.00, 12, "https://i.imgur.com/fMcfFem.png");
	MenuItem panini1 = new MenuItem("Panini1", "750", 12.00, 14, "https://i.imgur.com/7bNzak6.png");
	MenuItem panini2 = new MenuItem("Panini2", "750", 12.00, 13, "https://i.imgur.com/Ua7PRro.png");

	displayedMenu.add(pizza1);
	displayedMenu.add(pizza2);
	displayedMenu.add(pasta1);
	displayedMenu.add(pasta2);
	displayedMenu.add(salad1);
	displayedMenu.add(salad2);
	displayedMenu.add(panini1);
	displayedMenu.add(panini2);

	C_CM_pizzas.add(new ImageView(new Image(pizza1.getImage())), 0, 1);
	C_CM_pizzas.add(new ImageView(new Image(pizza2.getImage())), 1, 1);
	C_CM_pizzas.add(new Label(pizza1.getItemName()), 0, 2);
	C_CM_pizzas.add(new Label(pizza2.getItemName()), 1, 2);
	C_CM_pizzas.add(new Label("Price: $" + String.valueOf(pizza1.getPrice())), 0, 3);
	C_CM_pizzas.add(new Label("Price: $" + String.valueOf(pizza2.getPrice())), 1, 3);
	C_CM_pizzas.add(new Label("Ingredients: dough, cheese, tomato"), 0, 4);
	C_CM_pizzas.add(new Label("Ingredients: dough, cheese, tomato"), 1, 4);
	
	C_CM_pastas.add(new ImageView(new Image(pasta1.getImage())), 0, 1);
	C_CM_pastas.add(new ImageView(new Image(pasta2.getImage())), 1, 1);
	C_CM_pastas.add(new Label(pasta1.getItemName()), 0, 2);
	C_CM_pastas.add(new Label(pasta2.getItemName()), 1, 2);
	C_CM_pastas.add(new Label("Price: $" + String.valueOf(pasta1.getPrice())), 0, 3);
	C_CM_pastas.add(new Label("Price: $" + String.valueOf(pasta2.getPrice())), 1, 3);
	C_CM_pastas.add(new Label("Ingredients: flour, egg, vegetable juice, spices"), 0, 4);
	C_CM_pastas.add(new Label("Ingredients: flour, egg, vegetable juice, spices"), 1, 4);
	
	C_CM_paninis.add(new ImageView(new Image(panini1.getImage())), 0, 1);
	C_CM_paninis.add(new ImageView(new Image(panini2.getImage())), 1, 1);
	C_CM_paninis.add(new Label(panini1.getItemName()), 0, 2);
	C_CM_paninis.add(new Label(panini2.getItemName()), 1, 2);
	C_CM_paninis.add(new Label("Price: $" + String.valueOf(panini1.getPrice())), 0, 3);
	C_CM_paninis.add(new Label("Price: $" + String.valueOf(panini2.getPrice())), 1, 3);
	C_CM_paninis.add(new Label("Ingredients: bread, chese, chicken"), 0, 4);
	C_CM_paninis.add(new Label("Ingredients: bread, chese, beef"), 1, 4);
	
	C_CM_salads.add(new ImageView(new Image(salad1.getImage())), 0, 1);
	C_CM_salads.add(new ImageView(new Image(salad2.getImage())), 1, 1);
	C_CM_salads.add(new Label(salad1.getItemName()), 0, 2);
	C_CM_salads.add(new Label(salad2.getItemName()), 1, 2);
	C_CM_salads.add(new Label("Price: $" + String.valueOf(salad1.getPrice())), 0, 3);
	C_CM_salads.add(new Label("Price: $" + String.valueOf(salad2.getPrice())), 1, 3);
	C_CM_salads.add(new Label("Ingredients: lettuce, tomato, olive oil"), 0, 4);
	C_CM_salads.add(new Label("Ingredients: lettuce, tomato, olive oil"), 1, 4);
	
	//adding menu items to combobox
	ArrayList<String> menuItemNames = new ArrayList<String>();
	menuItemNames.add(salad1.getItemName());
	menuItemNames.add(salad2.getItemName());
	menuItemNames.add(pasta1.getItemName());
	menuItemNames.add(pasta2.getItemName());
	menuItemNames.add(panini1.getItemName());
	menuItemNames.add(panini2.getItemName());
	menuItemNames.add(pizza1.getItemName());
	menuItemNames.add(pizza2.getItemName());
	C_CM_orderSelection.setItems(FXCollections.observableArrayList(menuItemNames));
	
	C_CM_addToCart.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		public void handle(javafx.scene.input.MouseEvent e) {
			if(C_CM_orderSelection.getValue() != null) {
				for(int i = 0; i < displayedMenu.size(); i++) {
					if(displayedMenu.get(i).getItemName() == C_CM_orderSelection.getValue()) {
						currentCustomer.addMenuItem(displayedMenu.get(i));
					}
				}
			}
			else {
				System.out.println("Nothing is selected");
			}
			System.out.println(currentCustomer.getCart());
		}
	}));
	
	//remove item bug: cannot handle multiple items of different types
	C_CA_removeItemButton.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String cartItems = "";
		String removedItem = "";
		ArrayList<String> checkDuplicate = new ArrayList<String>();
		public void handle(javafx.scene.input.MouseEvent e) {

			if (C_CA_removeItemBox.getValue() == null)
			{
				System.out.println("nothing is selected");
			}
			else
			{
				removedItem = (String) C_CA_removeItemBox.getValue();
				currentCustomer.removeMenuItem(removedItem);
				//]C_CA_removeItemBox.getItems().remove(removedItem);
				System.out.println(currentCustomer.getCart());
				
				if(currentCustomer.getCart().isEmpty()) {
					C_CA_itemStatus.setText("!\nNo items are in your cart!");
				}
				else {
					//String cartItems = "";
					for (int i = 0; i < currentCustomer.getCart().size(); i++ ) // looping through the cart
					{
						if (checkDuplicate.contains(currentCustomer.getCart().get(i).getItemName()) == false) {
							cartItems += currentCustomer.getCart().get(i).getItemName() + "\t" + currentCustomer.getCart().get(i).getPrice()
									 + "\t" + currentCustomer.getAmountOfItem(currentCustomer.getCart().get(i)) + "\n";
							checkDuplicate.add(currentCustomer.getCart().get(i).getItemName());
						}
					}
						C_CA_itemStatus.setText(cartItems);
						C_CA_removeItemBox.setItems(FXCollections.observableArrayList(checkDuplicate));
				}
			}
	}}));
	
	C_CA_newOrder.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
        public void handle(javafx.scene.input.MouseEvent e) {
        	C_PI_totalPrice.setText("Total price: " + currentCustomer.getCartPrice());
            stage.setScene(CCardInfoScene);
        }
    }));

	C_PI_confirmOrder.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
        FileWriter fw = new FileWriter(CCINFOFILE, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        public void handle(javafx.scene.input.MouseEvent e) {
        	if(C_PI_cardNumber.getText() != "" && C_PI_cardName.getText() != "" && C_PI_secCode.getText() != "" && C_PI_expiration.getText() != "") {
                currentCustomer.setOrderPlaced(true); //setting orderplaced to true
                currentCustomer.setOrderETA();//calcuating customer orderETA
                int totalETA = 50; //hardcoded ETA. U1 = 20, U2 = 30
                totalETA += currentCustomer.getOrderETA();
                System.out.println("Order Placed!!!");
                System.out.println("Order ETA: " + totalETA);
                System.out.println("Order Total: " +currentCustomer.getCartPrice());
                currentCustomer.getCart().clear(); // emptying cart
                C_OSOrder3.setText("Customer 3(you)\t " + totalETA + " minutes");
                //writing to text file
				out.append("Customer: " + currentCustomer.getUsername() + "\n");
				out.append("Cardnum: " + C_PI_cardNumber.getText() + "\n");
				out.append("Cardname: " + C_PI_cardName.getText() + "\n");
				out.append("seccode: " + C_PI_secCode.getText() + "\n");
				out.append("expiration: " + C_PI_expiration.getText() + "\n");
				out.close();
                stage.setScene(COrderStatus);
        	}
        }
    }));
	
	C_PI_applyCoupon.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String currCoupon;
		Double discount;
		public void handle(javafx.scene.input.MouseEvent e) {
			//applies coupon if one is selected
			if(C_PI_couponBox.getValue() != null) {
				currCoupon = (String) C_PI_couponBox.getValue();
				currCoupon = currCoupon.substring(currCoupon.lastIndexOf("Discount: ") + 10);
				System.out.println(currCoupon);
				discount = Double.valueOf(currCoupon);
				C_PI_totalPrice.setText("Total Price: " + String.valueOf(currentCustomer.useCoupon(discount, currentCustomer.getCartPrice())));
			}
		}
	}));
	
	//Distributing existing coupon to users
	E_AI_couponButton.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
		String couponCode = "";
		Float couponValue = 0.0f;
		String currentline = "";
 
		//writing to user
        FileWriter fw = new FileWriter(COUPONFILE, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
		public void handle(javafx.scene.input.MouseEvent e) {
			//redoing code block to allow multiple instances of writing to user
	        try {
				fw = new FileWriter(COUPONFILE, true);
		        bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
			} catch (IOException e1) {
				System.out.println("Unexpected error when writing to file. couponbutton");
			}
 
			if(E_AI_couponField != null && E_AI_userBox != null) {
				couponCode = ((Coupon) E_AI_couponField.getValue()).getCouponCode();
				couponValue = ((Coupon) E_AI_couponField.getValue()).getCouponDiscount();
 
				//write coupons to user selected
				out.append("Username: " + E_AI_userField.getValue() + "\n");
				out.append("Code: " + couponCode + "\n");
				out.append("Discount: " + couponValue + "\n");
			}
			out.close();
	}}));
	
	stage.show();
	stage.setScene(SignInScene);
	//stage.setScene(SignUpScene);

	//stage.setScene(CAccountInfoScene);
	//stage.setScene(CCartScene);
	//stage.setScene(CMenuScene);
	//stage.setScene(CCardInfoScene);
	//stage.setScene(COrderStatus);
	
	//stage.setScene(EMenuScene);
	//stage.setScene(EAccountScene);
	



}

	public static void main(String[] args) {
		launch();
	}
}