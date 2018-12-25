package controller;

import db.PostDAO;
import home.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Post;

import java.io.IOException;
import java.net.URL;
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

    final static Stage NEW_POST_STAGE = new Stage();

    private ObservableList<Post> postDataList;

    private PostDAO postDAO=new PostDAO();

    private int page=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postsController=this;
        postDataList= FXCollections.observableArrayList();
        page=0;
        postDataList.addAll(postDAO.getPosts(page++));
        postListView.setItems(postDataList);
        System.out.println(postListView.getItems().size());
        postListView.setCellFactory(param -> new PostCellFactory());
    }

    public static Stage getNewPostStage() {
        return NEW_POST_STAGE;
    }

    public void handleNewPostBtnAction(ActionEvent event) throws IOException {
        Main.getApp().getStage().setOnCloseRequest(event1 -> {
            NEW_POST_STAGE.close();
        });
        Parent target = FXMLLoader.load(getClass().getResource( "/fxml/new_post.fxml" ));
        Scene scene = new Scene(target);
        NEW_POST_STAGE.setScene(scene);
        NEW_POST_STAGE.setOnCloseRequest( event1 -> refresh() );
        NEW_POST_STAGE.show();
    }

    public void handleRefreshBtnAction(ActionEvent event) {
        refresh();
    }

    public void handleLoadMoreBtnAction(ActionEvent event) {
        postDataList.addAll( postDAO.getPosts(++page));
    }

    void refresh(){
        postDataList.clear();
        page=0;
        postDataList.addAll( postDAO.getPosts(page));
    }

    static PostsController getPostsController() {
        return postsController;
    }
}
