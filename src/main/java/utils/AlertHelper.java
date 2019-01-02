package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author: zilla0148
 * @date: 2018/12/19 19:59
 */
public class AlertHelper {

    public static void showJDialog(Stage stage, String head, String body) {
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        Text text1 = new Text( head );
        text1.getStyleClass().add( "dialog-head" );
        dialogContent.setHeading( text1 );
        Text text = new Text( body );
        text.getStyleClass().add("usual-label");
        dialogContent.setBody( text );
        JFXButton close = new JFXButton( "Close" );
        close.setId( "closeBtn" );
        close.getStyleClass().add( "close-btn" );
        dialogContent.setActions( close );
        JFXDialog dialog = new JFXDialog( (StackPane) stage.getScene().getRoot(), dialogContent, JFXDialog.DialogTransition.CENTER );
        dialog.getDialogContainer().setId( "myDialog" );
        close.setOnAction( event -> dialog.close() );
        dialog.show();
    }
}
