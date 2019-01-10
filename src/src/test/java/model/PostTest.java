package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {
    @Test
    public void get_post_right(){
        Post post=new Post(ModelConstants.postID,ModelConstants.userID,
                ModelConstants.timeStamp,ModelConstants.withImgs,ModelConstants.content);

        assertEquals(ModelConstants.postID,post.getPostID());
        assertEquals(ModelConstants.userID,post.getUserID());
        assertEquals(ModelConstants.timeStamp,post.getTimeStamp());
        assertEquals(ModelConstants.withImgs,post.isWithImgs());
        assertEquals(ModelConstants.content,post.getContent());

        post.setImgFiles(ModelConstants.imgFiles);
        assertEquals(ModelConstants.imgFiles,post.getImgFiles());
    }
}