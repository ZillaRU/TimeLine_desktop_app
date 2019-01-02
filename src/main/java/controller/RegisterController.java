package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.AlertHelper;
import utils.FormatChecker;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:14
 */
public class RegisterController {

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField rePasswordField;

    @FXML
    protected void handleSignUpJFXButtonAction(ActionEvent event) {
        String name = nameField.getText();
        String pass1 = passwordField.getText();
        String pass2 = rePasswordField.getText();
        if (name.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            AlertHelper.showJDialog( Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "The required information is incomplete." );
            return;
        }
        if (!FormatChecker.isLetterDigit( name )) {
            AlertHelper.showJDialog( Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE, "Username format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" );
            return;
        }
        if (!FormatChecker.isLetterDigit( pass1 )) {
            AlertHelper.showJDialog( Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE, "Password format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" );
            return;
        }

        if (!pass2.equals( pass1 )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "Inconsistent passwords!" );
            return;
        }

        if (new UserDAO().register( name, pass1 )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "Registration succeeded", "Welcome " + name + " !\nPlease log in and enjoy your time." );
        } else {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "Registration failed!", "The username already exists" );
        }
    }
}