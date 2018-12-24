package controller;

import db.DBInterface;
import home.Main;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Post;
import ui.PostCell;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:04
 */
public class PostsController implements Initializable {
    @FXML
    public Button newPostBtn;

    @FXML
    public Button refreshBtn;

    @FXML
    public ListView<Post> postListView;

    private final static Stage NEW_POST_STAGE = new Stage();
    ;

    private ObservableList<Post> postDataList;

    private void getPostsData() {
        postDataList.clear();
        ResultSet resultSet = DBInterface.getResultSet("SELECT * FROM post ORDER BY updateTime desc;");
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String postID = resultSet.getString("postID");
                    boolean hasImg = resultSet.getBoolean("hasImg");
                    Post post = new Post(
                            postID,
                            resultSet.getString("username"),
                            resultSet.getTimestamp("updateTime"),
                            hasImg,
                            resultSet.getString("content")
                    );
                    System.out.println(post.toString());
                    if (hasImg) {
                        List<String> imageUrlList = new ArrayList<>();
                        ResultSet images = DBInterface.getResultSet("SELECT imgUrl FROM post_img "
                                + "WHERE postID = '"
                                + postID + "';");
                        while (images.next()) {
                            imageUrlList.add(images.getString("imgUrl"));
                        }
                        post.setImgFiles(imageUrlList);
                    }
                    postDataList.add(post);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postDataList= FXCollections.observableArrayList();
        getPostsData();
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
        Parent target = FXMLLoader.load(getClass().getResource("/fxml/new_post.fxml"));
        Scene scene = new Scene(target);
        NEW_POST_STAGE.setScene(scene);
        NEW_POST_STAGE.show();
    }

    public void handleRefreshBtnAction(ActionEvent event) {
        getPostsData();
    }
}
