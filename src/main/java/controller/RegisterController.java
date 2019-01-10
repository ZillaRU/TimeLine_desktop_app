package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.fxml.FXML;
import utils.AlertHelper;
import utils.FormatChecker;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:14
 */
public class RegisterController {
    AlertHelper alertHelper = new AlertHelper();
    @FXML
    protected JFXTextField nameField;

    @FXML
    protected JFXPasswordField passwordField;

    @FXML
    protected JFXPasswordField rePasswordField;

    @FXML
    protected String handleSignUpJFXButtonAction() {
        String name = nameField.getText();
        String pass1 = passwordField.getText();
        String pass2 = rePasswordField.getText();
        if (name.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "The required information is incomplete." );
            return "The required information is incomplete.";
        }
        if (FormatChecker.hasFormatMismatch( name )) {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE, "Username format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" );
            return "Username format error.Only contain 0-9,a-z,A-Z length: 6-18";
        }
        if (FormatChecker.hasFormatMismatch( pass1 )) {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE, "Password format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" );
            return "Password format error.Only contain 0-9,a-z,A-Z length: 6-18";
        }

        if (!pass2.equals( pass1 )) {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "Inconsistent passwords!" );
            return "Inconsistent passwords!";
        }

        if (new UserDAO().register( name, pass1 )) {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    "Registration succeeded", "Welcome " + name + " !\nPlease log in and enjoy your time." );
            return "Please log in and enjoy your time.";
        } else {
            alertHelper.showJDialog(Main.getApp().getStage(),
                    "Registration failed", "The username already exists" );
            return "The username already exists";
        }
    }
}