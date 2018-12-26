package controller;

import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    protected void handleLogInButtonAction(ActionEvent event) throws SQLException {
        Window owner = signInButton.getScene().getWindow();
        String inputName = nameField.getText();
        String inputPassword = passwordField.getText();
        if (inputName.isEmpty()) {
            AlertHelper.showAlert( Alert.AlertType.ERROR, owner, "Form Error!",
                    "用户名不可为空" );
            return;
        } else if (inputPassword.isEmpty()) {
            AlertHelper.showAlert( Alert.AlertType.ERROR, owner, "格式出错啦!",
                    "密码不可为空" );
            return;
        } else {
            if (!FormatChecker.isLetterDigit( inputPassword )) {
                AlertHelper.showAlert( Alert.AlertType.ERROR, owner, "格式出错啦!",
                        "密码格式错误，应仅包含数字、字母，6-18位" );
                return;
            }
        }


        if (new UserDAO().getAccount( inputName, inputPassword ) == 1) {
            app.setUser( new User( inputName ) );
            AlertHelper.showAlert( Alert.AlertType.INFORMATION, null,
                    ConstantSetting.ALERT_TITLE, "欢迎 " + inputName );
            app.switchHomepage();
        } else {
            AlertHelper.showAlert( Alert.AlertType.INFORMATION, null,
                    ConstantSetting.ALERT_TITLE, "未找到该用户..." );
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
