package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import db.PostDAO;
import home.Main;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import model.Post;
import utils.AlertHelper;
import utils.IdGenerator;

import java.io.File;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:15
 */
public class NewPostController {
    public JFXButton submitBtn;
    public JFXTextArea contentText;
    public JFXButton uploadImageBtn;
    private final FileChooser fileChooser = new FileChooser();
    private List<File> imageFileList = null;

    public void handleSubmitAction(ActionEvent event) {
        Post newPost = new Post( IdGenerator.getId(), Main.getApp().getCurrentUser().getUserID(),
                null, imageFileList != null, contentText.getText() );
        if (new PostDAO().addPost( newPost, imageFileList )) {
            AlertHelper.showJDialog(Main.getApp().getStage(),
                    "INSERT INTO post",
                    "post successfully~" );
            PostsController.NEW_POST_STAGE.close();
            PostsController.getPostsController().refresh();
        }
    }


    public void handleUploadImageAction(ActionEvent event) {
        fileChooser.setTitle( "Open Image File" );
        fileChooser.setSelectedExtensionFilter( new FileChooser.ExtensionFilter( "image", "jpg", "png", "jpeg", "bmp" ) );
        imageFileList = fileChooser.showOpenMultipleDialog( PostsController.getNewPostStage() );
    }
}