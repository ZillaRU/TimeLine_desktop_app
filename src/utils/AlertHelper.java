package utils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * @author: zilla0148
 * @date: 2018/12/19 19:59
 */
public class AlertHelper {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
