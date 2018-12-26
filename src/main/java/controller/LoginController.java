package controller;

import com.jfoenix.controls.*;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;
import model.User;
import utils.AlertHelper;
import utils.FormatChecker;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:40
 */
public class LoginController implements Initializable {

    private Main app = Main.getApp();

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    protected void handleLogInJFXButtonAction(ActionEvent event) throws SQLException {
        Window owner = signInButton.getScene().getWindow();
        String inputName = nameField.getText();
        String inputPassword = passwordField.getText();
        if (inputName.isEmpty()) {
            AlertHelper.showJDialog( app.getStage(),
                    "格式出错啦!", "用户名不可为空" );
            return;
        } else if (inputPassword.isEmpty()) {
            AlertHelper.showJDialog( app.getStage(),
                    "格式出错啦!", "密码不可为空" );
            return;
        } else {
            if (!FormatChecker.isLetterDigit( inputPassword )) {
                AlertHelper.showJDialog( app.getStage(),
                        "格式出错啦!", "密码格式错误，应仅包含数字、字母，6-18位" );
                return;
            }
        }


        if (new UserDAO().getAccount( inputName, inputPassword ) == 1) {
            app.setUser( new User( inputName ) );
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "欢迎 " + inputName );
            app.switchHomepage();
        } else {
            AlertHelper.showJDialog( app.getStage(),
                    ConstantSetting.ALERT_TITLE, "未找到该用户..." );
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
