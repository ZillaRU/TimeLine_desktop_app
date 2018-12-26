package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.UserDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;
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
                    "请完整填写注册信息。" );
            return;
        }

        if (!FormatChecker.isLetterDigit( pass1 )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "格式出错啦!",
                    "密码格式错误，应仅包含数字、字母，6-18位" );
            return;
        }

        if (!pass2.equals( pass1 )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "两次密码输入不一致!" );
            return;
        }

        if (new UserDAO().register( name, pass1 )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "注册成功", "欢迎 " + name +"登录体验吧！");
        } else {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "注册失败!", nameField.getText() + " 已存在同名用户。" );
        }
    }
}

