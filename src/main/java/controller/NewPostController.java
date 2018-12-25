package controller;

import db.DBInterface;
import db.PostDAO;
import home.ConstantSetting;
import home.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Post;
import utils.AlertHelper;
import utils.IdGenerator;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:15
 */
public class NewPostController {
    public Button submitBtn;
    public TextArea contentText;
    public Button uploadImageBtn;
    private final FileChooser fileChooser = new FileChooser();
    private List<File> imageFileList = null;

    public void handleSubmitAction(ActionEvent event) {
        Post newPost = new Post( IdGenerator.getId(), Main.getApp().getCurrentUser().getUserID(),
                null, imageFileList != null, contentText.getText() );
        if(new PostDAO().addPost(newPost,imageFileList)) {
            AlertHelper.showAlert( Alert.AlertType.INFORMATION, null, "INSERT INTO post",
                    "post successfully~" );
            PostsController.NEW_POST_STAGE.close();
            PostsController.getPostsController().refresh();

        }
    }


    public void handleUploadImageAction(ActionEvent event) {
        fileChooser.setTitle( "Open Image File" );
        fileChooser.setSelectedExtensionFilter( new FileChooser.ExtensionFilter( "jpg", "png", "jpeg" ) );
        imageFileList = fileChooser.showOpenMultipleDialog( PostsController.getNewPostStage() );
    }
}
