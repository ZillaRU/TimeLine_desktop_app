package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author: zilla0148
 * @date: 2018/12/19 19:59
 */
public class AlertHelper {

    public static void showJDialog(Stage stage, String head, String body) {
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        Label text1 = new Label( head );
        text1.setId( "headLabel" );
        dialogContent.setHeading( text1 );
        Label text = new Label( body );
        text.getStyleClass().add( "usual-label" );
        text.setId( "contentLabel" );
        dialogContent.setBody( text );
        JFXButton close = new JFXButton( "Close" );
        close.setId( "closeBtn" );
        dialogContent.setActions( close );
        JFXDialog dialog = new JFXDialog( (StackPane) stage.getScene().getRoot(), dialogContent, JFXDialog.DialogTransition.CENTER );
        dialog.getDialogContainer().setId( "myDialog" );
        close.setOnAction( event -> dialog.close() );
        dialog.show();
    }
}
