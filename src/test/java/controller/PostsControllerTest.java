package controller;

import com.jfoenix.controls.JFXListView;
import db.PostDAO;
import home.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ModelConstants;
import model.Post;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PostsControllerTest extends ApplicationTest {
    Stage stage;


    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @Test
    public void handle_Refresh_Btn() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PostsController postsController = new PostsController();
                postsController.postDAO = new PostDAO();
                postsController.postListView = new JFXListView<>();
                postsController.hintLabel = new Text();
                ObservableList<Post> postDataList;
                postDataList = FXCollections.observableArrayList();
                postsController.postListView = new JFXListView<>();
                Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                        ModelConstants.timeStamp, ModelConstants.withImgs, ModelConstants.content);
                ModelConstants.setImgFiles();
                postDataList.add(post);
                postsController.postDataList = postDataList;
                postsController.postsBorderPane = new BorderPane();
                boolean result = postsController.handleRefreshBtnAction();
                assertTrue(result);
            }
        });

    }

    @Test
    public void handle_load_Btn() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PostsController postsController = new PostsController();
                postsController.postDAO = new PostDAO();
                postsController.postListView = new JFXListView<>();
                postsController.hintLabel = new Text();
                ObservableList<Post> postDataList;
                postDataList = FXCollections.observableArrayList();
                postsController.postListView = new JFXListView<>();
                Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                        ModelConstants.timeStamp, ModelConstants.withImgs, ModelConstants.content);
                ModelConstants.setImgFiles();
                postDataList.add(post);
                postsController.postDataList = postDataList;
                postsController.postsBorderPane = new BorderPane();
                boolean result = postsController.handleLoadMoreBtnAction();
                assertTrue(result);
            }
        });

    }

    @Test
    public void handle_log_out() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PostsController postsController = new PostsController();
                postsController.postDAO = new PostDAO();
                postsController.postListView = new JFXListView<>();
                postsController.hintLabel = new Text();
                ObservableList<Post> postDataList;
                postDataList = FXCollections.observableArrayList();
                postsController.postListView = new JFXListView<>();
                Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                        ModelConstants.timeStamp, ModelConstants.withImgs, ModelConstants.content);
                ModelConstants.setImgFiles();
                postDataList.add(post);
                postsController.postDataList = postDataList;
                postsController.postsBorderPane = new BorderPane();
                boolean result = postsController.handleLogOutBtnAction();
                assertTrue(result);
            }
        });

    }

    @Test
    public void handle_new_post_btn() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PostsController postsController = new PostsController();
                postsController.postDAO = new PostDAO();
                postsController.postListView = new JFXListView<>();
                postsController.hintLabel = new Text();
                ObservableList<Post> postDataList;
                postDataList = FXCollections.observableArrayList();
                postsController.postListView = new JFXListView<>();
                Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                        ModelConstants.timeStamp, ModelConstants.withImgs, ModelConstants.content);
                ModelConstants.setImgFiles();
                postDataList.add(post);
                postsController.postDataList = postDataList;
                postsController.postsBorderPane = new BorderPane();
                boolean result = false;
                try {
                    result = postsController.handleNewPostBtnAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assertTrue(result);
            }
        });

    }
}