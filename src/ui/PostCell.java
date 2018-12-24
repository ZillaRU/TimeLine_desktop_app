package ui;

import db.DBInterface;
import home.ConstantSetting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Post;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 23:34
 */
public class PostCell extends ListCell<Post> {
    @FXML
    Label username;
    @FXML
    Label updateTime;
    @FXML
    Label content;
    @FXML
    HBox imageHBox;

    FXMLLoader fxmlLoader;

    public PostCell() {
        System.out.println( "PostCell PostCell" );
        fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/post_cell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            setGraphic(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(Post item, boolean empty) {
        super.updateItem( item, empty );
        System.out.println( "updateItem" );
        if(!empty&&item!=null){
            username.setText( item.getUserID() );
            updateTime.setText( item.getTimeStamp().toString() );
            content.setText( item.getContent() );
            if(item.isWithImgs()){
                List<String> imageUrlList = new ArrayList<>();
                ResultSet images=DBInterface.getResultSet( "SELECT imgUrl FROM post_img "
                        + "WHERE postID = '"
                        + item.getPostID() +"';");
                try {
                    if (images != null) {
                        while (images.next()){
                            imageUrlList.add( images.getString( "imgUrl" ) );
                            ImageView imageView=new ImageView( ConstantSetting.POST_IMAGE_PATH
                                    + images.getString( "imgUrl" ));
                            imageHBox.getChildren().add(imageView);
                        }
                        item.setImgFiles( imageUrlList );
                    }
                    setGraphic(fxmlLoader.load());
                }catch (SQLException | IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
