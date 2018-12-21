package controller;

import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import utils.AlertHelper;
import db.DBInterface;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:40
 */
public class LoginController implements Initializable {

    private Main app=Main.getApp();

    @FXML private TextField nameField;

    @FXML private PasswordField passwordField;

    @FXML private Button signInButton;

    @FXML
    protected void handleLogInButtonAction(ActionEvent event) throws SQLException {
        Window owner = signInButton.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert( Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }

        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }
        int userExist = 0;
        String stmt="SELECT count(*) as userExist\n" +
                "FROM userinfo\n"+
                "WHERE username='"+nameField.getText()+
                "'\nAND password='"+passwordField.getText()+"';";
        System.out.println( stmt );
        ResultSet resultSet=DBInterface.getResultSet( stmt );
        if (resultSet != null && resultSet.next()) {
            userExist = resultSet.getInt( "userExist" );
            if(userExist==1){
                AlertHelper.showAlert( Alert.AlertType.INFORMATION,null,"Logging in",
                        "Welcome "+nameField.getText() );
                app.switchHomepage();
            }else {
                AlertHelper.showAlert( Alert.AlertType.INFORMATION,null,"FAILED",
                        "No correspondent account found..." );
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
