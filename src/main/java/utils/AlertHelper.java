package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author: zilla0148
 * @date: 2018/12/19 19:59
 */
public class AlertHelper {

    public static void showJDialog(Stage stage,String head,String body) {
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading( new Text( head ) );
        dialogContent.setBody( new Text( body ) );
        JFXButton close = new JFXButton( "Close" );
        dialogContent.setActions( close );
        JFXDialog dialog = new JFXDialog( (StackPane) stage.getScene().getRoot(), dialogContent, JFXDialog.DialogTransition.CENTER );
        close.setOnAction( event -> dialog.close() );
        dialog.show();
    }
}
