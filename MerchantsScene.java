package com.example.sale_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MerchantsScene {
    BorderPane bp = new BorderPane();
    private TextField nameTextField;
    private TextField totalDebtsTextField;
    private TextField phoneNumberTextField;
    private TextField addressTextField;

    ObservableList<Merchants> merchantsList = FXCollections.observableArrayList();
    DataBaseConnection db = new DataBaseConnection();
    Scene addMerchants, merchantsPage;

    public void readMerchants() throws RuntimeException {
        Connection con = null;
        try {
        	con = db.getConnection().connectDB();
        	} catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String sql = "SELECT * FROM salemanagement.Merchants";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                merchantsList.add(new Merchants(rs.getInt(1), rs.getString(2),
                        rs.getFloat(3), rs.getString(4), rs.getString(5)));
            }
            con.close();

        } catch (Exception m) {
            System.out.println(m.getMessage());
        }
    }

    public Scene merchantsScene() {
        readMerchants();
        Stage addStage = new Stage();

        // Create UI elements
        Label nameLabel = new Label("Name:");
        Label totalDebtsLabel = new Label("Total Debts:");
        Label phoneNumberLabel = new Label("Phone Number:");
        Label addressLabel = new Label("Address:");

        nameTextField = new TextField();
        totalDebtsTextField = new TextField();
        phoneNumberTextField = new TextField();
        addressTextField = new TextField();

        Button addButton = new Button("Add Merchant");
        Button backButton = new Button("Back");

        // Set button actions
        addButton.setOnAction(e -> {
            Connection con = null;
            try {
            	con = db.getConnection().connectDB();
            	} catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                String sql = "INSERT INTO Merchants (name, total_debts, phone, address) VALUES ('" +
                        nameTextField.getText() + "', " +
                        Float.parseFloat(totalDebtsTextField.getText()) + ", '" +
                        phoneNumberTextField.getText() + "', '" +
                        addressTextField.getText() + "')";

                assert con != null;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);

                merchantsList.add(new Merchants(nameTextField.getText(), Float.parseFloat(totalDebtsTextField.getText()),
                        phoneNumberTextField.getText(), addressTextField.getText()));

                con.close();
                addStage.close();
            } catch (Exception m) {
                System.out.println(m.getMessage());
            }
            merchantsList.clear();
            readMerchants();
        });

        // Set up the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(totalDebtsLabel, 0, 1);
        gridPane.add(totalDebtsTextField, 1, 1);
        gridPane.add(phoneNumberLabel, 0, 2);
        gridPane.add(phoneNumberTextField, 1, 2);
        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressTextField, 1, 3);
        gridPane.add(addButton, 0, 4);
        gridPane.add(backButton, 1, 4);

        //==============================================================================================================
        TableView<Merchants> tableView = new TableView<>();
        tableView.setItems(merchantsList);
        tableView.setEditable(true);
        // Create TableColumn for name
        TableColumn<Merchants, String> nameColumn = getMerchantsStringTableColumn();
        // Create TableColumn for total_debts
        TableColumn<Merchants, Float> totalDebtsColumn = getMerchantsFloatTableColumn();
        // Create TableColumn for phone
        TableColumn<Merchants, String> phoneNumberColumn = getStringTableColumn();

        // Create TableColumn for address
        TableColumn<Merchants, String> addressColumn = getTableColumn();

        tableView.getColumns().addAll(nameColumn, totalDebtsColumn, phoneNumberColumn, addressColumn);

        Button deleteWorker = new Button("Delete Merchant");
        deleteWorker.setOnAction(e->{
            Merchants wo = tableView.getSelectionModel().getSelectedItem();

            try {

            	int id = wo.getMerchantsID();
                Connection con = db.getConnection().connectDB();
                Statement stmt = con.createStatement();
                String sql="DELETE FROM returned_items WHERE MerchantsID="+id+";";
                stmt.executeUpdate(sql);
                
                sql = "DELETE FROM orders WHERE MerchantsID=" + id + ";";
                stmt.executeUpdate(sql);

                sql = "DELETE FROM merchants WHERE MerchantsID=" + id + ";";
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }
            merchantsList.remove(wo);
            tableView.refresh();
            
        });

        Button addMerchantBtn = new Button("Add Merchant");

        backButton.setOnAction(e -> addStage.close());
        addMerchantBtn.setOnAction(e -> {
            addStage.setScene(addMerchants);
            addStage.show();
        });

        bp.setCenter(tableView);
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(20, 20, 20, 20));
        bp.setBottom(hb);
        hb.getChildren().addAll(addMerchantBtn, deleteWorker);
        hb.setAlignment(Pos.BOTTOM_CENTER);

        // Set up the scene
        merchantsPage = new Scene(bp, 590, 500);
        merchantsPage.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

        // Set up the scene
        addMerchants = new Scene(gridPane, 300, 200);
        addMerchants.getStylesheets().add(getClass().getResource("a.css").toExternalForm());

        return merchantsPage;
    }

   

    private TableColumn<Merchants, String> getTableColumn() {
        TableColumn<Merchants, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setResizable(false);
        addressColumn.setOnEditCommit(arg0 -> {
            arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAddress(arg0.getNewValue());
            int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
            String address = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAddress();
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE merchants SET address = '" + address + "' WHERE MerchantsID = " + id;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        });
        return addressColumn;
    }

    private TableColumn<Merchants, String> getStringTableColumn() {
        TableColumn<Merchants, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberColumn.setResizable(false);
        phoneNumberColumn.setOnEditCommit(arg0 -> {
            arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhone(arg0.getNewValue());
            int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
            String phone = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhone();
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE merchants SET phone = '" + phone + "' WHERE MerchantsID = " + id;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        });
        return phoneNumberColumn;
    }

    private TableColumn<Merchants, String> getMerchantsStringTableColumn() {
        TableColumn<Merchants, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setResizable(false);
        nameColumn.setOnEditCommit(arg0 -> {
            arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
            int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
            String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Merchants SET name = '" + name + "' WHERE MerchantsID = " + id;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        });
        return nameColumn;
    }

    private TableColumn<Merchants, Float> getMerchantsFloatTableColumn() {
        TableColumn<Merchants, Float> totalDebtsColumn = new TableColumn<>("Total Debts");
        totalDebtsColumn.setCellValueFactory(new PropertyValueFactory<>("total_debts"));
        totalDebtsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        totalDebtsColumn.setResizable(false);
        totalDebtsColumn.setOnEditCommit(arg0 -> {
            arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setTotal_debts(arg0.getNewValue());
            int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMerchantsID();
            float totalDebts = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getTotal_debts();
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE merchants SET total_debts = " + totalDebts + " WHERE MerchantsID = " + id;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        });
        return totalDebtsColumn;
    }
   

}