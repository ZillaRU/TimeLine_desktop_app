package controller;

import javafx.scene.control.ListCell;
import model.Post;
import ui.PostCell;

/**
 * @author: zilla0148
 * @date: 2018/12/24 16:04
 */

public class PostCellFactory extends ListCell<Post> {
    @Override
    protected void updateItem(Post item, boolean empty) {
        if (null != item && !empty) {
            PostCell postCell = new PostCell();
            postCell.setPostContent( item );
            setGraphic( postCell.getBox() );
        }
    }
}
