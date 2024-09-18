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

public class returneditemsScene {
    BorderPane bp=new BorderPane();
	 private TextField quantiyTextField;
	 private TextField RdateTextField;
	 private TextField ItemsTextField;
	    private TextField merchantTextField;
	    ObservableList<Ritems> RitemsList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addreturneditems , returneditemspage ;
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
	                String sql = "select * from returned_items";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                	RitemsList.add(new Ritems(rs.getInt(1) ,rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public returneditemsScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label quantiyLabel = new Label("Quantity:");
			        Label RdateLabel = new Label("Return Date:");
			        Label ItemsLabel = new Label("ItemsID:");
			        Label MerchantsIdLabel = new Label("MerchantsId:");

			        quantiyTextField = new TextField();
			        RdateTextField = new TextField();
			        ItemsTextField = new TextField();
			        merchantTextField = new TextField();

			        Button addButton = new Button("Add row");
			        Button backButton = new Button("Back");
			        // Set button actions
			        addButton.setOnAction(e->{
			            Connection con = null;
			            try {
			                con = db.getConnection().connectDB();
			            } catch (ClassNotFoundException ex) {
			                throw new RuntimeException(ex);
			            } catch (SQLException ex) {
			                throw new RuntimeException(ex);
			            }
			            try {
			                String sql = "insert into returned_items (quantity,returnedDate,itemsID,MerchantsID) values ('"+quantiyTextField.getText()+"','"+RdateTextField.getText()+"','"+ItemsTextField.getText()+"','"+Integer.parseInt(merchantTextField.getText())+"');\n";
			                System.out.println(sql);
			                RitemsList.add(new Ritems(Integer.parseInt(quantiyTextField.getText()),RdateTextField.getText(),Integer.parseInt(ItemsTextField.getText()),Integer.parseInt(merchantTextField.getText())));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                sql = "select buyingPrice from items where ItemsID="+ItemsTextField.getText()+";\n";
			                
			                ResultSet rs = stmt.executeQuery(sql);
			                if(rs.next()) {

			                int i=(int)rs.getFloat(1)*Integer.parseInt(quantiyTextField.getText());
			                sql="Update merchants set total_debts=total_debts -"+i+" where MerchantsID="+merchantTextField.getText()+";\n";
			                stmt.executeUpdate(sql);
			                }
                            sql = "select StoredQuantity from items where ItemsID="+ItemsTextField.getText()+";\n";
                            rs = stmt.executeQuery(sql);
			                if(rs.next()) {

			                if(rs.getInt(1)-Integer.parseInt(quantiyTextField.getText())<=0) {
			                	 sql="Update items set StoredQuantity=0 where ItemsID="+ItemsTextField.getText()+";";
					                stmt.executeUpdate(sql);
			                }
			                else {
			                sql="Update items set StoredQuantity=StoredQuantity -"+quantiyTextField.getText()+" where ItemsID="+ItemsTextField.getText()+";";
			                stmt.executeUpdate(sql);
			                }
			                }

			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            RitemsList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(quantiyLabel, 0, 0);
			        gridPane.add(quantiyTextField, 1, 0);
			        gridPane.add(RdateLabel, 0, 1);
			        gridPane.add(RdateTextField, 1, 1);
			        gridPane.add(ItemsLabel, 0, 2);
			        gridPane.add(ItemsTextField, 1, 2);
			        gridPane.add(MerchantsIdLabel, 0, 3);
			        gridPane.add(merchantTextField, 1, 3);
			        gridPane.add(addButton, 0, 4);
			        gridPane.add(backButton, 1, 4);
			        
			//==============================================================================================================
			        TableView<Ritems> tableView = new TableView<>();
			        tableView.setItems(RitemsList);
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<Ritems, String> DateColumn = new TableColumn<>("returnedDate");
			        DateColumn.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
			        DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        DateColumn.setResizable(false);
			        DateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ritems, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Ritems, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setReturnedDate(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getReturnedID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getReturnedDate();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE returned_items set Date ='" + name + "'  WHERE returnedID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        TableColumn<Ritems, Integer> QuantityColumn = new TableColumn<>("quantity");
			        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			        QuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        QuantityColumn.setResizable(false);
			        QuantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ritems, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Ritems, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setQuantity(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getReturnedID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getQuantity();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE returned_items set Quantity ='" + name + "'  WHERE returnedID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });

			        TableColumn<Ritems, Integer> ItemColumn = new TableColumn<>("itemsID");
			        ItemColumn.setCellValueFactory(new PropertyValueFactory<>("itemsID"));
			        ItemColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        ItemColumn.setResizable(false);
			        ItemColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ritems, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Ritems, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setItemsID(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getReturnedID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getItemsID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE returned_items set Quantity ='" + name + "'  WHERE returnedID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        TableColumn<Ritems, Integer> MerchantsColumn = new TableColumn<>("MerchantsID");
			        MerchantsColumn.setCellValueFactory(new PropertyValueFactory<>("MerchantsID"));
			        MerchantsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			        MerchantsColumn.setResizable(false);
			        MerchantsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ritems, Integer>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<Ritems, Integer> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setMerchantsID(arg0.getNewValue());;
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getReturnedID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
			                try {
			                    Connection con = db.getConnection().connectDB();
			          
			                    String sql = "UPDATE returned_items set MerchantsID ='" + name + "'  WHERE returnedID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        tableView.getColumns().addAll(QuantityColumn, DateColumn, ItemColumn,MerchantsColumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			        	Ritems wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getReturnedID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM returned_items WHERE returnedID=" + id + ";";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            RitemsList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addreturneditems);
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
			        returneditemspage = new Scene(bp, 500, 500);
			        returneditemspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addreturneditems = new Scene(gridPane, 300, 200);
			        addreturneditems.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
