package controller;

import db.DBInterface;
import home.ConstantSetting;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import model.Post;
import ui.PostCell;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostCellFactory extends ListCell<Post> {


    @Override
    protected void updateItem(Post item, boolean empty) {
        System.out.println("updateItem" + item + "|" + empty);
//        super.updateItem(item, empty);
        System.out.println("updateItem");
        if (null != item && !empty) {
            PostCell postCell = new PostCell();
            postCell.setPostContent(item);
            setGraphic(postCell.getBox());
        }
    }
}
