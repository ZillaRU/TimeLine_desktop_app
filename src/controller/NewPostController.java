package controller;

import db.DBInterface;
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
    private final FileChooser fileChooser=new FileChooser();
    private List<File> imageFileList=null;

    public void handleSubmitAction(ActionEvent event) {
        String content = contentText.getText();
        boolean hasImage=false;
        if(imageFileList!=null){
            hasImage=true;
        }
        Post newPost=new Post( IdGenerator.getId(),Main.getApp().getCurrentUser().getUserID(),
                null,hasImage,content);

        String stmt="INSERT INTO post(postID, username, hasImg, content) "
                + "VALUES ('"
                + newPost.getPostID() + "', '"
                + newPost.getUserID() + "', "
                + hasImage + ", '"
                + newPost.getContent() + "');";
        System.out.println( stmt );
        try {
            if(DBInterface.executeStatement(stmt)){
                AlertHelper.showAlert( Alert.AlertType.INFORMATION, null, "INSERT INTO post",
                        "post successfully~");
                if(hasImage) {
                    FileInputStream inputStream;
                    FileOutputStream outputStream;
                    for (File file : imageFileList) {
                        String originImageName=file.getName();
                        String newImageName=IdGenerator.getId()+"."+originImageName.substring(originImageName.lastIndexOf(".") + 1);;
                        inputStream=new FileInputStream(file);
                        outputStream=new FileOutputStream( ConstantSetting.POST_IMAGE_PATH
                                +"/"+newImageName );
                        byte[] buffer = new byte[1024];
                        int cnt=0;
                        while((cnt=inputStream.read(buffer))>0){
                            outputStream.write(buffer, 0, cnt);
                        }
                        String stmt1="INSERT INTO post_img(postID, imgUrl) "
                                + "VALUES ('"
                                + newPost.getPostID() + "', '"
                                + newImageName + "');";
                        System.out.println( stmt1 );
                        DBInterface.executeStatement( stmt1 );
                        outputStream.close();
                        inputStream.close();
                    }
                    AlertHelper.showAlert( Alert.AlertType.INFORMATION, null, "INSERT INTO post",
                            "post successfully~");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void handleUploadImageAction(ActionEvent event) {
        fileChooser.setTitle("Open Image File");
        fileChooser.setSelectedExtensionFilter( new FileChooser.ExtensionFilter( "jpg", "png", "jpeg" ));
        imageFileList=fileChooser.showOpenMultipleDialog(PostsController.getNewPostStage());
    }
}
