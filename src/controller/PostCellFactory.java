package controller;

import javafx.scene.control.ListCell;
import model.Post;
import ui.PostCell;

public class PostCellFactory extends ListCell<Post> {
    @Override
    protected void updateItem(Post item, boolean empty) {
        System.out.println("updateItem" + item + "|" + empty);
        System.out.println("updateItem");
        if (null != item && !empty) {
            PostCell postCell = new PostCell();
            postCell.setPostContent(item);
            setGraphic(postCell.getBox());
        }
    }
}
