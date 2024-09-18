package com.example.sale_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ItemScene {
    BorderPane bp=new BorderPane();
	 private TextField nameTextField;
	    private TextField barcodeTextField;
	    private TextField expirationDateTextField;
	    private TextField sellingPriceTextField;
	    private TextField buyingPriceTextField;
	    private TextField StoredQuantityTextField;
	    private TextField categoryTextField;
	    ObservableList<items> itemsList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene additems , itemspage ;
	        public void ReadWorker(){
	            Connection con = null;
	            try {
	                con = db.getConnection().connectDB();
	            } catch (ClassNotFoundException ex) {
	                throw new RuntimeException(ex);
	            } catch (SQLException ex) {
	                throw new RuntimeException(ex);
	            }
	            try {
	                String sql = "select * from items";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){
	                	
	                	System.out.println(rs.getInt(1));
	                	System.out.println(rs.getString(4));
	                	itemsList.add(new items(rs.getInt(2),rs.getInt(1),rs.getString(3) ,rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getString(8)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public ItemScene() {
				 ReadWorker();
				 for (int i = 0; i < itemsList.size(); i++) {
					 System.out.println(itemsList.get(i).getName());
					 System.out.println(itemsList.get(i).getBarcodeNum());
					 System.out.println(itemsList.get(i).getBuyingPrice());
					 System.out.println(itemsList.get(i).getCategory());
					 System.out.println(itemsList.get(i).getExpirationDate());
					 System.out.println(itemsList.get(i).getStoredQuantity());
					 System.out.println(itemsList.get(i).getSellingPrice());
					 System.out.println(itemsList.get(i).getItemsID());

				}
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label nameLabel = new Label("Name:");
			        Label barcodeLabel = new Label("Barcode:");
			        Label expirationtypeLabel = new Label("Expiration Date:");
			        Label sellingLabel = new Label("Selling Price:");
			        Label buyingLabel = new Label("Buying price:");
			        Label quantityLabel = new Label("Quantity:");
			        Label categoryLabel = new Label("Category:");

			        nameTextField=new TextField();
				    barcodeTextField=new TextField();
				    expirationDateTextField=new TextField();
				    sellingPriceTextField=new TextField();
				    buyingPriceTextField=new TextField();
				    StoredQuantityTextField=new TextField();
				    categoryTextField=new TextField();

			        Button addButton = new Button("Add row");
			        Button backButton = new Button("Back");
			        // Set button actions
			        addButton.setOnAction(e->{
			        	System.out.println("s");
			            Connection con = null;
			            try {
			                con = db.getConnection().connectDB();
			            } catch (ClassNotFoundException ex) {
			                throw new RuntimeException(ex);
			            } catch (SQLException ex) {
			                throw new RuntimeException(ex);
			            }
			            try {
			            
			                String sql = "insert into Items (name,BarcodeNum,ExpirationDate,sellingPrice,buyingPrice,StoredQuantity,category) values ('"+nameTextField.getText()+"','"+barcodeTextField.getText()+"','"+expirationDateTextField.getText()+"','"+sellingPriceTextField.getText()+"','"+buyingPriceTextField.getText()+"','"+StoredQuantityTextField.getText()+"','"+categoryTextField.getText()+"')";
			              
			                itemsList.add(new items(Integer.parseInt(barcodeTextField.getText()),nameTextField.getText(),expirationDateTextField.getText(),Float.parseFloat(sellingPriceTextField.getText()),Float.parseFloat(buyingPriceTextField.getText()),Integer.parseInt(StoredQuantityTextField.getText()),categoryTextField.getText()));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            itemsList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(nameLabel, 0, 0);
			        gridPane.add(nameTextField, 1, 0);
			        gridPane.add(barcodeLabel, 0, 1);
			        gridPane.add(barcodeTextField, 1, 1);
			        gridPane.add(expirationtypeLabel, 0, 2);
			        gridPane.add(expirationDateTextField, 1, 2);
			        gridPane.add(sellingLabel, 0, 3);
			        gridPane.add(sellingPriceTextField, 1, 3);
			        gridPane.add(buyingLabel, 0, 4);
			        gridPane.add(buyingPriceTextField, 1, 4);
			        gridPane.add(quantityLabel, 0, 5);
			        gridPane.add(StoredQuantityTextField, 1, 5);
			        gridPane.add(categoryLabel, 0, 6);
			        gridPane.add(categoryTextField, 1, 6);
			        gridPane.add(addButton, 0, 7);
			        gridPane.add(backButton, 1, 7);
			        
			//==============================================================================================================
			        TableView<items> tableView = new TableView<>();
			        tableView.setItems(itemsList);
			        
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<items, String> nameColumn = new TableColumn<>("Name");
			        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        nameColumn.setResizable(false);
			        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set name ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			     // Create TableColumn for name
			        TableColumn<items, Integer> barcodeColumn = new TableColumn<>("BarcodeNum");
			        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("BarcodeNum"));
			        barcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        barcodeColumn.setResizable(false);
			        barcodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setBarcodeNum(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBarcodeNum();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set BarcodeNum ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<items, String> expirationColumn = new TableColumn<>("expirationDate");
			        expirationColumn.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
			        expirationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        expirationColumn.setResizable(false);
			        expirationColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setExpirationDate(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                String ExpirationDate = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getExpirationDate();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set ExpirationDate ='" + ExpirationDate + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<items, Float> SellColumn = new TableColumn<>("sellingPrice");
			        SellColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
			        SellColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
			        SellColumn.setResizable(false);
			        SellColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, Float>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, Float> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setSellingPrice(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                float name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getSellingPrice();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set sellingPrice ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     // Create TableColumn for name
			        TableColumn<items, Float> buyingColumn = new TableColumn<>("buyingPrice");
			        buyingColumn.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
			        buyingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
			        buyingColumn.setResizable(false);
			        buyingColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, Float>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, Float> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setBuyingPrice(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                float name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBuyingPrice();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set buyingPrice ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        // Create TableColumn for name
			        TableColumn<items, Integer> quantityColumn = new TableColumn<>("StoredQuantity");
			        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("StoredQuantity"));
			        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        quantityColumn.setResizable(false);
			        quantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setStoredQuantity(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                Integer name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getStoredQuantity();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set StoredQuantity ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });			     // Create TableColumn for name
			        TableColumn<items, String> categoryColumn = new TableColumn<>("category");
			        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
			        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        categoryColumn.setResizable(false);
			        categoryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<items, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<items, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setCategory(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCategory();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Items set category ='" + name + "'  WHERE ItemsID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			     
			        tableView.getColumns().addAll(nameColumn, barcodeColumn,expirationColumn,SellColumn,buyingColumn,quantityColumn,categoryColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            items wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getItemsID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM Items WHERE ItemsID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            itemsList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(additems);
			            addstage.show();


			        }
			        );
			        
			        bp.setCenter(tableView);
			        HBox hb=new HBox();
			        hb.setSpacing(10);
			        hb.setPadding(new Insets(20,20,20,20));
			        bp.setBottom(hb);
			        hb.getChildren().addAll(addWorkerBtn,deleteWorker);
			        hb.setAlignment(Pos.BOTTOM_CENTER);
			        

			        // Set up the scene
			        itemspage = new Scene(bp, 500, 500);
			        itemspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        additems = new Scene(gridPane, 300, 200);
			        additems.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
