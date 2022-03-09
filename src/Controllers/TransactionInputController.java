package Controllers;
import Repository.DatabaseManager;
import Transactions.Transactions.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TransactionInputController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

// For the transaction input view
    @FXML private Label filePathLabel;
    @FXML private DatePicker dateInput;
    @FXML private TextField purchaseInput;
    @FXML private TextField priceInput;
    @FXML private ChoiceBox currencyInput;
    String transactionInputFilePath;

// For the transaction categories view

    @FXML private TableColumn<TransactionsForTable,String>dateColumn;
    @FXML private TableColumn<TransactionsForTable,String>currencyColumn;
    @FXML private TableColumn<TransactionsForTable,Double>priceColumn;
    @FXML private TableColumn<TransactionsForTable,String>purchaseColumn;
    @FXML private TableColumn<TransactionsForTable,String>categoryChooser;


    private TransactionsForTableList transList;

    @FXML private TableView<TransactionsForTable> tableView;

    ObservableList<String> categoriesList;



    DatabaseManager databaseManager;
    Connection conn;

    public TransactionInputController(){

        categoriesList = FXCollections.observableArrayList();
        categoriesList.add("Food");
        categoriesList.add("Clothes");
        categoriesList.add("Household");
        categoriesList.add("Healthcare");
        categoriesList.add("Housing");
        categoriesList.add("Entertainment");
        categoriesList.add("Transportation");
        categoriesList.add("Utilities");
        categoriesList.add("Savings");
        categoriesList.add("Earnings");
        categoriesList.add("Unsorted");

        transList = new TransactionsForTableList();

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();

        //for the currency input choicebox in transaction input window
        currencyInput.getItems().add("USD");
        currencyInput.getItems().add("GBP");
        currencyInput.getItems().add("EUR");



        if(dateColumn != null) {
            dateColumn.setCellValueFactory(new PropertyValueFactory<TransactionsForTable, String>("date"));
            currencyColumn.setCellValueFactory(new PropertyValueFactory<TransactionsForTable, String>("currency"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<TransactionsForTable, Double>("price"));
            purchaseColumn.setCellValueFactory(new PropertyValueFactory<TransactionsForTable, String>("purchase"));
            categoryChooser.setCellValueFactory(new PropertyValueFactory<TransactionsForTable,String>("categoryChooser"));


            tableView.setItems(transList.getAllTransactionForTable());
        }
    }


    @FXML
    private void BrowseForFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV"));
        File f = fc.showOpenDialog(null);
       if(f!= null){

           transactionInputFilePath = f.getAbsolutePath();
           filePathLabel.setText(transactionInputFilePath);
       }

    }

    @FXML
    private void AddButtonPressed(ActionEvent event){


    try {
        String transactionDate = dateInput.getValue().toString();
        String transactionPurchase = purchaseInput.getText();
        Double transactionPrice = Double.parseDouble(priceInput.getText());
        String transactionCurrency = currencyInput.getValue().toString();
        TransactionsForTable newTransaction = new TransactionsForTable(transactionDate,transactionCurrency,transactionPrice,transactionPurchase,categoriesList);
        transList.addTransactionsForTable(newTransaction);


    } catch (Exception e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void doneButtonPressed(ActionEvent event) {
        System.out.println(transactionInputFilePath);
        String line = "";
        String splitBy = ";";
        try {

            BufferedReader br = new BufferedReader(new FileReader(transactionInputFilePath));
            while ((line = br.readLine()) != null) {
                int k = 0;
                int Id = 0;
                while ((line = br.readLine()) != null) { //šī daļa (29-32) ļauj izlaist pirmo rindu CSV failā
                    if (k == 0) {
                        k++;
                        continue;
                    }
                    String[] transactionData = line.split(splitBy);
                    System.out.println("Date of payment: " + transactionData[1] + ", Currency: " + transactionData[2] + ", Sum: " + transactionData[3] + " Name: " + transactionData[4]);
                    String date = transactionData[1];
                    String currency = transactionData[2];
                    double price = Double.parseDouble(transactionData[3]);
                    String purchase = transactionData[4];
                    TransactionsForTable transaction = new TransactionsForTable(date, currency, price, purchase, categoriesList);
                    transList.addTransactionsForTable(transaction);
                    System.out.println(transList.toString());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong in reading the CSV file, please try again");
        }

        loadScene(event, "Views/TransactionCategories.fxml", 900, 475);
        System.out.println("Here2");

    }

    @FXML
    private void doneButton2Pressed(ActionEvent event){

        loadScene(event, "Views/TransactionCategories.fxml", 900, 475);
        }



    //Handle switching between fxml file scenes
    private void loadScene(ActionEvent event, String location, int width, int height){
        try {
            System.out.println(location);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(location));      //absolute reference for file path of scene
            loader.setController(this);     //since we're using the same controller, need to pass in a reference to itself "this" controller
            //this gives us access to all of the class variables from both scenes, like arrayLists
            scene = new Scene((Pane) loader.load(), width, height);                                       //set width and height of scene
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception){
            System.out.println(exception);
        }
    }
}





