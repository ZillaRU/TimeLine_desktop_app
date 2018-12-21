package controller;

import db.DBInterface;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.AlertHelper;

import java.sql.SQLException;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:14
 */
public class RegisterController {
    private Main app=Main.getApp();

    @FXML private TextField nameField;

    @FXML private PasswordField passwordField;

    @FXML private PasswordField rePasswordField;

    @FXML private Button signUpButton;

    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert( Alert.AlertType.ERROR, null, "Form Error!",
                    "Please enter your username");
            return;
        }

        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Form Error!",
                    "Please enter a password");
            return;
        }

        if(!rePasswordField.getText().equals(passwordField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Form Error!",
                    "Repeat incorrectly!");
            return;
        }
        String stmt="INSERT INTO userinfo(username, password) "
                + "VALUES ('"
                + nameField.getText() + "', '"
                + passwordField.getText() + "');";
        System.out.println( stmt );
        try {
            if(DBInterface.executeStatement(stmt)){
                AlertHelper.showAlert( Alert.AlertType.INFORMATION, null, "Registration Successful!",
                        "Welcome " + nameField.getText());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertHelper.showAlert( Alert.AlertType.INFORMATION, null, "Registration failed!",
                     nameField.getText()+" exists");;
        }
    }
}
