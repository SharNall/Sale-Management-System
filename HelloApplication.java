package com.example.sale_management;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
	private TextField userNameTextField=new TextField();
	PasswordField passwordField = new PasswordField();
	private Label userNameLabel=new Label("Username");
	 private Label PassWordLabel=new Label("Password");
	 private Button login=new Button("Sign In");
	 private Label l=new Label("Wrong Password or UserName");
	Button back=new Button("Back");
	Button back1=new Button("Back");

	Scene scene;
	Button b=new Button("Order Tables");
	Button b1=new Button("CashFlow Tables");
	Button b2=new Button("Items Tables");
	Button b4=new Button("Customers Table");
	
	HBox h1=new HBox();
	Button bb=new Button("Order Tables");
	Button bb1=new Button("Merchents Tables");
	Button bb2=new Button("Items_Orders Tables");
	
	HBox h2=new HBox();

	Button bb3=new Button("CashFlow Tables");
	Button bb4=new Button("Worker Tables");
	Button bb5=new Button("CashFlow_Merchents Tables");

	HBox h3=new HBox();

	Button bb6=new Button("Items Tables");
	Button bb7=new Button("Returned Items Tables");
	Button bb8=new Button("Items_Customers Table");    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
    	l.setVisible(false);
    	HBox hb=new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(10,10,10,10));
        hb.getChildren().add(back1);
        
        
        h1.setSpacing(10);
        h1.setPadding(new Insets(10,10,10,10));
        h1.getChildren().addAll(bb,bb1,bb2);
        h2.setSpacing(10);
        h2.setPadding(new Insets(10,10,10,10));
        h2.getChildren().addAll(bb3,bb4,bb5);
        h3.setSpacing(10);
        h3.setPadding(new Insets(10,10,10,10));
        h3.getChildren().addAll(bb6,bb7,bb8);
    	BackgroundImage myBI= new BackgroundImage(new Image("C:\\Users\\SKYNET\\Downloads\\comic-style-animation-green-screen-background-free-video.jpg"), null, null, null, null);
    	BorderPane pp=new BorderPane();
		pp.setBackground(new Background(myBI));

    	Scene scene1=new Scene(pp,600,650);
    	GridPane gridPane = new GridPane();
    	pp.setCenter(gridPane);
    	gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        
        gridPane.add(userNameLabel, 0, 0);
        gridPane.add(userNameTextField, 1, 0);
        gridPane.add(PassWordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(login, 1, 2);
        gridPane.add(l, 1, 3);
        login.setOnAction(e->{
        	l.setVisible(false);
        	if(userNameTextField.getText().equals("root")&&passwordField.getText().equals("monmonmon3434")) {
        		stage.setScene(scene);
        	}
        	else {
        		userNameTextField.clear();
        		passwordField.clear();
        		l.setVisible(true);
        	}
        });
        Image i=new Image("C:/Users/SKYNET/Downloads/1_29NeeIGT0BElEBNfzE_VlQ.jpg");
        ImageView iv=new ImageView(i);
        
 	BorderPane borpane=new BorderPane();
 	b.setOnAction(e->{
 		h1.getChildren().remove(back);
 		h2.getChildren().remove(back);
 		h3.getChildren().remove(back);
 		h1.getChildren().add(back);
 		borpane.setBottom(h1);
 	});
 	b1.setOnAction(e->{
 		h1.getChildren().remove(back);
 		h2.getChildren().remove(back);
 		h3.getChildren().remove(back);
 		h2.getChildren().add(back);
 		borpane.setBottom(h2);
 	});
 	b2.setOnAction(e->{
 		h1.getChildren().remove(back);
 		h2.getChildren().remove(back);
 		h3.getChildren().remove(back);
 		h3.getChildren().add(back);
 		borpane.setBottom(h3);
 	});
 	b4.setOnAction(e->{
 		CustomerScene c=new CustomerScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.Customerspage);
 	});
 	bb.setOnAction(e->{
 		OrdersScene c=new OrdersScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.Orderspage);
 	});
 	bb1.setOnAction(e->{
 		MerchantsScene c=new MerchantsScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.merchantsPage);
 	});
 	bb2.setOnAction(e->{
 		Items_OrdersScene c=new Items_OrdersScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.OrderItemspage);
 	});
 	bb3.setOnAction(e->{
 		CashFlowScene c=new CashFlowScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.cashflowpage);
 	});
 	bb4.setOnAction(e->{
 		WorkerScene c=new WorkerScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.Workerspage);
 	});
 	bb5.setOnAction(e->{
 		cashflow_MerchantsScene c=new cashflow_MerchantsScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.CustomerItemspage);
 	});
 	bb6.setOnAction(e->{
 		ItemScene c=new ItemScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.itemspage);
 	});
 	bb7.setOnAction(e->{
 		returneditemsScene c=new returneditemsScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.returneditemspage);
 	});
 	bb8.setOnAction(e->{
 		items_CustomersScene c=new items_CustomersScene();
   	
 		c.bp.setTop(hb);
 		stage.setScene(c.CustomerItemspage);
 	});
 	
 	borpane.setCenter(iv);
 	iv.setFitWidth(400);
	iv.setFitHeight(400);
 	borpane.setPadding(new Insets(20,20,20,20));
 	HBox names1=new HBox();
 	names1.setPadding(new Insets(20,20,20,20));
 	names1.setSpacing(10);
 	names1.setAlignment(Pos.BOTTOM_CENTER);
 	names1.getChildren().addAll(b,b1,b4,b2);
 	borpane.setBottom(names1);
		borpane.setBackground(new Background(myBI));
		back.setOnAction(e->{
			borpane.setBottom(names1);
			stage.setScene(scene);
		});
		back1.setOnAction(e->{
			borpane.setBottom(names1);
			stage.setScene(scene);
		});
 	scene= new Scene(borpane,600,650);
 	
     scene1.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

     scene.getStylesheets().add(getClass().getResource("a.css").toExternalForm());
     stage.setTitle("sale_management");
     stage.setScene(scene1);
     stage.show();
//       stage.setScene(w.CustomerItemspage);
    }

    public static void main(String[] args) {
        launch();
    }
}