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
import javafx.util.converter.DoubleStringConverter;

public class WorkerScene {
    BorderPane bp=new BorderPane();
	 private TextField nameTextField;
	    private TextField phoneTextField;
	    private TextField salaryTextField;
	    private TextField workertypeTextField;
	    ObservableList<worker> workerList = FXCollections.observableArrayList();
	    DataBaseConnection db = new DataBaseConnection();
	        Scene addWorker , Workerspage ;
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
	                String sql = "select * from Worker";
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery(sql);
	                while (rs.next()){

	                    workerList.add(new worker(rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5)));
	                }
	                con.close();
	                System.out.println("asdf");
	            }
	            catch (Exception m ){

	            }
	        }
			public WorkerScene() {
				 ReadWorker();
			     Stage addstage = new Stage();

				 // Create UI elements
			        Label nameLabel = new Label("Name:");
			        Label phoneLabel = new Label("Phone:");
			        Label salaryLabel = new Label("Salary:");
			        Label workertypeLabel = new Label("WorkerType:");

			        nameTextField = new TextField();
			        phoneTextField = new TextField();
			        salaryTextField = new TextField();
			        workertypeTextField = new TextField();

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
			                String sql = "insert into Worker (name,phone,salary,workertype) values ('"+nameTextField.getText()+"','"+phoneTextField.getText()+"','"+salaryTextField.getText()+"','"+workertypeTextField.getText()+"')";
			                workerList.add(new worker(nameTextField.getText(),phoneTextField.getText(),Double.parseDouble(salaryTextField.getText()),workertypeTextField.getText()));
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();
			                addstage.close();
			            }
			            catch (Exception m ){

			            }
			            workerList.clear();
			            ReadWorker();
			        });
			        // Set up the layout
			        GridPane gridPane = new GridPane();
			        gridPane.setPadding(new Insets(20, 20, 20, 20));
			        gridPane.setVgap(10);
			        gridPane.setHgap(10);

			        gridPane.add(nameLabel, 0, 0);
			        gridPane.add(nameTextField, 1, 0);
			        gridPane.add(phoneLabel, 0, 1);
			        gridPane.add(phoneTextField, 1, 1);
			        gridPane.add(salaryLabel, 0, 2);
			        gridPane.add(salaryTextField, 1, 2);
			        gridPane.add(workertypeLabel, 0, 3);
			        gridPane.add(workertypeTextField, 1, 3);
			        gridPane.add(addButton, 0, 4);
			        gridPane.add(backButton, 1, 4);
			        
			//==============================================================================================================
			        TableView<worker> tableView = new TableView<>();
			        tableView.setItems(workerList);
			        tableView.setEditable(true);
			        // Create TableColumn for name
			        TableColumn<worker, String> nameColumn = new TableColumn<>("Name");
			        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        nameColumn.setResizable(false);
			        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<worker, String>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<worker, String> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkerID();
			                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Worker set name ='" + name + "'  WHERE workerID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        
			        // Create TableColumn for phone
			        TableColumn<worker, String> phoneColumn = new TableColumn<>("Phone");
			        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        phoneColumn.setResizable(false);
			        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<worker, String>>() {
			            @Override
			            public void handle(TableColumn.CellEditEvent<worker, String> event) {
			                event.getTableView().getItems().get(event.getTablePosition().getRow()).setPhone(event.getNewValue());
			                int id = event.getTableView().getItems().get(event.getTablePosition().getRow()).getWorkerID();
			                String phone = event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhone();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Worker SET phone = '" + phone + "' WHERE workerID = '" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			            }
			        });

			        TableColumn<worker, Double> salaryColumn = new TableColumn<>("Salary");
			        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
			        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
			        salaryColumn.setResizable(false);

			        salaryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<worker, Double>>() {

			            @Override
			            public void handle(TableColumn.CellEditEvent<worker, Double> arg0) {

			                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setSalary(arg0.getNewValue());
			                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkerID();
			                double name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getSalary();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Worker set salary ='" + name + "'  WHERE workerID='" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e2) {
			                    e2.getMessage();
			                }
			            }

			        });
			        TableColumn<worker, String> workertypecolumn = new TableColumn<>("workertype");
			        workertypecolumn.setCellValueFactory(new PropertyValueFactory<>("workertype"));
			        workertypecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
			        workertypecolumn.setResizable(false);
			        workertypecolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<worker, String>>() {
			            @Override
			            public void handle(TableColumn.CellEditEvent<worker, String> event) {
			                event.getTableView().getItems().get(event.getTablePosition().getRow()).setWorkertype(event.getNewValue());
			                int id = event.getTableView().getItems().get(event.getTablePosition().getRow()).getWorkerID();
			                String phone = event.getTableView().getItems().get(event.getTablePosition().getRow()).getWorkertype();
			                try {
			                    Connection con = db.getConnection().connectDB();
			                    String sql = "UPDATE Worker SET workertype = '" + phone + "' WHERE workerID = '" + id + "'";
			                    Statement stmt = con.createStatement();
			                    stmt.executeUpdate(sql);
			                    con.close();
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			            }
			        });
			        tableView.getColumns().addAll(nameColumn, phoneColumn, salaryColumn,workertypecolumn);

			        Button deleteWorker = new Button("Delete row");
			        deleteWorker.setOnAction(e->{
			            worker wo = tableView.getSelectionModel().getSelectedItem();

			            try {

			            	int id = wo.getWorkerID();
			                Connection con = db.getConnection().connectDB();
			                String sql = "DELETE FROM Worker WHERE workerID='" + id + "'";
			                Statement stmt = con.createStatement();
			                stmt.executeUpdate(sql);
			                con.close();

			            } catch (Exception e2) {
			                e2.getMessage();
			            }
			            workerList.remove(wo);
			            tableView.refresh();
			            
			        });
			        Button addWorkerBtn = new Button("Add row");
			        
			        backButton.setOnAction(e->{
			        	addstage.close();
			        });
			        addWorkerBtn.setOnAction(e->{
			            addstage.setScene(addWorker);
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
			        Workerspage = new Scene(bp, 500, 500);
			        Workerspage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());


			        // Set up the scene
			        addWorker = new Scene(gridPane, 300, 200);
			        addWorker.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

			}
	        
}
