package controller;

import db.PostDAO;
import home.ConstantSetting;
import home.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:04
 */
public class PostsController implements Initializable {

    private static PostsController postsController;

    @FXML
    public Button newPostBtn;

    @FXML
    public Button refreshBtn;

    @FXML
    public ListView<Post> postListView;

    @FXML
    public Button loadMoreBtn;

    @FXML
    public Label updateCountLabel;

    final static Stage NEW_POST_STAGE = new Stage();

    private ObservableList<Post> postDataList;

    private PostDAO postDAO;

//    private ScheduledService getUpdateNumService;

    private int page = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postsController = this;
        postDAO = new PostDAO();
//        getUpdateNumService = new ScheduledService() {
//            @Override
//            protected Task createTask() {
//                return new Task<Void>() {
//                    @Override
//                    protected Void call() throws Exception {
//                        int count = 0;
//                        if (postDataList.size() > 0) {
//                            count = postDAO.countUpdate( postDataList.get( 0 ).getTimeStamp() );
//                            System.out.println( count );
//                        }
//                        if (count > 0) {
//                            updateCountLabel.setText( "new " + String.valueOf( count ) );
//                        } else if (count < 0) {
//                            updateCountLabel.setText( "failed" );
//                        }
//                        return null;
//                    }
//                };
//            }
//        };
//        getUpdateNumService.setPeriod( Duration.minutes( ConstantSetting.UPDATE_PERIOD_MIN ) );
//        getUpdateNumService.start();


        KeyFrame update = new KeyFrame( Duration.minutes( 0.2 ), event -> {
            int count = 0;
            if (postDataList.size() > 0) {
                count = postDAO.countUpdate( postDataList.get( 0 ).getTimeStamp() );
                System.out.println( count );
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


        postDataList = FXCollections.observableArrayList();
        page = 0;
        postDataList.addAll( postDAO.getPosts( page++ ) );
        postListView.setItems( postDataList );
        System.out.println( postListView.getItems().size() );
        postListView.setCellFactory( param -> new PostCellFactory() );
    }

    public static Stage getNewPostStage() {
        return NEW_POST_STAGE;
    }

    public void handleNewPostBtnAction(ActionEvent event) throws IOException {
        Main.getApp().getStage().setOnCloseRequest( event1 -> {
            NEW_POST_STAGE.close();
        } );
        Parent target = FXMLLoader.load( getClass().getResource( "/fxml/new_post.fxml" ) );
        Scene scene = new Scene( target );
        NEW_POST_STAGE.setScene( scene );
        NEW_POST_STAGE.setOnCloseRequest( event1 -> refresh() );
        NEW_POST_STAGE.show();
    }

    public void handleRefreshBtnAction(ActionEvent event) {
        refresh();
    }

    public void handleLoadMoreBtnAction(ActionEvent event) {
        postDataList.addAll( postDAO.getPosts( ++page ) );
    }

    void refresh() {
        postDataList.clear();
        page = 0;
        postDataList.addAll( postDAO.getPosts( page ) );
    }

    static PostsController getPostsController() {
        return postsController;
    }
}
