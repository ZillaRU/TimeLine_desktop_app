package home;

import db.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: zilla0148
 * @date: 2018/12/17 20:34
 */
public class Main extends Application {

    private static Main app;
    private Stage stage;
    private User currentUser;

    public static Main getApp() {
        return app;
    }

    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        stage = primaryStage;
        stage.setTitle( ConstantSetting.TITLE );
        stage.setResizable( false );
        stage.getIcons().add( new Image( "icon/timeline.png" ) );
        startUp();
    }

    public void startUp() {
        try {
            replaceSceneContent( "/fxml/start_up.fxml" );
            try {
                if (DBConnector.getInstance().getConnection() == null || DBConnector.getInstance().getConnection().isClosed()) {
                    Connection c = null;
                    c = DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                            ConstantSetting.DB_USER, ConstantSetting.DB_PASSWORD, ConstantSetting.DB_NAME );
                    if (c == null) {
                        Alert alert = new Alert( Alert.AlertType.WARNING,
                                "The program could not connect\nto DB with default settings.\nCheck DB-Setting" );
                        alert.show();
                    } else {
                        stage.show();
                    }
                } else {
                    stage.show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchHomepage() {
        try {
            replaceSceneContent( "/fxml/posts.fxml" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream( fxml );
        loader.setBuilderFactory( new JavaFXBuilderFactory() );
        loader.setLocation( Main.class.getResource( fxml ) );
        Pane page;
        if (in != null) {
            page = loader.load( in );
            Scene scene = new Scene( page, ConstantSetting.START_UP_SCENE_SIZE.width,
                    ConstantSetting.START_UP_SCENE_SIZE.height );
            scene.getStylesheets().addAll( "timeline_style.css" );
            stage.setScene( scene );
            stage.sizeToScene();
        } else {
            System.out.println( "InputStream is null" );
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
