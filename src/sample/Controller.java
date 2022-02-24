package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;


public class Controller implements Initializable {  //implements just means we need to implement the initialize function below

    /* FXML definitions to link variables from fxml files to the controller*/
    @FXML private TextField nameInputBox;
    @FXML private Button nameSubmitButton;
    DatabaseManager databaseManager;
    Connection conn;

    public Controller(){
        //constructor for new controller

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseManager = new DatabaseManager();
        conn = databaseManager.getConnection();
        //this function runs when the window is opened, so you could initialize your database connection here
        //just copy and paste all of the database code we went over the other day, with the username and password stuff here, then I will show you the parts to put inside the button code
//What to do now
        //put the database manager class in your project
        //same as before, in a new folder?
        //oh shit babe I won't know how to make the database connection here, I barely know how to do it the normal way
        //where is your database code
        //In my head
        //Will all this else be the same always
        //Yes I'm just doing all the base stuff then you can add to it, this is always the same

    }

    @FXML
    public void nameSubmitButtonPressed(ActionEvent event){
        System.out.println(nameInputBox.getText());
        //so here I put all my normal code for database and such? //yes
        //this function sits here and only runs when the submit button is pressed
        //every time this button is pressed this code runs
    }
}


