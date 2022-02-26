package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.util.converter.LocalDateStringConverter;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.time.*;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;


public class TransactionInputController implements Initializable {
    @FXML private Label filePathLabel;
    @FXML private DatePicker dateInput;
    @FXML private TextField purchaseInput;
    @FXML private TextField priceInput;
    @FXML private ChoiceBox currencyInput;
    String transactionInputFilePath;


    DatabaseManager databaseManager;
    Connection conn;

    public TransactionInputController(){}
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();

        currencyInput.getItems().add("USD");
        currencyInput.getItems().add("GBP");
        currencyInput.getItems().add("EUR");

    }


    @FXML
    private void BrowseForFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV"));
        File f = fc.showOpenDialog(null);
       if(f!= null){

           String transactionInputFilePath = f.getAbsolutePath();
           System.out.println(transactionInputFilePath);

           filePathLabel.setText(transactionInputFilePath);
       }

    }

    @FXML
    private void AddButtonPressed(ActionEvent event){
        LocalDate transactionDate = dateInput.getValue();
        String transactionPurchase = purchaseInput.getText();
        double transactionPrice = Double.parseDouble(priceInput.getText());
        String transactionCurrency = currencyInput.getValue().toString();

        System.out.println(transactionDate + transactionPurchase + transactionPrice + transactionCurrency);
    }
}


