package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import db.PostDAO;
import home.ConstantSetting;
import home.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:04
 */
public class PostsController implements Initializable {

    final static Stage NEW_POST_STAGE = new Stage();

    private static PostsController postsController;
    @FXML
    public BorderPane postsBorderPane;
    @FXML
    public Label usernameLabel;
    @FXML
    public JFXButton newPostBtn;
    @FXML
    public JFXButton refreshBtn;
    @FXML
    public JFXListView<Post> postListView;
    @FXML
    public JFXButton loadMoreBtn;
    @FXML
    public Label updateCountLabel;

    private ObservableList<Post> postDataList;

    private PostDAO postDAO;

    private int page = 0;

    private Text hintLabel;

    static Stage getNewPostStage() {
        return NEW_POST_STAGE;
    }

    static PostsController getPostsController() {
        return postsController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText( "Hello," + Main.getApp().getCurrentUser().getUserID() );
        postsController = this;
        postDAO = new PostDAO();
        KeyFrame update = new KeyFrame( Duration.minutes( ConstantSetting.UPDATE_PERIOD_MIN ), event -> {
            int count = 0;
            if (postDataList.size() > 0) {
                System.out.println( postDataList.get( 0 ).getTimeStamp() );
                count = postDAO.countUpdate( postDataList.get( 0 ).getTimeStamp() );
            }
            if (count > 0) {
                updateCountLabel.setText( "new " + String.valueOf( count ) );
            } else if (count < 0) {
                updateCountLabel.setText( "failed" );
            }
        } );
        Timeline tl = new Timeline( update );
        tl.setCycleCount( Timeline.INDEFINITE );
        tl.play();
        hintLabel = new Text( "No posts..." );
        hintLabel.getStyleClass().add( "hint-label" );
        hintLabel.setId( "hintLabel" );
        postDataList = FXCollections.observableArrayList();
        page = 0;
        postDataList.addAll( postDAO.getPosts( page++ ) );
        postListView = new JFXListView<>();
        postListView.setId( "postListView" );
        postListView.getStyleClass().add( "table-view" );
        postListView.setItems( postDataList );
        postListView.setCellFactory( param -> new PostCellFactory() );
        if (postDataList.size() != 0) {
            postsBorderPane.setCenter( postListView );
        } else {
            postsBorderPane.setCenter( hintLabel );
        }
    }

    public void handleNewPostBtnAction() throws IOException {
        Main.getApp().getStage().setOnCloseRequest( event1 -> NEW_POST_STAGE.close() );
        Parent target = FXMLLoader.load( getClass().getResource( "/fxml/new_post.fxml" ) );
        Scene scene = new Scene( target );
        scene.getStylesheets().add( "timeline_style.css" );
        NEW_POST_STAGE.setTitle( "New Post" );
        NEW_POST_STAGE.getIcons().add( new Image( "icon/message.png" ) );
        NEW_POST_STAGE.setScene( scene );
        NEW_POST_STAGE.setOnCloseRequest( event1 -> refresh() );
        NEW_POST_STAGE.show();
    }

    public void handleRefreshBtnAction() {
        refresh();
    }

    public void handleLoadMoreBtnAction() {
        postDataList.addAll( postDAO.getPosts( ++page ) );
    }

    void refresh() {
        postDataList.clear();
        page = 0;
        postDataList.addAll( postDAO.getPosts( page ) );
        if (postsBorderPane.getCenter() == null || postDataList.size() != 0) {
            postsBorderPane.setCenter( postListView );
        }
    }

    public void handleLogOutBtnAction() {
        Main.getApp().setUser( null );
        Main.getApp().startUp();
    }
}
