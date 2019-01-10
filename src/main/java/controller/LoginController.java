package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.fxml.FXML;
import model.User;
import utils.AlertHelper;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:40
 */
public class LoginController {

    protected Main app = Main.getApp();
    AlertHelper alertHelper = new AlertHelper();

    @FXML
    protected JFXTextField nameField;

    @FXML
    protected JFXPasswordField passwordField;

    @FXML
    protected String handleLogInJFXButtonAction() {//csy:去掉参数
        String inputName = nameField.getText();
        String inputPassword = passwordField.getText();
        if (inputName.isEmpty()) {
            alertHelper.showJDialog(app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Username cannot be empty." );
            return "Username cannot be empty.";
        } else if (inputPassword.isEmpty()) {
            alertHelper.showJDialog(app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Password cannot be empty." );
            return "Password cannot be empty.";
        }

        if (new UserDAO().getAccount( inputName, inputPassword ) == 1) {
            app.setUser( new User( inputName ) );
            app.switchHomepage();
            alertHelper.showJDialog(app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Welcome " + inputName );
            return "Welcome " + inputName;
        } else {
            alertHelper.showJDialog(app.getStage(),
                    ConstantSetting.ALERT_TITLE, "Wrong username and/or password" );
            return "Wrong username and/or password";
        }
    }
}
