package model;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:41
 */
public class Post {
    private Integer postID;
    private String userID;
    private Timestamp timeStamp;
    private boolean withImgs;
    private String content;

    private List<String> imgFiles;

    public Post(Integer postID, String userID, Timestamp timeStamp, boolean withImgs, String content) {
        this.postID = postID;
        this.userID = userID;
        this.timeStamp = timeStamp;
        this.withImgs = withImgs;
        this.content = content;
    }

    public void setImgFiles(List<String> imgFiles) {
        this.imgFiles = imgFiles;
    }
}
