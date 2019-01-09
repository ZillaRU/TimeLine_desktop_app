package ui;

import model.ModelConstants;
import model.Post;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostCellTest extends ApplicationTest {
    @Test
    public void set_post_cell_content_with_image() throws Exception {
        Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                ModelConstants.timeStamp, ModelConstants.withImgs, ModelConstants.content);
        post.setImgFiles(ModelConstants.imgFiles);
        PostCell postCell = new PostCell();
        postCell.setPostContent(post);
        assertEquals(ModelConstants.content, postCell.content.getText());
        assertEquals(ModelConstants.userID, postCell.username.getText());
        assertEquals("Just now", postCell.updateTime.getText());
        assertNotNull(postCell.imageHBox);
    }

    @Test
    public void set_post_cell_content_with_no_image() throws Exception {
        Post post = new Post(ModelConstants.postID, ModelConstants.userID,
                ModelConstants.timeStamp, ModelConstants.withNoImg, ModelConstants.content);
        PostCell postCell = new PostCell();
        postCell.setPostContent(post);
        assertEquals(ModelConstants.content, postCell.content.getText());
        assertEquals(ModelConstants.userID, postCell.username.getText());
        assertEquals("Just now", postCell.updateTime.getText());
        assertEquals(postCell.imageHBox.getChildren().size(), 0);
    }
}