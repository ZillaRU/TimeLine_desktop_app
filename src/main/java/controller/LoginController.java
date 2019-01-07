package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.User;
import utils.AlertHelper;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:40
 */
public class LoginController {

    private Main app = Main.getApp();

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    protected void handleLogInJFXButtonAction(ActionEvent event) {
        String inputName = nameField.getText();
        String inputPassword = passwordField.getText();
        if (inputName.isEmpty()) {
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Username cannot be empty." );
            return;
        } else if (inputPassword.isEmpty()) {
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Password cannot be empty." );
            return;
        }

        if (new UserDAO().getAccount( inputName, inputPassword ) == 1) {
            app.setUser( new User( inputName ) );
            app.switchHomepage();
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Welcome " + inputName );
        } else {
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Wrong username and/or password" );
        }
    }
}
