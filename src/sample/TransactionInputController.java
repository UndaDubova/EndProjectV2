package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;


public class TransactionInputController implements Initializable {
    @FXML private TextField DateInputBox;
    @FXML private TextField PurchaseInputBox;
    @FXML private TextField PriceInputBox;
    @FXML private Button BrowseForFile;

    DatabaseManager databaseManager;
    Connection conn;

    public TransactionInputController(){}
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();
    }


    @FXML
    private void BrowseForFileClick (ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.CSV"));
        File f = fc.showOpenDialog(null);
       if(f!= null){

           String transactionInputFilePath = f.getAbsolutePath();
           System.out.println(transactionInputFilePath);
       }

    }
    @FXML
    private void AddDate(ActionEvent event){
        System.out.println(DateInputBox.getText());
        String Date = DateInputBox.getText();
    }
    @FXML
    private void AddPurchase(ActionEvent event){
        System.out.println(PurchaseInputBox.getText());
        String Purchase = PurchaseInputBox.getText();
    }
    @FXML
    private void AddPrice(ActionEvent event){
        System.out.println(PriceInputBox.getText());
        double Price = Double.parseDouble(PriceInputBox.getText());
    }
    @FXML
    private void AddButtonPressed(ActionEvent event){
        System.out.println("Date + Purchase + Price");
    }
}


