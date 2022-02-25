package com.example.cse360_group7;

//import com.sun.javafx.stage.EmbeddedWindow;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Image logo = new Image("/logo.png"); //width = 75, height = 250



 //============================= Log In =============================//

	//----- Main Header Menu -----//
	ImageView LI_logo = new ImageView(logo);
	LI_logo.setFitHeight(75);
	LI_logo.setFitWidth(250);

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
	LI_userField.setStyle(li_entryField_css);

	Label LI_pass = new Label("Password");
	LI_pass.setFont(SUB1_FONT);
	LI_pass.setStyle(li_entryLab_css);

	TextField LI_passField = new TextField();
	LI_passField.setStyle(li_entryField_css);

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
	LI_userEntry.getChildren().addAll(LI_user, LI_userField, LI_pass, LI_passField, LI_accButtons);
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

	Scene signInScene = new Scene(LI_outerBox, 1600, 850);




	//==============================================================================================================
	// Section: Customer Pages
	// Description: Pages that are specific to the customer view
	//==============================================================================================================
	// code starts here




	//==============================================================================================================
	// Section: Employee Pages
	// Description: Pages that are specific to the employee view
	//==============================================================================================================
	// code starts here




	//==============================================================================================================
	// Section: GUI Management
	// Description: Sets page displays (opens and closes based on event handlers)
	//==============================================================================================================
	stage.show();
	stage.setScene(signInScene);

}

	public static void main(String[] args) {
		launch();
	}
}