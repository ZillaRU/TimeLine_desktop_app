package home;

import controller.PostsController;
import controller.StartUpController;
import db.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: zilla0148
 * @date: 2018/12/17 20:34
 */
public class Main extends Application {

    private Stage stage;

    private static Main app;

    public static Main getApp() {
        return app;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        app=this;
        stage = primaryStage;
        startUp();
    }

    private void startUp() {
        StartUpController startUpController = null;
        try {
            startUpController = (StartUpController) replaceSceneContent("/fxml/start_up.fxml");
            try {
                if (DBConnector.getInstance().getConnection() == null || DBConnector.getInstance().getConnection().isClosed()) {
                    Connection c = null;
                    try {
                        c = DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                                ConstantSetting.DB_USER, ConstantSetting.DB_PASSWORD, ConstantSetting.DB_NAME );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
        PostsController main = null;
        try {
            main = (PostsController) replaceSceneContent("/fxml/posts.fxml");
            if (main != null) {
                Alert alert = new Alert( Alert.AlertType.WARNING,
                        "switchHomepage" );
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream( fxml );
        loader.setBuilderFactory( new JavaFXBuilderFactory() );
        loader.setLocation( Main.class.getResource( fxml ) );
        Pane page;
        if(in!=null){
            page = loader.load( in );
            Scene scene = new Scene( page, ConstantSetting.SCENE_SIZE.width, ConstantSetting.SCENE_SIZE.height );
            stage.setScene( scene );
            stage.sizeToScene();
            return (Initializable) loader.getController();
        }else {
            System.out.println( "InputStream is null" );
            return null;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch( args );
    }
}
